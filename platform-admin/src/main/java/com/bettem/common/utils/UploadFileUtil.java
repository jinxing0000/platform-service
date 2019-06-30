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
import com.bettem.modules.sys.entity.SysEnclosureEntity;
import com.bettem.modules.sys.entity.VO.SysUeditorVO;
import org.apache.commons.net.ftp.FTPClient;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.util.MultiValueMap;
import org.springframework.util.StringUtils;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;
import org.springframework.web.multipart.MultipartResolver;
import org.springframework.web.multipart.commons.CommonsMultipartResolver;
import org.springframework.web.multipart.support.StandardServletMultipartResolver;

import javax.servlet.http.HttpServletRequest;
import java.io.*;
import java.net.SocketException;
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
	private String ftpServerIP;
	private int ftpServerPort;
	private String  ftpUserName;
	private String  ftpPassword;
	private long uploadFileSize;
	private String ftpPath;

	public UploadFileUtil(String ftpServerIP, int ftpServerPort, String  ftpUserName, String  ftpPassword,long uploadFileSize,String ftpPath){
		this.ftpServerIP=ftpServerIP;
		this.ftpServerPort=ftpServerPort;
		this.ftpUserName=ftpUserName;
		this.ftpPassword=ftpPassword;
		this.uploadFileSize=uploadFileSize;
		this.ftpPath=ftpPath;
	}
	public UploadFileUtil(){

	}
    /**
     * 注意： 文件名不能用中文！
     * 		文件夹名称以正斜杠结尾！
     * 		如果文件夹不存在会自动创建！
     * @Title saveinFTP
     * @Description (描述)
     * @author 颜金星
     * @create 2016年5月23日-上午9:41:35
     * @Param @param FolderName
     * @Param @param FileName
     * @Param @param data
     * @Param @return
     * @return boolean
     * @throws
     */
	public void uploadFileFTP(String folderName, String fileName, byte[] data){
		FTPClient ftpClient = new FTPClient();
		ByteArrayInputStream bis = null;
		folderName="/pub"+folderName;
		try {
			ftpClient.connect(ftpServerIP,ftpServerPort);
			if(ftpClient.login(ftpUserName, ftpPassword)){
				String[] a = folderName.split("/");
				for (int i = 0; i < a.length; i++) {
					if (!ftpClient.changeWorkingDirectory(a[i])) {
						ftpClient.makeDirectory(a[i]);
						ftpClient.changeWorkingDirectory(a[i]);
					}
				}
				bis = new ByteArrayInputStream(data);
				ftpClient.setBufferSize(1024);
				ftpClient.setDataTimeout(20000);
				ftpClient.enterLocalPassiveMode();
				// 设置文件类型(二进制类型)
				if (ftpClient.setFileType(FTPClient.BINARY_FILE_TYPE)) {
					ftpClient.storeFile(fileName, bis);
				}
				bis.close();
			}else{
				logger.debug("FTP登陆失败！！");
				throw new RRException("FTP登陆失败！！");
			}
		} catch (SocketException e) {
			logger.debug("FTP服务连接中断！！");
			e.printStackTrace();
			throw new RRException("FTP服务连接中断！！");
		} catch (IOException e) {
			logger.debug("IOException！！");
			e.printStackTrace();
			throw new RRException("FTP服务连接中断！！");
		}finally {  
            try {  
                // 关闭连接  
                ftpClient.disconnect();  
            } catch (IOException e) {
				logger.debug("FTP客户端关闭错误！！");
                e.printStackTrace();
				throw new RRException("FTP客户端关闭错误！！");
            }  
        }
	}
     
     /**
      * 
      * @Title downloadFile
      * @Description (FTP下载文件)
      * @author 颜金星
      * @create 2016年7月20日-下午12:45:42
      * @Param @param filePath（文件的路径）
      * @Param @return
      * @return boolean
      * @throws
      */
     public InputStream downloadFile(String filePath){
    	 InputStream result = null ;
 		 FTPClient ftpClient = new FTPClient();
 		 try {
			 String osName=System.getProperty("os.name");
			 if(osName.indexOf("Windows")!=-1){
				 filePath="/pub"+filePath;
			 }else{
				 filePath="/var/ftp/pub"+filePath;
			 }
 			 //判断下载的路径不为空
 			if(StringUtils.isEmpty(filePath)){
 				return result;
 			}
 			String str[]=filePath.split("/");
 			String fileName=str[str.length-1];
 			String remoteFile="";
 			for(int i=0;i<str.length-1;i++){
 				remoteFile+=str[i]+"/";
 			}
 			ftpClient.connect(ftpServerIP,ftpServerPort);
			if(ftpClient.login(ftpUserName, ftpPassword)){
				ftpClient.setBufferSize(1024);
                ftpClient.setDataTimeout(18000);
                ftpClient.enterLocalPassiveMode();
                ftpClient.changeWorkingDirectory(remoteFile);
                result=ftpClient.retrieveFileStream(fileName);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}finally {  
            try {  
                // 关闭连接  
                ftpClient.disconnect();  
            } catch (IOException e) { 
            	System.out.println("IOExceptionEND");
                e.printStackTrace();  
            }  
        }  
    	 return result;
     }

	/**
	 *  上传文件到本地
	 * @param file
	 * @return
	 */
	public SysEnclosureEntity uploadFile(MultipartFile file,String systemPath) throws IOException {
		SysEnclosureEntity sysEnclosureEntity=new SysEnclosureEntity();
		String oldFileName=file.getOriginalFilename();
		sysEnclosureEntity.setFileName(oldFileName);
		String fileType=oldFileName.substring(oldFileName.lastIndexOf(".")+1);
		sysEnclosureEntity.setFileSuffix(fileType);
		sysEnclosureEntity.setUploadDate(new Date());
		sysEnclosureEntity.setFileSize(file.getSize()/1000);
		String filePath=getFolderName();
		String newFileName=getFileName(fileType);
		File dest = new File(systemPath +filePath);
		dest.setWritable(true,false);
		//判断文件夹是否存在
		if (!dest.exists() && !dest.isDirectory()) {
			//创建目录
			dest.mkdirs();
		}
		logger.debug("存放位置："+systemPath +filePath+newFileName);
		File newFile=new File(systemPath +filePath+newFileName);
		newFile.setWritable(true,false);
		BufferedOutputStream out = new BufferedOutputStream(
				new FileOutputStream(newFile));
		//写文件
		out.write(file.getBytes());
		out.flush();
		out.close();
		sysEnclosureEntity.setFilePath(filePath+newFileName);
		return sysEnclosureEntity;
	 }

	/**
	 * api上传文件(本地存放)
	 * @param files
	 * @return
	 */
	public List<SysEnclosureEntity> apiUploadFile(List<MultipartFile> files,String systemPath) throws IOException {
		List<SysEnclosureEntity>  sysEnclosureEntityList=new ArrayList<>();
		SysEnclosureEntity sysEnclosureEntity=null;
		for(int i=0;i<files.size();i++){
			MultipartFile file=files.get(i);
			sysEnclosureEntity=new SysEnclosureEntity();
			String oldFileName=files.get(i).getOriginalFilename();
			sysEnclosureEntity.setFileName(oldFileName);
			String fileType=oldFileName.substring(oldFileName.lastIndexOf(".")+1);
			sysEnclosureEntity.setFileSuffix(fileType);
			sysEnclosureEntity.setUploadDate(new Date());
			//文件大小
//			double fileSize=fileItem.getSize()/1024.00;
			String filePath=getFolderName();
			String newFileName=getFileName(fileType);
			File dest = new File(systemPath +filePath);
			dest.setWritable(true,false);
			//判断文件夹是否存在
			if (!dest.exists() && !dest.isDirectory()) {
				//创建目录
				dest.mkdirs();
			}
			logger.debug("存放位置："+systemPath +filePath+newFileName);
			File newFile=new File(systemPath +filePath+newFileName);
			newFile.setWritable(true,false);
			BufferedOutputStream out = new BufferedOutputStream(
					new FileOutputStream(newFile));
			//写文件
			out.write(file.getBytes());
			out.flush();
			out.close();
			sysEnclosureEntity.setFilePath(filePath+newFileName);
			sysEnclosureEntityList.add(sysEnclosureEntity);
		}
		return sysEnclosureEntityList;
	}

	/**
	 * 文件上传到FTP
	 * @param file
	 * @return
	 * @throws IOException
	 */
	public SysEnclosureEntity  uploadFileByFtp(MultipartFile file,String uploadFileType) throws IOException {
		SysEnclosureEntity sysEnclosureEntity=new SysEnclosureEntity();
		String oldFileName=file.getOriginalFilename();
		sysEnclosureEntity.setFileName(oldFileName);
		String fileType=oldFileName.substring(oldFileName.lastIndexOf(".")+1);
		//checkFileHeader(file,fileType,uploadFileType);
		//判断文件大小
		if(file.getSize()>uploadFileSize){
			throw new RRException("您上传的文件过大，请上传100M以内的文件！！");
		}
		sysEnclosureEntity.setFileSuffix(fileType);
		sysEnclosureEntity.setUploadDate(new Date());
		String filePath=getFolderName();
		String newFileName=getFileName(fileType);
		sysEnclosureEntity.setFilePath(filePath+newFileName);
		InputStream in = file.getInputStream();
		byte[] buffer = null;
		ByteArrayOutputStream bos = new ByteArrayOutputStream();
		//判断为图片类型，进行图片压缩
		if("jpg".equals(fileType)||"png".equals(fileType)||"gif".equals(fileType)){
			buffer=ImageUtils.imageCompress(in,fileType);
		}else{
			byte[] b = new byte[10240];
			int n;
			while ((n = in.read(b)) != -1)
			{
				bos.write(b, 0, n);
			}
			buffer = bos.toByteArray();
		}
		in.close();
		bos.close();
		uploadFileFTP(filePath,newFileName,buffer);
		sysEnclosureEntity.setId(createId());
		sysEnclosureEntity.setCreateDate(new Date());
		sysEnclosureEntity.setDeleteState(Constant.DELETE_STATE_NO);
		return sysEnclosureEntity;
	}

	/**
	 * 上传文件到FTP
	 * @param files
	 * @return
	 * @throws IOException
	 */
	public List<SysEnclosureEntity> apiUploadFileByFtp(List<MultipartFile> files) throws IOException {
		List<SysEnclosureEntity>  sysEnclosureEntityList=new ArrayList<>();
		SysEnclosureEntity sysEnclosureEntity=null;
		for(int i=0;i<files.size();i++){
			MultipartFile file=files.get(i);
			sysEnclosureEntity=new SysEnclosureEntity();
			String oldFileName=files.get(i).getOriginalFilename();
			sysEnclosureEntity.setFileName(oldFileName);
			String fileType=oldFileName.substring(oldFileName.lastIndexOf(".")+1);
			sysEnclosureEntity.setFileSuffix(fileType);
			sysEnclosureEntity.setUploadDate(new Date());
			//文件大小
//			double fileSize=fileItem.getSize()/1024.00;
			String filePath=getFolderName();
			String newFileName=getFileName(fileType);
			sysEnclosureEntity.setFilePath(filePath+newFileName);
			InputStream in = file.getInputStream();
			byte[] buffer = null;
			ByteArrayOutputStream bos = new ByteArrayOutputStream();
			byte[] b = new byte[10240];
			int n;
			while ((n = in.read(b)) != -1)
			{
				bos.write(b, 0, n);
			}
			in.close();
			bos.close();
			buffer = bos.toByteArray();
			uploadFileFTP(filePath,newFileName,buffer);
			sysEnclosureEntityList.add(sysEnclosureEntity);
		}
		return sysEnclosureEntityList;
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
			SysEnclosureEntity sysEnclosureEntity=uploadFileByFtp(file,uploadFileType);
			sysUeditorVO.setTitle(sysEnclosureEntity.getFileName());
			sysUeditorVO.setUrl(sysEnclosureEntity.getFilePath());
			sysUeditorVO.setSize((long)sysEnclosureEntity.getFileSize());
			sysUeditorVO.setOriginal(sysEnclosureEntity.getFileName());
		}catch(Exception e){
			e.printStackTrace();
			sysUeditorVO.setState("ERROR");
		}
		return sysUeditorVO;
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
		return "/"+ftpPath+"/"+now.get(Calendar.YEAR)+"/"+(now.get(Calendar.MONTH) + 1) +"/"+now.get(Calendar.DAY_OF_MONTH)+"/";
	}

	/** 生成主键策略 */
	public String createId() {
		return UUID.randomUUID().toString().replaceAll("\\-", "");
	}
}
