package com.veiasai;

import com.veiasai.pojo.JWT;
import com.veiasai.pojo.Result;
import org.apache.catalina.filters.RemoteIpFilter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.client.RestTemplate;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@Configuration
public class WebConfig {
    @Bean
    public RemoteIpFilter remoteIpFilter() {
        return new RemoteIpFilter();
    }

    @Bean
    public FilterRegistrationBean testFilterRegistration() {
        FilterRegistrationBean registration = new FilterRegistrationBean();
        registration.setFilter(new MyFilter());
        registration.addUrlPatterns("/test");
        registration.addInitParameter("paramName", "paramValue");
        registration.setName("MyFilter");
        registration.setOrder(1);
        return registration;
    }

    public class MyFilter implements Filter {
        @Override
        public void destroy() {

        }

        @Override
        public void doFilter(ServletRequest srequest, ServletResponse sresponse, FilterChain filterChain)
                throws IOException, ServletException {
            HttpServletRequest request = (HttpServletRequest) srequest;
            HttpServletResponse response = (HttpServletResponse) sresponse;
            String token = request.getHeader("token");
            if (token == null)
            {
                response.sendError(HttpServletResponse.SC_FORBIDDEN, "need token");
            }
            else
            {
                JWT jwt = new JWT();
                jwt.setJwt(token);
                RestTemplate template = new RestTemplate();
                Result result = template.postForObject("http://localhost:8081/testtoken", jwt, Result.class);
                if (result.getCode() == 200)
                {
                    filterChain.doFilter(srequest, sresponse);
                }
                else{
                    response.sendError(HttpServletResponse.SC_FORBIDDEN, "invalid token");
                }
            }
        }

        @Override
        public void init(FilterConfig arg0) throws ServletException {
            // TODO Auto-generated method stub
        }
    }
}
