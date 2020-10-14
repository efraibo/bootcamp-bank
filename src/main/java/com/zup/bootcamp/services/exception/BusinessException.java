package com.zup.bootcamp.services.exception;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;

@RequiredArgsConstructor
@Getter
public abstract class BusinessException extends RuntimeException {

    private final String code;
    private final HttpStatus status;
}
