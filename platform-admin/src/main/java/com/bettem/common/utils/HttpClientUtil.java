/**
 * @Company 航天科技山西公司
 * @Project jd
 * @Package com.jd.util
 * @Title httpClientUtil.java
 * @Description TODO(描述)
 * @author 颜金星
 * @create 2016年7月25日-上午11:20:07
 * @version V 1.0
 */
package com.bettem.common.utils;

import com.alibaba.fastjson.JSONObject;
import com.google.common.collect.Lists;
import org.apache.http.Consts;
import org.apache.http.HttpEntity;
import org.apache.http.NameValuePair;
import org.apache.http.ParseException;
import org.apache.http.client.config.RequestConfig;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.util.EntityUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @Company 山西百得科技开发有限公司
 * @Project jd
 * @Package com.jd.util
 * @ClassName httpClientUtil.java
 * @Description TODO(描述)
 * @author 颜金星
 * @create 2016年7月25日-上午11:20:07
 */
public class HttpClientUtil {

	// 本地异常日志记录对象
	private static final Logger logger = LoggerFactory
			.getLogger(HttpClientUtil.class);
    /**
     * 
     * @Title getDataByPostMethod
     * @Description (httpClient post提交)
     * @author 颜金星
     * @create 2016年7月25日-下午12:59:27
     * @Param @param url(链接URL)
     * @Param @param parameters（参数）
     * @Param @return
     * @return JSONObject
     * @throws
     */
	public static JSONObject getDataByPostMethod(String url,Map<String, Object> parameters){
		 CloseableHttpClient httpClient = getHttpClient();
		 JSONObject data=new JSONObject();
	        try {
	            HttpPost post = new HttpPost(url);
				RequestConfig requestConfig = RequestConfig.custom()
						.setConnectTimeout(5000).setConnectionRequestTimeout(1000)
						.setSocketTimeout(5000).build();
				post.setConfig(requestConfig);
	            //创建参数列表
	            List<NameValuePair> list = new ArrayList<NameValuePair>();
	            //遍历参数
	            for (Map.Entry<String, Object> entry : parameters.entrySet()) {
	            	list.add(new BasicNameValuePair( entry.getKey(), entry.getValue().toString()));
	            }
	            //url格式编码
	            UrlEncodedFormEntity uefEntity = new UrlEncodedFormEntity(list,"UTF-8");
	            post.setEntity(uefEntity);
	            //执行请求
	            CloseableHttpResponse httpResponse = httpClient.execute(post);
	            try{
	                HttpEntity entity = httpResponse.getEntity();
	                data=JSONObject.parseObject(EntityUtils.toString(entity));
	            }finally{
	                httpResponse.close();
	            }
	             
	        } catch( UnsupportedEncodingException e){
	            e.printStackTrace();
	        }
	        catch (IOException e) {
	            e.printStackTrace();
	        }
	        finally{
	            try{
	                closeHttpClient(httpClient);                
	            } catch(Exception e){
	                e.printStackTrace();
	            }
	        }
		return data;
	}
	
