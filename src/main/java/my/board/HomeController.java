package my.board;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import my.board.domain.user.UserRepository;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@RestController
@RequiredArgsConstructor
public class HomeController {

    private final UserRepository userRepository;

    @GetMapping("/")
    public String home() {
        return "help";
    }
}