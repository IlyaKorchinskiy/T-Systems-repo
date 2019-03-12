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

import java.util.HashSet;
import java.util.Set;

@Service
public class UserDetailsServiceImpl implements UserDetailsService {
    private UserService userService;

    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        UserDto userDto = userService.getUserByEmail(email);
        if (userDto == null)
            throw new UsernameNotFoundException("Пользователь с почтой" + email + " не найден");
        Set<RoleDto> roles = userDto.getRoles();
        Set<GrantedAuthority> grantList = new HashSet<>();
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
