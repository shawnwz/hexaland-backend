package com.zhe.hexaland.dtos;

import lombok.*;

import java.util.Map;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class HexagonDTO {
    private String name;
    private Map<Integer, String> edges;
    private Integer r;
    private Integer c;
    private boolean status;
}
