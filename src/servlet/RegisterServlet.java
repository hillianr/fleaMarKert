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
	    //������������
	    request.setCharacterEncoding("utf-8");
	    response.setCharacterEncoding("utf-8");
//	    ���������е�����
	    Map<String,String[]> paramsOld = request.getParameterMap();
	    Object[] insertRegister = MapUtil.getInputData(paramsOld);
		UserServiceImpl service = new UserService();
//		�жϸ��û��Ƿ������ݿ���
		boolean flag = service.validateRegister((String)insertRegister[0]);
		//���û���Ϣ�������ݿ���
		service.vaildateUserId(insertRegister);
		 if(flag) {
		      request.setAttribute("message", "���û���ע��");
		      request.getRequestDispatcher("register.jsp").forward(request, response);
		 }else {
		 		//�ж����������Ƿ�����һ��
			 boolean flag2 = ((String)insertRegister[5]).equals((String)insertRegister[6]);
			 if(flag2) {
				 //��ͬ�����¼ҳ���¼
//				 Object param = service.validateRegister(params.values())
			      request.setAttribute("success", "ע��ɹ�");
			      request.getRequestDispatcher("login.jsp").forward(request, response);
			 }
	    }
	}

}
