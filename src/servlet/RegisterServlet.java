package servlet;

import java.io.IOException;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import service.UserService;
import service.impl.UserServiceImpl;
import until.utils.MapUtil;

/**
 * Servlet implementation class RegisterServlet
 */
@WebServlet("/RegisterServlet")
public class RegisterServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public RegisterServlet() {
        super();
        // TODO Auto-generated constructor stub
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
	    //处理乱码问题
	    request.setCharacterEncoding("utf-8");
	    response.setCharacterEncoding("utf-8");
//	    获得输入框中的数据
	    Map<String,String[]> paramsOld = request.getParameterMap();
	    Object[] insertRegister = MapUtil.getInputData(paramsOld);
		UserServiceImpl service = new UserService();
//		判断该用户是否在数据库中
		boolean flag = service.validateRegister((String)insertRegister[0]);
		//将用户信息插入数据库中
		service.vaildateUserId(insertRegister);
		 if(flag) {
		      request.setAttribute("message", "该用户已注册");
		      request.getRequestDispatcher("register.jsp").forward(request, response);
		 }else {
		 		//判断两次密码是否输入一样
			 boolean flag2 = ((String)insertRegister[5]).equals((String)insertRegister[6]);
			 if(flag2) {
				 //相同进入登录页面登录
//				 Object param = service.validateRegister(params.values())
			      request.setAttribute("success", "注册成功");
			      request.getRequestDispatcher("login.jsp").forward(request, response);
			 }
	    }
	}

}
