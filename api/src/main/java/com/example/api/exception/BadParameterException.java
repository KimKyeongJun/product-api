package com.example.api.exception;

import lombok.Data;
import lombok.EqualsAndHashCode;
import org.springframework.validation.BindingResult;

@Data
@EqualsAndHashCode(callSuper=false)
public class BadParameterException extends RuntimeException {

    private BindingResult bindingResult;

    public BadParameterException(BindingResult bindingResult) {
        this.bindingResult = bindingResult;
    }
}
