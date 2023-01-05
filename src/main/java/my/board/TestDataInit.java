package my.board;

import lombok.RequiredArgsConstructor;
import my.board.domain.entity.Post;
import my.board.domain.entity.User;
import my.board.domain.post.PostRepository;
import my.board.domain.user.UserRepository;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;

@Component
@RequiredArgsConstructor
public class TestDataInit {

    private final PostRepository postRepository;
    private final UserRepository userRepository;

    /**
     * 테스트용 데이터 추가
     */
    public void init() {
        postRepository.save(new Post("글쓴이1", "제목1", "내용1"));
        postRepository.save(new Post("글쓴이2", "제목2", "내용2"));

        User user = new User("test", "test!","테스터이메일");

        userRepository.save(user);
    }

}