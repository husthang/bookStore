package bookStore.liuhang.web.servlet;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import bookStore.liuhang.domain.User;
import bookStore.liuhang.exception.UserException;
import bookStore.liuhang.service.UserService;
import bookStore.liuhang.util.MD5Util;

/**
 * Created by liuhang on 2016/12/8.
 */
@WebServlet(name = "LoginServlet", urlPatterns = "/login")
public class LoginServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws
            ServletException, IOException {
        doGet(request, response);
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws
            ServletException, IOException {
        String username = request.getParameter("username");
        String password = request.getParameter("password");
        password= MD5Util.md5(password);//md5 加密
        UserService userService = new UserService();
        try {
            String path = "/modifyUserInfo.jsp";
            User user = userService.findUserByUsernameAndPassword(username, password);
            String autoLogin =request.getParameter("autoLogin");
            Cookie cookie=new Cookie("userInCookie",user.getUsername()+"&"+user.getPassword());
            if(autoLogin!=null){
                cookie.setMaxAge(60*60*24*7);
            }else{
                cookie.setMaxAge(0);
            }
            response.addCookie(cookie);
            if (user.getRole().equals("admin")) {
                path = "/admin/login/home.jsp";//转到后台管理页面
            }
            request.getSession().setAttribute("userInSession", user);
            request.getRequestDispatcher(path).forward(request, response);
        } catch (UserException e) {
            request.setAttribute("loginMessageInRequest", e.getMessage());
            request.getRequestDispatcher("/login.jsp").forward(request, response);
        }

    }
}
