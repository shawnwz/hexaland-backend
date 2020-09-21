package com.zhe.hexaland.dtos;

import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class addDTO {
    String neighbour;
    Integer edgeNumber;
    String type;
    Integer row;
    Integer col;
}
