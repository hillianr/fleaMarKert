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
		//ί��JSPҳ����ӻ���ʾ�������
	    request.setAttribute("user", list1.get(0));
	    request.getRequestDispatcher("pages/mypage.jsp").forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub

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
				 //�޸����ݿ��е�����
				 service.rePassword(password,account);
				 doGet(request, response);
			 }else{//���β�ͬ�����һ�����ҳ��
				 request.setAttribute("message", "�����������벻һ��");
				 request.getRequestDispatcher("../Newpassword.jsp").forward(request, response);
		}
	}
	}
}
