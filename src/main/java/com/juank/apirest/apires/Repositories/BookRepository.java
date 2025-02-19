package com.juank.apirest.apires.Repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.juank.apirest.apires.Entities.Book;

public interface BookRepository extends JpaRepository<Book,Long>{

}
