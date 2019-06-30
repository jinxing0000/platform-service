package com.bettem.modules.sys.service.impl;

import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.baomidou.mybatisplus.plugins.Page;
import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import com.bettem.common.utils.*;
import com.bettem.modules.sys.dao.SysEnclosureDao;
import com.bettem.modules.sys.entity.SysEnclosureEntity;
import com.bettem.modules.sys.entity.SysUserEntity;
import com.bettem.modules.sys.service.SysEnclosureService;
import org.apache.shiro.SecurityUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.StringUtils;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.*;
import java.util.*;


@Service("sysEnclosureService")
public class SysEnclosureServiceImpl extends ServiceImpl<SysEnclosureDao, SysEnclosureEntity> implements SysEnclosureService {


    @Value("${bettem.uploadFilePath}")
    private String  path;
    @Value("${bettem.ftpServerIP}")
    private String ftpServerIP;
    @Value("${bettem.ftpServerPort}")
    private int ftpServerPort;
    @Value("${bettem.ftpUserName}")
    private String  ftpUserName;
    @Value("${bettem.ftpPassword}")
    private String  ftpPassword;
    @Value("${bettem.imagePath}")
    private String  imagePath;
    @Value("${bettem.uploadFileType}")
    private String uploadFileType;
    @Value("${bettem.uploadFileSize}")
    private long uploadFileSize;
    @Value("${bettem.ftpPath}")
    private String ftpPath;

    @Autowired
    private ShiroTokenUtils shiroTokenUtils;

    @Override
    public PageUtils queryPage(Map<String, Object> params) {
        Page<SysEnclosureEntity> page = this.selectPage(
                new Query<SysEnclosureEntity>(params).getPage(),
                new EntityWrapper<SysEnclosureEntity>().eq("delete_state",Constant.DELETE_STATE_NO)
        );

        return new PageUtils(page);
    }

