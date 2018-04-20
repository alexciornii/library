package md.esempla.library.domain;


import lombok.*;

import javax.persistence.*;

@Entity
@Table(name = "books_edited")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class BookEdited {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @ManyToOne
    @JoinColumn(name = "book_id")
    private Book book;

    @ManyToOne
    @JoinColumn(name = "publisher_id")
    private Publisher publisher;

    public BookEdited(Book book, Publisher publisher) {
        this.book = book;
        this.publisher = publisher;
    }
}