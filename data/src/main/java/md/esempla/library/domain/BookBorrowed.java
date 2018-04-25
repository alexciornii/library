package md.esempla.library.domain;

import lombok.*;

import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
import java.util.Date;

@Entity
@Table(name = "books_borrowed")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class BookBorrowed {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @Column(name = "date_start")
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private Date date;

    @ManyToOne
    @JoinColumn(name = "client_id")
    private Client client;

    @ManyToOne
    @JoinColumn(name = "book_id")
    private Book book;

    public BookBorrowed(Date date, Client client, Book book) {
        this.date = date;
        this.client = client;
        this.book = book;
    }
}
