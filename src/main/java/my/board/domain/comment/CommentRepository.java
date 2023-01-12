package my.board.domain.comment;


import my.board.domain.entity.Comment;
import my.board.domain.entity.Post;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface CommentRepository extends JpaRepository<Comment,Long> {
    Comment save(Comment comment);

    void deleteById(Long id);
}
