/**
 * 
 */
package dao.users;

import java.sql.SQLException;
import java.util.Map;
/**
 * @author ╡Я╡Я
 *
 */
public class UsersDaoTest {

	private static UsersDao usersDAO = new UsersDao();
	/**
	 * @param args
	 */
	public static void main(String[] args) 
	{
		try {
//			findAllTest();
//			findByIdTest();
			insertTest();

		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally {
			try {
				usersDAO.release();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}

	}
	private static void findAllTest() throws ClassNotFoundException, SQLException
	{
		System.out.println(usersDAO.findAll().toString());
	}
	
	private static void findByIdTest() throws ClassNotFoundException, SQLException{
		Map<String, Object> users = usersDAO.findById("19380320211");
		System.out.println(users);
	}
	
	private static void insertTest() throws ClassNotFoundException, SQLException{
		usersDAO.insertRegister(new Object[] {"19380320206","уе╫Ю","D5-3063","000000","4897564567","yfhgiud"});
	}
}
