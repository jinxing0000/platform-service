/**
 * @Company 百得科技
 * @Project jd-1.0v
 * @Package com.jd.util.api
 * @Title FTPUtil.java
 * @Description TODO(描述)
 * @author 颜金星
 * @create 2016年5月20日-下午5:40:49
 * @version V 1.0
 */
package com.bettem.common.utils;



import com.bettem.common.exception.RRException;
import com.bettem.modules.sys.entity.VO.SysUeditorVO;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.util.StringUtils;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;
import org.springframework.web.multipart.MultipartResolver;
import org.springframework.web.multipart.support.StandardServletMultipartResolver;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.*;
import java.util.*;

/**
 * @Company 百得科技
 * @Project jd-1.0v
 * @Package com.jd.util.api
 * @ClassName FTPUtil.java
 * @Description TODO(描述)
 * @author 颜金星
 * @create 2016年5月20日-下午5:40:49
 */
public class UploadFileUtil {

	// 本地异常日志记录对象
	private static final Logger logger = LoggerFactory
			.getLogger(UploadFileUtil.class);
	//minio 服务端地址
	private String minioServerUrl;
	//minio用户名
	private String accessKey;
	//minio密码
	private String secretKey;
	//存储桶名称
	private String bucketName;
    //最大文件大小
	private long uploadFileSize;
    //可以上传文件后缀
	private String uploadFileType;

	/**
	 * 构造方法
	 * @param minioServerUrl
	 * @param accessKey
	 * @param secretKey
	 * @param bucketName
	 * @param uploadFileSize
	 * @param uploadFileType
	 */
	public UploadFileUtil(String minioServerUrl,String accessKey,String secretKey,String bucketName,long uploadFileSize,String uploadFileType){
		this.minioServerUrl=minioServerUrl;
		this.accessKey=accessKey;
		this.secretKey=secretKey;
		this.bucketName=bucketName;
		this.uploadFileSize=uploadFileSize;
		this.uploadFileType=uploadFileType;
	}
	public UploadFileUtil(){

	}
	/**
	 * 文件上传到FTP
	 * @param file
	 * @return
	 * @throws IOException
	 */
	public Map<String,Object> uploadFile(MultipartFile file){
		Map<String,Object> resultMap=new HashMap<>();
		try {
			String oldFileName=file.getOriginalFilename();
			resultMap.put("fileName",oldFileName);
			String fileType=oldFileName.substring(oldFileName.lastIndexOf(".")+1);
			checkFileHeader(file,fileType,uploadFileType);
			//判断文件大小
			if(file.getSize()>uploadFileSize){
				throw new RRException("您上传的文件过大，请上传100M以内的文件！！");
			}
			resultMap.put("fileSize",file.getSize());
			resultMap.put("fileSuffix",fileType);
			String minioPath=getFolderName()+getFileName(fileType);
			String contentType=file.getContentType();
			InputStream in = file.getInputStream();
			ByteArrayOutputStream bos = new ByteArrayOutputStream();
			//判断为图片类型，进行图片压缩
			if("jpg".equals(fileType)||"png".equals(fileType)||"gif".equals(fileType)){
				byte[] buffer=ImageUtils.imageCompress(in,fileType);
				in=new ByteArrayInputStream(buffer);
			}
			MinioClientUtils minioClientUtils=new MinioClientUtils(minioServerUrl,accessKey,secretKey,bucketName);
			String fileUrl=minioClientUtils.uploadFile(in,minioPath,contentType);
			bos.close();
			resultMap.put("minioPath",minioPath);
			resultMap.put("fileUrl",fileUrl);
		}
		catch (RRException e) {
			throw new RRException(e.getCode(),e.getMsg());
		}
		catch (Exception e) {
			e.printStackTrace();
			throw new RRException("上传文件失败！！");
		}
		return resultMap;
	}
	/**
	 * @Param [file, fileType, uploadFileType]
	 * @Return: void
	 * @Decription: 校验文件类型，校验文件头
	 * @CreateDate: Created in 2018/12/20 9:49
	 * @Author: 颜金星
	 */
	public void checkFileHeader(MultipartFile file,String fileType,String uploadFileType){
		if(uploadFileType.indexOf(fileType)==-1){
			throw new RRException("您上传的文件类型有误！！请重新上传！！");
		}
		String fileTypeCode=getFileHeader(file);
		logger.debug("文件头16进制编码："+fileTypeCode);
		if(Constant.fileTypeCode.get(fileTypeCode)==null){
			throw new RRException("您上传的文件类型有误！！请重新上传！！");
		}
	}

