package com.ef.golf.common.filter;

import org.springframework.web.filter.OncePerRequestFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * for wpfront
 * Created by Bart on 2016/11/15.
 * Date: 2016/11/15 10:14
 */
public class CrossFilter extends OncePerRequestFilter {

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
//        if (request.getHeader("Access-Control-Request-Method") != null && "OPTIONS".equals(request.getMethod())) {
//            response.addHeader("Access-Control-Allow-Origin", "*");
//            response.addHeader("Access-Control-Allow-Methods", "GET, POST, PUT, DELETE");
//            response.addHeader("Access-Control-Allow-Headers", "Content-Type");
//            response.addHeader("Access-Control-Max-Age", "1800");//30 min
//        }

        response.setHeader("Access-Control-Allow-Origin","*");
        // response.setHeader("Access-Control-Allow-Headers","X-Requested-With,application/x-www-form-urlencoded");
        response.setHeader("Access-Control-Allow-Headers","Content-Type, Access-Control-Allow-Headers, Authorization, X-Requested-With,X-Requested-With,application/x-www-form-urlencoded");
//        response.setHeader("Access-Control-Allow-Headers","Origin, X-Requested-With, Content-Type, Access-Token");
//        if (request.getMethod().equals("OPTIONS")) {
          //response.setStatus(HttpStatus.OK.value());
//            return;
//        }
        filterChain.doFilter(request, response);
    }
}