	/**
	 * 
	 * @Title getDataByPostMethodForJson
	 * @Description (参数以json的格式传递，post提交)
	 * @author 颜金星
	 * @create 2016年8月1日-上午10:08:58
	 * @Param @param url
	 * @Param @param parametersJson
	 * @Param @return
	 * @return JSONObject
	 * @throws
	 */
	public static JSONObject getDataByPostMethodForJson(String url,String parametersJson){
		 CloseableHttpClient httpClient = getHttpClient();
		 JSONObject data=new JSONObject();
	        try {
	            HttpPost post = new HttpPost(url);
				RequestConfig requestConfig = RequestConfig.custom()
						.setConnectTimeout(5000).setConnectionRequestTimeout(1000)
						.setSocketTimeout(5000).build();
				post.setConfig(requestConfig);
	            StringEntity s = new StringEntity(parametersJson);
	            s.setContentEncoding("UTF-8");
	            s.setContentType("application/json");
	            post.setEntity(s);
	            //执行请求
	            CloseableHttpResponse httpResponse = httpClient.execute(post);
	            try{
	                HttpEntity entity = httpResponse.getEntity();
	                if (null != entity){
	                	data=JSONObject.parseObject(EntityUtils.toString(entity));
	                }
	            } finally{
	                httpResponse.close();
	            }
	             
	        } catch( UnsupportedEncodingException e){
	            e.printStackTrace();
	        }
	        catch (IOException e) {
	            e.printStackTrace();
	        }
	        finally{
	            try{
	                closeHttpClient(httpClient);                
	            } catch(Exception e){
	                e.printStackTrace();
	            }
	        }
		return data;
	}
	
	
	/**
	 * 
	 * @Title getDataByGetMethod
	 * @Description (httpClient get提交)
	 * @author 颜金星
	 * @create 2016年7月25日-下午1:01:00
	 * @Param @param url
	 * @Param @return
	 * @return JSONObject
	 * @throws
	 */
	public static JSONObject getDataByGetMethod(String url,Map<String, Object> parameters){
		JSONObject data=new JSONObject();
		CloseableHttpClient httpClient = HttpClients.createDefault();
		CloseableHttpResponse response = null;
		InputStream is = null;
		//封装请求参数
		List<NameValuePair> params = Lists.newArrayList();
		//遍历参数
		for (Map.Entry<String, Object> entry : parameters.entrySet()) {
			params.add(new BasicNameValuePair( entry.getKey(), entry.getValue().toString()));
		}
		try {
			//转换为键值对
			String str = EntityUtils.toString(new UrlEncodedFormEntity(params, Consts.UTF_8));
			//创建Get请求
			HttpGet httpGet = new HttpGet(url+"?"+str);
			//执行Get请求，
			response = httpClient.execute(httpGet);
			//得到响应体
			HttpEntity entity = response.getEntity();
			if(entity != null){
				is = entity.getContent();
				//转换为字节输入流
				BufferedReader br = new BufferedReader(new InputStreamReader(is, Consts.UTF_8));
				String message = "";
				String line = null;
				while((line = br.readLine()) != null) {
					message += line;
				}
				data=JSONObject.parseObject(message);
			}
		} catch (ParseException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		} finally{
			//关闭输入流，释放资源
			if(is != null){
				try {
					is.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
			//消耗实体内容
			if(response != null){
				try {
					response.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
			//关闭相应 丢弃http连接
			if(httpClient != null){
				try {
					httpClient.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		}
		return data;
	}
	
	/**
	 * 
	 * @Title getHttpClient
	 * @Description (获取HttpClients)
	 * @author 颜金星
	 * @create 2016年7月25日-上午11:47:13
	 * @Param @return
	 * @return CloseableHttpClient
	 * @throws
	 */
	private static CloseableHttpClient getHttpClient(){
	        return HttpClients.createDefault();
	}
	/**
	 *    
	 * @Title closeHttpClient
	 * @Description (关闭HttpClients)
	 * @author 颜金星
	 * @create 2016年7月25日-上午11:47:37
	 * @Param @param client
	 * @Param @throws IOException
	 * @return void
	 * @throws
	 */
	private static void closeHttpClient(CloseableHttpClient client) throws IOException{
	      if (client != null){
	            client.close();
	      }
	 }
	
	
	
	public static void main(String[] args) {
		String url="http://192.168.1.210:8080/api/admin/push.json";
		Map<String,Object> parameters=new HashMap<>();
		parameters.put("userId","user-0");
		JSONObject data=new JSONObject();
		data.put("id","1111111");
		data.put("gmsfzhm","142401199212034213");
		data.put("xm","张三");
		parameters.put("content",data.toJSONString());
		data=  HttpClientUtil.getDataByGetMethod(url,parameters);
		System.out.print(data);
	}
}
