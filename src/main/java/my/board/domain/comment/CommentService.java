package my.board.domain.comment;

import lombok.RequiredArgsConstructor;
import my.board.domain.entity.Comment;
import my.board.domain.entity.Post;
import my.board.domain.entity.User;
import my.board.domain.post.PostRepository;
import my.board.domain.user.UserRepository;
import my.board.dto.CommentSaveDto;
import my.board.dto.SignupDto;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class CommentService {

    private final CommentRepository commentRepository;
    private final PostRepository postRepository;

    @Transactional
    public void commentSave(Long postId,CommentSaveDto commentSaveDto,User user) {
        Post post = postRepository.findById(postId).orElseThrow(
                () -> new IllegalArgumentException("해당 게시글을 찾을 수 없습니다")
        );
        commentRepository.save(new Comment(commentSaveDto, user, post));
    }

    public List<Comment> commentList(Long postId) {
        return commentRepository.findAllByIdOrderByCreatedAtAsc(postId);
    }
}
