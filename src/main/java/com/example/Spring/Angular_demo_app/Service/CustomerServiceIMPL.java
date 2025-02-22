package com.example.Spring.Angular_demo_app.Service;

import com.example.Spring.Angular_demo_app.DTO.CustomerDTO;
import com.example.Spring.Angular_demo_app.DTO.CustomerSaveDTO;
import com.example.Spring.Angular_demo_app.DTO.CustomerUpdateDTO;
import com.example.Spring.Angular_demo_app.Entity.Customer;
import com.example.Spring.Angular_demo_app.Repository.CustomerRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.ArrayList;
import java.util.List;
@Service
public class CustomerServiceIMPL  implements CustomerService {

    @Autowired
    private CustomerRepo customerRepo;

    @Override
    @PostMapping
    public String addCustomer(CustomerSaveDTO customerSaveDTO) {
        Customer customer = new Customer(
                customerSaveDTO.getCustomername(),
                customerSaveDTO.getCustomeraddress(),
                customerSaveDTO.getMobile()
        );
        customer = customerRepo.save(customer);  // Ensure the saved customer entity is returned
        return String.valueOf(customer.getCustomerid());  // Return the generated ID
    }

    @Override
    @GetMapping
    public List<CustomerDTO> getAllCustomer() {
        List<Customer> getCustomers = customerRepo.findAll();
        List<CustomerDTO> customerDTOList = new ArrayList<>();
        for (Customer a : getCustomers) {
            CustomerDTO customerDTO = new CustomerDTO(
                    a.getCustomerid(),
                    a.getCustomername(),
                    a.getCustomeraddress(),
                    a.getMobile()
            );
            customerDTOList.add(customerDTO);
        }
        return customerDTOList;  // Correctly return the list
    }

    @Override
    public String updateCustomers(CustomerUpdateDTO customerUpdateDTO) {
        if (customerRepo.existsById(customerUpdateDTO.getCustomerid())) {
            Customer customer = customerRepo.findById(customerUpdateDTO.getCustomerid()).orElse(null);
            if (customer != null) {
                customer.setCustomername(customerUpdateDTO.getCustomername());
                customer.setCustomeraddress(customerUpdateDTO.getCustomeraddress());
                customer.setMobile(customerUpdateDTO.getMobile());
                customerRepo.save(customer);
                return "Updated";
            }
        } else {
            System.out.println("CustomerId doesn't exist");
        }
        return "CustomerId doesn't exist";
    }

    @Override
    public boolean deleteCustomer(int id) {
        if (customerRepo.existsById(id)) {
            customerRepo.deleteById(id);
            return true;
        } else {
            System.out.println("CustomerId doesn't exist");
        }
        return false;
    }
}
