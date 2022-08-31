package service.impl;

import java.util.List;
import java.util.Map;

public interface GoodTableServiceImpl {

	//返回用分类找出的数据的数组集
	public List<Map<String, Object>> vaildateUserId(String classfiy);
	
	//返回用全新找出的数据的数组集
	public List<Map<String, Object>> vaildateUserIf(String classfiy);
	
	//返回全部商品数组集
	public List<Map<String, Object>> vaildateAll();
	
	//返回通过查找出的商品数据
	public List<Map<String, Object>> vaildateSearch(String param);
	
	//添加新的商品
	public void vaildateInsert(Object[] params);
	
	//返回通过用户账号查找出商品数据
	public List<Map<String, Object>> vaildateshop(String param);
	
//	删除商品
	public void vaildateDelete(Object[] params);
	
//	通过id查找商品
	public Map<String, Object> vaildateByIntid(int account);
	
//	更新商品数量
	public void vaildateModify(Object[] params);
	
//	修改商品
	public void vaildateupdate(Object[] params);
	
}

