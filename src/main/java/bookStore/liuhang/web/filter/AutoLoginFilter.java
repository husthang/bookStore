package bookStore.liuhang.web.filter;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import bookStore.liuhang.domain.User;
import bookStore.liuhang.exception.UserException;
import bookStore.liuhang.service.UserService;

/**
 * Created by liuhang on 2016/12/14.
 */
@WebFilter(filterName = "AutoLoginFilter", urlPatterns = "/*")
public class AutoLoginFilter implements Filter {
    public void destroy() {
    }

    public void doFilter(ServletRequest req, ServletResponse resp, FilterChain chain) throws
            ServletException, IOException {
        HttpServletRequest request = (HttpServletRequest) req;
        HttpServletResponse response = (HttpServletResponse) resp;
        String uri = request.getRequestURI();
        String path = request.getContextPath();
        path = uri.substring(path.length());

        if (!(path.equals("/login.jsp") || path.equals("/login")||path.equals("/myAccount"))) {
            User user = (User) request.getSession().getAttribute("userInSession");
            if (user == null) {
                Cookie[] cookies = request.getCookies();
                String username = "";
                String password = "";
                for (Cookie cookie : cookies) {
                    if (cookie.getName().equals("userInCookie")) {
                        String value = cookie.getValue();
                        String[] values = value.split("&");
                        username = values[0];
                        password = values[1];
                    }
                }
                UserService userService = new UserService();
                try {
                    User user1 = userService.findUserByUsernameAndPassword(username, password);
                    request.getSession().setAttribute("userInSession", user1);
                } catch (UserException e) {
                    //没有找到匹配的,,直接放行
                }
            }
        }

        chain.doFilter(request, response);
    }

    public void init(FilterConfig config) throws ServletException {

    }

}
