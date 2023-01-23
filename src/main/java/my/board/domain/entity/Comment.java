package my.board.domain.entity;

import lombok.AllArgsConstructor;
import lombok.Getter;
import my.board.dto.CommentSaveDto;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Getter
@AllArgsConstructor
public class Comment extends Timestamped{

    @Id
    @GeneratedValue
    private Long id;

    @Column(nullable = false)
    private String author;

    @Column(nullable = false)
    private String text;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id")
    private User user;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "post_id")
    private Post post;

    private Integer totalLikes;


    public void setPost(Post post) {
        this.post = post;
        post.getCommentList().add(this);
    }

    public Comment() {
    }

    public Comment(String author, String text, User user, Post post) {
        this.author = author;
        this.text = text;
        this.user = user;
        this.post = post;
    }

    public Comment(CommentSaveDto commentSaveDto,User user, Post post) {
        this.author = commentSaveDto.getAuthor();
        this.text = commentSaveDto.getText();
        this.user = user;
        this.post = post;
        this.setPost(post);
    }

    public void plusLike() {
        this.totalLikes = totalLikes +1;
    }

    public void minusLike() {
        this.totalLikes = totalLikes -1;
    }
}
