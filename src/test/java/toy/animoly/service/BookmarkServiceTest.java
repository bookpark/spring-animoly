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

import java.util.List;
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
    @Autowired
    BookmarkService bookmarkService;

    @Test
    void createBookmark() {
        //given
        User user = new User();
        user.setId("Booki");
        userRepository.save(user);
        assertEquals("Booki", user.getId());
        Animal animal = new Animal();
        animal.setDesertionNo(1L);
        animalRepository.save(animal);
        assertEquals(1L, animal.getDesertionNo());

        //when
        Bookmark bookmark = Bookmark.createBookmark(user, animal);
        bookmarkRepository.save(bookmark);

        //then
        assertEquals("Booki", bookmark.getUser().getId());
        assertEquals(1L, bookmark.getAnimal().getDesertionNo());
    }

    @Test
    void deleteBookmark() {
        //given
        User user = new User();
        user.setId("Booki");
        userRepository.save(user);
        assertEquals("Booki", user.getId());
        Animal animal = new Animal();
        animal.setDesertionNo(1L);
        animalRepository.save(animal);
        assertEquals(1L, animal.getDesertionNo());
        Bookmark bookmark = Bookmark.createBookmark(user, animal);
        bookmarkRepository.save(bookmark);
        Long id = bookmark.getId();
        System.out.println(id);
        assertEquals(1L, bookmark.getId());

        //when
        bookmarkService.deleteBookmark(bookmark.getId());

        //then
        assertEquals(Optional.empty(), bookmarkRepository.findById(bookmark.getId()));
    }

    @Test
    void getList() {
        //given
        User user = new User();
        user.setId("bookpark");
        userRepository.save(user);
        Animal animal = new Animal();
        animal.setDesertionNo(1L);
        animalRepository.save(animal);
        bookmarkService.createBookmark(user.getId(), animal.getDesertionNo());

        //when
        List<Bookmark> list = bookmarkService.getList(user.getId());

        //then
        assertEquals(1, list.size());
    }
}