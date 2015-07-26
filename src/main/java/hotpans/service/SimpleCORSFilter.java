package hotpans.service;
import java.io.IOException;
import java.util.Date;
import java.util.Iterator;
import java.util.Map;
import java.util.Set;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Component;

@Component
public class SimpleCORSFilter implements Filter {

	public void doFilter(ServletRequest req, ServletResponse res, FilterChain chain) throws IOException, ServletException {
		System.out.println("★filter:" + new Date());
	    HttpServletResponse response = (HttpServletResponse) res;
		response.setHeader("Access-Control-Allow-Origin", "*");
		response.setHeader("Access-Control-Allow-Methods", "POST, GET, OPTIONS, DELETE");
		response.setHeader("Access-Control-Max-Age", "3600");
		response.setHeader("Access-Control-Allow-Headers", "Origin, X-Requested-With, Content-Type, Accept");
		//response.setHeader("Access-Control-Allow-Headers", "x-requested-with");

		// RequestParameterの表示
		if(req == null){
		    System.out.println("req is null.");
		}else if(req.getParameterMap().size() == 0){
		    System.out.println("req size = 0");
		}
		Map map_par=req.getParameterMap();
		Set map_ite1 = map_par.keySet( );
		Iterator i = map_ite1.iterator( );
		while (i.hasNext()) {
		    String key = (String)i.next();
		    String par[]=(String[])map_par.get(key);
		    System.out.println(key + " : " + par[0]);
		}

		chain.doFilter(req, res);
	}

	public void init(FilterConfig filterConfig) {}

	public void destroy() {}

}