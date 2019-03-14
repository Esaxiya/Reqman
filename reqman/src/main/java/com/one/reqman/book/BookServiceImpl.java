package com.one.reqman.book;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class BookServiceImpl implements BookService {

    @Autowired
    private BookRepository bookRepository;


    private List<Book> books;
    private List<Category> categories;


    public BookServiceImpl(){

        /**
         * 添加书籍类别
         */
        categories = new ArrayList<Category>();
        Category category1 = new Category(1,"java");
        categories.add(category1);
        Category category2 = new Category(2,"spring");
        categories.add(category2);
        Category category3 = new Category(3,"python");
        categories.add(category3);

        /**
         * 添加书籍
         */
        books = new ArrayList<Book>();
        books.add(new Book(1L,"12341243210","java核心技术",category1,"心飞"));
        books.add(new Book(2L,"12341243211","spring核心技术",category2,"心悦"));
        books.add(new Book(3L,"12341243212","python核心技术",category3,"心新"));
    }


    @Override
    public List<Category> getAllCategories() {
        return categories;
    }

    @Override
    public Category getCategory(int id) {
        for(Category category:categories){
            if(id == category.getId()){
                return category;
            }
        }
        return null;
    }

    @Override
    public List<Book> getAllBooks() {
        return books;
    }

    @Override
    public Book save(Book book) {
        book.setId(getNextId());
        books.add(book);
        return book;
    }

    @Override
    public Book update(Book book) {
        for(int i =0;i<books.size();i++){
            Book savedBook = books.get(i);
            if(savedBook.getId() == book.getId()){
                books.set(i,book);
                return book;
            }
        }
        return null;
    }

    @Override
    public Book get(long id) {
        for(Book book:books){
            if(id == book.getId()){
                return book;
            }
        }
        return null;
    }

    @Override
    public long getNextId() {
        //需要添加锁
        long id =0L;
        for(Book book:books){
            long bookId = book.getId();
            if(bookId>id){
                id =bookId;
            }
        }
        return id+1;
    }
}
