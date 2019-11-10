package exercise.customeraccount.web.controller;

import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.server.ResponseStatusException;

@ControllerAdvice
public class PageExceptionHandler {

    @ExceptionHandler(value= ResponseStatusException.class)
    public String handlePageNotFound(Model m,ResponseStatusException ex){
        m.addAttribute("errorMessage",ex.getMessage());
        return "/error";
    }
}
