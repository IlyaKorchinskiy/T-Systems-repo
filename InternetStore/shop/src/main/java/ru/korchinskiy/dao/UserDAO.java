package ru.korchinskiy.dao;

import ru.korchinskiy.entity.User;

public interface UserDAO {
    User getUserById(Long id);

    User getUserByEmail(String email);

    void saveUser(User user);

    void updateUser(User user);
}
