package com.dung.nguyenchidung.controller;

import com.dung.nguyenchidung.entity.Customer;
import com.dung.nguyenchidung.service.CustomerService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;

import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@SpringBootTest
@AutoConfigureMockMvc
public class CustomerControllerTest {
    @MockBean
    CustomerService customerService;

    @Autowired
    private MockMvc mockMvc;

    @Test
    public void should_return_index_view() throws Exception {
        this.mockMvc.perform(get("/"))
                .andExpect(status().isOk())
                .andExpect(view().name("Index"))
                .andExpect(model().attributeExists("Customers"))
                .andDo(print());
    }

    @Test
    public void should_return_create_form_view() throws Exception {
        this.mockMvc.perform(get("/create"))
                .andExpect(status().isOk())
                .andExpect(view().name("CreateForm"))
                .andExpect(model().attributeExists("newuser"))
                .andDo(print());
    }

    @Test
    public void should_redirect_to_index_when_email_not_in_use() throws Exception {
        Customer newCustomer = Customer.builder()
                .email("newemail@example.com")
                .build();

        when(customerService.findByEmail(newCustomer.getEmail())).thenReturn(null);

        this.mockMvc.perform(post("/create")
                        .flashAttr("customer", newCustomer))
                .andExpect(status().is3xxRedirection())
                .andExpect(redirectedUrl("/"))
                .andDo(print());
    }

    @Test
    public void should_return_create_form_with_error_when_email_in_use() throws Exception {
        Customer existingCustomer = Customer.builder()
                .email("existingemail@example.com")
                .build();

        when(customerService.findByEmail(existingCustomer.getEmail())).thenReturn(existingCustomer);

        this.mockMvc.perform(post("/create")
                        .flashAttr("customer", existingCustomer))
                .andExpect(status().isOk())
                .andExpect(view().name("CreateForm"))
                .andExpect(model().attributeExists("error"))
                .andExpect(model().attribute("error", "email_is_in_used"))
                .andDo(print());
    }
}