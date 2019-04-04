package ru.korchinskiy.handler;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;
import org.springframework.stereotype.Component;
import ru.korchinskiy.service.UserService;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

@Component
public class CustomAuthenticationSuccessHandler implements AuthenticationSuccessHandler {
    private UserService userService;

    @Override
    public void onAuthenticationSuccess(HttpServletRequest request, HttpServletResponse response, Authentication auth)
            throws IOException, ServletException {
        String email = auth.getName();
        HttpSession session = request.getSession();
        session.setAttribute("user", userService.getUserByEmail(email));
        if (!request.getHeader("referer").contains("login")) {
            response.sendRedirect(request.getHeader("referer"));
        } else {
            response.sendRedirect(request.getContextPath() + "/");
        }
    }

    @Autowired
    public void setUserService(UserService userService) {
        this.userService = userService;
    }
}
