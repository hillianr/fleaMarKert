package filter;

import java.io.IOException;
import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 * Servlet Filter implementation class LoginFilter
 */
@WebFilter("/LoginFilter")
public class LoginFilter implements Filter {

    /**
     * Default constructor. 
     */
    public LoginFilter() {
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see Filter#destroy()
	 */
	public void destroy() {
		// TODO Auto-generated method stub
	}

	/**
	 * @see Filter#doFilter(ServletRequest, ServletResponse, FilterChain)
	 */
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
		 // TODO Auto-generated method stub
	    // place your code here
	    HttpServletRequest req = (HttpServletRequest) request;
	      HttpServletResponse resp = (HttpServletResponse) response;
	      HttpSession session =  req.getSession();
	      //��ȡ����·��
	        String path = req.getRequestURI();
	        //��ȡsession����Ϊ�жϵ��ֶ�
	        String account = (String)session.getAttribute("account");
	      //�ж������ ·�����Ƿ������ ��¼ҳ�������
	        //��������ˣ���ô������ ����ִ�в���
	        if (path.indexOf("/pages/login.jsp") > -1) {
	            chain.doFilter(req, resp);
	        } else {
	            //�粻��������ô��Ҫ�ж� session�з��б�־�����û�б�־����ô��������������ȥ��¼����ִ֮�в�����
	            if (account == null || "".equals(account)) {
	                resp.sendRedirect("/pages/login.jsp");
	            } else {
	              chain.doFilter(req, resp);
	            }
	        }
	}

	/**
	 * @see Filter#init(FilterConfig)
	 */
	public void init(FilterConfig fConfig) throws ServletException {
		// TODO Auto-generated method stub
	}

}