    @Override
    public void deleteByIds(String[] ids) {
        List<SysEnclosureEntity> SysEnclosureList=new ArrayList<>();
        SysEnclosureEntity sysEnclosureEntity=null;
        SysUserEntity sysUserEntity= (SysUserEntity)SecurityUtils.getSubject().getPrincipal();
        for(int i=0;i<ids.length;i++){
            sysEnclosureEntity=new SysEnclosureEntity();
            sysEnclosureEntity.setId(ids[i]);
            sysEnclosureEntity.setModifyUserId(sysUserEntity.getUserId());
            sysEnclosureEntity.setModifyDate(new Date());
            sysEnclosureEntity.setDeleteState(Constant.DELETE_STATE_YES);
            SysEnclosureList.add(sysEnclosureEntity);
        }
        this.updateBatchById(SysEnclosureList);
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public List<Map<String,Object>> uploadFile(Map<String, Object> params) throws Exception {
        MultipartFile file=(MultipartFile)params.get("file");
        UploadFileUtil uploadFileUtil=new UploadFileUtil(ftpServerIP,ftpServerPort,ftpUserName,ftpPassword,uploadFileSize,ftpPath);
        SysEnclosureEntity sysEnclosureEntity=uploadFileUtil.uploadFileByFtp(file,uploadFileType);
        params.put("fileName",sysEnclosureEntity.getFileName());
        params.put("filePath",sysEnclosureEntity.getFilePath());
        params.put("fileSuffix",sysEnclosureEntity.getFileSuffix());
        params.put("createDate",new Date());
        params.put("createUserId",shiroTokenUtils.getUserId());
        params.put("deleteState",Constant.DELETE_STATE_NO);
        params.put("id",sysEnclosureEntity.getId());
        this.baseMapper.saveUploadData(params);
        params.remove("file");
        List<Map<String,Object>> data=this.baseMapper.selectUploadData(params);
        return data;
    }

    @Override
    public List<Map<String, Object>> selectUploadData(Map<String, Object> params) {
        return this.baseMapper.selectUploadData(params);
    }

    @Override
    public List<Map<String, Object>> selectFileData() {
        List<Map<String,Object>> resultData = new ArrayList<>();
        Map<String, Object> map = new HashMap<>();
        map.put("fileName","操作手册.docx");
        map.put("filePath","/syshelp/operationDoc.docx");
        map.put("createDate","2019-5-27 14:54:08");
        resultData.add(map);
        return resultData;
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public List<Map<String, Object>> deleteByIdParams(Map<String, Object> params) {
        String id = (String)params.get("id");
        this.baseMapper.deleteByParams(params);
        params.remove("id");
        return this.baseMapper.selectUploadData(params);
    }

    @Override
    public Map<String, Object> selectByParams(Map<String, Object> params) {
        return this.baseMapper.selectByParams(params);
    }

    @Override
    public void downloadFileByParams(HttpServletRequest request, HttpServletResponse response, Map<String, Object> params) {
        try {
            //按照id查询附件信息
            Map<String,Object> dataMap = this.baseMapper.selectByParams(params);
            String fileName=(String)dataMap.get("fileName");
            String filePath=(String)dataMap.get("filePath");
            //解决乱码
            String agent = request.getHeader("USER-AGENT");
            if (null != agent){
                if (-1 != agent.indexOf("Firefox")) {//Firefox
                    fileName = "=?UTF-8?B?" + (new String(org.apache.commons.codec.binary.Base64.encodeBase64(fileName.getBytes("UTF-8"))))+ "?=";
                }else if (-1 != agent.indexOf("Chrome")) {//Chrome
                    fileName = new String(fileName.getBytes(), "ISO8859-1");
                } else {//IE7+
                    fileName = java.net.URLEncoder.encode(fileName, "UTF-8");
                    fileName = StringUtils.replace(fileName, "+", "%20");//替换空格
                }
            }
            // 设置response的编码方式
            response.setContentType("application/x-msdownload");
            // 设置附加文件名
            response.setHeader("Content-Disposition","attachment;filename="+fileName);
            //获取FTP文件流
            InputStream fileIs=new UploadFileUtil(ftpServerIP,ftpServerPort,ftpUserName,ftpPassword,uploadFileSize,ftpPath).downloadFile(filePath);
            //重复利用文件流
            ByteArrayOutputStream baos = new ByteArrayOutputStream();
            byte[] buffer = new byte[1024];
            int len=0;
            while ((len = fileIs.read(buffer)) > -1) {
                baos.write(buffer, 0, len);
            }
            baos.flush();
            //新建下载文件流
            InputStream fis = new ByteArrayInputStream(baos.toByteArray());
            //新建获取文件size流
            InputStream SizeIs = new ByteArrayInputStream(baos.toByteArray());
            //获取apk文件大小
            int count = 0;
            byte[] bb = new byte[1024];
            int size=0;
            while ((size = SizeIs.read(bb)) > 0) {
                count += size;
            }
            response.setContentLength(count);
            OutputStream os = response.getOutputStream();
            byte[] b = new byte[1024];
            int length=0;
            while ((length = fis.read(b)) > 0) {
                os.write(b, 0, length);
            }
            // 这里主要关闭。
            os.close();
            SizeIs.close();
            fis.close();
            baos.close();
        }
        catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public void downloadFile(HttpServletRequest request, HttpServletResponse response, Map<String, Object> params) {
        try {
            String fileName="操作手册.docx";
            String filePath=(String)params.get("filePath");
            //解决乱码
            String agent = request.getHeader("USER-AGENT");
            if (null != agent){
                if (-1 != agent.indexOf("Firefox")) {//Firefox
                    fileName = "=?UTF-8?B?" + (new String(org.apache.commons.codec.binary.Base64.encodeBase64(fileName.getBytes("UTF-8"))))+ "?=";
                }else if (-1 != agent.indexOf("Chrome")) {//Chrome
                    fileName = new String(fileName.getBytes(), "ISO8859-1");
                } else {//IE7+
                    fileName = java.net.URLEncoder.encode(fileName, "UTF-8");
                    fileName = StringUtils.replace(fileName, "+", "%20");//替换空格
                }
            }
            // 设置response的编码方式
            response.setContentType("application/x-msdownload");
            // 设置附加文件名
            response.setHeader("Content-Disposition","attachment;filename="+fileName);
            //获取FTP文件流
            InputStream fileIs=new UploadFileUtil(ftpServerIP,ftpServerPort,ftpUserName,ftpPassword,uploadFileSize,ftpPath).downloadFile(filePath);
            //重复利用文件流
            ByteArrayOutputStream baos = new ByteArrayOutputStream();
            byte[] buffer = new byte[1024];
            int len=0;
            while ((len = fileIs.read(buffer)) > -1) {
                baos.write(buffer, 0, len);
            }
            baos.flush();
            //新建下载文件流
            InputStream fis = new ByteArrayInputStream(baos.toByteArray());
            //新建获取文件size流
            InputStream SizeIs = new ByteArrayInputStream(baos.toByteArray());
            //获取apk文件大小
            int count = 0;
            byte[] bb = new byte[1024];
            int size=0;
            while ((size = SizeIs.read(bb)) > 0) {
                count += size;
            }
            response.setContentLength(count);
            OutputStream os = response.getOutputStream();
            byte[] b = new byte[1024];
            int length=0;
            while ((length = fis.read(b)) > 0) {
                os.write(b, 0, length);
            }
            // 这里主要关闭。
            os.close();
            SizeIs.close();
            fis.close();
            baos.close();
        }
        catch (Exception e) {
            e.printStackTrace();
        }
    }
}
