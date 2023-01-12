package my.board.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import my.board.domain.entity.Comment;

import java.time.LocalDateTime;

@Data
@NoArgsConstructor
public class CommentResponseDto {

    private Long id;

    private String author;

    private String text;

    private LocalDateTime createdAt;

    public CommentResponseDto(Comment comment) {
        this.id = comment.getId();
        this.author = comment.getAuthor();
        this.text = comment.getText();
        this.createdAt = comment.getCreatedAt();
    }
}
