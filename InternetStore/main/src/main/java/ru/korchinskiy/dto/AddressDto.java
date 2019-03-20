package ru.korchinskiy.dto;

import lombok.Data;
import ru.korchinskiy.enums.AddressType;

import javax.persistence.EnumType;
import javax.persistence.Enumerated;

@Data
public class AddressDto {
    private Long id;
    private String address;
    private AddressType addressType;
}
