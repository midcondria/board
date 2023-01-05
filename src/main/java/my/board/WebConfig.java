package my.board;

import lombok.RequiredArgsConstructor;
import my.board.domain.jwt.JwtUtil;
import my.board.domain.user.UserRepository;
import my.board.web.interceptor.LoginCheckInterceptor;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

//@Configuration
@RequiredArgsConstructor
public class WebConfig implements WebMvcConfigurer {

    private final JwtUtil jwtUtil;
    private final UserRepository userRepository;
    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(new LoginCheckInterceptor(jwtUtil,userRepository))
                .order(1)
                .addPathPatterns("/**")
                .excludePathPatterns("/","/users/signup","/login","/posts/add",
                        "/css/**","/*.ico","/error");

    }
}
