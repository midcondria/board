package my.board.domain.post;

import my.board.domain.entity.Post;
import my.board.dto.PostUpdateDto;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface PostRepository extends JpaRepository<Post, Long> {
    List<Post> findAllByOrderByCreatedAtDesc();

    Optional<Post> findById(Long id);

    Post save(Post post);

    void deleteById(Long id);
}
