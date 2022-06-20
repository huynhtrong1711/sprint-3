package com.example.bookstoremanagement.controller;


import com.example.bookstoremanagement.model.Book;
import com.example.bookstoremanagement.service.IBookService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@CrossOrigin("*")
@RequestMapping("/api/book")
public class BookController {
    @Autowired
    private  IBookService iBookService;


    @GetMapping("/list")
    public ResponseEntity<?> getAll(
                                    @PageableDefault(size = 8) Pageable pageable)  {
//        @RequestParam(defaultValue = "" ) String category,
//        @RequestParam(defaultValue = "" ) String name,
//        Page<Book> bookPage = iBookService.findAll(category, name, pageable);
        Page<Book> bookPage = iBookService.findAllBook(pageable);

        if (bookPage.isEmpty()) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(bookPage, HttpStatus.OK);

    }
}



