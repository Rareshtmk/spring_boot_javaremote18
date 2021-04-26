package com.sda.javaremote18.spring_boot;

import com.sda.javaremote18.spring_boot.beans.Book;
import com.sda.javaremote18.spring_boot.config.AppConfig;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.security.servlet.SecurityAutoConfiguration;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import java.lang.reflect.AnnotatedArrayType;

@SpringBootApplication(exclude = {SecurityAutoConfiguration.class})
public class Application implements CommandLineRunner {

    public static void main(String[] args) {
        SpringApplication.run(Application.class, args);
    }

    @Override
    public void run(String... args) throws Exception {
        AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext();
        context.register(AppConfig.class);
        context.refresh();

        Book book = context.getBean(Book.class);
        System.out.println(book.getTitle());
        System.out.println(book.getAuthor());
    }
}
