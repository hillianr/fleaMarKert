package servlet;

import java.io.IOException;
import java.util.List;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import service.GoodTableSevice;
import service.impl.GoodTableServiceImpl;
import until.utils.MapUtil;

/**
 * Servlet implementation class SearchFunction
 */
@WebServlet("/SearchFunction")
public class SearchFunction extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public SearchFunction() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	    request.setCharacterEncoding("utf-8");
	    response.setCharacterEncoding("utf-8");
	    String param = request.getParameter("param");
	    param = "["+param+"]";
//	    param = "%"+param+"%";
	    GoodTableServiceImpl goodTableSevice = new GoodTableSevice();
	    //获取该分类的商品数据
	    List<Map<String, Object>> goodTable = MapUtil.InsertGoodTable(goodTableSevice.vaildateSearch(param));
	    //委托JSP页面可视化显示结果数据
	    request.setAttribute("goodTable", goodTable);
	    request.getRequestDispatcher("pages/classification.jsp").forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub

	}

}
