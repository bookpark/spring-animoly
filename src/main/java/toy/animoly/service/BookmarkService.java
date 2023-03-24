package toy.animoly.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import toy.animoly.entity.Animal;
import toy.animoly.entity.Bookmark;
import toy.animoly.entity.User;
import toy.animoly.repository.AnimalRepository;
import toy.animoly.repository.BookmarkRepository;
import toy.animoly.repository.UserRepository;

@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class BookmarkService {
    private final BookmarkRepository bookmarkRepository;
    private final UserRepository userRepository;
    private final AnimalRepository animalRepository;

    @Transactional
    public Long createBookmark(String userId, Long desertionNo) {
        User getUser = userRepository.findById(userId).orElseThrow();
        Animal getAnimal = animalRepository.findByDesertionNo(desertionNo);
        Bookmark bookmark = Bookmark.createBookmark(getUser, getAnimal);
        return bookmark.getId();
    }

    @Transactional
    public void deleteBookmark(Long id) {
        Bookmark bookmark = bookmarkRepository.findById(id).orElseThrow();
        bookmarkRepository.delete(bookmark);
    }
}
