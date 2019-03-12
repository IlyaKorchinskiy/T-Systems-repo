package ru.korchinskiy.dto;

import lombok.Data;

import java.util.Date;
import java.util.HashSet;
import java.util.Set;

@Data
public class UserDto {
    private Long id;
    private String name;
    private String lastname;
    private Date birthday;
    private String email;
    private String password;
    private String phoneNumber;
    private Set<RoleDto> roles;

}
