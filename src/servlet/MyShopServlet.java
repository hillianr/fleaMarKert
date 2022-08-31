package servlet;

import java.io.IOException;
import java.util.List;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import service.GoodTableSevice;
import service.impl.GoodTableServiceImpl;
import until.utils.MapUtil;

/**
 * Servlet implementation class MyShopServlet
 */
@WebServlet("/MyShopServlet")
public class MyShopServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public MyShopServlet() {
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
		String name = (String)session.getAttribute("account");
	    GoodTableServiceImpl goodTableSevice = new GoodTableSevice();
	    List<Map<String, Object>> goodTable = MapUtil.InsertGoodTable(goodTableSevice.vaildateshop(name));
	    //委托JSP页面可视化显示结果数据
	    request.setAttribute("goodTable", goodTable);
	    request.getRequestDispatcher("pages/myshop.jsp").forward(request, response);

	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		String num = request.getParameter("num");
		String id = request.getParameter("id");
	    GoodTableServiceImpl goodTableSevice = new GoodTableSevice();
	    goodTableSevice.vaildateModify(new Object[] {num,id});
	}

}
