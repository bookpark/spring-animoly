package toy.animoly.controller;

import lombok.Data;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;
import toy.animoly.entity.Bookmark;
import toy.animoly.service.BookmarkService;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequiredArgsConstructor
public class BookmarkApiController {
    private final BookmarkService bookmarkService;

    @GetMapping("/api/bookmarks")
    public Long createBookmark(String userId, Long desertionNo) {
        return bookmarkService.createBookmark(userId, desertionNo);
    }

    @GetMapping("/api/{id}/bookmarks")
    public List<BookmarkDto> getBookmarkList(@PathVariable("id") String id) {
        List<Bookmark> bookmarks = bookmarkService.getList(id);
        return bookmarks.stream()
                .map(BookmarkDto::new)
                .collect(Collectors.toList());
    }

    @Data
    public static class BookmarkDto {
        private Long bookmarkId;
        private String userId;
        private Long desertionNo;

        public BookmarkDto(Bookmark bookmark) {
            bookmarkId = bookmark.getId();
            userId = bookmark.getUser().getId();
            desertionNo = bookmark.getAnimal().getDesertionNo();
        }
    }
}