    /**
     * @Param
     * @Return: 
     * @Decription: 获取文件头信息
     * @CreateDate: Created in 2018/12/20 9:18
     * @Author: 颜金星
     */
    
	public String getFileHeader(MultipartFile file){
		InputStream is = null;
		String value = null;
		try {
			is = file.getInputStream();
			byte[] b = new byte[4];
			is.read(b, 0, b.length);
			value = bytesToHexString(b);
		}catch (Exception e) {
			e.printStackTrace();
		}
		finally {
			if (null != is) {
				try {
					is.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		}
		return value;
	}
    /**
     * @Param
     * @Return: 
     * @Decription: 根据字符串转换码
     * @CreateDate: Created in 2018/12/20 9:20
     * @Author: 颜金星
     */
    
	private String bytesToHexString(byte[] src){
		StringBuilder builder = new StringBuilder();
		if (src == null || src.length <= 0) {
			return null;
		}
		String hv;
		for (int i = 0; i < src.length; i++) {
			hv = Integer.toHexString(src[i] & 0xFF).toUpperCase();
			if (hv.length() < 2) {
				builder.append(0);
			}
			builder.append(hv);
		}
		return builder.toString();
	}
	/**
	 * @Param [uploadFileType]
	 * @Return: com.bettem.modules.sys.entity.VO.SysUeditorVO
	 * @Decription: 富文本编辑器上传文件
	 * @CreateDate: Created in 2019/2/13 9:42
	 * @Author: 颜金星
	 */
	public SysUeditorVO ueditorUploadFileFTP(String uploadFileType){
		SysUeditorVO sysUeditorVO=new SysUeditorVO();
		try{
			sysUeditorVO.setState("SUCCESS");
			ServletRequestAttributes attributes = (ServletRequestAttributes) RequestContextHolder.getRequestAttributes();
			HttpServletRequest request = attributes.getRequest();
			MultipartResolver resolver = new StandardServletMultipartResolver();
			MultipartHttpServletRequest mRequest = resolver.resolveMultipart(request);
			Map<String, MultipartFile> fileMap = mRequest.getFileMap();
			MultipartFile file=fileMap.get("upfile");
			Map<String,Object> resultMap=uploadFile(file);
			sysUeditorVO.setTitle((String)resultMap.get("fileName"));
			sysUeditorVO.setUrl((String)resultMap.get("fileUrl"));
			sysUeditorVO.setSize((long)resultMap.get("fileSize"));
			sysUeditorVO.setOriginal((String)resultMap.get("fileName"));
		}catch(Exception e){
			e.printStackTrace();
			sysUeditorVO.setState("ERROR");
		}
		return sysUeditorVO;
	}


	public void downFile(String filePath,String fileName, HttpServletRequest request, HttpServletResponse response){
		try{
			MinioClientUtils minioClientUtils=new MinioClientUtils(minioServerUrl,accessKey,secretKey,bucketName);
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
			InputStream fileIs=minioClientUtils.downFile(filePath);
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
		}catch(Exception e){
			e.printStackTrace();
			throw new RRException(ErrorCodeConstant.ERROR,"下载文件失败！！");
		}
	}

	/**
	 * 获取文件名称
	 * @param fileType
	 * @return
	 */
	public static  String  getFileName(String  fileType){
     	return UUID.randomUUID().toString().replaceAll("\\-", "")+"."+fileType;
	 }

	/**
	 * 获取文件存放路径
	 * @return
	 */
	public String  getFolderName(){
		Calendar now = Calendar.getInstance();
		return "/"+now.get(Calendar.YEAR)+"/"+(now.get(Calendar.MONTH) + 1) +"/"+now.get(Calendar.DAY_OF_MONTH)+"/";
	}

	/** 生成主键策略 */
	public String createId() {
		return UUID.randomUUID().toString().replaceAll("\\-", "");
	}
}
