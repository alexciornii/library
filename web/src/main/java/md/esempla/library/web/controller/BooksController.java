package md.esempla.library.web;

import md.esempla.library.domain.*;
import md.esempla.library.repository.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;

@Controller
public class BooksController {

    private static final String VIEW_INDEX = "index";
    private static final String VIEW_LOGIN = "login";
    private static final String VIEW_BOOKS = "books";
    private static final String VIEW_AUTHORS = "authors";
    private static final String VIEW_CLIENTS = "clients";
    private static final String VIEW_PUBLISHERS = "publishers";
    private static final String VIEW_BOOKS_EDITED = "booksedited";

    private static final String VIEW_BOOK = "newbook";
    private static final String VIEW_AUTHOR = "newauthor";
    private static final String VIEW_CLIENT = "newclient";
    private static final String VIEW_PUBLISHER = "newpublisher";
    private static final String VIEW_EDITION = "newedition";

    private static final String VIEW_CHANGE_AUTH = "changeauthor";
    private static final String VIEW_CHANGE_BOOK = "changebook";
    private static final String VIEW_CHANGE_CLIENT = "changeclient";
    private static final String VIEW_CHANGE_PUBLISHER = "changepublisher";
    private static final String VIEW_CHANGE_EDITION = "changeedition";

    @Autowired
    private BooksRepository libRepo;

    @Autowired
    private AuthorsRepository authRepo;

    @Autowired
    private ClientsRepository clientsRepo;

    @Autowired
    private PublishersRepository pubRepo;

    @Autowired
    private BooksEditedRepository booksEditedRepo;

    @RequestMapping(value = "/", method = RequestMethod.GET)
    public ModelAndView index(ModelAndView modelAndView) {

        modelAndView.setViewName(VIEW_INDEX);

        return modelAndView;
    }

    @RequestMapping(value = "/login")
    public ModelAndView login(ModelAndView modelAndView) {

        modelAndView.setViewName(VIEW_LOGIN);

        return modelAndView;
    }

    @RequestMapping(value = "/books")
    public ModelAndView books(ModelAndView modelAndView) {

        modelAndView.setViewName(VIEW_BOOKS);
        modelAndView.addObject("books", libRepo.findAll());

        return modelAndView;
    }

    @RequestMapping(value = "/authors")
    public ModelAndView authors(ModelAndView modelAndView) {

        modelAndView.setViewName(VIEW_AUTHORS);
        modelAndView.addObject("authors", authRepo.findAll());

        return modelAndView;
    }

    @RequestMapping(value = "/clients")
    public ModelAndView clients(ModelAndView modelAndView) {

        modelAndView.setViewName(VIEW_CLIENTS);
        modelAndView.addObject("clients", clientsRepo.findAll());

        return modelAndView;
    }

    @RequestMapping(value = "/publishers")
    public ModelAndView publishers(ModelAndView modelAndView) {

        modelAndView.setViewName(VIEW_PUBLISHERS);
        modelAndView.addObject("publishers", pubRepo.findAll());

        return modelAndView;
    }

    @GetMapping(value = "/newbook")
    public ModelAndView newBook(ModelAndView modelAndView) {

        modelAndView.setViewName(VIEW_BOOK);
        modelAndView.addObject("book", new Book());
        modelAndView.addObject("authors", authRepo.findAll());

        return modelAndView;
    }

    @PostMapping(value = "/newbook")
    public String newBook(HttpServletRequest httpServletRequest, @ModelAttribute Book book, ModelMap map) {

        long idAuthor = Long.parseLong(httpServletRequest.getParameter("authorSelect"));
        book.setAuthor(authRepo.findById(idAuthor));
        libRepo.save(book);
        map.addAttribute("books", libRepo.findAll());

        return VIEW_BOOKS;
    }

    @GetMapping("/changebook/{id}")
    public String changeBook(@PathVariable("id") int id, ModelMap map) {

        Book book = libRepo.findById(id);
        map.addAttribute("book", book);
        map.addAttribute("authors", authRepo.findAll());

        return VIEW_CHANGE_BOOK;
    }

    @PostMapping("/changebook")
    public ModelAndView changeBook(@ModelAttribute Book book, ModelAndView modelAndView) {

        modelAndView.setViewName(VIEW_BOOKS);
        libRepo.save(book);
        modelAndView.addObject("books", libRepo.findAll());

        return modelAndView;
    }

    @GetMapping("/newauthor")
    public String newAuthor(ModelMap map) {

        map.addAttribute("author", new Author());

        return VIEW_AUTHOR;
    }

    @PostMapping("/newauthor")
    public String newAuthor(@ModelAttribute Author author, ModelMap map) {

        authRepo.save(author);
        map.addAttribute("authors", authRepo.findAll());

        return VIEW_AUTHORS;
    }

