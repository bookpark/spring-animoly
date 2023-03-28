package toy.animoly.controller;

import lombok.Data;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;
import toy.animoly.service.BlogService;

@RestController
@RequiredArgsConstructor
public class BlogApiController {
    private final BlogService blogService;

    @PostMapping("/api/blogs/create")
    public Long create(CreateBlogRequest request) {
        return blogService.create(request.getId(), request.getTitle(), request.getContent());
    }

    @Data
    static class CreateBlogRequest {
        private String id;
        private String title;
        private String content;

        public CreateBlogRequest(String id, String title, String content) {
            this.id = id;
            this.title = title;
            this.content = content;
        }
    }
}
