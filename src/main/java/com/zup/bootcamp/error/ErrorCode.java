package com.zup.bootcamp.error;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;

@Getter
@RequiredArgsConstructor
public class ErrorCode {

    private final String code;

    private final HttpStatus httpStatus;
}
