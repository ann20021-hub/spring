package com.ann20021.spring.book.service;


import com.ann20021.spring.book.entity.BookEntity;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.Random;

import static java.util.Locale.filter;

@Service
public class BookService {
    static List<BookEntity> bookStorage = new ArrayList<>();

    public BookService() {
        fillStorage();
    }

    public void fillStorage (){
        Random random = new Random();

        for (int i=0; i<100; i++){
            BookEntity book = new BookEntity();
            book.setId(i);
            book.setTitle("Book #" + random.nextInt(100, 999));
            book.setDescription("Интересное описание книги");
            bookStorage.add(book);

        }
    }

    //Метод возвращающий весь список (GET)
    public List<BookEntity> all(){
        return bookStorage;
    }

    //возвращает опциональную переменную BookEntity по id
    public Optional<BookEntity> byId( Integer id){
        return bookStorage.stream().filter((book -> book.getId().equals(id))).findFirst();
    }

    //Метод POST
    public BookEntity create(String title, String description){
        BookEntity book = new BookEntity();
        book.setId(bookStorage.size());
        book.setTitle(title);
        book.setDescription(description);
        bookStorage.add(book);
        return book;
    }

    //Метод PUT, Optional тк может быть Exception
    public Optional<BookEntity> edit(BookEntity book){
        BookEntity oldBookEntity = byId(book.getId()).orElseThrow();
        oldBookEntity.setTitle(book.getTitle());
        oldBookEntity.setDescription(book.getDescription());
        return Optional.of(oldBookEntity);
    }

}
