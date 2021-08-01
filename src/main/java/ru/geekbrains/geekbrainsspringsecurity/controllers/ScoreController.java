package ru.geekbrains.geekbrainsspringsecurity.controllers;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import ru.geekbrains.geekbrainsspringsecurity.entities.User;
import ru.geekbrains.geekbrainsspringsecurity.services.UserService;
import java.security.Principal;


@RestController
@Slf4j
@RequiredArgsConstructor
public class ScoreController {

    private final UserService userService;

    @GetMapping("/score/inc")
    public String daoIncUserScore(Principal principal) {
        User user = userService.findByUsername(principal.getName()).orElseThrow(() -> new RuntimeException("unable to fing user by username: " + principal.getName()));
        userService.changeUserScore(user, 1);
        return "score inc: " + user.getUsername() + " : " + user.getScore();
    }

    @GetMapping("/score/dec")
    public String daoDecUserScore(Principal principal) {
        User user = userService.findByUsername(principal.getName()).orElseThrow(() -> new RuntimeException("unable to fing user by username: " + principal.getName()));
        userService.changeUserScore(user, -1);
        return "score dec: " + user.getUsername() + " : " + user.getScore();
    }

    @GetMapping("/score/get/current")
    public String daoGetCurrentUserScore(Principal principal) {
        User user = userService.findByUsername(principal.getName()).orElseThrow(() -> new RuntimeException("unable to fing user by username: " + principal.getName()));
        return "current user: " + user.getUsername() + " score:" + user.getScore();
    }

    @GetMapping("/score/get/{id}")
    public String daoGetUserById(@PathVariable Long id) {
        User user = userService.findUserById(id).orElseThrow(() -> new RuntimeException("unable to fing user by id: " + id));
        return "authenticated: " + user.getUsername() + " : " + user.getEmail() + " : " + user.getScore();
    }
}
