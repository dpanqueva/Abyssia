package com.dapb.multy.tenant.config;

import com.dapb.multy.tenant.repository.UsersRepository;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.SneakyThrows;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import javax.sql.DataSource;
import java.io.IOException;
import java.sql.Connection;
import java.sql.Statement;

@Component
public class ShemaFilter extends OncePerRequestFilter {

    @Autowired
    private UsersRepository usersRepository;

    @Autowired
    private DataSource dataSource;

    @Override
    @SneakyThrows
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
            throws ServletException, IOException {
        String username = obtenerUsuarioDesdeJWT(request); // Implementa esta función

        if (username != null) {
            String schema = usersRepository.findSchemaByUsername(username); // Implementa este método
//            try (Connection conn = dataSource.getConnection();
//                 Statement stmt = conn.createStatement()) {
//                stmt.execute("SET search_path TO " + schema);
//            }
            TenantContext.setCurrentTenant(schema);
        }
        filterChain.doFilter(request, response);
    }

    private String obtenerUsuarioDesdeJWT(HttpServletRequest request) {

        return "daerb9011";
    }
}
