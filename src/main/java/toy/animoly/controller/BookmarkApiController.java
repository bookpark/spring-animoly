package toy.animoly.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import toy.animoly.service.BookmarkService;

@RestController
@RequiredArgsConstructor
public class BookmarkApiController {
    private final BookmarkService bookmarkService;

    @GetMapping("/api/bookmarks")
    public Long createBookmark(String userId, Long desertionNo) {
        return bookmarkService.createBookmark(userId, desertionNo);
    }
}
