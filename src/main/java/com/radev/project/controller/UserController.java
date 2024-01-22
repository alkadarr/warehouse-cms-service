package com.radev.project.controller;

import com.radev.project.dto.PageTemplate;
import com.radev.project.dto.user.UserRegister;
import com.radev.project.dto.user.UserUpdate;
import com.radev.project.entity.User;
import com.radev.project.service.UserService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/user")
public class UserController {
    @Autowired
    private UserService userService;
    @GetMapping
    public List<User> findAll() {
        return userService.findAll();
    }
    @GetMapping("/paging")
    public PageTemplate getMoviesPaging(
            @RequestParam(name = "search",defaultValue = "",required = false) String search,
            @RequestParam(name = "roleId",defaultValue = "0",required = false) Long roleId,
            @RequestParam(name = "page", defaultValue = "1", required = false) Integer page,
            @RequestParam(name = "size", defaultValue = "10", required = false) Integer size,
            @RequestParam(name = "sortBy", defaultValue = "id", required = false) String sortBy,
            @RequestParam(name = "sortType", defaultValue = "desc", required = false) String sortType){
        return userService.findAllPagination(search,roleId, page, size, sortBy, sortType);
    }
    @PostMapping
    public User registerUser(@Valid @RequestBody UserRegister userRegister) {
        return userService.create(userRegister);
    }
    @PutMapping
    public User updateUser(@Valid @RequestBody UserUpdate payload) {
        return userService.update(payload);
    }
    @DeleteMapping("/{userId}")
    public ResponseEntity<String> softDeleteUser(@PathVariable Long userId) {
        userService.delete(userId);
        return ResponseEntity.ok("User deleted successfully");
    }
}
