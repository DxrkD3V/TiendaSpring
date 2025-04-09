package es.iesclaradelrey.da2d1e2425.shopaymendavidrodrigo.filters;

import es.iesclaradelrey.da2d1e2425.shopaymendavidrodrigo.services.jwt.JwtService;
import es.iesclaradelrey.da2d1e2425.shopaymendavidrodrigo.services.jwt.JwtServiceImpl;
import es.iesclaradelrey.da2d1e2425.shopaymendavidrodrigo.services.users.AppUserDetailsService;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.apache.coyote.Response;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;

@Component
public class JwtAutenticationFilter extends OncePerRequestFilter {
    private final JwtServiceImpl jwtServiceImpl;
    private final JwtService jwtService;
    private final AppUserDetailsService appUserDetailsService;


    public JwtAutenticationFilter(JwtServiceImpl jwtServiceImpl, JwtService jwtService, AppUserDetailsService appUserDetailsService) {
        this.jwtServiceImpl = jwtServiceImpl;
        this.jwtService = jwtService;
        this.appUserDetailsService = appUserDetailsService;
    }

    @Override
    protected void doFilterInternal(HttpServletRequest request,
                                    HttpServletResponse response,
                                    FilterChain filterChain) throws ServletException, IOException {

        String pathRequest = request.getRequestURI();

        if(pathRequest.contains("/api/v1/cart")){
            String authHeader = request.getHeader("Authorization");
            if(authHeader == null || !authHeader.startsWith("Bearer ")){
                response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);

                return;
            }
            //extraer el token
            String token = authHeader.substring(7);
            //Validar el token
            try{
                jwtService.validateAccessToken(token);

                String username = jwtService.extractUsername(token);

                UserDetails userDetails = appUserDetailsService.loadUserByUsername(username);
                Authentication authentication = new UsernamePasswordAuthenticationToken(userDetails,null);

                SecurityContextHolder.getContext().setAuthentication(authentication);






            } catch (Exception e) {
                response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
                return;
            }
        }


        filterChain.doFilter(request, response);
    }
}
