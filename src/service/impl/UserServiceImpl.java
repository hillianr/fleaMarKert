package service.impl;


import java.io.IOException;
import java.util.List;
import java.util.Map;

import javax.servlet.ServletException;


public interface UserServiceImpl {
	
	//��¼�û�,�ж��û������������ݿ����Ƿ����
	public boolean validateLogin(String account, String password);
	
	//ע���û����жϸ��û��Ƿ���ע��
	public boolean validateRegister(String account);
	
	
	//�����µ��û��������û��˺�
	public void vaildateUserId(Object[] params);

	//��ȡ�û���Ϣ
	public Map<String, Object> vaildateGet(String account);
	
	public void rePassword(String password ,String acccount);
	

	public List<Map<String, Object>> findUser(String account);
	
}
