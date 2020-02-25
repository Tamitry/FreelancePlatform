package by.tarlikovski.freelance.control.filters;


import javax.servlet.*;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class CookieFilter implements Filter {
    @Override
    public void init(final FilterConfig filterConfig)
            throws ServletException {

    }

    @Override
    public void doFilter(final ServletRequest servletRequest,
                         final ServletResponse servletResponse,
                         final FilterChain filterChain)
            throws IOException, ServletException {
        HttpServletResponse response = (HttpServletResponse) servletResponse;
        HttpServletRequest request = (HttpServletRequest) servletRequest;
        if (request.getParameter("locale") != null
                && !request.getParameter("locale").equals("")) {
            Cookie cookie = new Cookie("locale", request.getParameter("locale"));
            cookie.setMaxAge(30 * 60 * 60);
            response.addCookie(cookie);
            request.setAttribute("locale", request.getParameter("locale"));
        }
        filterChain.doFilter(request, response);
    }

    @Override
    public void destroy() {

    }
}
