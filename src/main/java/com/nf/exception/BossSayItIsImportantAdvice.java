package com.nf.exception;

import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ExceptionHandler;

import javax.servlet.http.HttpServletRequest;

public class BossSayItIsImportantAdvice {

    @ExceptionHandler(EmptyResultDataAccessException.class)
    public String handleEmptyResult(HttpServletRequest request, Model model, Exception ex) {
        model.addAttribute("err", "[error] : " + ex.getMessage());
        return "error";
    }

    @ExceptionHandler(Exception.class)
    public String handleException(HttpServletRequest request, Model model, Exception ex) {
        model.addAttribute("err", "[error] : " + ex.getMessage());
        return "error";
    }

}