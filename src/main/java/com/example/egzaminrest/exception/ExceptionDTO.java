package com.example.egzaminrest.exception;

import lombok.Builder;

import java.time.LocalDateTime;

@Builder
public class ExceptionDTO {

        private String message;
        private LocalDateTime occurrence;

        public static ExceptionDTO fromException(Exception exception) {
            return ExceptionDTO.builder()
                    .message(exception.getMessage())
                    .occurrence(LocalDateTime.now())
                    .build();
        }
}
