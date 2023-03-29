package toy.animoly.controller;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.RequiredArgsConstructor;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import toy.animoly.entity.Blog;
import toy.animoly.entity.User;
import toy.animoly.service.BlogService;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequiredArgsConstructor
public class BlogApiController {
    private final BlogService blogService;

    @PostMapping("/api/blogs/create")
    public Long create(CreateBlogRequest request) {
        return blogService.create(request.getId(), request.getTitle(), request.getContent());
    }

    @GetMapping("/api/blogs/v1")
    public List<BlogListDTO> getListV1() {
        List<Blog> blogs = blogService.getList();
        return blogs.stream()
                .map(b -> new BlogListDTO(
                        b.getId(),
                        b.getTitle(),
                        b.getContent(),
                        b.getCreatedAt(),
                        b.getUser().getNickname()
                )).collect(Collectors.toList());
    }

    @GetMapping("/api/blogs/v2")
    public Result getListV2() {
        List<Blog> blogs = blogService.getList();
        List<BlogListDTO> collect = blogs.stream()
                .map(b -> new BlogListDTO(
                        b.getId(),
                        b.getTitle(),
                        b.getContent(),
                        b.getCreatedAt(),
                        b.getUser().getNickname()
                )).collect(Collectors.toList());
        return new Result(collect);
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
    @AllArgsConstructor
    static class Result<T> {
        private T data;
    }

    @Data
    @AllArgsConstructor
    static class BlogListDTO {
        private Long id;
        private String title;
        private String content;
        private LocalDateTime createdAt;
        private String nickname;
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
