package com.sda.javaremote18.spring_boot.controllers;

import com.sda.javaremote18.spring_boot.models.ServerResponse;
import com.sda.javaremote18.spring_boot.models.UserModel;
import com.sda.javaremote18.spring_boot.models.category.CategoryModel;
import com.sda.javaremote18.spring_boot.models.item.ItemDto;
import com.sda.javaremote18.spring_boot.models.item.ItemModel;
import org.apache.catalina.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.Date;
import java.util.List;

@RestController
public class ItemsController {
    private ItemsRepository itemsRepository;
    private UsersRepository usersRepository;
    private CategoriesRepository categoriesRepository;

    @Autowired
    public ItemsController(ItemsRepository itemsRepository, UsersRepository usersRepository, CategoriesRepository categoriesRepository){
        this.itemsRepository = itemsRepository;
        this.usersRepository = usersRepository;
        this.categoriesRepository = categoriesRepository;

    }

    @PostMapping("/items/create")
    public ServerResponse create(@RequestBody ItemDto itemDto) {
        if(!itemDto.isValid()) {
            return new ServerResponse(HttpStatus.BAD_REQUEST.value(), "Campurile sunt invalide!");
        }

        UserModel owner = this.usersRepository.findById(itemDto.getOwnerId()).orElse(null);

        if(owner == null) {
            return new ServerResponse(HttpStatus.BAD_REQUEST.value(), "OwnerId invalid");
        }

        CategoryModel category = this.categoriesRepository.findById(itemDto.getCategoryId()).orElse(null);

        if(category == null){
            return new ServerResponse(HttpStatus.BAD_REQUEST.value(),"CategoryId invalid");
        }

        ItemModel item = new ItemModel();
        item.setTitle(itemDto.getTitle());
        item.setDescription(itemDto.getDescription());
        item.setPrice(itemDto.getPrice());
        item.setDate(new Date());
        item.setDeleted(false);
        item.setOwner(owner);

        item.setCategory(category);

        this.itemsRepository.save(item);

        return new ServerResponse(HttpStatus.OK.value(), "item creat cu succes", "", item);
    }

    @PutMapping("/items/{itemId}")
    public ServerResponse update(@PathVariable int itemId, @RequestBody ItemDto itemDto) {
        if(!itemDto.isValid()) {
            return new ServerResponse(HttpStatus.BAD_REQUEST.value(), "Campurile sunt invalide!");
        }

        ItemModel itemFromDb = this.itemsRepository.findById(itemId).orElse(null);

        if(itemFromDb == null) {
            return new ServerResponse(HttpStatus.BAD_REQUEST.value(), "Item-ul nu exista in baza de date");
        }

        UserModel owner = this.usersRepository.findById(itemDto.getOwnerId()).orElse(null);

        if(owner == null) {
            return new ServerResponse(HttpStatus.BAD_REQUEST.value(), "OwnerId invalid");
        }

        CategoryModel category = this.categoriesRepository.findById(itemDto.getCategoryId()).orElse(null);

        if(category == null){
            return new ServerResponse(HttpStatus.BAD_REQUEST.value(),"CategoryId invalid");
        }


        itemFromDb.setTitle(itemDto.getTitle());
        itemFromDb.setDescription(itemDto.getDescription());
        itemFromDb.setPrice(itemDto.getPrice());
        itemFromDb.setOwner(owner);
        itemFromDb.setCategory(category);

        this.itemsRepository.save(itemFromDb);

        return new ServerResponse(HttpStatus.OK.value(), "item actualizat cu succes", "", itemFromDb);
    }

    @DeleteMapping("/items/{itemId}")
    public ServerResponse delete(@PathVariable int itemId) {
        if(itemId <= 0){
            return new ServerResponse(HttpStatus.BAD_REQUEST.value(), "Item-ul nu exista in baza de date");
        }

        ItemModel itemFromDb = this.itemsRepository.findById(itemId).orElse(null);

        if(itemFromDb == null){
            return new ServerResponse(HttpStatus.BAD_REQUEST.value(), "Item-ul nu exista in baza de date");
        }

        itemFromDb.setDeleted(true);

        this.itemsRepository.save(itemFromDb);

        return new ServerResponse(HttpStatus.OK.value(), "Item-ul a fost sters cu succes", null);
    }

    @GetMapping("/items")
    public ServerResponse findAll() {
        List<ItemModel> items = this.itemsRepository.findAll();

        return new ServerResponse(HttpStatus.OK.value(), "Lista item-elor", items);
    }
}
