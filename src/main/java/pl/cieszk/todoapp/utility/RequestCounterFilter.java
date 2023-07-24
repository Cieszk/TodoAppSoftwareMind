package pl.cieszk.todoapp.utility;

import jakarta.servlet.*;
import jakarta.servlet.annotation.WebFilter;
import org.springframework.stereotype.Component;

import java.io.IOException;
@WebFilter("/*")
@Component
public class RequestCounterFilter implements Filter {
    private int requestCount = 0;
    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        requestCount++;

        filterChain.doFilter(servletRequest, servletResponse);
    }

    public int getRequestCount() {
        return requestCount;
    }
}
