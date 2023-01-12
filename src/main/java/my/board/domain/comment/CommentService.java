package my.board.domain.comment;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import my.board.domain.entity.Comment;
import my.board.domain.entity.Post;
import my.board.domain.entity.User;
import my.board.domain.post.PostRepository;
import my.board.domain.user.UserRepository;
import my.board.dto.CommentResponseDto;
import my.board.dto.CommentSaveDto;
import my.board.dto.PostUpdateDto;
import my.board.dto.SignupDto;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Slf4j
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

    public List<CommentResponseDto> findComments(Long postId) {
        log.info("postId = " + postId);

        List<CommentResponseDto> commentList= new ArrayList<>();
        Post findPost = postRepository.findById(postId).orElseThrow(
                ()-> new IllegalArgumentException("해당 게시글을 찾을 수 없습니다")
        );
        List<Comment> comments = findPost.getCommentList();
        log.info("comments = " + comments);
        for (Comment comment : comments) {
            commentList.add(new CommentResponseDto(comment));
        }

        return commentList;
    }


//    @Transactional
//    public void updatePost(Long postId, PostUpdateDto postUpdateDto) {
//        Post foundPost = postRepository.findById(postId).orElseThrow(
//                () -> new IllegalArgumentException("잘못된 요청입니다.")
//        );
//        foundPost.update(postUpdateDto);
//    }
    @Transactional
    public void delete(Long commentId) {
        commentRepository.deleteById(commentId);
    }

}
