package com.sda.javaremote18.spring_boot.controllers;


import com.sda.javaremote18.spring_boot.models.ServerResponse;
import com.sda.javaremote18.spring_boot.models.UserModel;
import org.apache.catalina.Server;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
public class UsersController {
    private UsersRepository usersRepository;


    @Autowired
    public UsersController(UsersRepository usersRepository) {
        this.usersRepository = usersRepository;
    }

//    @GetMapping("/users/welcome") // in acest caz dorim sa afisam continutul paginii HTML welcome
//    public String showWelcomePage() {
//        return "welcome"; // welcome reprezinta numele paginii de HTML
//    }
    @PutMapping("/users/update")
    public ServerResponse update(@RequestBody UserModel user){
        Optional<UserModel> checkUser = this.usersRepository.findById(user.getId());

        if(checkUser.isPresent()) {
            UserModel userFromDb = checkUser.get();

            userFromDb.setFirstName(user.getFirstName());
            userFromDb.setLastName(user.getLastName());

            this.usersRepository.save(userFromDb);

            return new ServerResponse(HttpStatus.OK.value(), "Utilizator actualizat cu succes", "", userFromDb);
        } else {
            return new ServerResponse(HttpStatus.BAD_REQUEST.value(), "", "Nu am gasit utilizator", null);
        }
    }
}
