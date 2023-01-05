package my.board.web.user;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import my.board.domain.user.UserService;
import my.board.dto.SignupDto;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

@Slf4j
@Controller
@RequiredArgsConstructor
@RequestMapping("/users")
public class UserController {

    private final UserService userService;

    @GetMapping("signup")
    public String signupPage() {
        log.info("Get UserController");
        return "users/signup";
    }

    @PostMapping("signup")
    public String signup(SignupDto signupDto) {
        log.info("form: "+signupDto.getUsername());
        userService.signup(signupDto);
        return "redirect:/login";
    }
}
