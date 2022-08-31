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
 * JDBC�����࣬�ṩ���ӣ��ر�����
 * @author 26338
 *
 */
public abstract class DBUtil {
	protected Connection con = null;
	protected PreparedStatement pst = null;
	protected ResultSet rst = null;
	

	/**
	 * ��ñ������м�¼
	 * ����ֵ
	 * @return
	 * @throws ClassNotFoundException
	 * @throws SQLException
	 */
	public List<Map<String, Object>> findAll() throws ClassNotFoundException, SQLException
	{
		return queryForList(getSql_findAll(), new Object[] {});
	}
	
	/**
	 *��ñ����ض������µ���������
	 * ����ֵ
	 * @return
	 * @throws ClassNotFoundException
	 * @throws SQLException
	 */
	public List<Map<String, Object>> findAllIf(String aIf) throws ClassNotFoundException, SQLException
	{
		return queryForList(getSql_findAllIf(), new Object[] {aIf});
	}
	
	/**
	 *��ñ����ض������µ���������
	 * ����ֵ
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
	 *��ñ����ض������µ���������
	 * ����ֵ
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
	 *�������������������
	 * ����ֵ
	 * @return
	 * @throws ClassNotFoundException
	 * @throws SQLException
	 */
	public List<Map<String, Object>> findBySearch(String aIf) throws ClassNotFoundException, SQLException
	{
		return queryForList(getSql_Search(), new Object[] {aIf});
	}
	
	/**
	 * �����û�������û������õ�����¼
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
	 * ����id��õ�����¼
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
	 * ����в���һ����¼
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
	 * ����в���ע��ʱ��Ҫ���������
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
	 * �޸ı���ָ�������ļ�¼
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
	 * �޸ı���ָ�������ļ�¼
	 * @param params
	 * @throws ClassNotFoundException
	 * @throws SQLException
	 */
	public  void updateSql(Object[] params) throws ClassNotFoundException, SQLException
	{
		update(getSql_update(), params);
	}
	
	/**
	 * ɾ������ָ�������ļ�¼
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
	 * ��ѯ������¼
	 * ��ɶ����ݿ�Ľ���
	 * @param sql
	 * @param params
	 * @return
	 * @throws ClassNotFoundException
	 * @throws SQLException
	 */
	public Map<String, Object> queryForMap(String sql,Object[] params) throws ClassNotFoundException, SQLException
	{
		Map<String, Object> result = null;
		//������ݿ�����
		con = getConnection();
		//��װSQL
		pst = getPreparedStatementAndBindParam(con, sql, params);
		//ִ��SQL
		 rst = pst.executeQuery();
		//����������
		 while(rst.next())
			{
			  result = processingResult(rst);
			}
		//�ر�DB����
		release();
			
		return result;
	}
	
	/**
	 * ��ѯ������¼
	 * @param sql
	 * @return
	 * @throws ClassNotFoundException
	 * @throws SQLException
	 */
	public List<Map<String, Object>> queryForList(String sql,Object[] params) throws ClassNotFoundException, SQLException
	{
		List<Map<String, Object>> result = new ArrayList<Map<String,Object>>();
		//������ݿ�����
		con = getConnection();
		//��װSQL
		pst = getPreparedStatementAndBindParam(con, sql, params);
		//ִ��SQL
		 rst = pst.executeQuery();
		//����������
		 while(rst.next())
			{
			 Map<String, Object> recordObj = processingResult(rst);
			 result.add(recordObj);
			}
		//�ر�DB����
		release();
			
		return result;
	}
	
	/**
	 * ���¼�¼��������insert,update,delete��
	 * @param sql
	 * @param params
	 * @throws ClassNotFoundException
	 * @throws SQLException
	 */
	public void update(String sql,Object[] params) throws ClassNotFoundException, SQLException
	{
		//������ݿ�����
		con = getConnection();
		//��װSQL
		pst = getPreparedStatementAndBindParam(con, sql, params);
		//ִ��SQL
		pst.executeUpdate();
		
		release();
	}
	/**
	 * ����һ����¼�������ش˼�¼������ֵ
	 * @param sql
	 * @param params
	 * @return
	 * @throws ClassNotFoundException
	 * @throws SQLException
	 */
	public Object insertAndBackId(String sql,Object[] params) throws ClassNotFoundException, SQLException
	{
		//������ݿ�����
		con = getConnection();
		//��װSQL
		pst = getPreparedStatementAndBindParam(con, sql, params);
		//ִ��SQL
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
	 * ��װSQL
	 * @param sql
	 * @return
	 * @throws ClassNotFoundException
	 * @throws SQLException
	 */
	public Connection getConnection() throws ClassNotFoundException, SQLException
	{
		//����JDBC����
		Class.forName("com.mysql.cj.jdbc.Driver");
		//���DB����
		con = DriverManager.getConnection("jdbc:mysql://localhost:3306/filemarket?useUnicode=true&characterEncoding=utf-8&serverTimezone=UTC&useSSL=false", "root", "123123");
		//��װSQL
		return con;
	}
	
	/**
	 * �����󶨣�ָ��SQL�����ÿ������ֵ��
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
	 * Oӳ�䣨��Զ��DB�е�һ����¼ת��Ϊ�����ڴ��е�һ������
	 * getColumnName��ȡ����sql�����field��ԭʼ���֡�
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
	 * �ͷ����ݿ�����
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
	 * ���findAll��SQL���
	 * @return
	 */
	public abstract String getSql_findAll();
	
	/**
	 * ���findAllIf��SQL���������ȡ�ض������µ����鼯
	 * @return
	 */
	public abstract String getSql_findAllIf();
	
	/**
	 * ���findIf��SQL���������ȡ�ض������µ����鼯
	 * @return
	 */
	public abstract String getSql_findIf();
	
	/**
	 * ���findById��SQL���
	 * @return
	 */
	public abstract String getSql_findById();
	
	/**
	 * ���findByIntId��SQL���
	 * @return
	 */
	public abstract String getSql_findByIntId();
	
	/**
	 * ���insert��SQL���
	 * @return
	 */
	public abstract String getSql_insert();
	
	/**
	 * ���벿������
	 * @return
	 */
	public abstract String getSql_insertRegister();
	
	/**
	 * ���modify��SQL���
	 * @return
	 */
	public abstract String getSql_modify();
	
	/**
	 * ���update��SQL���
	 * @return
	 */
	public abstract String getSql_update();
	
	/**
	 * ���delete��SQL���
	 * @return
	 */
	public abstract String getSql_delete();
	
	/**
	 * ��������ؼ���
	 * @return
	 */
	public abstract String getSql_Search();
	
	/**
	 * ����һ�����ݻ�ö������ݵ�sql���
	 * @return
	 */
	
	public abstract String getSql_findByParam();
	
	public abstract String getSql_findIfIndent();
	
	public abstract String getSql_modifyIndent();
	
	public abstract String getSql_findNumber();
	
}