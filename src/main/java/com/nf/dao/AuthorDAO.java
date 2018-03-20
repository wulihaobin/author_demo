package com.nf.dao;

import com.nf.entity.Author;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AuthorDAO extends JpaRepository<Author, Long> {
}
