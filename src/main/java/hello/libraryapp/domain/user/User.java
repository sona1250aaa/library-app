package hello.libraryapp.domain.user;

import jakarta.persistence.*;
import lombok.Getter;

@Entity
@Getter
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, length = 20) // name varchar(20) 테이블이름이랑 동일시 name 옵션 생략 가능
    private String name;

    //@Column 어노테이션도 생략가능
    private Integer age;

    protected User() {
    }

    public User(String name, Integer age) {
        if (name == null || name.isBlank()) {
            throw new IllegalArgumentException(String.format("잘못된 name(%s)이 들어왔습니다.", name));
        }
        this.name = name;
        this.age = age;
    }

    public void updateName(String name){
        this.name = name;

    }

}
