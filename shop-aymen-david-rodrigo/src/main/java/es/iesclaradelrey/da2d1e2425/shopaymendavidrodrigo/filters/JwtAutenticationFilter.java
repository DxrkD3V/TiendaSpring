package es.iesclaradelrey.da2d1e2425.shopaymendavidrodrigo.filters;

import es.iesclaradelrey.da2d1e2425.shopaymendavidrodrigo.services.jwt.JwtService;
import es.iesclaradelrey.da2d1e2425.shopaymendavidrodrigo.services.users.AppUserDetailsService;
import io.jsonwebtoken.JwtException;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.validation.constraints.NotNull;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;

@Component
public class JwtAutenticationFilter extends OncePerRequestFilter {

    private final JwtService jwtService;
    private final AppUserDetailsService appUserDetailsService;

    private static final String AUTH_HEADER = "Authorization";

    private static final String BEARER_PREFIX = "Bearer ";

    private static final String PROTECTED_PATHCART = "/api/v1/cart/**";
    private static final String PROTECTED_PATHPRODUCT = "/api/v1/products/**";
    private static final String PROTECTED_PATHAUTH = "/api/v1/auth";

    private static final AntPathRequestMatcher protectedPathMatcherCart = new AntPathRequestMatcher(PROTECTED_PATHCART);
    private static final AntPathRequestMatcher protectedPathMatcherProducts = new AntPathRequestMatcher(PROTECTED_PATHPRODUCT);


    public JwtAutenticationFilter(JwtService jwtService, AppUserDetailsService appUserDetailsService) {
        this.jwtService = jwtService;
        this.appUserDetailsService = appUserDetailsService;
    }

    @Override
    protected void doFilterInternal(HttpServletRequest request,
                                    @NotNull HttpServletResponse response,
                                    @NotNull FilterChain filterChain) throws ServletException, IOException {

        if(protectedPathMatcherCart.matches(request) || protectedPathMatcherProducts.matches(request)) {
            try {
                String authHeader = request.getHeader(AUTH_HEADER);

                if (authHeader == null || !authHeader.startsWith(BEARER_PREFIX)) {
                    throw new JwtException("Authorization header missing or incorrect.");
                }

                String token = authHeader.substring(7);

                jwtService.validateAccessToken(token);

                String username = jwtService.extractUsername(token);

                UserDetails userDetails = appUserDetailsService.loadUserByUsername(username);

                Authentication authentication = new UsernamePasswordAuthenticationToken(userDetails, null, userDetails.getAuthorities());
                SecurityContextHolder.getContext().setAuthentication(authentication);

            } catch (Exception e){
                String errorMessage = String.format("Error validating access token: %s", e.getMessage());
                response.sendError(HttpServletResponse.SC_UNAUTHORIZED, errorMessage);

                return;
            }
        }
        filterChain.doFilter(request, response);
    }
}
