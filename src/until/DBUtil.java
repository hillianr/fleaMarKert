/**
 * 
 */
package until;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;



/**
 * JDBC连接类，提供连接，关闭连接
 * @author 26338
 *
 */
public abstract class DBUtil {
	protected Connection con = null;
	protected PreparedStatement pst = null;
	protected ResultSet rst = null;
	

	/**
	 * 获得表中所有记录
	 * 返回值
	 * @return
	 * @throws ClassNotFoundException
	 * @throws SQLException
	 */
	public List<Map<String, Object>> findAll() throws ClassNotFoundException, SQLException
	{
		return queryForList(getSql_findAll(), new Object[] {});
	}
	
	/**
	 *获得表中特定条件下的所有数据
	 * 返回值
	 * @return
	 * @throws ClassNotFoundException
	 * @throws SQLException
	 */
	public List<Map<String, Object>> findAllIf(String aIf) throws ClassNotFoundException, SQLException
	{
		return queryForList(getSql_findAllIf(), new Object[] {aIf});
	}
	
	/**
	 *获得表中特定条件下的所有数据
	 * 返回值
	 * @return
	 * @throws ClassNotFoundException
	 * @throws SQLException
	 */
	public List<Map<String, Object>> findByParam(String param) throws ClassNotFoundException, SQLException
	{
		return queryForList(getSql_findByParam(), new Object[] {param});
	}
	

	  public  Map<String, Object> findNumber(String account,String id) throws ClassNotFoundException, SQLException 
	  {
	    return queryForMap(getSql_findNumber(), new Object[] {account,id});
	  }
	/**
	 *获得表中特定条件下的所有数据
	 * 返回值
	 * @return
	 * @throws ClassNotFoundException
	 * @throws SQLException
	 */
	public List<Map<String, Object>> findIf(String aIf) throws ClassNotFoundException, SQLException
	{
		return queryForList(getSql_findIf(), new Object[] {aIf});
	}
	
	public List<Map<String, Object>> findIfIndent(String account,String id) throws ClassNotFoundException, SQLException
	{
		return queryForList(getSql_findIfIndent(), new Object[] {account,id});
	}
	
	/**
	 *获得搜索出来所有数据
	 * 返回值
	 * @return
	 * @throws ClassNotFoundException
	 * @throws SQLException
	 */
	public List<Map<String, Object>> findBySearch(String aIf) throws ClassNotFoundException, SQLException
	{
		return queryForList(getSql_Search(), new Object[] {aIf});
	}
	
	/**
	 * 根据用户号码和用户密码获得单条记录
	 * @param account
	 * @param password
	 * @return
	 * @throws ClassNotFoundException
	 * @throws SQLException
	 */
	public Map<String, Object> findById(String account) throws ClassNotFoundException, SQLException 
	{
		return queryForMap(getSql_findById(), new Object[] {account});
	}
	
	/**
	 * 根据id获得单条记录
	 * @param account
	 * @param password
	 * @return
	 * @throws ClassNotFoundException
	 * @throws SQLException
	 */
	
	public Map<String, Object> findByIntId(int id) throws ClassNotFoundException, SQLException 
	{
		return queryForMap(getSql_findByIntId(), new Object[] {id});
	}
	/**
	 * 向表中插入一条记录
	 * @param params
	 * @return
	 * @throws ClassNotFoundException
	 * @throws SQLException
	 */
	public Object insert(Object[] params) throws ClassNotFoundException, SQLException
	{
		return insertAndBackId(getSql_insert(), params);
	}
	
	/**
	 * 向表中插入注册时需要插入的数据
	 * @param params
	 * @return
	 * @throws ClassNotFoundException
	 * @throws SQLException
	 */
	public Object insertRegister(Object[] params) throws ClassNotFoundException, SQLException
	{
		return insertAndBackId(getSql_insertRegister(), params);
	}
	
	/**
	 * 修改表中指定主键的记录
	 * @param params
	 * @throws ClassNotFoundException
	 * @throws SQLException
	 */
	public  void modify(Object[] params) throws ClassNotFoundException, SQLException
	{
		update(getSql_modify(), params);
	}
	
	public  void modifyIndent(Object[] params) throws ClassNotFoundException, SQLException
	{
		update(getSql_modifyIndent(), params);
	}
	
	/**
	 * 修改表中指定主键的记录
	 * @param params
	 * @throws ClassNotFoundException
	 * @throws SQLException
	 */
	public  void updateSql(Object[] params) throws ClassNotFoundException, SQLException
	{
		update(getSql_update(), params);
	}
	
	/**
	 * 删除表中指定主键的记录
	 * @param id
	 * @throws ClassNotFoundException
	 * @throws SQLException
	 */
//	public  void delete(int id) throws ClassNotFoundException, SQLException 
//	{
//		update(getSql_delete(), new Object[] {id});
//	}
	public  void delete(Object[] id) throws ClassNotFoundException, SQLException 
	{
		update(getSql_delete(),id);
	}
	
	
	
	/**
	 * 查询单条记录
	 * 完成对数据库的交互
	 * @param sql
	 * @param params
	 * @return
	 * @throws ClassNotFoundException
	 * @throws SQLException
	 */
	public Map<String, Object> queryForMap(String sql,Object[] params) throws ClassNotFoundException, SQLException
	{
		Map<String, Object> result = null;
		//获得数据库连接
		con = getConnection();
		//封装SQL
		pst = getPreparedStatementAndBindParam(con, sql, params);
		//执行SQL
		 rst = pst.executeQuery();
		//处理结果数据
		 while(rst.next())
			{
			  result = processingResult(rst);
			}
		//关闭DB连接
		release();
			
		return result;
	}
	
