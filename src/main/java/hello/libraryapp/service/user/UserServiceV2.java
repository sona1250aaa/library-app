package hello.libraryapp.service.user;

import hello.libraryapp.domain.user.User;
import hello.libraryapp.domain.user.UserRepository;
import hello.libraryapp.dto.user.request.UserCreateRequest;
import hello.libraryapp.dto.user.request.UserUpdateRequest;
import hello.libraryapp.dto.user.response.UserResponse;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class UserServiceV2 {

    private final UserRepository userRepository;

    public UserServiceV2(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    /*
    * 함수 시작시 start transaction;
    * 함수가 예외 없이 잘 처리 되면 commit
    * 문제 발생시 rollback;
    * */
    @Transactional
    public void saveUser(UserCreateRequest request) {
        User u = userRepository.save(new User(request.getName(), request.getAge()));

    }
    @Transactional(readOnly = true)
    public List<UserResponse> getUser() {
        return userRepository.findAll().stream()
                .map(UserResponse::new)
                .collect(Collectors.toList());
    }
    @Transactional
    public void updateUser(UserUpdateRequest request){
        //Select * from user where id = ?;
        //Optional<User>
        User user = userRepository.findById(request.getId())
                .orElseThrow(IllegalArgumentException::new);

        user.updateName(request.getName());
    }
    @Transactional
    public void deleteUser(String name){
        User user = userRepository.findByName(name).orElseThrow(IllegalArgumentException::new);
        userRepository.delete(user);


    }
}



