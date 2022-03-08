package com.example.assignmentjavabootcamp.Service;

import com.example.assignmentjavabootcamp.Entity.AuthenEntity;
import com.example.assignmentjavabootcamp.Repository.AuthenRepository;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;



@ExtendWith(MockitoExtension.class)
class AuthenServiceTest {

    @Mock
    public AuthenRepository repository;

    @Test
    @DisplayName("ทดสอบ service CheckUserAuthen กรณีมี user login อยู่ในระบบ")
    void checkhasuserauthentrue(){

        String username = "Test01";
        boolean result;
        AuthenEntity authen = new AuthenEntity();
        authen.setUsername(username);


        Mockito.when(repository.findByUsername(username)).thenReturn(Optional.of(authen));

        AuthenService service = new AuthenService();
        service.setAuthenRepository(repository);
        result = service.CheckUserAuthen(username);

        assertTrue(result);

    }

    @Test
    @DisplayName("ทดสอบ service CheckUserAuthen กรณีมี user ไม่ได้ login อยู่ในระบบ")
    void checkhasuserauthenfalse(){

        String username = "Test02";
        boolean result;


        Mockito.when(repository.findByUsername(username)).thenReturn(Optional.empty());

        AuthenService service = new AuthenService();
        service.setAuthenRepository(repository);
        result = service.CheckUserAuthen(username);

        assertFalse(result);

    }

}