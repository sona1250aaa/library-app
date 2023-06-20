package hello.libraryapp.controller.user;

import hello.libraryapp.dto.user.request.UserCreateRequest;
import hello.libraryapp.dto.user.request.UserUpdateRequest;
import hello.libraryapp.dto.user.response.UserResponse;
import hello.libraryapp.service.user.UserServiceV1;
import hello.libraryapp.service.user.UserServiceV2;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class UserController {


    private final UserServiceV2 userServiceV2;

    public UserController(UserServiceV2 userServiceV2) {

        this.userServiceV2 = userServiceV2;
    }
    @GetMapping("/user")
    public List<UserResponse> getUser(){
        return userServiceV2.getUser();
    }
    @PostMapping("/user")
    public void saveUser(@RequestBody UserCreateRequest request){
        userServiceV2.saveUser(request);
    }
    @PutMapping("/user")
    public void updateUser(@RequestBody UserUpdateRequest request){
        userServiceV2.updateUser(request);
    }

    @DeleteMapping("/user")
    public void deleteUser(@RequestParam String name){
        userServiceV2.deleteUser(name);
    }




}
