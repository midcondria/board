package my.board.dto;

import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import my.board.domain.entity.Comment;
import my.board.domain.entity.Post;

import javax.persistence.Column;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;


@Data
@Slf4j
@NoArgsConstructor
public class PostResponseDto {

    private Long id;

    private String author;

    private String title;

    private String content;

    private LocalDateTime createdAt;

    private List<CommentResponseDto> commentList = new ArrayList<>();

    public PostResponseDto(Post post) {
        this.id = post.getId();
        this.author = post.getAuthor();
        this.title = post.getTitle();
        this.content = post.getContent();
        this.createdAt = post.getCreatedAt();
        List<Comment> commentList1 = post.getCommentList();
        log.info("commentList = " + commentList.size());
        for (Comment comment : commentList1) {
            this.commentList.add(new CommentResponseDto(comment));
        }
    }


}
