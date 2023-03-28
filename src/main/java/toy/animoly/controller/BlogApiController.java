package toy.animoly.controller;

import lombok.Data;
import lombok.RequiredArgsConstructor;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import toy.animoly.service.BlogService;

@RestController
@RequiredArgsConstructor
public class BlogApiController {
    private final BlogService blogService;

    @PostMapping("/api/blogs/create")
    public Long create(CreateBlogRequest request) {
        return blogService.create(request.getId(), request.getTitle(), request.getContent());
    }

    @PutMapping("/api/blogs/{id}/update")
    public void update(@PathVariable("id") Long id,
                       UpdateBlogRequest request) {
        blogService.update(id, request.getTitle(), request.getContent());
    }

    @DeleteMapping("/api/blogs/{id}/delete")
    public ResponseEntity<String> delete(@PathVariable("id") Long id) {
        blogService.delete(id);
        return new ResponseEntity<String>("블로그 글 삭제 성공", HttpStatus.OK);
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

    @Data
    static class UpdateBlogRequest {
        private Long id;
        private String title;
        private String content;

        public UpdateBlogRequest(Long id, String title, String content) {
            this.id = id;
            this.title = title;
            this.content = content;
        }
    }
}
