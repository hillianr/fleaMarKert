/**
 * 
 */
package dao.indent;

import until.DBUtil;

public class IndentDao  extends DBUtil{

	@Override
	public String getSql_findAll() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public String getSql_findAllIf() {
		// TODO Auto-generated method stub
		return "SELECT goodtable.goodTable_id,goodtable.goodTable_number,indent.indent_id,goodtable.goodTable_portrait, goodtable.goodTable_name, indent.indent_number, goodtable.goodTable_price, goodtable.goodTable_new, users.users_wx,SUBSTRING( users.users_address FROM 1 FOR 2 )as address,goodtable.goodTable_price * indent.indent_number AS gn FROM goodtable INNER JOIN users ON goodtable.users_account = users.users_account INNER JOIN indent ON indent.goodTable_id = goodtable.goodTable_id WHERE indent.users_account = ? and indent.indent_really = 1";
	}


	@Override
	public String getSql_findById() {
		// TODO Auto-generated method stub
		return "SELECT  goodtable.goodTable_price * indent.indent_number AS gn FROM goodtable INNER JOIN users ON goodtable.users_account = users.users_account INNER JOIN indent ON indent.goodTable_id = goodtable.goodTable_id WHERE indent.users_account = ? AND indent.indent_really = 1" ;
	}

	@Override
	public String getSql_insert() {
		// TODO Auto-generated method stub
		return "INSERT INTO indent ( indent_number, users_account, goodTable_id ) VALUES (?,?,?)";
	}
	
	@Override
	public String getSql_findIfIndent() {
		// TODO Auto-generated method stub
		return "select indent_really FROM indent WHERE users_account = ? AND goodTable_id = ?";
	}
	
	@Override
	public String getSql_insertRegister() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public String getSql_modify() {
		// TODO Auto-generated method stub
		return "UPDATE indent SET indent_number = indent_number + 1 WHERE users_account = ? AND goodTable_id = ?";
	}

	@Override
	public String getSql_delete() {
		// TODO Auto-generated method stub
		return "UPDATE indent SET indent_really = 0 WHERE indent_id = ?";
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
	  public String getSql_modifyIndent() {
	    // TODO Auto-generated method stub
	    return "UPDATE indent SET indent_number = ? WHERE users_account = ? AND goodTable_id = ?";
	  }
	  
	  @Override
	  public String getSql_update() {
	    // TODO Auto-generated method stub
	    return "UPDATE indent SET indent_really = 1 WHERE indent_id = ?";
	  }

	  @Override
		public String getSql_findNumber() {
			// TODO Auto-generated method stub
			return "SELECT goodtable.goodTable_number FROM goodtable INNER JOIN users ON goodtable.users_account = users.users_account INNER JOIN indent ON indent.goodTable_id = goodtable.goodTable_id WHERE indent.users_account = ? AND indent.indent_id = ? AND indent.indent_really = 1; ";
		}
}
