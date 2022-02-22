package com.mapper.basereponse;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class BaseReponse {
    private int code;
    private Object data;
    private String message;

    public BaseReponse(int code, Object data) {
        this.code = code;
        this.data = data;
    }

    public BaseReponse(int code, String message) {
        this.code = code;
        this.message = message;
    }
}
