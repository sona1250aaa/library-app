package hello.libraryapp.service.user;

import hello.libraryapp.dto.user.request.UserCreateRequest;
import hello.libraryapp.dto.user.request.UserUpdateRequest;
import hello.libraryapp.dto.user.response.UserResponse;
import hello.libraryapp.repository.user.UserJDBCRepository;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserServiceV1 {

    private final UserJDBCRepository userJDBCRepository;

    public UserServiceV1(JdbcTemplate jdbcTemplate) {
        userJDBCRepository = new UserJDBCRepository(jdbcTemplate);
    }
    public void saveUser(UserCreateRequest request){
        userJDBCRepository.saveUser(request.getName(), request.getAge());
    }
    public List<UserResponse> getUser(){
        return userJDBCRepository.getUser();
    }
    public void updateUser(UserUpdateRequest request){

        if(userJDBCRepository.isUserNotExist( request.getId())){
            throw new IllegalArgumentException();
        }
        userJDBCRepository.updateUserName(request.getName(), request.getId());
    }
    public void deleteUser(String name){
        if(userJDBCRepository.isUserNameNotExist(name)){
            throw new IllegalArgumentException();
        }
        userJDBCRepository.deleteUser(name);
    }




}
