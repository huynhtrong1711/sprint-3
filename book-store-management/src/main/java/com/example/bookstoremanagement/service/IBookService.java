package com.example.bookstoremanagement.service;

import com.example.bookstoremanagement.model.Book;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface IBookService {

    Page<Book> findAll(String category, String name, Pageable pageable);

    Page<Book> findAllBook(Pageable pageable);
}
