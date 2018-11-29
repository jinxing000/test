package com.ef.golf.core.structure.filter;

import com.ef.golf.util.Tools;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletRequestWrapper;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.io.UnsupportedEncodingException;

public class CharacterEncodingFilter implements Filter {

	private String defaultEncoding = "utf-8";
	private FilterConfig config = null;

	class sword extends HttpServletRequestWrapper {

		public sword(HttpServletRequest request) {
			super(request);
		}

		@Override
		public String getParameter(String name) {
			name = super.getParameter(name);
			//System.out.println("-------------------------------super.getMethod()="+super.getMethod()+"     -------------- name="+ name);
			if (Tools.notEmpty(name) == true) {
				try {
					return new String(name.getBytes("iso8859-1"), "utf-8");
				} catch (UnsupportedEncodingException e) {
					e.printStackTrace();
				}
				return null;
			}
			return null;
		}

	}

	public void init(FilterConfig filterConfig) throws ServletException {
		config = filterConfig;
	}

	public void doFilter(ServletRequest request, ServletResponse response,
			FilterChain chain) throws IOException, ServletException {
		
		HttpServletRequest req = (HttpServletRequest) request;
		HttpServletResponse resp = (HttpServletResponse) response;
		HttpSession session = req.getSession();
		// 不过滤的uri
		String[] notFilter = new String[] { "login.jsp", "getCode.do", "login.do" ,"upload.do","bidFinish.do","bidPlan.do"};
		// 过滤的uri
		String[] yesFilter = new String[] { "manage","userManage","front/firms.html","front/user.html"};	
		// 请求的uri
		String uri = req.getRequestURI();
		boolean doFilter = false;
//		boolean dootherFilter = false;
		//boolean doFilter = false;
		for (String yes : yesFilter) {
			if (uri.indexOf(yes) != -1) {
				doFilter = true;
			}
		}
		for (String not : notFilter) {
			if (uri.indexOf(not) != -1) {
				doFilter = false;
			}
		}


		req.setCharacterEncoding("utf-8");
		String charset = config.getInitParameter("charset");
		if (charset == null) {
			charset = defaultEncoding;
		}
		response.setCharacterEncoding(charset);
		response.setContentType("text/html;charset=" + charset);
		
		chain.doFilter(request, response);
	}

	public void destroy() {

	}

}
