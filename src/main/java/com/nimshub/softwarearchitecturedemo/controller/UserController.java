package com.nimshub.softwarearchitecturedemo.controller;


import com.nimshub.softwarearchitecturedemo.entity.User;
import com.nimshub.softwarearchitecturedemo.entity.VerifyEmailRequest;
import com.nimshub.softwarearchitecturedemo.entity.VerifyEmailResponse;
import com.nimshub.softwarearchitecturedemo.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;



import javax.annotation.PostConstruct;

@RestController
public class UserController {

    @Autowired
    private UserService userService;

    @PostConstruct
    public void initRoleAndUser() {
        userService.initRoleAndUser();
    }

    @PostMapping({"/registerNewUser"})
    public User registerNewUser(@RequestBody User user) {
        return userService.registerNewUser(user);
    }

    @PostMapping({"/verifyEmail"})
    public VerifyEmailResponse verifyEmail(@RequestBody VerifyEmailRequest req) {
        System.out.println(req.getToken());
        return userService.verifyEmail(req.getToken());
    }

    @GetMapping({"/forAdmin"})
    @PreAuthorize("hasRole('Admin')")
    public String forAdmin(){
        return "This URL is only accessible to the admin";
    }

    @GetMapping({"/forUser"})
    @PreAuthorize("hasRole('User')")
    public String forUser(){
        return "This URL is only accessible to the user";
    }
}
