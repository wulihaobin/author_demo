package com.nf.web;

import com.nf.dao.AuthorDAO;
import com.nf.entity.Author;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.Mapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.validation.Valid;

@Controller
@RequestMapping("/author")
public class AuthorController {
    @Autowired
    private AuthorDAO authorDAO;

    @GetMapping("/index")
    public String index(Model model){
        model.addAttribute("authors",authorDAO.findAll());
        return "/author/index";
    }

    @GetMapping("/add_author")
    public String add(Author author) {
        return "author/add";
    }


    @PostMapping("/add_author")
    public String store(@Valid Author author, BindingResult errors) {
        if(errors.hasErrors()){
            return "author/add";
        }
        authorDAO.save(author);
        return "redirect:index";
    }

    @GetMapping("/edit")
    public String edit(Long id,Model model){
        model.addAttribute("author",authorDAO.findOne(id));
        return "author/update";
    }

    @PostMapping("/update")
    public String update(@Valid Author author,BindingResult errors){
        if(errors.hasErrors()){
            return "author/update";
        }
        authorDAO.save(author);
        return "redirect:index";
    }

    @GetMapping("/delete")
    public String delete(Long id){
        authorDAO.delete(id);
        return "redirect:index";
    }
}
