package com.algafoodapi.api.exceptionhandler;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Builder;
import lombok.Getter;

import java.time.OffsetDateTime;
import java.util.List;

@JsonInclude(JsonInclude.Include.NON_NULL) // exibir no retorno da API apenas dados não nulo
@Getter
@Builder
public class Problem {

    private Integer status;
    private OffsetDateTime timestamp;
    private String type;
    private String title;
    private String detail;
    private String userMessage;
    private List<Object> objects;

    @Getter
    @Builder
    public static class Object {
        private String name;
        private String userMessage;
    }
}
