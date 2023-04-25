package hello.libraryapp.controller.user;

import hello.libraryapp.dto.user.request.UserCreateRequest;
import hello.libraryapp.domain.user.User;
import hello.libraryapp.dto.user.request.UserUpdateRequest;
import hello.libraryapp.dto.user.response.UserResponse;
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

    public UserController(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    @GetMapping("/user")
    public List<UserResponse> getUser(){
        String sql = "SELECT * FROM user";
        return jdbcTemplate.query(sql, (rs, rowNum) -> {
            long id = rs.getLong("id");
            String name  = rs.getString("name");
            int age = rs.getInt("age");
            return new UserResponse(id,name,age);
        });
    }
    @PostMapping("/user")
    public void saveUser(@RequestBody UserCreateRequest request){
        String sql = "INSERT INTO user (name, age) VALUES (?, ?)";
        jdbcTemplate.update(sql,request.getName(),request.getAge());
    }
    @PutMapping("/user")
    public void updateUser(@RequestBody UserUpdateRequest request){
        String readsql = "SELECT * FROM user WHERE id = ?";
        boolean isUserNotExist = jdbcTemplate.query(readsql, (rs, rowNum)
                -> 0, request.getId()).isEmpty();
        if(isUserNotExist){
            throw new IllegalArgumentException();
        }

        String sql = "UPDATE user SET name = ? WHERE id = ?";
        jdbcTemplate.update(sql, request.getName(),request.getId());
    }

    @DeleteMapping("/user")
    public void deleteUser(@RequestParam String name){
        String readsql = "SELECT * FROM user WHERE name = ?";
        boolean isUserNotExist = jdbcTemplate.query(readsql, (rs, rowNum) -> 0, name).isEmpty();
        if(isUserNotExist){
            throw new IllegalArgumentException();
        }

        String sql = "DELETE FROM user WHERE name = ?";
        jdbcTemplate.update(sql,name);

    }




}
