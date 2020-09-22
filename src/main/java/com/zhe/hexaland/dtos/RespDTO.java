package com.zhe.hexaland.dtos;

import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class RespDTO {

    private Integer status;
    private String message;
    private Object data;

    public static RespDTO ok(String msg, Object data) {
        return new RespDTO(200, msg, data);
    }

    public static RespDTO error(String msg) {
        return new RespDTO(500, msg, null);
    }

}
