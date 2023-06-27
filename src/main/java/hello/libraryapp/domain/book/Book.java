package hello.libraryapp.domain.book;

import jakarta.persistence.*;
import lombok.Getter;

@Entity
@Getter
public class Book {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, length = 255)
    private String name;

    protected Book() {

    }

    public Book(String name) {
        if(name == null || name.isBlank()){
            throw new IllegalArgumentException(String.format("잘못된 name(%s)이 들어왔습니다",name));
        }
        this.name = name;
    }
}