    @GetMapping("/newclient")
    public String newClient(ModelMap map) {

        map.addAttribute("client", new Client());

        return VIEW_CLIENT;
    }

    @PostMapping("/newclient")
    public String newClient(@ModelAttribute Client client, ModelMap map) {

        clientsRepo.save(client);
        map.addAttribute("clients", clientsRepo.findAll());

        return VIEW_CLIENTS;
    }

    @GetMapping("/newpublisher")
    public String newPublisher(ModelMap map) {

        map.addAttribute("publisher", new Publisher());

        return VIEW_PUBLISHER;
    }

    @PostMapping("/newpublisher")
    public String newPublisher(@ModelAttribute Publisher publisher, ModelMap map) {

        pubRepo.save(publisher);
        map.addAttribute("publishers", pubRepo.findAll());

        return VIEW_PUBLISHERS;
    }

    @GetMapping("/books/delete{id}")
    public String deleteBook(@PathVariable("id") int id, ModelMap map) {

        libRepo.delete(libRepo.findById(id));
        map.addAttribute("books", libRepo.findAll());

        return VIEW_BOOKS;
    }

    @GetMapping("/changeauthor{id}")
    public String changeAuthor(@PathVariable("id") int id, ModelMap map) {

        map.addAttribute("author", authRepo.findById(id));

        return VIEW_CHANGE_AUTH;
    }

    @PostMapping("/changeauthor")
    public String saveChangeAuthor(@ModelAttribute Author author, ModelMap map) {

        authRepo.save(author);
        map.addAttribute("authors", authRepo.findAll());

        return VIEW_AUTHORS;
    }

    @GetMapping("/changeclient{id}")
    public String changeClient(@PathVariable("id") int id, ModelMap map) {

        map.addAttribute("client", clientsRepo.findById(id));

        return VIEW_CHANGE_CLIENT;
    }

    @PostMapping("/changeclient")
    public String saveChangeClient(@ModelAttribute Client client, ModelMap map) {

        clientsRepo.save(client);
        map.addAttribute("clients", clientsRepo.findAll());

        return VIEW_CLIENTS;
    }

    @GetMapping("/changepublisher{id}")
    public String changePublisher(@PathVariable("id") int id, ModelMap map) {

        map.addAttribute("publisher", pubRepo.findById(id));

        return VIEW_CHANGE_PUBLISHER;
    }

    @PostMapping("/changepublisher")
    public String saveChangePublisher(@ModelAttribute Publisher publisher, ModelMap map) {

        pubRepo.save(publisher);
        map.addAttribute("publishers", pubRepo.findAll());

        return VIEW_PUBLISHERS;
    }

    @GetMapping("/publisher/delete{id}")
    public String deletePublisher(@PathVariable("id") int id, ModelMap map) {

        pubRepo.delete(pubRepo.findById(id));
        map.addAttribute("publishers", pubRepo.findAll());

        return VIEW_PUBLISHERS;
    }

    @GetMapping("/booksedited")
    public String getViewEditedBooks(ModelMap map) {

        map.addAttribute("booksEdited", booksEditedRepo.findAll());

        return VIEW_BOOKS_EDITED;
    }

    @GetMapping("/newedition")
    public String getViewEdition(ModelMap map) {

        map.addAttribute("bookEdited", new BookEdited());
        map.addAttribute("books", libRepo.findAll());
        map.addAttribute("publishers", pubRepo.findAll());

        return VIEW_EDITION;
    }

    @PostMapping("/newedition")
    public String saveNewEdiion(@ModelAttribute BookEdited bookEdited, ModelMap map) {

        booksEditedRepo.save(bookEdited);
        map.addAttribute("booksEdited", booksEditedRepo.findAll());

        return VIEW_BOOKS_EDITED;
    }

    @GetMapping("/edition/delete{id}")
    public String deleteEdition(@PathVariable("id") long id, ModelMap map) {

        booksEditedRepo.delete(id);
        map.addAttribute("booksEdited", booksEditedRepo.findAll());

        return VIEW_BOOKS_EDITED;
    }

    @GetMapping("/changeedition{id}")
    public String getViewChangeEdition(@PathVariable("id") long id, ModelMap map) {

        map.addAttribute("bookEdited", booksEditedRepo.findById(id));
        map.addAttribute("books", libRepo.findAll());
        map.addAttribute("publishers", pubRepo.findAll());

        return VIEW_CHANGE_EDITION;
    }

    @PostMapping("/changeedition")
    public String saveChangedEdition(@ModelAttribute BookEdited bookEdited, ModelMap map) {

        booksEditedRepo.save(bookEdited);
        map.addAttribute("booksEdited", booksEditedRepo.findAll());

        return VIEW_BOOKS_EDITED;
    }
}