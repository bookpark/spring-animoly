package toy.animoly.service;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;
import toy.animoly.entity.Animal;
import toy.animoly.entity.Bookmark;
import toy.animoly.entity.Member;
import toy.animoly.repository.AnimalRepository;
import toy.animoly.repository.BookmarkRepository;
import toy.animoly.repository.MemberRepository;

import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
@Transactional
class BookmarkServiceTest {
    @Autowired
    MemberRepository memberRepository;
    @Autowired
    AnimalRepository animalRepository;
    @Autowired
    BookmarkRepository bookmarkRepository;
    @Autowired
    BookmarkService bookmarkService;

    @Test
    void createBookmark() {
        //given
        Member member = new Member();
        member.setId("Booki");
        memberRepository.save(member);
        assertEquals("Booki", member.getId());
        Animal animal = new Animal();
        animal.setDesertionNo(1L);
        animalRepository.save(animal);
        assertEquals(1L, animal.getDesertionNo());

        //when
        Bookmark bookmark = Bookmark.createBookmark(member, animal);
        bookmarkRepository.save(bookmark);

        //then
        assertEquals("Booki", bookmark.getMember().getId());
        assertEquals(1L, bookmark.getAnimal().getDesertionNo());
    }

    @Test
    void deleteBookmark() {
        //given
        Member member = new Member();
        member.setId("Booki");
        memberRepository.save(member);
        assertEquals("Booki", member.getId());
        Animal animal = new Animal();
        animal.setDesertionNo(1L);
        animalRepository.save(animal);
        assertEquals(1L, animal.getDesertionNo());
        Bookmark bookmark = Bookmark.createBookmark(member, animal);
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
        Member member = new Member();
        member.setId("bookpark");
        memberRepository.save(member);
        Animal animal = new Animal();
        animal.setDesertionNo(1L);
        animalRepository.save(animal);
        bookmarkService.createBookmark(member.getId(), animal.getDesertionNo());

        //when
        List<Bookmark> list = bookmarkService.getList(member.getId());

        //then
        assertEquals(1, list.size());
    }
}