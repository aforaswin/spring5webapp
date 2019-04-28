package guru.springframework.spring5webapp.bootstarp;

import guru.springframework.spring5webapp.model.Author;
import guru.springframework.spring5webapp.model.Book;
import guru.springframework.spring5webapp.model.Publisher;
import guru.springframework.spring5webapp.repositories.AuthorRepository;
import guru.springframework.spring5webapp.repositories.BookRepository;
import guru.springframework.spring5webapp.repositories.PublisherRepository;
import org.omg.CORBA.PUBLIC_MEMBER;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationListener;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.stereotype.Component;

@Component
public class AswinBootStrap implements ApplicationListener<ContextRefreshedEvent> {

    private AuthorRepository authorRepository;
    private BookRepository bookRepository;
    private PublisherRepository publisherRepository;

    //When this object is created, repositories are autowired by spring using this constructor. ie Constructor auto-injection
    public AswinBootStrap(AuthorRepository authorRepository, BookRepository bookRepository, PublisherRepository publisherRepository) {
        this.authorRepository = authorRepository;
        this.bookRepository = bookRepository;
        this.publisherRepository = publisherRepository;
    }

    @Override
    public void onApplicationEvent(ContextRefreshedEvent contextRefreshedEvent) {
        initData();
    }

    private void initData() {

        Author ruskin = new Author("Ruskin", "Bond");
        Publisher penguin = new Publisher("Penguin", "123, darshan nagar, Tvpm-5");
        Book room = new Book("The Room on the Roof", "12121", penguin);
        ruskin.getBooks().add(room);
        room.getAuthors().add(ruskin);

        authorRepository.save(ruskin);
        publisherRepository.save(penguin);
        bookRepository.save(room);

    }
}
