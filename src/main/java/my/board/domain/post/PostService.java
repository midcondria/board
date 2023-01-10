package my.board.domain.post;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import my.board.domain.entity.Post;
import my.board.dto.PostUpdateDto;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Service
@Slf4j
@RequiredArgsConstructor
public class PostService {

    private final PostRepository postRepository;

    @Transactional
    public void updatePost(Long postId, PostUpdateDto postUpdateDto) {
        Post foundPost = postRepository.findById(postId).orElseThrow(
                () -> new IllegalArgumentException("잘못된 요청입니다.")
        );
        foundPost.update(postUpdateDto);
    }

    @Transactional
    public void delete(Long postId) {
        postRepository.deleteById(postId);
    }
}
