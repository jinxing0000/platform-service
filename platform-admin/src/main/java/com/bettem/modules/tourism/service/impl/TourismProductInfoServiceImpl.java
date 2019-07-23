package com.bettem.modules.tourism.service.impl;

import com.bettem.common.utils.Constant;
import com.bettem.common.utils.ShiroTokenUtils;
import com.bettem.modules.tourism.entity.TourismProductPicEntity;
import com.bettem.modules.tourism.entity.VO.TourismProductInfoVO;
import com.bettem.modules.tourism.service.TourismProductPicService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;

import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.baomidou.mybatisplus.plugins.Page;
import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import com.bettem.common.utils.PageUtils;
import com.bettem.common.utils.Query;

import com.bettem.modules.tourism.dao.TourismProductInfoDao;
import com.bettem.modules.tourism.entity.TourismProductInfoEntity;
import com.bettem.modules.tourism.service.TourismProductInfoService;
import org.springframework.transaction.annotation.Transactional;


@Service("tourismProductInfoService")
public class TourismProductInfoServiceImpl extends ServiceImpl<TourismProductInfoDao, TourismProductInfoEntity> implements TourismProductInfoService {

    @Autowired
    private ShiroTokenUtils shiroTokenUtils;


    @Autowired
    private TourismProductPicService tourismProductPicService;


    /** 生成主键策略 */
    public String createId() {
        return UUID.randomUUID().toString().replaceAll("\\-", "");
    }

    @Override
    public PageUtils queryPage(Map<String, Object> params) {
        String productName=(String)params.get("productName");
        Page<TourismProductInfoEntity> page = this.selectPage(
                new Query<TourismProductInfoEntity>(params).getPage(),
                new EntityWrapper<TourismProductInfoEntity>().eq("delete_state",Constant.DELETE_STATE_NO)
                .like(productName!=null,"product_name",productName)
                .orderBy("create_date desc,state")
        );

        return new PageUtils(page);
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void deleteByIds(String[] ids) {
        List<TourismProductInfoEntity> TourismProductInfoList=new ArrayList<>();
        TourismProductInfoEntity tourismProductInfoEntity=null;
        for(int i=0;i<ids.length;i++){
            tourismProductInfoEntity=new TourismProductInfoEntity();
            tourismProductInfoEntity.setId(ids[i]);
            tourismProductInfoEntity.setModifyUserId(shiroTokenUtils.getUserId());
            tourismProductInfoEntity.setModifyDate(new Date());
            tourismProductInfoEntity.setDeleteState(Constant.DELETE_STATE_YES);
            TourismProductInfoList.add(tourismProductInfoEntity);
        }
        Map<String,Object> params=new HashMap<>();
        params.put("deleteState",Constant.DELETE_STATE_YES);
        params.put("modifyDate",new Date());
        params.put("modifyUserId",shiroTokenUtils.getUserId());
        params.put("productIds",Arrays.asList(ids));
        tourismProductPicService.deleteProductPicByProductId(params);
        this.updateBatchById(TourismProductInfoList);

    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void saveTourismProductInfo(TourismProductInfoVO tourismProductInfoVO) {
        String id=createId();
        String userId=shiroTokenUtils.getUserId();
        Date date=new Date();
        tourismProductInfoVO.setId(id);
        tourismProductInfoVO.setState(Constant.PRODUCT_STATE_INIT);
        tourismProductInfoVO.setSupplierId(userId);
        tourismProductInfoVO.setSupplierName(shiroTokenUtils.getUserName());
        tourismProductInfoVO.setCreateUserId(userId);
        tourismProductInfoVO.setCreateDate(date);
        tourismProductInfoVO.setDeleteState(Constant.DELETE_STATE_NO);
        List<TourismProductPicEntity> picList=tourismProductInfoVO.getPicList();
        for(TourismProductPicEntity tourismProductPicEntity:picList){
            tourismProductPicEntity.setProductId(id);
            tourismProductPicEntity.setCreateDate(date);
            tourismProductPicEntity.setCreateUserId(userId);
            tourismProductPicEntity.setDeleteState(Constant.DELETE_STATE_NO);
        }
        String productGuidePicUrl="";
        if(picList.size()>0){
            productGuidePicUrl=picList.get(0).getThumbUrl();
            tourismProductPicService.insertBatch(picList);
        }
        tourismProductInfoVO.setProductGuidePicUrl(productGuidePicUrl);
        //新增产品
        this.insert(tourismProductInfoVO);
    }

    @Override
    public TourismProductInfoVO findProductInfoVOById(String id) {
        TourismProductInfoVO tourismProductInfoVO=this.baseMapper.selectProductInfoVOById(id);
        List<TourismProductPicEntity>  picList=tourismProductPicService.selectList(new EntityWrapper<TourismProductPicEntity>().eq("product_id",id).eq("delete_state",Constant.DELETE_STATE_NO).orderBy("create_date desc"));
        tourismProductInfoVO.setPicList(picList);
        return tourismProductInfoVO;
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void editTourismProductInfo(TourismProductInfoVO tourismProductInfoVO) {
        String userId=shiroTokenUtils.getUserId();
        String id=tourismProductInfoVO.getId();
        Date date=new Date();
        tourismProductInfoVO.setModifyDate(date);
        tourismProductInfoVO.setModifyUserId(userId);
        Map<String,Object> params=new HashMap<>();
        params.put("product_id",id);
        tourismProductPicService.deleteByMap(params);
        List<TourismProductPicEntity> picList=tourismProductInfoVO.getPicList();
        for(TourismProductPicEntity tourismProductPicEntity:picList){
            tourismProductPicEntity.setProductId(id);
            tourismProductPicEntity.setCreateDate(date);
            tourismProductPicEntity.setCreateUserId(userId);
            tourismProductPicEntity.setDeleteState(Constant.DELETE_STATE_NO);
        }
        String productGuidePicUrl="";
        if(picList.size()>0){
            productGuidePicUrl=picList.get(0).getThumbUrl();
            tourismProductPicService.insertBatch(picList);
        }
        tourismProductInfoVO.setProductGuidePicUrl(productGuidePicUrl);
        //新增产品
        this.updateById(tourismProductInfoVO);
    }

}
