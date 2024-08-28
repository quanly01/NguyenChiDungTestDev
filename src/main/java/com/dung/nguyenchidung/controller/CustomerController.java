package com.dung.nguyenchidung.controller;

import com.dung.nguyenchidung.entity.Customer;
import com.dung.nguyenchidung.service.CustomerService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.ArrayList;
import java.util.List;

@Controller
public class CustomerController {
    List<Customer> customerList = new ArrayList<Customer>();

    CustomerService customerService;

    public CustomerController(CustomerService customerService) {
        this.customerService = customerService;
    }

    @GetMapping("/")
    public String index(Model model) {
        model.addAttribute("Customers", customerList);
        return "Index";
    }

    @GetMapping("/create")
    public String create(final Model model){
        model.addAttribute("newuser", Customer.builder());
        return "CreateForm";
    }

    @PostMapping("/create")
    public String create(@ModelAttribute Customer customer, final Model model) {
        Customer tmpUser = customerService.findByEmail(customer.getEmail());
        if(tmpUser==null)
                return "redirect:/";
        else {
            model.addAttribute("error", "email_is_in_used");
        }
        return "CreateForm";
    }
}
