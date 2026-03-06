package com.internverse.internverse.config;

import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;
import java.util.List;

@Component
public class JwtFilter extends OncePerRequestFilter {

    @Autowired
    private JwtUtil jwtUtil;

    @Override
    protected boolean shouldNotFilter(HttpServletRequest request) {
        String path = request.getServletPath();
        // Skip filter for auth endpoints and OPTIONS preflight
        return path.startsWith("/auth") || request.getMethod().equals("OPTIONS");
    }

    @Override
    protected void doFilterInternal(HttpServletRequest request,
                                    HttpServletResponse response,
                                    FilterChain filterChain)
            throws ServletException, IOException {

        System.out.println("=== JWT FILTER HIT ===");
        System.out.println("Method : " + request.getMethod());
        System.out.println("Path   : " + request.getServletPath());

        String authHeader = request.getHeader("Authorization");
        System.out.println("Header : " + authHeader);

        if (authHeader != null && authHeader.startsWith("Bearer ")) {

            String token = authHeader.substring(7);

            try {
                String email = jwtUtil.extractEmail(token);
                String role  = jwtUtil.extractRole(token);

                System.out.println("Email  : " + email);
                System.out.println("Role   : " + role);

                if (email != null && SecurityContextHolder.getContext().getAuthentication() == null) {

                    // role is stored as "ROLE_INTERN" or "ROLE_ADMIN"
                    // Spring's hasRole("INTERN") checks for authority "ROLE_INTERN" — this matches correctly
                    SimpleGrantedAuthority authority = new SimpleGrantedAuthority(role);

                    UsernamePasswordAuthenticationToken authToken =
                            new UsernamePasswordAuthenticationToken(
                                    email,
                                    null,
                                    List.of(authority)
                            );

                    SecurityContextHolder.getContext().setAuthentication(authToken);
                    System.out.println("Auth   : " + SecurityContextHolder.getContext().getAuthentication().getAuthorities());
                }

            } catch (Exception e) {
                System.out.println("JWT Error: " + e.getMessage());
                response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
                return;
            }

        } else {
            System.out.println("No Bearer token in request!");
        }

        filterChain.doFilter(request, response);
    }
}
