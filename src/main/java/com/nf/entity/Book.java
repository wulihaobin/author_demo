package com.nf.entity;

import javax.persistence.*;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@Entity
public class Book {
    @Id
    @GeneratedValue
    private Long Id;
    @Size(min = 2,max = 10)
    private String name;
    @NotNull(message = "不能为空")
    @Min(value = 10)
    private Float price;
    @ManyToOne()
    private Author author;

    @Override
    public String toString() {
        return "Book{" +
                "Id=" + Id +
                ", name='" + name + '\'' +
                ", price=" + price +
                ", author=" + author +
                '}';
    }

    public Long getId() {
        return Id;
    }

    public void setId(Long id) {
        Id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Float getPrice() {
        return price;
    }

    public void setPrice(Float price) {
        this.price = price;
    }

    public Author getAuthor() {
        return author;
    }

    public void setAuthor(Author author) {
        this.author = author;
    }
}
