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
import service.UserService;
import service.impl.GoodTableServiceImpl;
import service.impl.UserServiceImpl;
import until.utils.MapUtil;

/**
 * Servlet implementation class ClassifyServlet
 */
@WebServlet("/ClassifyServlet")
public class ClassifyServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public ClassifyServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	    request.setCharacterEncoding("utf-8");
	    response.setCharacterEncoding("utf-8");
	    List<Map<String, Object>> goodTable = null;
	    String classifyId = request.getParameter("classifyId");
	    GoodTableServiceImpl goodTableSevice = new GoodTableSevice();
	    if(classifyId.equals("全新") || classifyId.equals("二手")) {
	    	goodTable = MapUtil.InsertGoodTable(goodTableSevice.vaildateUserIf(classifyId));
	    }else {
	    //获取该分类的商品数据
	    goodTable = MapUtil.InsertGoodTable(goodTableSevice.vaildateUserId(classifyId));
	    }
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
