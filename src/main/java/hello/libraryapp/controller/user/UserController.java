package hello.libraryapp.controller.user;

import hello.libraryapp.dto.user.request.UserCreateRequest;
import hello.libraryapp.domain.user.User;
import hello.libraryapp.dto.user.response.UserResponse;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;

@RestController
public class UserController {

    List<User> users = new ArrayList<>();

    @GetMapping("/user")
    public List<UserResponse> getUser(){
        List<UserResponse> response = new ArrayList<>();
        for(int i = 0; i<users.size(); i++){
            response.add(new UserResponse(i+1,users.get(i)));
        }
        return response;
    }


    @PostMapping("/user")
    public void saveUser(@RequestBody UserCreateRequest request){
        users.add(new User(request.getName(),request.getAge()));
    }




}
