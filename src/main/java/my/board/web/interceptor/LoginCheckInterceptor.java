package my.board.web.interceptor;

import io.jsonwebtoken.Claims;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import my.board.domain.entity.User;
import my.board.domain.jwt.JwtUtil;
import my.board.domain.user.UserRepository;
import org.springframework.util.StringUtils;
import org.springframework.web.servlet.HandlerInterceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

@Slf4j
@RequiredArgsConstructor
public class LoginCheckInterceptor implements HandlerInterceptor {

    public static final String AUTHORIZATION_HEADER = "Authorization";
    public static final String AUTHORIZATION_KEY = "auth";
    private static final String BEARER_PREFIX = "Bearer ";

    private final JwtUtil jwtUtil;
    private final UserRepository userRepository;

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) {

        log.info("검증 시작");
        log.info("header = " +request.getHeader(""));
        String token = request.getHeader(AUTHORIZATION_HEADER);
        log.info("token before: "+ token);
        if (StringUtils.hasText(token) && token.startsWith(BEARER_PREFIX)) {
            token = token.substring(7);
        }

        log.info("token = "+token);
        Claims claims;

        // 토큰 검증
        if (token != null) {
            log.info("토큰 검증");
            if (jwtUtil.validateToken(token)) {
                // 토큰에서 사용자 정보 가져오기
                claims = jwtUtil.getUserInfoFromToken(token);
            } else {
                throw new IllegalArgumentException("Token Error");
            }

            // 토큰에서 가져온 사용자 정보를 사용하여 DB 조회
            User user = userRepository.findByUsername(claims.getSubject()).orElseThrow(
                    () -> new IllegalArgumentException("사용자가 존재하지 않습니다.")
            );
            log.info("토큰 검증 성공");
            return true;
        }
        log.info("토큰 검증 실패");
        return false;
    }
}
//
//
//
//        String requestURI = request.getRequestURI();
//
//        log.info("인증 체크 인터셉터 실행 {}", requestURI);
//
//        HttpSession session = request.getSession();
//
//        if (session == null || session.getAttribute(SessionConst.LOGIN_MEMBER) == null) {
//            log.info("미인증 사용자 요청");
//            //로그인으로 redirect
//            response.sendRedirect("/login?redirectURL=" + requestURI);
//            return false;
//        }
//        return true;
