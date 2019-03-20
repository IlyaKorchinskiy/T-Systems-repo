package ru.korchinskiy.entity;

import lombok.Data;
import ru.korchinskiy.enums.AddressType;

import javax.persistence.*;

@Entity
@Table(name = "address")
@Data
public class Address {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String address;

    @Enumerated(EnumType.STRING)
    @Column(name = "address_type")
    private AddressType addressType;
}
