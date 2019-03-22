package ru.korchinskiy.dto;

import lombok.Data;
import ru.korchinskiy.enums.AddressType;

@Data
public class AddressDto {
    private Long id;
    private String address;
    private AddressType addressType;
}
