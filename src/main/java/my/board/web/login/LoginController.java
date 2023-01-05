package my.board.web.login;

import lombok.RequiredArgsConstructor;
import my.board.domain.login.LoginService;
import my.board.dto.LoginDto;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletResponse;

@Controller
@RequiredArgsConstructor
public class LoginController {

    private final LoginService userService;

    @GetMapping("/login")
    public ModelAndView loginPage() {
        return new ModelAndView("login");
    }

    @ResponseBody
    @PostMapping("/login")
    public String login(@RequestBody LoginDto loginDto, HttpServletResponse response) {
        userService.login(loginDto, response);
        return "success";
    }

}
