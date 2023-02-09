package jwt;

import com.ourcompany.content.service.UserService;
import io.jsonwebtoken.*;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;
import role.Role;

import javax.annotation.PostConstruct;
import javax.servlet.http.HttpServletRequest;
import java.nio.charset.StandardCharsets;
import java.util.Base64;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

@Component
public class JwtTokenProvider {

    private final UserService userService;

    public JwtTokenProvider(UserService userService) {
        this.userService = userService;
    }

    @Value("${jwt.token.secret}")
    private String secret;

    @Value("${jwt.token.validate}")
    private Long validateInMillisecond;

    @PostConstruct
    public void init(){
        secret = Base64.getEncoder().encodeToString(secret.getBytes(StandardCharsets.UTF_8));
    }

    public Map<String, Object> createToken(String email, Role role){
        Claims claims = Jwts.claims().setSubject(email);
        claims.put("role", role);

        Date issue = new Date();
        Date expiresAt = new Date(issue.getTime() + validateInMillisecond);

        String token = Jwts.builder().setClaims(claims).setIssuedAt(issue).setExpiration(expiresAt).signWith(SignatureAlgorithm.HS256, secret).compact();
        Map<String, Object> map = new HashMap<>();
        map.put("token", token);
        map.put("expires_At", expiresAt);
        map.put("token_type", "Bearer");
        return map;
    }

    public String getEmail(String token){
        return Jwts.parser().setSigningKey(secret).parseClaimsJws(token).getBody().getSubject();
    }

    public Authentication authentication(String token){
        UserDetails userDetails = userService.loadUserByUsername(getEmail(token));
        return new UsernamePasswordAuthenticationToken(userDetails, "", userDetails.getAuthorities());
    }

    public String resolveToken(HttpServletRequest httpServletRequest){
        String bearer = httpServletRequest.getHeader("Authorization");
        if(bearer != null && bearer.startsWith("Bearer ")){
            return bearer.substring(7);
        }
        return null;
    }

    public boolean validateToken(String token){
        try {
            Jws<Claims> jws = Jwts.parser().setSigningKey(secret).parseClaimsJws(token);
            return !jws.getBody().getExpiration().before(new Date());
        }
        catch (Exception e){
            return false;
        }
    }
}
