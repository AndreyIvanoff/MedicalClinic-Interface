/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package ru.hospital.filter;

import com.google.inject.Provider;
import com.google.inject.Singleton;
import java.io.IOException;
import javax.servlet.*;

/**
 *
 * @author a.yakischik
 */
@Singleton
public class LoginFilter implements Filter, Provider<InjectInfo> {

	private ThreadLocal<InjectInfo> container = new ThreadLocal<InjectInfo>();

	public void init(FilterConfig fc) throws ServletException {

	}

	//TODO как определимся с куками - написать нормально
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
		InjectInfo info = new InjectInfo();
		info.userId = 1L;
		container.set(info);
		chain.doFilter(request, response);
		container.remove();

	}

	public void destroy() {

	}

	public InjectInfo get() {
		return container.get();
	}
}
