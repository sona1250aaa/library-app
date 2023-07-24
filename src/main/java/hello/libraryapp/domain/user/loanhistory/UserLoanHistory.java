package hello.libraryapp.domain.user.loanhistory;


import hello.libraryapp.domain.user.User;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;

@Entity
@Getter

public class UserLoanHistory {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id = null;

    @ManyToOne
    private User user;

    private String bookName;

    private boolean isReturn;

    public UserLoanHistory() {
    }

    public UserLoanHistory(User user, String bookName) {
        this.user = user;
        this.bookName = bookName;
        this.isReturn = false;
    }
    public void doReturn(){
        this.isReturn = true;
    }

    public String getBookName() {
        return bookName;
    }
}
