package com.bettem.common.aspect;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.bettem.common.utils.*;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import javax.servlet.http.HttpServletRequest;
import java.util.UUID;

@Aspect
@Component
public class RepeatSubmitAspect {

    private static final Logger LOGGER = LoggerFactory.getLogger(RepeatSubmitAspect.class);

    @Value("${bettem.resubmitTime}")
    private int resubmitTime;

    @Autowired
    private RedisLock redisLock;

    @Pointcut("execution(* com.bettem.modules.*.controller.*.*(..))")
    public void pointCut() {

    }

    @Around("pointCut()")
    public Object around(ProceedingJoinPoint pjp) throws Throwable {
        //获取请求信息
        HttpServletRequest request = RequestUtils.getRequest();
        //获取请求中的token
        String token = request.getHeader("Authorization");
        //判断token是否为空
        if(token==null){
            //从参数中获取
            token=request.getParameter("Authorization");
        }
        //获取验证码token
        if(token==null){
            request.getHeader("verifyCodeToken");
        }
        //判断参数中是否带有token，没有换取sessionId
        if(token==null){
            token=request.getSession().getId();
        }
        //获取请求路径
        String path = request.getServletPath();
        //获取get请求参数
        String queryString=request.getQueryString();
        //获取post、put、delete请求参数
        Object[] args = pjp.getArgs();
        if(queryString!=null){
            String strs[]=queryString.split("&");
            //判断最后一个参数是否为_=
            if(strs[strs.length-1].indexOf("_=")!=-1){
                queryString="";
                for(int i=0;i<strs.length-1;i++){
                    queryString+=strs[i]+"&";
                }
            }
        }else{
            queryString="";
        }
        //判断body 参数是否为空
        if(args.length>0){
            try {
                JSONObject params = (JSONObject)  JSONObject.toJSON(args[0]);
                //获取上传文件名称
                String originalFilename=(String)params.get("originalFilename");
                //判断文件名称是否存在
                if(originalFilename!=null){
                    //将文件名称加入key
                    queryString+=originalFilename;
                }
                //其他请求将所有参数加入key
                else{
                    queryString+=params.toString();
                }
            }catch(Exception e){
                try{
                    JSONArray params = (JSONArray)  JSONArray.toJSON(args[0]);
                    for(int i=0;i<params.size();i++){
                        JSONObject obj=(JSONObject)params.get(i);
                        //获取上传文件名称
                        String originalFilename=(String)obj.get("originalFilename");
                        //判断文件名称是否存在
                        if(originalFilename!=null){
                            //将文件名称加入key
                            queryString+=originalFilename;
                        }
                        //其他请求将所有参数加入key
                        else{
                            queryString+=params.toString();
                        }
                    }
                }catch(Exception x){
                    try{
                        queryString+=args[0].toString();
                    }catch(Exception t){

                    }
                }
            }
        }
        String ip= IPUtils.getIpAddr(request);
        String key = getKey(token, path,queryString,ip);
        String clientId = getClientId();
        boolean isSuccess = redisLock.tryLock(key, clientId, resubmitTime);
        LOGGER.debug("tryLock key = [{}], clientId = [{}]", key, clientId);
        if (isSuccess) {
            LOGGER.debug("tryLock success, key = [{}], clientId = [{}]", key, clientId);
            // 获取锁成功
            Object result;
            try {
                // 执行进程,执行控制层代码
                result = pjp.proceed();
            } finally {
                // 解锁
                //redisLock.releaseLock(key, clientId);
                LOGGER.debug("releaseLock success, key = [{}], clientId = [{}]", key, clientId);
            }
            return result;

        } else {
            // 获取锁失败，认为是重复提交的请求
            LOGGER.debug("tryLock fail, key = [{}]", key);
            return R.error(ErrorCodeConstant.RESUBMIT_ERROR,"请勿重复提交请求！！");
        }
    }

    /**
     * 生成redis锁的key
     * @param token
     * @param path
     * @param ip
     * @return
     */
    private String getKey(String token, String path,String queryString,String ip) {
        return token + "-" + path + "-" +queryString + "-" + ip;
    }

    /**
     * 生成客户端id
     * @return
     */
    private String getClientId() {
        return UUID.randomUUID().toString().replaceAll("\\-", "");
    }

}
