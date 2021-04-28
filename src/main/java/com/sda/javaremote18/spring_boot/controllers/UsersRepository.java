package com.sda.javaremote18.spring_boot.controllers;

import com.sda.javaremote18.spring_boot.models.UserModel;
import org.springframework.data.repository.CrudRepository;

public interface UsersRepository extends CrudRepository<UserModel, Long> {
}
