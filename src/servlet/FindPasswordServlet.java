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
		//������������
	    request.setCharacterEncoding("utf-8");
	    response.setCharacterEncoding("utf-8");
	    //�õ�ҳ������
	    String name = request.getParameter("username");
	    String account = request.getParameter("account");
	    String password = request.getParameter("pwd");
	    String repassword = request.getParameter("pwd1");
	    UserServiceImpl service = new UserService();
//	    HttpSession session = request.getSession();
		boolean flag = service.validateRegister(account);
		if(flag) {//�˺�������ȷ
			//�ж����������Ƿ�����һ��
			boolean flag2 = password.equals(repassword);
			if(flag2) {
				 //��ͬ�����¼ҳ���¼
				 request.setAttribute("message", "�޸ĳɹ�");
				 //�޸����ݿ��е�����
				 service.rePassword(password,account);
				 request.getRequestDispatcher("login.jsp").forward(request, response);
			 }else{//���β�ͬ�����һ�����ҳ��
				 request.setAttribute("message", "���������������");
				 request.getRequestDispatcher("findpassword.jsp").forward(request, response);
		}
		}else {//�û������ڽ���ע��ҳ��
			request.setAttribute("message", "���û�������");
			request.getRequestDispatcher("register.jsp").forward(request, response);
		}
	}

}