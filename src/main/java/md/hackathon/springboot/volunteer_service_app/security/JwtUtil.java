package md.hackathon.springboot.volunteer_service_app.security;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class JwtUtil {

    @Value("${jwt.secret}")
    private String secret;

    public String getUserFromToken(String token) {
        return getClaimsFromToken(token).getSubject();
    }

    public List<String> getRolesFromToken(String token) {
        return (List<String>) getClaimsFromToken(token).get("roles");
    }

    private Claims getClaimsFromToken(String token) {
        return Jwts.parser().setSigningKey(secret).parseClaimsJws(token).getBody();
    }
}
