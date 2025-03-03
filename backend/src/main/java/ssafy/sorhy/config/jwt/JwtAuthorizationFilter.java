package ssafy.sorhy.config.jwt;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.web.authentication.www.BasicAuthenticationFilter;
import ssafy.sorhy.config.auth.PrincipalDetails;
import ssafy.sorhy.domain.user.User;
import ssafy.sorhy.repository.user.UserRepository;

import java.io.IOException;

import static ssafy.sorhy.config.jwt.JwtProperties.*;

public class JwtAuthorizationFilter extends BasicAuthenticationFilter {

    private UserRepository userRepository;

    public JwtAuthorizationFilter(AuthenticationManager authenticationManager, UserRepository userRepository) {

        super(authenticationManager);
        this.userRepository = userRepository;
    }

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain chain)
            throws IOException, ServletException {

        System.out.println("권한이나 인증이 요청됨");

        String jwtHeader = request.getHeader("Authorization");
        System.out.println("jwtHeader = " + jwtHeader);

        // header가 있는지 확인
        if (jwtHeader == null || !jwtHeader.startsWith("Bearer")) {
            chain.doFilter(request, response);
            return;
        }

        //JWT토큰을 검증해서 정상적인 사용자인지 확인
        String jwtToken = request.getHeader(HEADER_STRING).replace(TOKEN_PREFIX, "");

        String username =
                JWT.require(Algorithm.HMAC512(SECRET)).build().verify(jwtToken).getClaim("username").asString();

        // 서명이 정상적으로 됨
        if (username != null) {

            User user = userRepository.findByNickname(username).orElseThrow(() -> new UsernameNotFoundException(username));

            PrincipalDetails principalDetails = new PrincipalDetails(user);

            //JWT 토큰 서명을 통해 서명이 정상이면 Authentication 객체를 만든다.
            Authentication authentication =
                    new UsernamePasswordAuthenticationToken(principalDetails, null, principalDetails.getAuthorities());
            // 강제로 시큐리티 세션에 접근하여 Authentication 객체 저장
            SecurityContextHolder.getContext().setAuthentication(authentication);
        }

        chain.doFilter(request, response);
    }
}
