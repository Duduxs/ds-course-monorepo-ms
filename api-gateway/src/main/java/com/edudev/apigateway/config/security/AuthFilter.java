package com.edudev.apigateway.config.security;

import com.edudev.apigateway.exceptions.UnauthorizedHttpException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.gateway.filter.GatewayFilterChain;
import org.springframework.cloud.gateway.filter.GlobalFilter;
import org.springframework.http.server.reactive.ServerHttpRequest;
import org.springframework.stereotype.Component;
import org.springframework.web.server.ServerWebExchange;
import reactor.core.publisher.Mono;

import java.util.Arrays;
import java.util.Collection;
import java.util.List;
import java.util.function.Predicate;

@Component
public class AuthFilter implements GlobalFilter {

    private static Logger logger = LoggerFactory.getLogger(AuthFilter.class);

    @Autowired
    private JwtProvider provider;

    private static final Collection<String> PUBLIC = List.of(
            "/auth/login"
    );

    // TODO fazer autorização mais robusta com esses dois camaradas também.
    // TODO JOGAR O ACTUATOR AQ E A ANNOTATION @REFRESHSCOPE NO JWTPROVIDER.
    private static final Collection<String> OPERATOR = List.of(
            "/ms-worker/**"
    );

    private static final Collection<String> ADMIN = List.of(
            "/ms-payroll/**",
            "/ms-user/**",
            "/actuator/**",
            "/ms-worker/actuator/**",
            "/ms-oauth/actuator/**"
    );

    private final Predicate<ServerHttpRequest> isSecured = request -> PUBLIC
            .stream()
            .noneMatch(uri -> request.getURI().getPath().contains(uri));

    @Override
    public Mono<Void> filter(ServerWebExchange exchange, GatewayFilterChain chain) {
        ServerHttpRequest request = exchange.getRequest();

        logger.info("Gateway filter executed!");

        if (isSecured.test(request)) {

            if (!hasAuthorizationHeader(request))
                onError("Authorization Header is missing");

            String token = getTokenHeader(request);

            if (!provider.validateJwt(token))
                onError("Invalid JWT");

            String[] userRoles = provider.getRolesJwt(token).split(",");

            if (Arrays.stream(userRoles).noneMatch(s -> s.equals("ROLE_ADMIN"))) {
                onError("User not authorized to access the resource");
            }

        }

        return chain.filter(exchange);

    }

    private void onError(String errorMessage) {
        throw new UnauthorizedHttpException(errorMessage);
    }

    private Boolean hasAuthorizationHeader(ServerHttpRequest request) {
        return request.getHeaders().containsKey("Authorization");
    }

    private String getTokenHeader(ServerHttpRequest request) {

        String headerAuth = request.getHeaders().getOrEmpty("Authorization").get(0);

        if (headerAuth.startsWith("Bearer ")) {
            return headerAuth.substring(7);
        }
        return null;
    }
}
