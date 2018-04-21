package md.esempla.library.web;

import md.esempla.library.domain.BookBorrowed;
import md.esempla.library.repository.BooksBorrowedRepository;
import md.esempla.library.repository.BooksRepository;
import md.esempla.library.repository.ClientsRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

@ComponentScan
@Controller
public class BooksBorrowedController {

    private static final String VIEW_BOOKS_BORROWED = "booksborrowed";
    private static final String VIEW_ORDER = "neworder";
    private static final String VIEW_CHANGE_ORDER = "changeorder";

    @Autowired
    private BooksRepository booksRepository;

    @Autowired
    private ClientsRepository clientsRepository;

    @Autowired
    private BooksBorrowedRepository booksBorrowedRepository;

    @GetMapping("/booksborrowed")
    public String getViewBooksBorrowed(ModelMap map) {

        map.addAttribute("orders", booksBorrowedRepository.findAll());

        return VIEW_BOOKS_BORROWED;
    }

    @GetMapping("/neworder")
    public String getViewNewOrder(ModelMap map) {

        map.addAttribute("order", new BookBorrowed());
        map.addAttribute("clients", clientsRepository.findAll());
        map.addAttribute("books", booksRepository.findAll());

        return VIEW_ORDER;
    }

    @PostMapping("/neworder")
    public String saveNewOrder(@ModelAttribute BookBorrowed bookBorrowed, ModelMap map) {

        booksBorrowedRepository.save(bookBorrowed);
        map.addAttribute("orders", booksBorrowedRepository.findAll());

        return VIEW_BOOKS_BORROWED;
    }

    @GetMapping("/changeorder{id}")
    public String getViewChangeOrder(@PathVariable("id") long id, ModelMap map) {

        map.addAttribute("order", booksBorrowedRepository.findById(id));
        map.addAttribute("books", booksRepository.findAll());
        map.addAttribute("clients", clientsRepository.findAll());

        return VIEW_CHANGE_ORDER;
    }

    @PostMapping("/changeorder")
    public String saveChangeOrder(@ModelAttribute BookBorrowed bookBorrowed, ModelMap map) {

        booksBorrowedRepository.save(bookBorrowed);
        map.addAttribute("orders", booksBorrowedRepository.findAll());

        return VIEW_BOOKS_BORROWED;
    }

    @GetMapping("/delete/order{id}")
    public String deleteBookBorred(@PathVariable("id") long id, ModelMap map) {

        booksBorrowedRepository.delete(id);
        map.addAttribute("orders", booksBorrowedRepository.findAll());

        return VIEW_BOOKS_BORROWED;
    }
}