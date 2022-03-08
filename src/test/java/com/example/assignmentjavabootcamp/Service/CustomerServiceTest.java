package com.example.assignmentjavabootcamp.Service;

import com.example.assignmentjavabootcamp.Entity.CustomerEntity;
import com.example.assignmentjavabootcamp.Exception.CustomerException;
import com.example.assignmentjavabootcamp.Repository.AuthenRepository;
import com.example.assignmentjavabootcamp.Repository.CustomerRepository;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.assertEquals;

@ExtendWith(MockitoExtension.class)
class CustomerServiceTest {
    @Mock
    public CustomerRepository repository;


    @Test
    @DisplayName("ทดสอบ service getCustomerProfileByUsername กรณีมี user อยู่ในระบบ")
    void getCustomerProfileTrue() throws CustomerException {

         String username = "CustMock001";
        CustomerEntity customerEntity1 = new CustomerEntity();
        customerEntity1.setCustid(username);
        customerEntity1.setAddress("123/456 ต.บางเมือง อ.เมืองสมุทรปราการ จ.สมุทรปราการ 10270");
        customerEntity1.setFirstname("Test01");
        customerEntity1.setLastname("Test01");
        customerEntity1.setUsername(username);
        customerEntity1.setMobileno("0800000000");
        customerEntity1.setEmail("CustMock001@gmail.com");
        customerEntity1.setDistrict("บางเมือง");
        customerEntity1.setSub_district("เมืองสมุทรปราการ");
        customerEntity1.setProvince("สมุทรปราการ");
        customerEntity1.setZipcode("10270");

        Mockito.when(repository.findByUsername(username)).thenReturn(Optional.of(customerEntity1));

        CustomerService service = new CustomerService();
        service.setCustomerRepository(repository);
        Optional<CustomerEntity> customerEntity = service.GetCustomerProfileByUsername(username);

        assertEquals(customerEntity.isPresent(),true);
        assertEquals(customerEntity.get().getUsername(),username);
    }

    @Test
    @DisplayName("ทดสอบ service getCustomerProfileByUsername กรณีไม่มีมี user อยู่ในระบบ")
    void getCustomerProfileFalse() throws CustomerException {

        String username = "CustMock002";
        CustomerEntity customerEntity1 = new CustomerEntity();
        customerEntity1.setCustid(username);
        customerEntity1.setAddress("123/456 ต.บางเมือง อ.เมืองสมุทรปราการ จ.สมุทรปราการ 10270");
        customerEntity1.setFirstname("Test01");
        customerEntity1.setLastname("Test01");
        customerEntity1.setUsername(username);
        customerEntity1.setMobileno("0800000000");
        customerEntity1.setEmail("CustMock001@gmail.com");
        customerEntity1.setDistrict("บางเมือง");
        customerEntity1.setSub_district("เมืองสมุทรปราการ");
        customerEntity1.setProvince("สมุทรปราการ");
        customerEntity1.setZipcode("10270");

        Mockito.when(repository.findByUsername(username)).thenReturn(Optional.empty());


        CustomerService service = new CustomerService();
        service.setCustomerRepository(repository);

        Exception exception = assertThrows(CustomerException.class, () -> {
            Optional<CustomerEntity> customerEntity = service.GetCustomerProfileByUsername(username);
        });

        String expectedMessage = String.format("CustomerException : Username %s Not Found.",username);
        String actualMessage = exception.getMessage();

        assertTrue(actualMessage.contains(expectedMessage));

    }
}