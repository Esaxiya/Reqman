package com.one.reqman.book;

import com.sun.org.apache.xpath.internal.operations.Mod;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@Controller
public class BookController {

    /**
     * 添加BookService注入
     * Autowiired 提供bookService对象的实现
     */
    @Autowired
    private BookService bookService;

    private static final Log logger = LogFactory.getLog(BookController.class);



    @RequestMapping("/book_input")
    public String inputBook(Model model){
        List<Category> categories = bookService.getAllCategories();
        model.addAttribute("categories", categories);
        model.addAttribute("book",new Book());
        return "BookAddForm";
    }
    @RequestMapping("/book_edit/{id}")
    public String editBook(Model model, @PathVariable long id){
        List<Category> categories = bookService.getAllCategories();
        model.addAttribute("categories",categories);
        Book book = bookService.get(id);
        model.addAttribute("book",book);
        return "BookEditForm";
    }

    @RequestMapping("/book_save")
    public String saveBook(@ModelAttribute Book book){
        Category category = bookService.getCategory(book.getCategory().getId());
        book.setCategory(category);
        bookService.save(book);
        return "redirect:/book_list";

    }

    @RequestMapping("/update_book")
    public String updateBook(@ModelAttribute Book book){
        Category category = bookService.getCategory(book.getCategory().getId());
        book.setCategory(category);
        bookService.update(book);
        return "redirect:/book_list";
    }

    @RequestMapping("/book_list")
    public String books(Model model){
        logger.info("book list ...");
        List<Book> books = bookService.getAllBooks();
        model.addAttribute("books",books);
        return "BookList";
    }

}
