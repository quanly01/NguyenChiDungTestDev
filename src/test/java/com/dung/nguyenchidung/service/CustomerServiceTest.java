package com.dung.nguyenchidung.service;

import com.dung.nguyenchidung.entity.Customer;
import com.dung.nguyenchidung.repository.CustomerRepository;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.boot.test.context.SpringBootTest;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.when;

@SpringBootTest
public class CustomerServiceTest {
    @Mock
    private CustomerRepository customerRepository;

    @InjectMocks
    private CustomerService customerService;

    @Test
    public void should_return_customer_does_not_exist_with_this_email() {
        String email = "nonexistent@example.com";

        when(customerRepository.getCustomerByEmail(email)).thenReturn(null);

        Customer result = customerService.findByEmail(email);

        assertThat(result).isNull();
    }

    @Test
    public void should_return_customer_exists_with_this_email() {
        String email = "existing@example.com";
        Customer expectedCustomer = Customer.builder()
                .email(email)
                .build();

        when(customerRepository.getCustomerByEmail(email)).thenReturn(expectedCustomer);

        Customer result = customerService.findByEmail(email);

        assertThat(result).isEqualTo(expectedCustomer);
    }
}