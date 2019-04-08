package ru.korchinskiy.dto;

import lombok.Data;
import ru.korchinskiy.validation.PasswordMatches;
import ru.korchinskiy.validation.PhoneNumber;

import javax.validation.constraints.Email;
import javax.validation.constraints.Size;
import java.util.List;

@Data
@PasswordMatches
public class UserDto {
    private Long id;

    @Size(min = 2, message = "name should be min 2 characters")
    private String name;

    @Size(min = 2, message = "last name should be min 2 characters")
    private String lastname;

    @Size(min = 6, message = "should not be empty")
    private String birthday;

    @Email(message = "incorrect e-mail")
    @Size(min = 6, message = "should not be empty")
    private String email;

    @Size(min = 6, message = "password should be min 6 symbols")
    private String password;

    @Size(min = 6, message = "password should be min 6 symbols")
    private String matchingPassword;

    @PhoneNumber
    private String phoneNumber;

    private List<AddressDto> addresses;
    private List<RoleDto> roles;
    private List<OrderDto> orders;
}
