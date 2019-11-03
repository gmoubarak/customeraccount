package exercise.customeraccount.web.controller;

import exercise.customeraccount.customer.service.CustomerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class DefaultController {
    @Autowired
    CustomerService customerService;

    @GetMapping(value="/")
    public String homePage(Model model){
        model.addAttribute("pageName","home");
        model.addAttribute("customers",customerService.getCustomers());
        return "/home";
    }
}
