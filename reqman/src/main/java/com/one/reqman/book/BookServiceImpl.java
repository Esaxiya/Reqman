package com.one.reqman.book;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class BookServiceImpl implements BookService {

    @Autowired
    BookRepository bookRepository;


    @Override
    public List<Category> getAllCategories() {
        return null;
    }

    @Override
    public Category getCategory(int id) {
        return null;
    }

    @Override
    public List<Book> getAllBooks() {
        return null;
    }

    @Override
    public Book save(Book book) {
        return null;
    }

    @Override
    public Book update(Book book) {
        return null;
    }

    @Override
    public Book get(long id) {
        return null;
    }

    @Override
    public Book getNextId() {
        return null;
    }
}
