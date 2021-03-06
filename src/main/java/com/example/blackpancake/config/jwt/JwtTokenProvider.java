package com.example.blackpancake.config.jwt;

import com.example.blackpancake.config.role.UserRole;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jws;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.security.Keys;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import javax.servlet.http.HttpServletRequest;
import java.security.Key;
import java.util.Date;
import java.util.List;

@Component
public class JwtTokenProvider {
    private final long TOKEN_VALID_MILISECONDS = 1000L * 60 * 60*10; // 1시간

    @Value("${jwt.secret}")
    private String secretKey;
    private Key key;

    private final LoginPwdValidator userDetailsService;

    public JwtTokenProvider(LoginPwdValidator userDetailsService) {
        this.userDetailsService = userDetailsService;
    }

    @PostConstruct
    protected void init(){
        key = Keys.hmacShaKeyFor(secretKey.getBytes());
    }

    // jwt 반환(정보 포함)
    public String createToken(String userEmail, List<UserRole> roles){
        Claims claims = Jwts.claims().setSubject(userEmail);
        claims.put("roles", roles);
        Date now = new Date();
        return Jwts.builder()
                .setClaims(claims)
                .setIssuedAt(now)
                .setExpiration(new Date(now.getTime() + TOKEN_VALID_MILISECONDS))
                .signWith(key, SignatureAlgorithm.HS512)
                .compact();
    }

//    public Authentication getAuthentication(String token){ //LoginPwdValidator 클래스사용 -> email, pwd, 권한정보 담음(UserDetails 객체)
//        UserDetails userDetails = userDetailsService.loadUserByUsername(this.getUserEmail(token));
//        return new UsernamePasswordAuthenticationToken(userDetails, "", userDetails.getAuthorities());
//    }

//    private String getUserEmail(String token) {
//        return Jwts.parserBuilder().setSigningKey(key).build()
//                .parseClaimsJws(token).getBody().getSubject();
//    }

    public Authentication getAuthentication(String token){ //LoginPwdValidator 클래스사용 -> email, pwd, 권한정보 담음(UserDetails 객체)
        UserDetails userDetails = userDetailsService.loadUserByUsername(getUserEmail(token));
        return new UsernamePasswordAuthenticationToken(userDetails, "", userDetails.getAuthorities());
    }

    public String getUserEmail(String token) {
        return Jwts.parserBuilder().setSigningKey(key).build()
                .parseClaimsJws(token).getBody().getSubject();
    }

    public String resolveToken(HttpServletRequest request){ //토큰 값 가져오기(헤더의 "X-AUTH-TOKEN" 값)
        return request.getHeader("X-AUTH-TOKEN");
    }

    public boolean validateToken(String jwtToken){ //토큰의 만료시간 확인
        try{
            Jws<Claims> claims = Jwts.parserBuilder().setSigningKey(key).build().parseClaimsJws(jwtToken);
            return !claims.getBody().getExpiration().before(new Date()); //token의 expiration이 만료되지 않았을때
        } catch(Exception e){
            //예외처리 추가
            return false;
        }
    }
}
























