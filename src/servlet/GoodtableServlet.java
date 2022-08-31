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

import service.GoodTableSevice;
import service.IndentService;
import service.impl.GoodTableServiceImpl;
import service.impl.IndentServiceImpl;
import until.utils.MapUtil;

/**
 * Servlet implementation class GoodtableServlet
 */
@WebServlet("/GoodtableServlet")
public class GoodtableServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public GoodtableServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("utf-8");
	    response.setCharacterEncoding("utf-8");
	    GoodTableServiceImpl goodTableSevice = new GoodTableSevice();
	    //获取该分类的商品数据
	    List<Map<String, Object>> goodTable = MapUtil.InsertGoodTable(goodTableSevice.vaildateAll());
	    //委托JSP页面可视化显示结果数据
	    request.setAttribute("goodTable", goodTable);
	    request.getRequestDispatcher("pages/classification.jsp").forward(request, response);
	
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		//处理商品详情的添加购物车按钮
		request.setCharacterEncoding("utf-8");
	    response.setCharacterEncoding("utf-8");
	    
	    HttpSession session = request.getSession();
	    String account = (String)session.getAttribute("account"); 
	    String id = request.getParameter("goodtable_id");
		String acnumber = request.getParameter("acnumber");
		IndentServiceImpl service = new IndentService();
		
//	    Map<String,String[]> paramsOld1 = new HashMap<>();
//	    paramsOld1.put("users_account", new String[] {account});
//	    paramsOld1.put("goodTable_id", new String[] {id});
//	    Object[] insertRegister1 = MapUtil.getInputData(paramsOld1);
		List<Map<String, Object>> list1 = service.find_indent(account,id);
	    if(list1.isEmpty()) {
	    	System.out.println(2);
	    	Map<String,String[]> paramsOld2 = new HashMap<>();
	 	    paramsOld2.put("indent_number", new String[] {acnumber});
	 		paramsOld2.put("users_account", new String[] {account});
	 	    paramsOld2.put("good_id", new String[] {id});
//	 	    for(String key : paramsOld.keySet()) {
//	 	    	System.out.println("key"+key+":"+paramsOld.get(key)[0]);
//	 	    }
	 	    Object[] insertRegister = MapUtil.getInputData(paramsOld2);
//	 	    for(Object x:insertRegister) {
//	 	    	System.out.println(x);
//	 	    }
	 		
	 		service.insert_indent(insertRegister);
	 		request.setAttribute("params", id);
	 		request.getRequestDispatcher("ShopcarServlet").forward(request, response);
	    }else {
	    	List<Map<String, Object>> list2 = service.find_indent(account,id);
	    	String flag = (String) list2.get(0).get("indent_really");
	    	if(flag.equals("1")) {
		    	Map<String,String[]> paramsOld3 = new HashMap<>();
		    	paramsOld3.put("users_account", new String[] {account});
			    paramsOld3.put("good_id", new String[] {id});
			    Object[] insertRegister3 = MapUtil.getInputData(paramsOld3);
		    	service.update_indent(insertRegister3);
		    	request.setAttribute("params", id);
		    	request.getRequestDispatcher("ShopcarServlet").forward(request, response);
		    	
		    }else if(flag.equals("0")){
		    	System.out.println(3);
		    	int id1 = Integer.parseInt(id);
		    	service.update_really(id1);
		    	request.getRequestDispatcher("ShopcarServlet").forward(request, response);
		    }
		   
	    }

	}

}
