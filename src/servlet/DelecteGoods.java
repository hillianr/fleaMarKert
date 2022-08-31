package servlet;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import service.GoodTableSevice;
import service.impl.GoodTableServiceImpl;

/**
 * Servlet implementation class DelecteGoods
 */
@WebServlet("/DelecteGoods")
public class DelecteGoods extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public DelecteGoods() {
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
	    String goodTable = request.getParameter("params");
	    int goodId = Integer.parseInt(goodTable);
		GoodTableServiceImpl goodTableSevice = new GoodTableSevice();
		goodTableSevice.vaildateDelete(new Object[] {goodId});
	    //委托JSP页面可视化显示结果数据
	    request.getRequestDispatcher("MyShopServlet").forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
