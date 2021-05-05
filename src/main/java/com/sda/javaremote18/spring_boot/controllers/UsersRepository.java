package com.sda.javaremote18.spring_boot.controllers;

import com.sda.javaremote18.spring_boot.models.UserModel;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

public interface UsersRepository extends CrudRepository<UserModel, Integer> {

    @Query("SELECT user from UserModel user where user.email=?1")
    public UserModel findByEmail(String email);

    UserDetails loadUserByEmail (String email) throws UsernameNotFoundException;
}
