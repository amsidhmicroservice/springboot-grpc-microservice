package com.amsidh.mvc;

import com.amsidh.mvc.entity.User;
import com.amsidh.mvc.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.util.Arrays;
import java.util.List;

@RequiredArgsConstructor
@SpringBootApplication
@Slf4j
public class UserServiceApplication implements CommandLineRunner {

    private final UserRepository userRepository;

    public static void main(String[] args) {
        SpringApplication.run(UserServiceApplication.class, args);
    }

    @Override
    public void run(String... args) throws Exception {
        List<User> users = Arrays.asList(User.builder().name("Amsidh").balance(10000).build(),
                User.builder().name("Anjali").balance(10000).build(),
                User.builder().name("Adithi").balance(10000).build(),
                User.builder().name("Aditya").balance(10000).build());
        this.userRepository.saveAll(users).forEach(user -> log.info("User Details {}", user));
    }
}
