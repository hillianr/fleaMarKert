package servlet;

import java.io.IOException;
import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import service.UserService;
import service.impl.UserServiceImpl;

/**
 * Servlet implementation class FindPasswordServlet
 */
@WebServlet("/FindPasswordServlet")
public class FindPasswordServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public FindPasswordServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see Servlet#init(ServletConfig)
	 */
	public void init(ServletConfig config) throws ServletException {
		// TODO Auto-generated method stub
	}

	/**
	 * @see Servlet#destroy()
	 */
	public void destroy() {
		// TODO Auto-generated method stub
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
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
				 //相同进入登录页面登录
				 request.setAttribute("message", "修改成功");
				 //修改数据库中的密码
				 service.rePassword(password,account);
				 request.getRequestDispatcher("login.jsp").forward(request, response);
			 }else{//两次不同还在找回密码页面
				 request.setAttribute("message", "两次密码输入错误");
				 request.getRequestDispatcher("findpassword.jsp").forward(request, response);
		}
		}else {//用户不存在进入注册页面
			request.setAttribute("message", "该用户不存在");
			request.getRequestDispatcher("register.jsp").forward(request, response);
		}
	}

}