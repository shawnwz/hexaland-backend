package com.zhe.hexaland.services;

import com.zhe.hexaland.dtos.EdgeDTO;
import com.zhe.hexaland.dtos.EdgesDTO;
import com.zhe.hexaland.dtos.HexagonDTO;
import com.zhe.hexaland.entities.HexagonsEntity;
import org.springframework.stereotype.Component;

@Component
public class HexagonDTOFactory {

    HexagonDTO buildWithEntity(HexagonsEntity entity){
        HexagonDTO hexagonDTO = HexagonDTO.builder()
                .Name(entity.getName())
                .Edges(buildEdgesByEntity(entity))
                .build();
        return hexagonDTO;
    }

    EdgesDTO buildEdgesByEntity(HexagonsEntity entity){
        EdgesDTO edgesDTO = EdgesDTO.builder()
                .edge_0(buildEdge(0, entity.getEdge0()))
                .edge_1(buildEdge(1, entity.getEdge1()))
                .edge_2(buildEdge(2, entity.getEdge2()))
                .edge_3(buildEdge(3, entity.getEdge3()))
                .edge_4(buildEdge(4, entity.getEdge4()))
                .edge_5(buildEdge(5, entity.getEdge5()))
                .build();
        return edgesDTO;

    }

    EdgeDTO buildEdge(Integer borderNumber, String neighborName) {
        EdgeDTO edgeDTO = EdgeDTO.builder()
                .edge(borderNumber)
                .neighbour(neighborName)
                .build();
        return edgeDTO;
    }
}
