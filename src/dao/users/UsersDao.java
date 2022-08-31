package dao.users;

import until.DBUtil;

public class UsersDao extends DBUtil{

	@Override
	public String getSql_findAll() {
		return "SELECT * from users WHERE users_account=?";
	}

	@Override
	public String getSql_findById() {
		return "SELECT * from users WHERE users_account=?";
	}

	@Override
	public String getSql_insert() {
		return "INSERT INTO users (users_account, users_name, users_address, users_password, users_portrat, users_phone, users_wx)VALUES (?,?,?,?,?,?,?)";
	}
	
	
	@Override
	public String getSql_modify() {
	   return "update users set users_password=? WHERE users_account=?";
	}

	@Override
	public String getSql_delete() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public String getSql_insertRegister() {
		return "INSERT INTO users (users_account, users_name,  users_address, users_phone,users_wx, users_password)VALUES (?,?,?,?,?,?)";
	}

	@Override
	public String getSql_findAllIf() {
		// TODO Auto-generated method stub
		return "SELECT * from users WHERE users_account=?";
	}

	@Override
	public String getSql_Search() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public String getSql_findIf() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public String getSql_findByParam() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public String getSql_findByIntId() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public String getSql_update() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public String getSql_findIfIndent() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public String getSql_modifyIndent() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public String getSql_findNumber() {
		// TODO Auto-generated method stub
		return null;
	}
	
	

}
