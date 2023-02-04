package my.board;

import lombok.RequiredArgsConstructor;
import my.board.domain.comment.CommentRepository;
import my.board.domain.entity.Comment;
import my.board.domain.entity.Post;
import my.board.domain.entity.User;
import my.board.domain.jwt.JwtUtil;
import my.board.domain.post.PostRepository;
import my.board.domain.user.UserRepository;
import my.board.domain.user.UserRoleEnum;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;

@Component
@RequiredArgsConstructor
public class TestDataInit implements ApplicationRunner {

    private final PostRepository postRepository;
    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final CommentRepository commentRepository;

    /**
     * 테스트용 데이터 추가
     */
    @Override
    public void run(ApplicationArguments args) throws Exception {
        User user1 = new User("13", passwordEncoder.encode("13"), "테스터이메일", UserRoleEnum.USER);
        User user2 = new User("15", passwordEncoder.encode("15"), "테스터이메일2", UserRoleEnum.USER);
        userRepository.save(user1);
        userRepository.save(user2);
        Post post1 = postRepository.save(new Post("글쓴이1", "제목1", "내용1", user1));
        Post post2 = postRepository.save(new Post("글쓴이2", "제목2", "내용2", user2));
        commentRepository.save(new Comment("1233", "123", user1, post1));
        commentRepository.save(new Comment("1233", "123", user1, post2));
    }
}