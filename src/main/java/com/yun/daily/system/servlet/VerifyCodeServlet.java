package com.yun.daily.system.servlet;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

@WebServlet(urlPatterns = "/verifyCode/*")
public class VerifyCodeServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;


    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        System.out.println("-----验证码------------------------------");
        response.setContentType("image/jpeg");
        response.setHeader("Pragma", "no-cache");
        response.setHeader("Cache-Control", "no-cache");
        response.setDateHeader("Expires", 0);
        ValidateCode vCode = new ValidateCode(100,30,4,100);
        HttpSession session = request.getSession();
        session.removeAttribute("validateCode");
        vCode.write(response.getOutputStream());
        session.setAttribute("validateCode", vCode.getCode());
        System.out.println("-----验证码--"+vCode.getCode()+"----------------------------");
        vCode.write(response.getOutputStream());
    }
}
