package com.zhe.hexaland.dtos;

import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class EdgeDTO {
    Integer edge;
    String neighbour;
}
