package hello.libraryapp.controller.user;

import hello.libraryapp.dto.user.request.UserCreateRequest;
import hello.libraryapp.domain.user.User;
import hello.libraryapp.dto.user.request.UserUpdateRequest;
import hello.libraryapp.dto.user.response.UserResponse;
import hello.libraryapp.service.user.UserService;
import org.springframework.boot.autoconfigure.jdbc.JdbcTemplateAutoConfiguration;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.web.bind.annotation.*;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

@RestController
public class UserController {

    private final JdbcTemplate jdbcTemplate;
    private final UserService userService;

    public UserController(JdbcTemplate jdbcTemplate, UserService userService) {
        this.jdbcTemplate = jdbcTemplate;
        this.userService = new UserService(jdbcTemplate);
    }
    @GetMapping("/user")
    public List<UserResponse> getUser(){
        return userService.getUser();
    }
    @PostMapping("/user")
    public void saveUser(@RequestBody UserCreateRequest request){
        userService.saveUser(request);
    }
    @PutMapping("/user")
    public void updateUser(@RequestBody UserUpdateRequest request){
        userService.updateUser(request);
    }

    @DeleteMapping("/user")
    public void deleteUser(@RequestParam String name){
        userService.deleteUser(name);
    }




}
