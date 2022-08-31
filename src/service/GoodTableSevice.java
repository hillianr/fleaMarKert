package service;

import java.sql.SQLException;
import java.util.List;
import java.util.Map;

import dao.goodtable.GoodTableDao;
import dao.users.UsersDao;
import service.impl.GoodTableServiceImpl;

public class GoodTableSevice implements GoodTableServiceImpl {

	//�����÷����ҳ������ݵ����鼯
	@Override
	public  List<Map<String, Object>> vaildateUserId(String classfiy) {
		List<Map<String, Object>> goodTable = null;
		GoodTableDao goodTableDao = new GoodTableDao();
		try {
			goodTable = goodTableDao.findAllIf(classfiy);
		} catch (ClassNotFoundException | SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return goodTable;
	}
	
	@Override
	public List<Map<String, Object>> vaildateUserIf(String classfiy) {
		List<Map<String, Object>> goodTable = null;
		GoodTableDao goodTableDao = new GoodTableDao();
		try {
			goodTable = goodTableDao.findIf(classfiy);
		} catch (ClassNotFoundException | SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return goodTable;
	
	}

	//����ȫ����Ʒ���ݼ�
	@Override
	public List<Map<String, Object>> vaildateAll() {
		List<Map<String, Object>> goodTable = null;
		GoodTableDao goodTableDao = new GoodTableDao();
		try {
			goodTable = goodTableDao.findAll();
		} catch (ClassNotFoundException | SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return goodTable;
	}

	@Override
	public List<Map<String, Object>> vaildateSearch(String param) {
		List<Map<String, Object>> goodTable = null;
		GoodTableDao goodTableDao = new GoodTableDao();
		try {
			goodTable = goodTableDao.findBySearch(param);
		} catch (ClassNotFoundException | SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return goodTable;
	}

	//����µ���Ʒ
	@Override
	public void vaildateInsert(Object[] params) {
		GoodTableDao goodTableDao = new GoodTableDao();
		try {
			goodTableDao.insert(params);
		} catch (ClassNotFoundException | SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	//����ͨ���û��˺Ų��ҳ���Ʒ����
	@Override
	public List<Map<String, Object>> vaildateshop(String param) {
		List<Map<String, Object>> goodTable = null;
		GoodTableDao goodTableDao = new GoodTableDao();
		try {
			goodTable = goodTableDao.findByParam(param);
		} catch (ClassNotFoundException | SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return goodTable;
	}

//	������ƷIdɾ����Ʒ
	@Override
	public void vaildateDelete(Object[] params) {
		// TODO Auto-generated method stub
		GoodTableDao goodTableDao = new GoodTableDao();
		try {
			goodTableDao.delete(params);
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
	
//	������ƷId������Ʒ
	@Override
	public Map<String, Object> vaildateByIntid(int id) {
		Map<String, Object> goods = null;
		GoodTableDao goodTableDao = new GoodTableDao();
		try {
			goods = goodTableDao.findByIntId(id);
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return goods;
	}

@Override
public void vaildateModify(Object[] params) {
	// TODO Auto-generated method stub
	GoodTableDao goodTableDao = new GoodTableDao();
	try {
		goodTableDao.modify(params);;
	} catch (ClassNotFoundException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	} catch (SQLException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	}
}

@Override
public void vaildateupdate(Object[] params) {
	// TODO Auto-generated method stub
	GoodTableDao goodTableDao = new GoodTableDao();
	try {
		goodTableDao.updateSql(params);
	} catch (ClassNotFoundException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	} catch (SQLException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	}
}



}
