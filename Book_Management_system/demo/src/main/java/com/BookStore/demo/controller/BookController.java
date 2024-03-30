package com.BookStore.demo.controller;

import com.BookStore.demo.entity.MyBookList;
import com.BookStore.demo.service.BookService;
import com.BookStore.demo.service.MyBookListService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import  com.BookStore.demo.entity.Book;
import org.springframework.web.servlet.ModelAndView;

import  java.util.*;
@Controller
public class BookController {
    @Autowired
    private BookService bookService;
    @Autowired
    private MyBookListService myBookListService;
    @GetMapping("/")
    public String home() {

        return "home";
    }

    @GetMapping("/book_register")
    public String bookRegister() {

        return "book_register";

    }

    @GetMapping("/Available_books")
    public ModelAndView getAllBooks() {

     List<Book>list= bookService.getAllBook();
     /* ModelAndView m= new ModelAndView();
      m.setViewName("book_list");
      m.addObject("book",list);*/
     return new ModelAndView("book_list","book",list);
    }
    @PostMapping("/save")
    public String addBook(@ModelAttribute Book book) {
        bookService.save(book);
        return "redirect:/Available_books";

    }

     @GetMapping("/my_books")
    public  String getMyBooks(Model model){
   List<MyBookList>list=myBookListService.getAllMyBooks();
   model.addAttribute("book",list);
   return "myBooks";


     }
@RequestMapping("/mylist/{id}")
    public String getMyList(@PathVariable("id") int id){
        Book b=bookService.getBookById(id);
        MyBookList mb= new MyBookList(b.getId(),b.getName(),b.getAuthor(),b.getPrice());
        myBookListService.saveMyBooks(mb);
        return "redirect:/my_books";

}
    @RequestMapping("/editBook/{id}")
    public String editBook(@PathVariable("id")int id, Model model){
      Book b= bookService.getBookById(id);
      model.addAttribute("book",b);
        return "bookEdit";

    }
    @RequestMapping("/deleteBook/{id}")
    public String deleteBook(@PathVariable("id") int id) {
        bookService.deleteById(id);
        return "redirect:/Available_books";
    }
    }
