package hello.libraryapp.dto.user.response;

import hello.libraryapp.domain.user.User;
import lombok.AllArgsConstructor;
import lombok.Getter;


@Getter
public class UserResponse {
    private long id;
    private String name;
    private Integer age;

    public UserResponse(long id, User user) {
        this.id = id;
        this.name = user.getName();
        this.age = user.getAge();
    }
}