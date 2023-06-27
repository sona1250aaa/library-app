package hello.libraryapp.controller.book;

import hello.libraryapp.dto.book.request.BookCreateRequest;
import hello.libraryapp.dto.book.request.BookLoanRequest;
import hello.libraryapp.dto.book.request.BookReturnRequest;
import hello.libraryapp.service.book.BookService;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class BookController {

    private final BookService bookService;

    public BookController(BookService bookService) {
        this.bookService = bookService;
    }


    @PostMapping("/book")
    public void saveBook(@RequestBody BookCreateRequest request){
        bookService.saveBook(request);

    }

    @PostMapping("/book/loan")
    public void loanBook(@RequestBody BookLoanRequest bookLoanRequest){
        bookService.loanBook(bookLoanRequest);
    }

    @PostMapping("/book/return")
    public void returnBook(@RequestBody BookReturnRequest bookReturnRequest){
        bookService.returnBook(bookReturnRequest);

    }




}
