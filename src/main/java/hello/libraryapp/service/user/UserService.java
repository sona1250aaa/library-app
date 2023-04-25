package hello.libraryapp.service.user;

import hello.libraryapp.dto.user.request.UserCreateRequest;
import hello.libraryapp.dto.user.request.UserUpdateRequest;
import hello.libraryapp.dto.user.response.UserResponse;
import hello.libraryapp.repository.user.UserRepository;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserService {

    private final UserRepository userRepository;

    public UserService(JdbcTemplate jdbcTemplate) {
        userRepository = new UserRepository(jdbcTemplate);
    }
    public void saveUser(UserCreateRequest request){
        userRepository.saveUser(request.getName(), request.getAge());
    }
    public List<UserResponse> getUser(){
        return userRepository.getUser();
    }
    public void updateUser(UserUpdateRequest request){

        if(userRepository.isUserNotExist( request.getId())){
            throw new IllegalArgumentException();
        }
        userRepository.updateUserName(request.getName(), request.getId());
    }
    public void deleteUser(String name){
        if(userRepository.isUserNameNotExist(name)){
            throw new IllegalArgumentException();
        }
        userRepository.deleteUser(name);
    }




}
