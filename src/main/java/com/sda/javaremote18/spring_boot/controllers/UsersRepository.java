package com.sda.javaremote18.spring_boot.controllers;

import com.sda.javaremote18.spring_boot.models.UserModel;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

public interface UsersRepository extends CrudRepository<UserModel, Long> {

    @Query("SELECT user from UserModel user where user.email=?1")
    public UserModel findByEmail(String email);
}
