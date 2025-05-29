
package edu.mamontova.lab7;

import edu.mamontova.lab7.Utils.Utils;
import edu.mamontova.lab7.model.Book;
import edu.mamontova.lab7.repository.BookRepository;
import edu.mamontova.lab7.request.BookCreateRequest;
import edu.mamontova.lab7.request.BookUpdateRequest;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultActions;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

import static org.junit.jupiter.api.Assertions.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@SpringBootTest
@AutoConfigureMockMvc
public class IntegrationTests {

        @Autowired
        private MockMvc mockMvc;

        @Autowired
        private BookRepository repository;



        private List<Book> books = new ArrayList<>();

    @BeforeEach
    void setUp() {
        repository.deleteAll(); // очистити для безпечного середовища

        books = new ArrayList<>();
        books.add(repository.save(new Book(null, "Freddie Mercury", "0001", "vocal")));
        books.add(repository.save(new Book(null, "Paul McCartney", "0002", "bass")));
        books.add(repository.save(new Book(null, "Mick Jagger", "0003", "vocals")));
    }


    @AfterEach
    void tearDown() {
        repository.deleteAll();
        books.clear();
    }



    @Test
    void itShouldCreateNewItem() throws Exception {
        // given
        BookCreateRequest request = new BookCreateRequest(
                "Pride and prejustice", "000B3", "desc");
        // when
        ResultActions perform = mockMvc.perform(post("http://localhost:8080/api/v1/books/dto")
                .contentType(MediaType.APPLICATION_JSON)
                .content(Utils.toJson(request)));

        //then
        Book book = repository.findAll()
                .stream()
                .filter(it -> it.getCode().equals(request.code()))
                .findFirst().orElse(null);

        perform.andExpect(status().isOk());
        assertThat(repository.existsByCode(request.code())).isTrue();
        assertNotNull(book);
        assertNotNull(book.getId());
        assertThat(book.getId()).isNotEmpty();
        assertThat(book.getId().length()).isEqualTo(24);
        assertThat(book.getDescription()).isEqualTo(request.description());
        assertThat(book.getName()).isEqualTo(request.name());
        assertThat(book.getCode()).isEqualTo(request.code());
        assertThat(book.getUpdateDate()).isEmpty();
        assertThat(book.getCreateDate()).isNotNull();
        //   assertSame(LocalDateTime.class, item.getCreateDate());
    }

    @Test
    void itShouldNotCreateBookWithExistingCode() throws Exception {
        BookCreateRequest request = new BookCreateRequest("New Name", "Queen", "new desc");

        mockMvc.perform(post("/api/v1/books/dto")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(Utils.toJson(request)))
                .andExpect(status().isOk());

        long count = repository.findAll().stream()
                .filter(book -> book.getCode().equals("Queen")).count();

        assertThat(count).isEqualTo(1); // не створилось дубліката
    }



    @Test
    void itShouldNotUpdateNonExistentBook() throws Exception {
        BookUpdateRequest request = new BookUpdateRequest(
                "999999999999999999999999", "Ghost", "Z999", "not exist");

        mockMvc.perform(put("/api/v1/books/dto")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(Utils.toJson(request)))
                .andExpect(status().isOk());

        boolean exists = repository.findAll()
                .stream()
                .anyMatch(book -> book.getCode().equals("Z999"));
        assertThat(exists).isFalse();
    }

    @Test
    void itShouldUpdateBookUsingRawBook() throws Exception {
        Book existing = repository.findAll().get(1);
        existing.setName("Changed Name");

        mockMvc.perform(put("/api/v1/books/")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(Utils.toJson(existing)))
                .andExpect(status().isOk());

        Book updated = repository.findById(existing.getId()).orElse(null);
        assertThat(updated.getName()).isEqualTo("Changed Name");
    }

    @Test
    void itShouldGetBookById() throws Exception {
        Book existing = repository.findAll().get(0);

        mockMvc.perform(get("/api/v1/books/" + existing.getId()))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.name").value(existing.getName()));
    }

    @Test
    void itShouldReturnNullForNonExistingId() throws Exception {
        mockMvc.perform(get("/api/v1/books/999999999999999999999999"))
                .andExpect(status().isOk())
                .andExpect(content().string(""));
    }

    @Test
    void itShouldGetAllBooks() throws Exception {
        mockMvc.perform(get("/api/v1/books/"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.length()").value(3));
    }

    @Test
    void itShouldDeleteBook() throws Exception {
        Book existing = repository.findAll().get(0);

        mockMvc.perform(delete("/api/v1/books/" + existing.getId()))
                .andExpect(status().isOk());

        assertThat(repository.findById(existing.getId())).isEmpty();
    }

    @Test
    void itShouldNotFailDeletingNonExistingId() throws Exception {
        mockMvc.perform(delete("/api/v1/books/999999999999999999999999"))
                .andExpect(status().isOk());

    }

    @Test
    void itShouldCreateBookWithoutDescription() throws Exception {
        BookCreateRequest request = new BookCreateRequest("No Desc Book", "CODE11", null);

        mockMvc.perform(post("/api/v1/books/dto")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(Utils.toJson(request)))
                .andExpect(status().isOk());

        Book book = repository.findAll().stream()
                .filter(b -> "CODE11".equals(b.getCode()))
                .findFirst().orElse(null);
        assertNotNull(book);
        assertNull(book.getDescription());
    }

    @Test
    void itShouldCreateBookWithEmptyName() throws Exception {
        BookCreateRequest request = new BookCreateRequest("", "CODE12", "Empty name");

        mockMvc.perform(post("/api/v1/books/dto")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(Utils.toJson(request)))
                .andExpect(status().isOk());

        Book book = repository.findAll().stream()
                .filter(b -> "CODE12".equals(b.getCode()))
                .findFirst().orElse(null);
        assertNotNull(book);
        assertEquals("", book.getName());
    }



    @Test
    void itShouldReturnEmptyForInvalidObjectIdGet() throws Exception {
        mockMvc.perform(get("/api/v1/books/invalid-id"))
                .andExpect(status().isOk())
                .andExpect(content().string(""));
    }

    @Test
    void itShouldNotFailDeleteForInvalidObjectId() throws Exception {
        mockMvc.perform(delete("/api/v1/books/invalid-id"))
                .andExpect(status().isOk());
    }

    @Test
    void itShouldReturnEmptyListAfterDeletingAll() throws Exception {
        repository.deleteAll();

        mockMvc.perform(get("/api/v1/books/"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.length()").value(0));
    }

    @Test
    void itShouldCreateBookWithLowercaseCode() throws Exception {
        BookCreateRequest request = new BookCreateRequest("Lowercase Code", "lowercode", "desc");

        mockMvc.perform(post("/api/v1/books/dto")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(Utils.toJson(request)))
                .andExpect(status().isOk());

        assertThat(repository.existsByCode("lowercode")).isTrue();
    }



    @Test
    void itShouldCreateBookWithVeryLongDescription() throws Exception {
        String longDesc = "x".repeat(1000);
        BookCreateRequest request = new BookCreateRequest("Long Desc Book", "CODE19", longDesc);

        mockMvc.perform(post("/api/v1/books/dto")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(Utils.toJson(request)))
                .andExpect(status().isOk());

        Book book = repository.findAll().stream()
                .filter(b -> "CODE19".equals(b.getCode()))
                .findFirst().orElse(null);
        assertNotNull(book);
        assertEquals(longDesc, book.getDescription());
    }



}