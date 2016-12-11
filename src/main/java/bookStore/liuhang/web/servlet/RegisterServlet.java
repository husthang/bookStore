package bookStore.liuhang.web.servlet;


import org.apache.commons.beanutils.BeanUtils;

import java.io.IOException;
import java.util.UUID;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import bookStore.liuhang.domain.User;
import bookStore.liuhang.exception.UserException;
import bookStore.liuhang.service.UserService;

/**
 * Created by liuhang on 2016/12/10.
 */
@WebServlet(name = "RegisterServlet",urlPatterns = "/register")
public class RegisterServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doGet(request,response);
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setCharacterEncoding("UTF-8");
        String checkCodeSession = (String) request.getSession().getAttribute("checkCodeInSession");
        String checkCodeJsp = request.getParameter("checkCode");
        if(!checkCodeJsp.equals(checkCodeSession)){
            request.setAttribute("checkCodeMessage","验证码错误");
            request.getRequestDispatcher("/register.jsp").forward(request,response);
            return;
        }
        User user=new User();
        try{
            /**
             * UUID:Universally Unique Identifier 通用唯一识别码
             * BeanUtils 工具类,操作bean装配
             */
            BeanUtils.populate(user,request.getParameterMap());
            user.setActiveCode(UUID.randomUUID().toString());
            UserService userService=new UserService();
            userService.userRegister(user);
            request.getRequestDispatcher("/registerSuccess.jsp").forward(request,response);
        } catch (UserException e) {
            request.setAttribute("userMessage",e.getMessage());
            request.getRequestDispatcher("/register.jsp").forward(request,response);
            return;
        } catch (Exception e) {
            e.printStackTrace();
        }

    }
}
