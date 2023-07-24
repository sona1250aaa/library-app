package hello.libraryapp.service.book;

import hello.libraryapp.domain.book.Book;
import hello.libraryapp.domain.book.BookRepository;
import hello.libraryapp.domain.user.User;
import hello.libraryapp.domain.user.UserRepository;
import hello.libraryapp.domain.user.loanhistory.UserLoanHistory;
import hello.libraryapp.domain.user.loanhistory.UserLoanHistoryRepository;
import hello.libraryapp.dto.book.request.BookCreateRequest;
import hello.libraryapp.dto.book.request.BookLoanRequest;
import hello.libraryapp.dto.book.request.BookReturnRequest;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class BookService {

    private final BookRepository bookRepository;
    private final UserLoanHistoryRepository userLoanHistoryRepository;
    private final UserRepository userRepository;

    public BookService(BookRepository bookRepository, UserLoanHistoryRepository userLoanHistoryRepository,
                       UserRepository userRepository) {
        this.bookRepository = bookRepository;
        this.userLoanHistoryRepository = userLoanHistoryRepository;
        this.userRepository = userRepository;
    }

    @Transactional
    public void saveBook(BookCreateRequest request) {
        bookRepository.save(new Book(request.getName()));

    }

    @Transactional
    public void loanBook(BookLoanRequest request) {

        // 1. 책 정보 가져오기
        Book book = bookRepository.findByName(request.getBookName()).
                orElseThrow(IllegalArgumentException::new);

        // 2. 대출 정보를 확인 대출 중인지
        // 3. 대출 중이면 예외 발생
        if(userLoanHistoryRepository.existsByBookNameAndIsReturn(book.getName(), false)){
            throw new IllegalArgumentException("대출 되어 있는 책입니다");
        }

        // 4. 유저 정보 가져오기
        User user = userRepository.findByName(request.getUserName())
                .orElseThrow(IllegalArgumentException::new);
        user.loanBook(book.getName());

    }

    @Transactional
    public void returnBook(BookReturnRequest request) {

        // 1. 유저 정보 가져오기
        User user = userRepository.findByName(request.getUserName())
                .orElseThrow(IllegalArgumentException::new);

        user.returnBook(request.getBookName());
    }
}
