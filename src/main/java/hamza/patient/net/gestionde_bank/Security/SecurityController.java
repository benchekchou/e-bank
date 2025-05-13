package hamza.patient.net.gestionde_bank.Security;

import org.springframework.security.core.Authentication;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.oauth2.jose.jws.MacAlgorithm;
import org.springframework.security.oauth2.jwt.JwsHeader;
import org.springframework.security.oauth2.jwt.JwtClaimsSet;
import org.springframework.security.oauth2.jwt.JwtEncoderParameters;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.time.Instant;
import java.time.temporal.ChronoUnit;
import java.util.Map;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/auth")
public class SecurityController {
    private final AuthenticationManager authenticationManager;

    public SecurityController(AuthenticationManager authenticationManager) {
        this.authenticationManager = authenticationManager;
    }
    @GetMapping("/profil")
    public Authentication authentication(Authentication authentication){
        return authentication;
    }
    @PostMapping("/login")
    public Map<String,String> login(String username,String password){
        Authentication authenticate = authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(username, password));
        Instant instant = Instant.now();
        String scope=authenticate.getAuthorities().stream().map(a->a.getAuthority()).collect(Collectors.joining());
        JwtClaimsSet jwtClaimsSet= JwtClaimsSet.builder()
                        .issuedAt(instant)
                        .expiresAt(instant.plus(10, ChronoUnit.MINUTES))
                .subject(username).claim("scope",scope)
        .build();
        JwtEncoderParameters jwtEncoderParameters=JwtEncoderParameters.from(JwsHeader.with(MacAlgorithm.HS512).build(),jwtClaimsSet);

        return null;
    }
}
