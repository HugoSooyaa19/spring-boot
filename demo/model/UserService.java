package com.example.demo.service;

import com.example.demo.model.User;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Mono;
import java.util.List;

@Service

public class UserService {

    private final WebClient webClient;

    public UserService(WebClient.Builder webClientBuilder) {
        this.webClient = webClientBuilder.baseUrl("https://jsonplaceholder.typicode.com").build();
    }
    @Cacheable("users")
    public Mono<List<User>> getUsers() {
        return webClient.get()
                .uri("/users")
                .retrieve()
                .bodyToFlux(User.class)
                .collectList()
                .onErrorResume(e -> {
                    // Aquí puedes manejar la excepción, como enviar una respuesta vacía o un error
                    System.err.println("Error al obtener los usuarios: " + e.getMessage());
                    return Mono.empty();
                });
    }
}
