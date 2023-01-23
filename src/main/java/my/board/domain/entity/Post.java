package my.board.domain.entity;

import lombok.Getter;
import lombok.NoArgsConstructor;
import my.board.dto.PostSaveDto;
import my.board.dto.PostUpdateDto;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Getter
@Entity
@NoArgsConstructor
public class Post extends Timestamped {

    @Id @GeneratedValue
    @Column(name = "post_id")
    private Long id;

    @Column(nullable = false)
    private String author;

    @Column(nullable = false)
    private String title;

    private String content;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id")
    private User user;

    @OneToMany(mappedBy = "post", orphanRemoval = true)
    private List<Comment> commentList = new ArrayList<>();

    private Integer totalLikes;

    public Post(PostSaveDto form,User user) {
        this.author = form.getAuthor();
        this.title = form.getTitle();
        this.content = form.getContent();
        this.user = user;
    }

    public Post(String author, String title, String content,User user) {
        this.author = author;
        this.title = title;
        this.content = content;
        this.user = user;
    }

    public void update(PostUpdateDto postUpdateDto) {
        this.author = postUpdateDto.getAuthor();
        this.title = postUpdateDto.getTitle();
        this.content = postUpdateDto.getContent();
    }

    public void plusLike() {
        this.totalLikes = totalLikes +1;
    }

    public void minusLike() {
            this.totalLikes = totalLikes;
    }

}
