package com.sda.javaremote18.spring_boot.beans;

import org.springframework.stereotype.Component;

@Component
public class Book {
    private String title;
    private String author;

    public Book(){
        this.title = "Amintiri din copilarie";
        this.author = "Ion Creanga";
    }
    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

}
