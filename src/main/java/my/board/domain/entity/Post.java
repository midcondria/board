package my.board.domain.entity;

import lombok.Getter;
import my.board.dto.PostSaveDto;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

@Getter
@Entity
public class Post extends Timestamped {

    @Id @GeneratedValue
    private Long id;

    @Column(nullable = false,name = "username")
    private String author;

    @Column(nullable = false)
    private String title;

    private String content;

    public Post() {
    }

    public Post(PostSaveDto form) {
        this.author = form.getAuthor();
        this.title = form.getTitle();
        this.content = form.getContent();
    }

    public Post(String author, String title, String content) {
        this.author = author;
        this.title = title;
        this.content = content;
    }
}
