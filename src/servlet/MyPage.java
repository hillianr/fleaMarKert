package servlet;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import service.UserService;
import service.impl.UserServiceImpl;
import until.utils.MapUtil;

/**
 * Servlet implementation class MyPage
 */
@WebServlet("/MyPage")
public class MyPage extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public MyPage() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		request.setCharacterEncoding("utf-8");
	    response.setCharacterEncoding("utf-8");
		HttpSession session = request.getSession();
		String account = (String)session.getAttribute("account");
//		Map<String,String[]> paramsOld = new HashMap<>();
// 		paramsOld.put("users_account", new String[] {account});
// 		Object[] insertRegister = MapUtil.getInputData(paramsOld);
		UserServiceImpl service = new UserService();
		List<Map<String, Object>> list1 = (List<Map<String, Object>>) service.findUser(account);
		//委托JSP页面可视化显示结果数据
	    request.setAttribute("user", list1.get(0));
	    request.getRequestDispatcher("pages/mypage.jsp").forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub

		// TODO Auto-generated method stub
		//处理乱码问题
	    request.setCharacterEncoding("utf-8");
	    response.setCharacterEncoding("utf-8");
	    //得到页面输入
	    String name = request.getParameter("username");
	    String account = request.getParameter("account");
	    String password = request.getParameter("pwd");
	    String repassword = request.getParameter("pwd1");
	    UserServiceImpl service = new UserService();
//	    HttpSession session = request.getSession();
		boolean flag = service.validateRegister(account);
		if(flag) {//账号输入正确
			//判断两次密码是否输入一样
			boolean flag2 = password.equals(repassword);
			if(flag2) {
				 //修改数据库中的密码
				 service.rePassword(password,account);
				 doGet(request, response);
			 }else{//两次不同还在找回密码页面
				 request.setAttribute("message", "两次密码输入不一致");
				 request.getRequestDispatcher("../Newpassword.jsp").forward(request, response);
		}
	}
	}
}
