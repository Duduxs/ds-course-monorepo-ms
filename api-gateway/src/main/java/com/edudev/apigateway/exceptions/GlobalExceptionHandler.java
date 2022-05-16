package com.edudev.apigateway.exceptions;

import com.edudev.apigateway.config.security.DataBufferWriter;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.web.reactive.error.ErrorWebExceptionHandler;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;
import org.springframework.web.server.ServerWebExchange;
import reactor.core.publisher.Mono;

import static org.springframework.http.HttpStatus.FORBIDDEN;

@Component
public class GlobalExceptionHandler implements ErrorWebExceptionHandler {

    @Autowired
    private DataBufferWriter bufferWriter;

    private static Logger logger = LoggerFactory.getLogger(GlobalExceptionHandler.class);

    @Override
    public Mono<Void> handle(ServerWebExchange exchange, Throwable ex) {
        HttpStatus status = HttpStatus.INTERNAL_SERVER_ERROR;
        RuntimeException appError = new RuntimeException("Something went wrong");

        if (ex instanceof UnauthorizedHttpException e) {
            status = FORBIDDEN;
            appError = e;

            logger.debug(appError.toString());
        } else {
            logger.error(ex.getMessage(), ex);
        }

        if (exchange.getResponse().isCommitted()) {
            return Mono.error(ex);
        }

        exchange.getResponse().setStatusCode(status);
        return bufferWriter.write(exchange.getResponse(), appError.getMessage());
    }
}
