package service;

import java.io.IOException;
import java.sql.SQLException;
import java.util.List;
import java.util.Map;

import javax.servlet.ServletException;

import dao.users.UsersDao;
import service.impl.UserServiceImpl;

public class UserService implements UserServiceImpl{

	/**
	 * ��¼�û�,�ж��û������ݿ����Ƿ����
	 */
	@Override
	public boolean validateLogin(String account, String password) {
		UsersDao usersDao = new UsersDao();
		boolean flag = false;
		try {
			Map<String, Object> users = usersDao.findById(account);
			//�ж��û�����������Ƿ���ȷ
			if(users != null && users.get("users_account").equals(account) && users.get("users_password").equals(password)) {
				flag = true;
			}
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return flag;
	}

		//��֤ע���û����Ƿ���������
	@Override
	public boolean validateRegister(String account) {
		UsersDao usersDao = new UsersDao();
		boolean flag = false;
		try {
			Map<String, Object> users = usersDao.findById(account);
			//�ж��û�����������Ƿ���ȷ
			if(users != null && users.get("users_account").equals(account)) {
				flag = true;
			}
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return flag;
	}

	//�����µ��û��������û��˺�
	@Override
	public void vaildateUserId(Object[] params) {
		Object[] par = new Object[params.length-1];
		for(int i=0; i<par.length; i++) {
			par[i] = params[i];
		}
		UsersDao usersDao = new UsersDao();
		try {
			usersDao.insertRegister(par);
		} catch (ClassNotFoundException | SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}


	@Override
	public Map<String, Object> vaildateGet(String account) {
		Map<String, Object> users = null;
		UsersDao usersDao = new UsersDao();
		try {
			users = usersDao.findById(account);
		} catch (ClassNotFoundException | SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return users;
	}

	@Override
	public void rePassword(String password, String acccount) {
		 // TODO Auto-generated method stub
	    UsersDao usersDao = new UsersDao();
	    try {
			usersDao.modify(new Object[] {password, acccount});
		} catch (ClassNotFoundException | SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}

	@Override
	public List<Map<String, Object>> findUser(String account) {
		// TODO Auto-generated method stub
		UsersDao usersDao = new UsersDao();
		List<Map<String, Object>> a = null;
		try {
			a = usersDao.findAllIf(account);
		} catch (ClassNotFoundException | SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return a;
	}

}
