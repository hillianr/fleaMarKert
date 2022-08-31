package servlet;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.servlet.http.Part;

import dao.goodtable.GoodTableImpl;
import service.GoodTableSevice;
import service.UserService;
import service.impl.GoodTableServiceImpl;
import service.impl.UserServiceImpl;
import until.utils.MapUtil;

/**
 * �ϴ���Ʒ
 * Servlet implementation class uploadGoods
 */
@WebServlet("/UploadGoods")
//�����ļ�ʱ���ע��
@MultipartConfig(location = "D:\\code\\fleaMarket\\WebContent\\clientImgs")
public class UploadGoods extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private static String account = null;
	private static String goodstime = "";
	private static String upimg = "";
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public UploadGoods() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("utf-8");
	    response.setCharacterEncoding("utf-8");
	    String goodTable = request.getParameter("params");
	    int goodId = Integer.parseInt(goodTable);
	    GoodTableServiceImpl goodTableSevice = new GoodTableSevice();
	    Map<String, Object> goods = goodTableSevice.vaildateByIntid(goodId);
	    //ί��JSPҳ����ӻ���ʾ�������  
	    request.setAttribute("goodTable", goods);
	    request.getRequestDispatcher("pages/updateGoods.jsp").forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("utf-8");
	    response.setCharacterEncoding("utf-8");
	    String imgPath = "";
		HttpSession session = request.getSession();
		account = (String)session.getAttribute("account");
		Map<String,String[]> paramsOld = new LinkedHashMap<>(request.getParameterMap());
		upimg = request.getParameter("upimg");
		boolean isFlag = false;
		if(upimg.isEmpty()) {
			isFlag = true;
		}
		GoodTableServiceImpl service = new GoodTableSevice();
		
//	    �����ļ�
	    for(Part part : request.getParts()) {
	    	if (part.getName().startsWith("photo")) {
	    		imgPath = getFileName(part);
//		    ����part.write���������ļ�
	    		if(!imgPath.isEmpty()) {
	    			part.write(imgPath);
	    		}
	    	}
	    }
//	    ��ȡ�������ݲ���list
	    paramsOld.put("upimg", new String[] {upimg});
//	    ���û�������Ʒ�������ݿ�
//	    �ж��Ƿ�Ϊ������Ʒ
	    if(isFlag) {
	    	paramsOld.put("goodstime", new String[] {goodstime});
	    	paramsOld.put("account", new String[] {account});
	    	Object[] insertRegister = MapUtil.getInputData(paramsOld);
	    	service.vaildateInsert(insertRegister);
	    }else {
	    	paramsOld.put("goodsid", new String[] {goodstime});
	    	paramsOld.put("goodstime", new String[] {request.getParameter("goodsid")});
	    	Object[] insertRegister = MapUtil.getInputData(paramsOld);
	    	service.vaildateupdate(insertRegister);
	    }
	    response.sendRedirect(request.getContextPath()+"/MyShopServlet");
	}
	    
     private String getFileName(Part part) {
//  	    ��ȡ�����ļ��� ��ȡ����ͷ����Ϣ
     	//form-data; name="photo1"; filename="9F2F249A8DF95DAA09C994C9754A9F40.jpg"
 	        String head = part.getHeader("Content-Disposition");
 	        String imgPath = "";
// 	        �ж��Ƿ���ͼƬ����
 	        if(!head.substring(head.indexOf("filename=\"") + 10,head.lastIndexOf("\"")).isEmpty()){
 	        	String fileName = head.substring(head.indexOf("name=\"") + 6,head.lastIndexOf("\";"));
// 			��ȡ��ǰϵͳ����
 	        	String timeString=new SimpleDateFormat("yyyy-MM-ddHHmmss").format(new Date());
 	        	goodstime = timeString.substring(0,10);
 	        	
// 	    	ͼƬ����·��
 	        	imgPath = account+fileName + timeString+".jpg";
 	        	int num = Integer.parseInt(fileName.substring(5,6));
 	        	if(upimg.contains(imgPath)) {
 	        		System.out.println(imgPath);
 	        		int index = upimg.indexOf("photo"+num);
 	        		upimg = upimg.replace(upimg.substring(index-11,index+26),imgPath);
 	        	}else {
 	        		upimg += imgPath;
 	        		System.out.println(upimg);
 	        	}
 	        }
 	         return imgPath;
	   }
}
