package com.example.Spring.Angular_demo_app.Service;

import com.example.Spring.Angular_demo_app.DTO.CustomerDTO;
import com.example.Spring.Angular_demo_app.DTO.CustomerSaveDTO;
import com.example.Spring.Angular_demo_app.DTO.CustomerUpdateDTO;

import java.util.List;

public interface CustomerService {
    String addCustomer(CustomerSaveDTO customerSaveDTO);
    List<CustomerDTO> getAllCustomer();
    String updateCustomers(CustomerUpdateDTO customerUpdateDTO);
    boolean deleteCustomer(int id);
}
