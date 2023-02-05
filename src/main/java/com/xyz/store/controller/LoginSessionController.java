package com.xyz.store.controller;

import com.xyz.store.service.ex.UserNotFoundException;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpSession;
import javax.servlet.http.HttpSessionActivationListener;
import java.nio.file.attribute.UserDefinedFileAttributeView;

@RestController
public class LoginSessionController {
    @RequestMapping("get_username")
    public String login(HttpSession session){
        String username = UserController.getUsernameFromSession(session);
        return username;
    }
}
