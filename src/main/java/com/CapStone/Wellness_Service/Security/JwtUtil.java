package com.CapStone.Wellness_Service.Security;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.security.Keys;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

import javax.crypto.SecretKey;
import java.time.Instant;
import java.util.Base64;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.TimeUnit;

import static javax.crypto.Cipher.SECRET_KEY;
import static org.springframework.cache.interceptor.SimpleKeyGenerator.generateKey;
@Service
public class JwtUtil {

    private  final String SECRET_KEY = "36F4A8EB4B18CAC332F9DBA5DC1E1775D6EB45BCDD6F9F90B741791B65724758";  // Use environment variable in production
    private final long EXPIRATION_TIME = TimeUnit.MINUTES.toMillis(30);

    // Generate a JWT token for a given UserDetails object
    public String generateToken(UserDetails userDetails) {
        Map<String, String> claims = new HashMap<>();
        claims.put("iss", "SecureAPI");
        claims.put("aud", "Authorized Users");
        claims.put("roles", userDetails.getAuthorities().toString());
        return Jwts.builder()
                .claims(claims)
                .subject(userDetails.getUsername())
                .issuedAt(Date.from(Instant.now()))
                .expiration(Date.from(Instant.now().plusMillis(EXPIRATION_TIME)))
                .signWith(generateKey())
                .compact();}


    private SecretKey generateKey() {
        byte[] decodedKey = Base64.getDecoder().decode(SECRET_KEY);
        return Keys.hmacShaKeyFor(decodedKey);
    }

    public boolean validateToken(String token){
        Claims claims = getClaims(token);
        return claims.getExpiration().after(Date.from(Instant.now()));}

    private Claims getClaims(String token) {
        Claims claims = Jwts.parser()
                .verifyWith(generateKey())
                .build()
                .parseSignedClaims(token)
                .getPayload();
         return claims;
    }
public String extractUsername(String token) {
    Claims claims = getClaims(token);
    return claims.getSubject();
}

}


//    private Key getSigningKey() {
//        byte[] keyBytes = SECRET_KEY.getBytes();
//        return Keys.hmacShaKeyFor(keyBytes);
//    }
//
//
//    private Claims extractAllClaims(String token) {
//        Claims claims=  Jwts.parser()
//                .setSigningKey(getSigningKey())
//                .build()
//                .parseClaimsJws(token)
//                .getBody();
//        return claims;
//    }
//
//    // Extract username (subject) from the token
//    public String extractUsername(String token) {
//        return extractAllClaims(token).getSubject();  // Get subject (username) from claims
//    }
//
//    // Extract the expiration date from the token
//    public Date extractExpiration(String token) {
//        return extractAllClaims(token).getExpiration();  // Get expiration date from claims
//    }
//
//    // Check if the token has expired
//    private Boolean isTokenExpired(String token) {
//        return extractExpiration(token).before(new Date());
//    }



    // Create a JWT token
//    private String createToken(Map<String, Object> claims, String subject) {
//        return Jwts.builder()
//                .setClaims(claims)  // Set claims (additional information)
//                .setSubject(subject)  // Set subject (username)
//                .setIssuedAt(new Date(System.currentTimeMillis()))  // Set issued date
//                .setExpiration(new Date(System.currentTimeMillis() + 1000 * 60 * 60 * 10))  // Set expiration (10 hours)
//                .signWith(getSigningKey())  // Sign the token using the secret key
//                .compact();  // Build the token
//    }

//    // Validate the JWT token against user details
//    public Boolean validateToken(String token, UserDetails userDetails) {
//        final String username = extractUsername(token);
//        return (username.equals(userDetails.getUsername()) && !isTokenExpired(token));
//    }

