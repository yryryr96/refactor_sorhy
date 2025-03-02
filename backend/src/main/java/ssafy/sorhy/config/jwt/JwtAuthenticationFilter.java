package ssafy.sorhy.config.jwt;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.fasterxml.jackson.databind.ObjectMapper;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import ssafy.sorhy.config.auth.PrincipalDetails;
import ssafy.sorhy.config.auth.dto.LoginRequestDto;

import java.io.IOException;
import java.util.Date;

import static ssafy.sorhy.config.jwt.JwtProperties.*;

@RequiredArgsConstructor
public class JwtAuthenticationFilter extends UsernamePasswordAuthenticationFilter {

    private final AuthenticationManager authenticationManager;

    // /login 요청 시 로그인 시도를 위해 실행되는 함수
    @Override
    public Authentication attemptAuthentication(HttpServletRequest request, HttpServletResponse response) throws AuthenticationException {
        System.out.println("로그인 시도중 attemptAuthentication()");

        // 1. username, password 받아서

        // 2. 로그인 시도. authenticationManager 를 사용해 로그인을 시도하면
        // PrincipalDetailsService 호출 -> loadUserByUsername() 실행

        // 3. PrincipalDetails를 세션에 담고 (권한 관리를 위해서)

        // 4. JWT토큰을 생성하여 응답
        ObjectMapper objectMapper = new ObjectMapper();
        LoginRequestDto loginRequestDto = null;
        try {
            loginRequestDto = objectMapper.readValue(request.getInputStream(), LoginRequestDto.class);
        } catch (IOException e) {
            e.printStackTrace();
        }

        UsernamePasswordAuthenticationToken authenticationToken =
                new UsernamePasswordAuthenticationToken(loginRequestDto.getUsername(), loginRequestDto.getPassword());

        // PrincipalDetailsService의 loadUserByUsername() 함수 실행 -> authentication 반환
        Authentication authentication
                = authenticationManager.authenticate(authenticationToken);

        // authentication 객체가 session 영역에 저장된다. -> 로그인 성공
        PrincipalDetails principalDetails = (PrincipalDetails) authentication.getPrincipal();

        return authentication;
    }

    // attemptAuthentication 실행 후 인증이 정상적으로 되었으면 successfulAuthentication 함수가 실행된다.
    // JWT 토큰을 만들어 request 요청한 사용자에게 JWT 토큰을 response 해주면 된다.
    @Override
    protected void successfulAuthentication(HttpServletRequest request, HttpServletResponse response, FilterChain chain, Authentication authResult)
            throws IOException, ServletException {
        System.out.println("successfulAuthentication() = " + "인증이 완료되었다는 뜻");

        PrincipalDetails principalDetails = (PrincipalDetails) authResult.getPrincipal();

        // Hash 암호 방식
        String jwtToken = JWT.create()
                .withSubject("jwt토큰")
                .withExpiresAt(new Date(System.currentTimeMillis() + (EXPIRATION_TIME)))
                .withClaim("id", principalDetails.getUser().getId())
                .withClaim("username", principalDetails.getUser().getNickname())
                .sign(Algorithm.HMAC512(SECRET));

        response.addHeader(HEADER_STRING, TOKEN_PREFIX + jwtToken);
    }
}
