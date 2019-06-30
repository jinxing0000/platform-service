package com.bettem.service;

import com.bettem.dao.GeneratorDao;
import com.bettem.utils.GenUtils;
import com.bettem.utils.PageUtils;
import com.bettem.utils.Query;
import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import org.apache.commons.io.IOUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.ByteArrayOutputStream;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.zip.ZipOutputStream;

/**
 * 代码生成器
 * 
 * @author chenshun
 * @email sunlightcs@gmail.com
 * @date 2016年12月19日 下午3:33:38
 */
@Service
public class SysGeneratorService {
	@Autowired
	private GeneratorDao generatorDao;

	public PageUtils queryList(Query query) {
		Page<?> page = PageHelper.startPage(query.getPage(), query.getLimit());
		List<Map<String, Object>> list = generatorDao.queryList(query);

		return new PageUtils(list, (int)page.getTotal(), query.getLimit(), query.getPage());
	}

	public Map<String, String> queryTable(String tableName) {
		return generatorDao.queryTable(tableName);
	}

	public List<Map<String, String>> queryColumns(String tableName) {
		return generatorDao.queryColumns(tableName);
	}

	public byte[] generatorCode(String[] tableNames,String module,String author) {
		ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
		ZipOutputStream zip = new ZipOutputStream(outputStream);
//		Map<String,Object> parms=new HashMap<>();
//		parms.put("updateData","1");
//		List<Map<String, Object>> updateData=new ArrayList();
//		List<Map<String, Object>> list = generatorDao.queryList(parms);
//		for(int i=0;i<list.size();i++){
//			Map<String, Object> data=new HashMap<>();
//			boolean flag=true;
//			String  tableName=(String)list.get(i).get("tableName");
//			List<Map<String, String>> columns = queryColumns(tableName);
//			String str[]=tableName.split("_");
//			if("ACT".equals(str[0])){
//				flag=false;
//			}
//			data.put("flag",flag);
//			data.put("tableName",tableName);
//			data.put("columns",columns);
//			updateData.add(data);
//		}
		for(String tableName : tableNames){
			//查询表信息
			Map<String, String> table = queryTable(tableName);
			//查询列信息
			List<Map<String, String>> columns = queryColumns(tableName);
			//生成代码
			GenUtils.generatorCode(table, columns, zip,module,author);
		}
		IOUtils.closeQuietly(zip);
		return outputStream.toByteArray();
	}
}
