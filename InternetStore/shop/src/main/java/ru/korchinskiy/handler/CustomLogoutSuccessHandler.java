package ru.korchinskiy.handler;

import org.springframework.security.core.Authentication;
import org.springframework.security.web.authentication.logout.LogoutSuccessHandler;
import org.springframework.stereotype.Component;

import javax.servlet.ServletException;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@Component
public class CustomLogoutSuccessHandler implements LogoutSuccessHandler {
    @Override
    public void onLogoutSuccess(HttpServletRequest request, HttpServletResponse response, Authentication auth)
            throws IOException, ServletException {
        Cookie cookieCart = new Cookie("cart", null);
        cookieCart.setMaxAge(0);
        cookieCart.setPath("/");
        cookieCart.setHttpOnly(true);
        response.addCookie(cookieCart);

        if (!request.getHeader("referer").contains("profile") && !request.getHeader("referer").contains("admin")) {
            response.sendRedirect(request.getHeader("referer"));
        } else {
            response.sendRedirect(request.getContextPath() + "/");
        }
    }
}
