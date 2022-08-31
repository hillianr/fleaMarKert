package servlet;

import java.io.IOException;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import service.IndentService;
import service.UserService;
import service.impl.IndentServiceImpl;
import service.impl.UserServiceImpl;
import until.utils.MapUtil;

/**
 * Servlet implementation class ShopcarServlet
 */
@WebServlet("/ShopcarServlet")
public class ShopcarServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public ShopcarServlet() {
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
	    IndentServiceImpl goodTableService = new IndentService();
	    HttpSession session = request.getSession();
	    String account = (String)session.getAttribute("account");
	    //获取展示的数据
	    List<Map<String, Object>> indent_good = goodTableService.vaildateindent(account);
	  //委托JSP页面可视化显示结果数据
	    request.setAttribute("indent_good", indent_good);
	    request.getRequestDispatcher("pages/shopcar.jsp").forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		request.setCharacterEncoding("utf-8");
	    response.setCharacterEncoding("utf-8");
		HttpSession session = request.getSession();
	    String account = (String)session.getAttribute("account");
	    
	    IndentServiceImpl service = new IndentService();
	    
	    String rem = (String)request.getParameter("remove");
	    String id1 = request.getParameter("indent_id");
	    String number = request.getParameter("num");
	    String id = request.getParameter("id");
	    
    	Map<String,String[]> paramsOld3 = new HashMap<>();
    	paramsOld3.put("indent_number", new String[] {number});
    	paramsOld3.put("users_account", new String[] {account});
	    paramsOld3.put("good_id", new String[] {id});
	    Object[] insertRegister3 = MapUtil.getInputData(paramsOld3);
    	service.update_indent_shop(insertRegister3);
    	request.setAttribute("params", id);	    
	    System.out.println(id);
	    if(rem != null) {
		    service.reindent(id1);    
//		    request.getRequestDispatcher("ShopcarServlet").forward(request, response);
	    }
	    doGet(request, response);
	}
}

