package com.sda.javaremote18.spring_boot.models.item;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.sda.javaremote18.spring_boot.models.UserModel;
import com.sda.javaremote18.spring_boot.models.category.CategoryModel;

import javax.persistence.*;
import java.util.Date;

@Entity
@Table(name = "tbl_items")
public class ItemModel {
    /**
     *  ID -> INTEGER/LONG
     *  TITLE -> STRING
     *  OWNER -> USERMODEL relation ManyToOne
     *  PRICE -> DOUBLE
     *  DESCRIPTION -> STRING
     *  DATE -> DATE
     *  DELETED -> BOOLEAN
     */
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer id;
    private String title;
    private String description;
    private Double price;
    private Date date;
    private Boolean deleted;
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "fk_category")
    @JsonIgnore
    private CategoryModel category;
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "fk_item")
    @JsonIgnore
    private UserModel owner;

    public CategoryModel getCategory() {
        return category;
    }

    public void setCategory(CategoryModel category) {
        this.category = category;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Double getPrice() {
        return price;
    }

    public void setPrice(Double price) {
        this.price = price;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public Boolean getDeleted() {
        return deleted;
    }

    public void setDeleted(Boolean deleted) {
        this.deleted = deleted;
    }

    public UserModel getOwner() {
        return owner;
    }

    public void setOwner(UserModel owner) {
        this.owner = owner;
    }
}
