package ru.korchinskiy.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import ru.korchinskiy.dto.RoleDto;
import ru.korchinskiy.dto.UserDto;
import ru.korchinskiy.service.UserService;

import java.util.ArrayList;
import java.util.List;

@Service
public class UserDetailsServiceImpl implements UserDetailsService {
    private UserService userService;

    @Override
    public UserDetails loadUserByUsername(String email) {
        UserDto userDto = userService.getUserByEmail(email);
        List<RoleDto> roles = userDto.getRoles();
        List<GrantedAuthority> grantList = new ArrayList<>();
        if (roles != null) {
            for (RoleDto role : roles) {
                grantList.add(new SimpleGrantedAuthority(role.getRole()));
            }
        }
        return new User(userDto.getEmail(), userDto.getPassword(), grantList);
    }

    @Autowired
    public void setUserService(UserService userService) {
        this.userService = userService;
    }
}
