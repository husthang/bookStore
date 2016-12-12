package bookStore.liuhang.web.servlet;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import bookStore.liuhang.domain.User;

/**
 * Created by liuhang on 2016/12/11.
 */
@WebServlet(name = "MyAccountServlet", urlPatterns = "/myAccount")
public class MyAccountServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws
            ServletException, IOException {
        doGet(request, response);
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws
            ServletException, IOException {
        User user = (User) request.getSession().getAttribute("userInSession");
        if (user == null) {
            /**
             * response.sendRedirect客户端跳转
             * request.getRequestDispatcher("/login.jsp").forward(request, response);服务端跳转
             */
            response.sendRedirect(request.getContextPath() + "/login.jsp");
        } else {
            //普通用户页面
            String path = "/modifyUserInfo.jsp";
            if ("admin".equals(user.getRole())) {
                //管理员页面
                path = "/admin/login/home.jsp";
            }
            request.getRequestDispatcher(path).forward(request, response);
        }
    }
}
