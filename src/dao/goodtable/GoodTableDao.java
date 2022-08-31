/**
 * 
 */
package dao.goodtable;

import until.DBUtil;

/**
 * @author 柴柴
 *
 */
public class GoodTableDao extends DBUtil{

	@Override
	public String getSql_findAll() {
		return "SELECT * from goodtable WHERE goodtable_really=1";
	}

	@Override
	public String getSql_findById() {
		return "SELECT * from goodtable WHERE goodTable_id=? and goodtable_really=1";
	}

	@Override
	public String getSql_insert() {
		return "INSERT INTO goodtable (goodTable_portrait, goodTable_classify, goodTable_price, goodTable_number, goodTable_describe, goodTable_name, "
				+ "goodTable_attribute, goodTable_new, goodTable_time, users_account)VALUES (?,?,?,?,?,?,?,?,?,?)";
	}
	
	@Override
	public String getSql_modify() {
	   return "update goodtable set goodTable_number=? WHERE goodTable_id=?;";
	}
	
	@Override
	public String getSql_update() {
		return "update goodtable set goodTable_portrait=?, goodTable_classify=?, goodTable_price=?, goodTable_number=?, goodTable_describe=?, goodTable_name=?, " 
				+ "goodTable_attribute=?, goodTable_new=?, goodTable_time=? WHERE goodTable_id=?;";
	}

//	删除商品数据
	@Override
	public String getSql_delete() {
	    return "update goodtable set goodtable_really=0 WHERE goodTable_id=?";
	}

	@Override
	public String getSql_insertRegister() {
		return "INSERT INTO users (users_account, users_name,  users_address, users_phone,users_wx, users_password)VALUES (?,?,?,?,?,?)";
	}

//	通过分类搜索商品
	@Override
	public String getSql_findAllIf() {
		return "SELECT * from goodtable WHERE goodTable_classify=? and goodtable_really=1";
	}

//	通过搜索框搜索商品
	@Override
	public String getSql_Search() {
		//"SELECT * FROM goodtable WHERE CONCAT( goodTable_classify, goodTable_describe, goodTable_name, goodTable_attribute ) REGEXP '[]'";
		// TODO Auto-generated method stub
		return "SELECT * FROM goodtable WHERE CONCAT( goodTable_classify, goodTable_describe, goodTable_name, goodTable_attribute ) REGEXP ? and goodtable_really=1";
	}

//	通过几成新搜索商品
	@Override
	public String getSql_findIf() {
		// TODO Auto-generated method stub
		return "SELECT * from goodtable WHERE goodTable_new=? and goodtable_really=1";
	}

//	通过用户搜索商品
	@Override
	public String getSql_findByParam() {
		// TODO Auto-generated method stub
		return "SELECT * from goodtable WHERE users_account=? and goodtable_really=1";
	}

//	根据id获取单条商品信息
	@Override
	public String getSql_findByIntId() {
		return "SELECT goodtable.*, users.users_wx FROM goodtable INNER JOIN users ON goodtable.users_account = users.users_account WHERE goodtable.goodTable_id = ?";
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
