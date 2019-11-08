package com.iweb.filter;

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

import com.iweb.entity.Use;

@WebFilter("/*")
public class Check implements Filter {

	public void destroy() {
	}

	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
			throws IOException, ServletException {
		// 判断请求的路径是否是login.html
		HttpServletRequest httpRequest = (HttpServletRequest) request;

		String str = httpRequest.getRequestURL().toString();

		// 解析路径，获取URL中最后一部分(文件名称)
		String[] strs = str.split("/");
		String path = strs[strs.length - 1];
		System.out.println(path);
		if (path.equals("login.jsp") || path.equals("LoginServlet") || path.equals("jquery-1.10.2.js")
				|| path.equals("RegisteredServlet")) {
			// 如果是则直接放行，如果不是则判断用户是否登录
			chain.doFilter(request, response);
		} else {
			// 如果用户登录则放行，如果未登录则返回错误信息
			// 从session取出username判断是否为空
			Object use = httpRequest.getSession().getAttribute("use");
			// 判断username==null
			if (use == null) {
				((HttpServletResponse) response).sendRedirect("/TeachingManagementSystem/page/login.jsp");
			} else {
				if (path.equals("index.jsp")) {
					if (((Use) use).getPosition().equals("学生")) {
						((HttpServletResponse) response).sendRedirect("/TeachingManagementSystem/page/indexstu.jsp");
					} else {
						chain.doFilter(request, response);
					}
				} else {
					chain.doFilter(request, response);
				}
			}
		}
	}

	@Override
	public void init(FilterConfig filterConfig) throws ServletException {
		// TODO 自动生成的方法存根

	}
}
