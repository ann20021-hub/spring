package com.ann20021.spring.book.controller;

import com.ann20021.spring.base.exception.ResourseNotFoundEception;
import com.ann20021.spring.book.entity.BookEntity;
import com.ann20021.spring.book.service.BookService;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
public class BookApiControler {

    private final BookService bookService;

    public BookApiControler(BookService bookService) {
        this.bookService = bookService;
    }

    @GetMapping("/")
    public String ok(){
        return "ok";
    }

    @GetMapping("/api/v1/book")
    public List<BookEntity> all(){
        return bookService.all();
    }

    @GetMapping("/api/v1/book/{id}")
    public BookEntity byId(@PathVariable Integer id){
        return bookService.byId(id).orElseThrow(ResourseNotFoundEception::new);
    }

    @PostMapping("/api/v1/book")
    public BookEntity create (@RequestBody BookEntity request){
        return bookService.create(request.getTitle(), request.getDescription());
    }

    @PutMapping("/api/v1/book/{id}")
    public BookEntity edit(@PathVariable Integer id, @RequestBody BookEntity request){
        return bookService.edit(request).orElseThrow(ResourseNotFoundEception::new);
    }

    @DeleteMapping("/api/v1/book/{id}")
    public Boolean delete(@PathVariable Integer id){
        return bookService.delete(id);
    }

    @PatchMapping("/api/v1/book/{id}")
    public BookEntity path (@PathVariable Integer id, @RequestBody Map <String, String> fields){
        return bookService.editParth(id, fields).orElseThrow(ResourseNotFoundEception::new);
    }

}
