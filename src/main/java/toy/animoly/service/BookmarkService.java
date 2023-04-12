package toy.animoly.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import toy.animoly.entity.Animal;
import toy.animoly.entity.Bookmark;
import toy.animoly.entity.Member;
import toy.animoly.repository.AnimalRepository;
import toy.animoly.repository.BookmarkRepository;
import toy.animoly.repository.MemberRepository;

import java.util.List;

@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class BookmarkService {
    private final BookmarkRepository bookmarkRepository;
    private final MemberRepository memberRepository;
    private final AnimalRepository animalRepository;

    @Transactional
    public Long createBookmark(String userId, Long desertionNo) {
        Member getMember = memberRepository.findById(userId).orElseThrow();
        Animal getAnimal = animalRepository.findByDesertionNo(desertionNo);
        Bookmark bookmark = Bookmark.createBookmark(getMember, getAnimal);
        return bookmark.getId();
    }

    @Transactional
    public void deleteBookmark(Long id) {
        Bookmark bookmark = bookmarkRepository.findById(id).orElseThrow();
        bookmarkRepository.delete(bookmark);
    }

    public List<Bookmark> getList(String userId) {
        Member member = memberRepository.findById(userId).orElseThrow();
        return member.getBookmarks();
    }
}
