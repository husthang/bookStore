<%@ page import="bookStore.liuhang.domain.User" %>
<%@ page language="java" pageEncoding="UTF-8" %>
<div id="divhead">
    <table cellspacing="0" class="headtable">
        <tr>
            <td><a href="index.jsp"><img src="images/logo.png"
                                         width="95" height="30" border="0"/> </a></td>
            <td style="text-align:right">
                <img src="images/cart.gif" width="26" height="23" style="margin-bottom:-4px"/>&nbsp;
                <a href="cart.jsp">购物车</a> |
                <a href="#">帮助中心</a> |
                <a href="${pageContext.request.contextPath}/myAccount">
                    <% User user = (User) request.getSession().getAttribute("userInSession");
                        if (user == null) {
                            out.println("请登录");
                        } else {
                            out.print("欢迎您:" + user.getUsername());
                        }
                    %>
                </a>
                | <a href="register.jsp">新用户注册</a></td>
        </tr>
    </table>
</div>