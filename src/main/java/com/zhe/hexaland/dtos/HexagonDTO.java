package com.zhe.hexaland.dtos;

import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class HexagonDTO {
    private String Name;
    private EdgesDTO Edges;
}
