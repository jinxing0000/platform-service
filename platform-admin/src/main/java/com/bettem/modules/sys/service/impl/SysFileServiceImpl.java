package com.bettem.modules.sys.service.impl;


import com.bettem.common.base.service.impl.BaseMongdbServiceImpl;
import com.bettem.common.utils.PageUtils;
import com.bettem.common.utils.UploadFileUtil;
import com.bettem.modules.sys.service.SysFileService;
import com.bettem.modules.tourism.dao.TourismProductInfoMongdbDao;
import com.bettem.modules.tourism.entity.TourismProductInfoEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.domain.Sort;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.math.BigDecimal;
import java.util.*;


@Service("sysFileService")
public class SysFileServiceImpl extends BaseMongdbServiceImpl<TourismProductInfoMongdbDao,TourismProductInfoEntity> implements SysFileService {

    @Autowired
    public void setDao(TourismProductInfoMongdbDao tourismProductInfoMongdbDao) {
        this.mongdbDao=tourismProductInfoMongdbDao;
    }

    //minio 服务端地址
    @Value("${bettem.minio.serverUrl}")
    private String minioServerUrl;
    //minio用户名
    @Value("${bettem.minio.accessKey}")
    private String accessKey;
    //minio密码
    @Value("${bettem.minio.secretKey}")
    private String secretKey;
    //存储桶名称
    @Value("${bettem.minio.bucketName}")
    private String bucketName;
    //最大文件大小
    @Value("${bettem.uploadFileSize}")
    private long uploadFileSize;
    //可以上传文件后缀
    @Value("${bettem.uploadFileType}")
    private String uploadFileType;

    @Override
    public Map<String, Object> uploadFile(MultipartFile file) {
        UploadFileUtil uploadFileUtil=new UploadFileUtil(minioServerUrl,accessKey,secretKey,bucketName,uploadFileSize,uploadFileType);
        Map<String, Object> resultMap=uploadFileUtil.uploadFile(file);
        return resultMap;
    }

    @Override
    public void mongdbTest() {
//        List<TourismProductInfoEntity>  list=new ArrayList<>();
//        TourismProductInfoEntity tourismProductInfoEntity=new TourismProductInfoEntity();
//        tourismProductInfoEntity.setProductName("新疆");
//        tourismProductInfoEntity.setState("1");
//        tourismProductInfoEntity.setDeleteState("0");
//        tourismProductInfoEntity.setAdultPrice(new BigDecimal(1));
//        tourismProductInfoEntity.setCostExcluded("4444444");
//        tourismProductInfoEntity.setEndDate(new Date());
//        tourismProductInfoEntity.setReturnRules("1111111111111111111111111111111");
//        tourismProductInfoEntity.setLineType("4");
//        this.saveOrEditToMongDB(tourismProductInfoEntity);
//        list.add(tourismProductInfoEntity);
//        tourismProductInfoEntity.setAdultPrice(new BigDecimal(2));
//        list.add(tourismProductInfoEntity);
//        tourismProductInfoEntity.setAdultPrice(new BigDecimal(3));
//        list.add(tourismProductInfoEntity);
//        tourismProductInfoEntity.setAdultPrice(new BigDecimal(4));
//        list.add(tourismProductInfoEntity);
        //tourismProductInfoEntity.setId("5d4aa0d98de1d0358006cdf3");
//        this.saveOrEditToMongDB(tourismProductInfoEntity);
        //TourismProductInfoEntity tourismProductInfoEntity=this.getInfoByIdForMongdb("5d4aa0d98de1d0358006cdf3");
        //tourismProductInfoEntity.setLineType("222222222222222222222222");
        //this.saveOrEditToMongDB(tourismProductInfoEntity);
        //List<TourismProductInfoEntity> list=this.findListByParam();
        //this.saveOrEditListToMongDB(list);



        //""内为您要查询所对应的字段名
        Query query =new Query();
        //字段相等查询
        //query.addCriteria(Criteria.where("lineType").is("1"));
        //字段不等查询
        query.addCriteria(Criteria.where("deleteState").ne("1"));
//        //字段模糊查询
        //query.addCriteria(Criteria.where("productName").regex(".*?\\" +"海南"+ ".*"));
        List<Sort.Order> orders = new ArrayList<>();
        //time为您要作为查询排序的字段
        Sort.Order order = new Sort.Order(Sort.Direction.DESC, "endDate");
        orders.add(order);
        Date date=new Date();
        //时间范围查询
        if(date != null ){
            Criteria sub = Criteria.where("endDate");
            //gte:>=   gt:>
            //sub = sub.gte(date);
            //lte:<=   lt:<
            sub = sub.lte(date);
            query.addCriteria(sub);
        }
        PageUtils page=this.findPageList(query,orders,1,10,TourismProductInfoEntity.class);
        List<TourismProductInfoEntity> list=this.findListByParam(query,orders,TourismProductInfoEntity.class);
    }


}
