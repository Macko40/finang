package com.maykelmarrero.springboot.finangportal.controller;

import com.maykelmarrero.springboot.finangportal.service.UserService;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import org.springframework.security.core.Authentication;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;
import org.springframework.stereotype.Controller;

import java.io.IOException;

@Controller
public class CustomAuthenticationSuccessHandler implements AuthenticationSuccessHandler {

    private UserService userService;

    // Constructor
    public CustomAuthenticationSuccessHandler(UserService userService) {
        this.userService = userService;
    }


    @Override
    public void onAuthenticationSuccess(HttpServletRequest request, HttpServletResponse response, Authentication authentication) throws IOException, ServletException {
        System.out.println("In customAuthenticationSuccessHandler");
        String userName = authentication.getName();
        System.out.println("userName = " + userName);

        //now place in the session
        HttpSession session = request.getSession();
        session.setAttribute("user", userName);

        // forward to home page
        response.sendRedirect(request.getContextPath() + "/finangportal/admin");
    }
}
