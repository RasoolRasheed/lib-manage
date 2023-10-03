package com.rasool.assessment.payload.common;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.http.HttpStatus;

@NoArgsConstructor
@AllArgsConstructor
@Data
public class ApiResponse<T> {
    protected HttpStatus status;
    protected String message;
    protected boolean success;
    protected T data;
}
