package ru.korchinskiy.dto;

import lombok.Data;

import java.util.Date;
import java.util.List;

@Data
public class UserDto {
    private Long id;
    private String name;
    private String lastname;
    private Date birthday;
    private String email;
    private String password;
    private String phoneNumber;
    private List<AddressDto> addresses;
    private List<RoleDto> roles;
}
