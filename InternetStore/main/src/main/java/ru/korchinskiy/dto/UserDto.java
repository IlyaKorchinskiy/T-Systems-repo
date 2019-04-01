package ru.korchinskiy.dto;

import lombok.Data;
import ru.korchinskiy.validation.PasswordMatches;
import ru.korchinskiy.validation.PhoneNumber;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.sql.Date;
import java.time.Instant;
import java.time.LocalDate;
import java.util.List;

@Data
@PasswordMatches
public class UserDto {
    @NotNull
    private Long id;

    @Size(min = 2, message = "Name should be min 2 characters")
    private String name;

    @Size(min = 2, message = "Last name should be min 2 characters")
    private String lastname;

    @NotNull
    private String birthday;

    @Email
    @NotNull
    private String email;

    @Size(min = 6, message = "Password should be min 6 symbols")
    private String password;

    @Size(min = 6, message = "Password should be min 6 symbols")
    private String matchingPassword;

    @PhoneNumber
    private String phoneNumber;

    private List<AddressDto> addresses;
    private List<RoleDto> roles;
    private List<OrderDto> orders;
}
