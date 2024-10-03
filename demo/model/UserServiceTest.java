package com.example.demo.service;

import com.example.demo.model.User;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Mono;

import java.util.List;

import static org.mockito.Mockito.when;

@SpringBootTest
public class UserServiceTest {

    @Test
    public void testGetUsers() {
        WebClient.Builder webClientBuilder = Mockito.mock(WebClient.Builder.class);
        UserService userService = new UserService(webClientBuilder);

        // Simula una respuesta de la API
        when(userService.getUsers()).thenReturn(Mono.just(List.of(new User())));

        // Verifica que el servicio devuelve la lista simulada
        Mono<List<User>> users = userService.getUsers();
        assert users.block().size() > 0;
    }
}
