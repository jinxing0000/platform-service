package com.bettem.common.base.service.impl;

import com.bettem.common.base.dao.BaseMongdbDao;
import com.bettem.common.base.entity.BaseEntity;
import com.bettem.common.utils.PageUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.*;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Query;

import java.util.List;

public abstract class BaseMongdbServiceImpl<baseMongdbDao extends BaseMongdbDao<T>, T extends BaseEntity> {

    protected  baseMongdbDao mongdbDao;

    @Autowired
    private MongoTemplate mongoTemplate;

    /**
     * 新增、修改数据接口
     * @param entity
     * @return
     */
    public T saveOrEditToMongDB(T entity) {
        return mongdbDao.save(entity);
    }

    /**
     * 按照id查询数据接口
     * @param id
     * @return
     */
    public T getInfoByIdForMongdb(String id){
        return mongdbDao.findById(id).get();
    }

    /**
     * 批量新增或者修改
     * @param list
     * @return
     */
    public List<T> saveOrEditListToMongDB(List<T> list){
        return mongdbDao.saveAll(list);
    }

    /**
     * 按照实体删除
     * @param id
     */
    public void deleteByIdForMongdb(String id){
        this.mongdbDao.deleteById(id);
    }

    /**
     * 删除所有数据
     */
    public void deleteAllForMongdb(){
        this.mongdbDao.deleteAll();
    }

    /**
     * 按照list删除数据
     * @param list
     */
    public void deleteByIdsForMongdb(List<T> list){
        this.mongdbDao.deleteAll(list);
    }

    /**
     * 按照id查找是否存在数据
     * @param id
     * @return
     */
    public boolean existsByIdForMongdb(String id){
        return this.mongdbDao.existsById(id);
    }

    /**
     * 按照条件查询数据列表
     * @param query
     * @param orders
     * @param cla
     * @return
     */
    public List<T> findListByParam(Query query,List<Sort.Order> orders,Class<T> cla){
        if(orders!=null){
            Sort sort = new Sort(orders);
            return this.mongoTemplate.find(query.with(sort), cla);
        }else{
            return this.mongoTemplate.find(query, cla);
        }
    }

    /**
     * 分页条件查询
     * @param query  查询条件
     * @param orders 查询字段
     * @param page   查询页码
     * @param limit  一页数据条数
     * @param cla    实体class
     * @return
     */
    public PageUtils findPageList(Query query,List<Sort.Order> orders,int page,int limit,Class<T> cla){
        /**
         *
         *     条件查询示例
         *  //""内为您要查询所对应的字段名
         *         Query query =new Query();
         *         //字段相等查询
         *         //query.addCriteria(Criteria.where("lineType").is("1"));
         *         //字段不等查询
         *         query.addCriteria(Criteria.where("deleteState").ne("1"));
         * //        //字段模糊查询
         *         //query.addCriteria(Criteria.where("productName").regex(".*?\\" +"海南"+ ".*"));
         *         List<Sort.Order> orders = new ArrayList<>();
         *         //time为您要作为查询排序的字段
         *         Sort.Order order = new Sort.Order(Sort.Direction.DESC, "endDate");
         *         orders.add(order);
         *         Date date=new Date();
         *         //时间范围查询
         *         if(date != null ){
         *             Criteria sub = Criteria.where("endDate");
         *             //gte:>=   gt:>
         *             //sub = sub.gte(date);
         *             //lte:<=   lt:<
         *             sub = sub.lte(date);
         *             query.addCriteria(sub);
         *        }
         */
        PageRequest pageRequest;
        if(orders!=null){
            Sort sort = new Sort(orders);
            //mongodb里面分页起始页码从0开始，而页面传过来的一般从1开始
            pageRequest = new PageRequest(page - 1 < 0 ? 0
                    : page - 1, limit, sort);
        }else{
            //mongodb里面分页起始页码从0开始，而页面传过来的一般从1开始
            pageRequest = new PageRequest(page - 1 < 0 ? 0
                    : page - 1, limit);
        }
        //查询出一共的条数
        long count =  this.mongoTemplate.count(query, cla);
        //查询
        List<? extends BaseEntity> list = this.mongoTemplate.find(query.with(pageRequest), cla);
        return new PageUtils(list,(int)count,limit,page);
    }

}