	/**
	 * 查询多条记录
	 * @param sql
	 * @return
	 * @throws ClassNotFoundException
	 * @throws SQLException
	 */
	public List<Map<String, Object>> queryForList(String sql,Object[] params) throws ClassNotFoundException, SQLException
	{
		List<Map<String, Object>> result = new ArrayList<Map<String,Object>>();
		//获得数据库连接
		con = getConnection();
		//封装SQL
		pst = getPreparedStatementAndBindParam(con, sql, params);
		//执行SQL
		 rst = pst.executeQuery();
		//处理结果数据
		 while(rst.next())
			{
			 Map<String, Object> recordObj = processingResult(rst);
			 result.add(recordObj);
			}
		//关闭DB连接
		release();
			
		return result;
	}
	
	/**
	 * 更新记录，包括（insert,update,delete）
	 * @param sql
	 * @param params
	 * @throws ClassNotFoundException
	 * @throws SQLException
	 */
	public void update(String sql,Object[] params) throws ClassNotFoundException, SQLException
	{
		//获得数据库连接
		con = getConnection();
		//封装SQL
		pst = getPreparedStatementAndBindParam(con, sql, params);
		//执行SQL
		pst.executeUpdate();
		
		release();
	}
	/**
	 * 插入一条记录，并返回此记录的主键值
	 * @param sql
	 * @param params
	 * @return
	 * @throws ClassNotFoundException
	 * @throws SQLException
	 */
	public Object insertAndBackId(String sql,Object[] params) throws ClassNotFoundException, SQLException
	{
		//获得数据库连接
		con = getConnection();
		//封装SQL
		pst = getPreparedStatementAndBindParam(con, sql, params);
		//执行SQL
		pst.execute();
		//===============================================
		Object idObj = null;
		rst = pst.executeQuery("SELECT LAST_INSERT_ID() as lastId");
		while(rst.next())
		{
			idObj = rst.getObject("lastId");
		}
		release();
		return idObj;
	}
	
	/**
	 * 封装SQL
	 * @param sql
	 * @return
	 * @throws ClassNotFoundException
	 * @throws SQLException
	 */
	public Connection getConnection() throws ClassNotFoundException, SQLException
	{
		//加载JDBC驱动
		Class.forName("com.mysql.cj.jdbc.Driver");
		//获得DB连接
		con = DriverManager.getConnection("jdbc:mysql://localhost:3306/filemarket?useUnicode=true&characterEncoding=utf-8&serverTimezone=UTC&useSSL=false", "root", "123123");
		//封装SQL
		return con;
	}
	
	/**
	 * 参数绑定（指定SQL语句中每个？的值）
	 * @param pst
	 * @param params
	 * @return
	 * @throws SQLException
	 */
	public PreparedStatement getPreparedStatementAndBindParam(Connection con,String sql,Object[] params) throws SQLException
	{
		pst = con.prepareStatement(sql);
		 for(int i=0;i<params.length;i++)
		 {
			 pst.setObject(i+1,params[i]);
		 }
		 return pst;
	}
	
	/**
	 * O映射（将远端DB中的一条记录转存为本地内存中的一个对象）
	 * getColumnName获取的是sql语句中field的原始名字。
	 * @param rst
	 * @return
	 * @throws SQLException
	 */
	public Map<String, Object> processingResult(ResultSet rst) throws SQLException
	{
		Map<String, Object> recordObj = new HashMap<String, Object>();
		ResultSetMetaData resultSetMetaData = rst.getMetaData();
		
		for(int i=1;i<=resultSetMetaData.getColumnCount();i++)
		{
			recordObj.put(resultSetMetaData.getColumnName(i), rst.getObject(resultSetMetaData.getColumnName(i)));
		}
		
		return recordObj;
	}
	/**
	 * 释放数据库连接
	 * @throws SQLException
	 */
	public void release() throws SQLException
	{
		if(rst != null)
		{
			rst.close();
		}
		if(pst != null)
		{
			pst.close();
		}
		if(con != null)
		{
			con.close();
		}
	}
	
	
	/**
	 * 获得findAll的SQL语句
	 * @return
	 */
	public abstract String getSql_findAll();
	
	/**
	 * 获得findAllIf的SQL语句用来获取特定条件下的数组集
	 * @return
	 */
	public abstract String getSql_findAllIf();
	
	/**
	 * 获得findIf的SQL语句用来获取特定条件下的数组集
	 * @return
	 */
	public abstract String getSql_findIf();
	
	/**
	 * 获得findById的SQL语句
	 * @return
	 */
	public abstract String getSql_findById();
	
	/**
	 * 获得findByIntId的SQL语句
	 * @return
	 */
	public abstract String getSql_findByIntId();
	
	/**
	 * 获得insert的SQL语句
	 * @return
	 */
	public abstract String getSql_insert();
	
	/**
	 * 插入部分数据
	 * @return
	 */
	public abstract String getSql_insertRegister();
	
	/**
	 * 获得modify的SQL语句
	 * @return
	 */
	public abstract String getSql_modify();
	
	/**
	 * 获得update的SQL语句
	 * @return
	 */
	public abstract String getSql_update();
	
	/**
	 * 获得delete的SQL语句
	 * @return
	 */
	public abstract String getSql_delete();
	
	/**
	 * 获得搜索关键字
	 * @return
	 */
	public abstract String getSql_Search();
	
	/**
	 * 根据一个数据获得多组数据得sql语句
	 * @return
	 */
	
	public abstract String getSql_findByParam();
	
	public abstract String getSql_findIfIndent();
	
	public abstract String getSql_modifyIndent();
	
	public abstract String getSql_findNumber();
	
}