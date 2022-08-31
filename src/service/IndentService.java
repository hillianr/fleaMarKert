/**
 * 
 */
package service;

import java.sql.SQLException;
import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.Set;

import dao.indent.IndentDao;
import service.impl.IndentServiceImpl;

/**
 * @author ²ñ²ñ
 *
 */
public class IndentService implements IndentServiceImpl{

	@Override
	public List<Map<String, Object>> vaildateindent(String account) {
		// TODO Auto-generated method stub\
		IndentDao indent = new IndentDao();
		List<Map<String, Object>> indent_table = null;
		try {
			indent_table = indent.findAllIf(account);
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return indent_table;
	}

	@Override
	public void reindent(String id) {
		// TODO Auto-generated method stub
		IndentDao indent = new IndentDao();
		int i  = Integer.parseInt( id );
		try {
			indent.delete(new Object[] {i});
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}


	@Override
	public Map<String, Object> accept_number(String account, String id) {
		// TODO Auto-generated method stub
		IndentDao indent = new IndentDao();
		Map<String, Object> a = null;
		try {
			a = indent.findNumber(account, id);
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return a;
	}

	@Override
	public void insert_indent(Object[] insertRegister) {
		// TODO Auto-generated method stub
		IndentDao indent = new IndentDao();
		try {
			indent.insert(insertRegister);
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	
	@Override
	public List<Map<String, Object>> find_indent(String account, String id) {
		// TODO Auto-generated method stub
		IndentDao indent = new IndentDao();
		List<Map<String, Object>> really = null;
		try {
			really = indent.findIfIndent(account, id);
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return really;
	}

	@Override
	public void update_indent(Object[] insertRegister3) {
		// TODO Auto-generated method stub
		IndentDao indent = new IndentDao();
		try {
			indent.modify(insertRegister3);
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	

	@Override
	public void update_indent_shop(Object[] insertRegister3) {
		// TODO Auto-generated method stub
		IndentDao indent = new IndentDao();
		try {
			indent.modifyIndent(insertRegister3);
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	@Override
	public void update_really(int id1) {
		// TODO Auto-generated method stub
		IndentDao indent = new IndentDao();
		try {
			indent.updateSql(new Object[] {id1});;
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}


	}



	

	

}
