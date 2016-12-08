package bookStore.liuhang.web.servlet;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import bookStore.liuhang.domain.User;
import bookStore.liuhang.exception.UserException;
import bookStore.liuhang.service.UserService;

/**
 * Created by liuhang on 2016/12/8.
 */
@WebServlet(name = "LoginServlet",urlPatterns = "/loginServlet")
public class LoginServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doGet(request,response);
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String username=request.getParameter("username");
        String password=request.getParameter("password");
        UserService userService=new UserService();
        try{
            String path="/index.jsp";
            User user=userService.userLogin(username,password);
            request.getSession().setAttribute("user",user);
            request.getRequestDispatcher(path).forward(request,response);
        }catch (UserException e){
            e.printStackTrace();
            request.setAttribute("user_msg",e.getMessage());
            request.getRequestDispatcher("/login.jsp").forward(request,response);
        }

    }
}
