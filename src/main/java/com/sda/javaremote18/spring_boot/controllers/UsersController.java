package com.sda.javaremote18.spring_boot.controllers;


import com.sda.javaremote18.spring_boot.models.UserModel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCrypt;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class UsersController {
    private UsersRepository usersRepository;
    private BCryptPasswordEncoder bCryptPasswordEncoder;

    @Autowired
    public UsersController(UsersRepository usersRepository, BCryptPasswordEncoder bCryptPasswordEncoder) {
        this.bCryptPasswordEncoder = bCryptPasswordEncoder;
        this.usersRepository = usersRepository;
    }

    @GetMapping("/users/welcome") // in acest caz dorim sa afisam continutul paginii HTML welcome
    public String showWelcomePage() {
        return "welcome"; // welcome reprezinta numele paginii de HTML
    }

    @PostMapping("/users/create")
    public void creatUser(@RequestBody UserModel user) {
//        UserModel userModel = new UserModel();
//        userModel.setFirstName("Cipi");
//        userModel.setLastName("Hampu");
//        userModel.setEmail("ciprian@sda.com");
//        userModel.setPassword("12345678");
        System.out.println(user.getFirstName());
        System.out.println(user.getLastName());
        System.out.println(user.getEmail());
        String currentPassword = user.getPassword();
        String newPassword = this.bCryptPasswordEncoder.encode(currentPassword);
        System.out.println(newPassword);
        user.setPassword(newPassword);
        System.out.println(user.getPassword());
        this.usersRepository.save(user);
    }
}
