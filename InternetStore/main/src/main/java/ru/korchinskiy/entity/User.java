package ru.korchinskiy.entity;

import lombok.Data;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Entity
@Table(name = "user")
@Data
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;
    private String lastname;
    private Date birthday;
    private String email;
    private String password;

    @Column(name = "phone_number")
    private String phoneNumber;

    @ManyToMany
    @JoinTable(
            name = "user_address",
            joinColumns = @JoinColumn(name = "user_id"),
            inverseJoinColumns = @JoinColumn(name = "address_id")
    )
    private List<Address> addresses = new ArrayList<>();

    @ManyToMany
    @JoinTable(
            name = "user_role",
            joinColumns = @JoinColumn(name = "user_id"),
            inverseJoinColumns = @JoinColumn(name = "role_id")
    )
    private List<Role> roles = new ArrayList<>();

    @Override
    public String toString() {
        return "User{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", lastname='" + lastname + '\'' +
                ", birthday=" + birthday +
                ", email='" + email + '\'' +
                ", password='" + password + '\'' +
                ", phoneNumber='" + phoneNumber + '\'' +
                ", addresses=" + addresses +
                ", roles=" + roles +
                '}';
    }
}
