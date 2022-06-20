package com.example.bookstoremanagement.repository;

import com.example.bookstoremanagement.model.Book;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface IBookRepository  extends JpaRepository<Book,Long> {

    Page<Book> findAllByCategory_IdAndNameContaining(String category, String name, Pageable page);

}
