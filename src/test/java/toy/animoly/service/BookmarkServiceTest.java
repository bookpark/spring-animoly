package toy.animoly.service;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;
import toy.animoly.entity.Animal;
import toy.animoly.entity.Bookmark;
import toy.animoly.entity.User;
import toy.animoly.repository.AnimalRepository;
import toy.animoly.repository.BookmarkRepository;
import toy.animoly.repository.UserRepository;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
@Transactional
class BookmarkServiceTest {
    @Autowired
    UserRepository userRepository;
    @Autowired
    AnimalRepository animalRepository;
    @Autowired
    BookmarkRepository bookmarkRepository;
    @Test
    void createBookmark() {
        //given
        User user = new User();
        user.setId("Booki");
        userRepository.save(user);
        assertEquals("Booki", user.getId());
        Animal animal = new Animal();
        animal.setId(1L);
        animalRepository.save(animal);
        assertEquals(1L, animal.getId());

        //when
        Bookmark bookmark = Bookmark.createBookmark(user, animal);
        bookmarkRepository.save(bookmark);

        //then
        assertEquals("Booki", bookmark.getUser().getId());
        assertEquals(1L, bookmark.getAnimal().getId());
    }

    @Test
    void deleteBookmark() {
        //given
        User user = new User();
        user.setId("Booki");
        userRepository.save(user);
        assertEquals("Booki", user.getId());
        Animal animal = new Animal();
        animal.setId(1L);
        animalRepository.save(animal);
        assertEquals(1L, animal.getId());
        Bookmark bookmark = Bookmark.createBookmark(user, animal);
        bookmarkRepository.save(bookmark);
        Long id = bookmark.getId();
        System.out.println(id);
        assertEquals(6L, bookmark.getId());

        //when
        bookmarkRepository.delete(bookmark);

        //then
        assertEquals(Optional.empty(), bookmarkRepository.findById(bookmark.getId()));
    }
}