package my.board.domain.post;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import my.board.domain.entity.Post;
import my.board.dto.PostResponseDto;
import my.board.dto.PostUpdateDto;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

@Service
@Slf4j
@RequiredArgsConstructor
public class PostService {

    private final PostRepository postRepository;

    public List<PostResponseDto> findPosts() {
        List<PostResponseDto> postList = new ArrayList<>();
        List<Post> posts = postRepository.findAllByOrderByCreatedAtDesc();
        for (Post post : posts) {
            postList.add(new PostResponseDto(post));
        }

        return postList;
    }

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

    public Post findPost(Long postId) {
        Post post = postRepository.findById(postId).orElseThrow(
                () -> new IllegalArgumentException("잘못된 요청입니다.")
        );
        return post;
    }
}
