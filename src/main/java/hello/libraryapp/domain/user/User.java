package hello.libraryapp.domain.user;

import hello.libraryapp.domain.user.loanhistory.UserLoanHistory;
import hello.libraryapp.domain.user.loanhistory.UserLoanHistoryRepository;
import jakarta.persistence.*;
import lombok.Getter;

import java.util.ArrayList;
import java.util.List;

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

    @OneToMany(mappedBy = "user", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<UserLoanHistory> userLoanHistoryList = new ArrayList<>();

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

    public void loanBook(String bookName){
        this.userLoanHistoryList.add(new UserLoanHistory(this, bookName));
    }

    public void returnBook(String bookName){
        UserLoanHistory targetHistory = this.userLoanHistoryList.stream()
                .filter(history -> history.getBookName().equals(bookName))
                .findFirst()
                .orElseThrow(IllegalArgumentException::new);
        targetHistory.doReturn();
    }

}
