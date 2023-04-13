package com.sita.bonus.dtos.response;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class Response {
    private String errorMessage;
    private Object data;
}
