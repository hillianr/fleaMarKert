package service.impl;

import java.util.List;
import java.util.Map;

public interface GoodTableServiceImpl {

	//�����÷����ҳ������ݵ����鼯
	public List<Map<String, Object>> vaildateUserId(String classfiy);
	
	//������ȫ���ҳ������ݵ����鼯
	public List<Map<String, Object>> vaildateUserIf(String classfiy);
	
	//����ȫ����Ʒ���鼯
	public List<Map<String, Object>> vaildateAll();
	
	//����ͨ�����ҳ�����Ʒ����
	public List<Map<String, Object>> vaildateSearch(String param);
	
	//����µ���Ʒ
	public void vaildateInsert(Object[] params);
	
	//����ͨ���û��˺Ų��ҳ���Ʒ����
	public List<Map<String, Object>> vaildateshop(String param);
	
//	ɾ����Ʒ
	public void vaildateDelete(Object[] params);
	
//	ͨ��id������Ʒ
	public Map<String, Object> vaildateByIntid(int account);
	
//	������Ʒ����
	public void vaildateModify(Object[] params);
	
//	�޸���Ʒ
	public void vaildateupdate(Object[] params);
	
}

