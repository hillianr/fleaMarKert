package service.impl;


import java.io.IOException;
import java.util.List;
import java.util.Map;

import javax.servlet.ServletException;


public interface UserServiceImpl {
	
	//登录用户,判断用户和密码在数据库中是否存在
	public boolean validateLogin(String account, String password);
	
	//注册用户，判断改用户是否已注册
	public boolean validateRegister(String account);
	
	
	//插入新的用户，返回用户账号
	public void vaildateUserId(Object[] params);

	//获取用户信息
	public Map<String, Object> vaildateGet(String account);
	
	public void rePassword(String password ,String acccount);
	

	public List<Map<String, Object>> findUser(String account);
	
}
