package ru.geekbrains.geekbrainsspringsecurity.controllers;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import ru.geekbrains.geekbrainsspringsecurity.entities.User;
import ru.geekbrains.geekbrainsspringsecurity.services.UserService;
import java.security.Principal;

@RestController
@Slf4j
@RequiredArgsConstructor
public class DaoController {

    private final UserService userService;

    @GetMapping("/dao")
    public String daoTestPage(Principal principal) {
        User user = userService.findByUsername(principal.getName()).orElseThrow(() -> new RuntimeException("unable to fing user by username: " + principal.getName()));
        return "authenticated: " + user.getUsername() + " : " + user.getRoles() + " : " + user.getScore();
    }


}
