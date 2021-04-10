package com.jornwer.basicauthapp.controller;

import com.jornwer.basicauthapp.service.UserService;
import lombok.AllArgsConstructor;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@AllArgsConstructor
@RequestMapping("/users")
@PreAuthorize("hasAuthority('EDIT')")
public class UsersController {
    private final UserService userService;

    @PostMapping("/block")
    public void blockUsers(@RequestBody List<Integer> list) {
        userService.blockUsers(list);
    }

    @PostMapping("/unblock")
    public void unblockUsers(@RequestBody List<Integer> list) {
        userService.unblockUsers(list);
    }

    @DeleteMapping("/delete")
    public void deleteUsers(@RequestBody List<Integer> list) {
        userService.deleteUsers(list);
    }

}
