package com.zhe.hexaland.services;

import com.zhe.hexaland.dtos.EdgeDTO;
import com.zhe.hexaland.dtos.HexagonDTO;
import com.zhe.hexaland.entities.HexagonsEntity;
import com.zhe.hexaland.repositories.HexagonsRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;

import java.util.HashMap;
import java.util.Map;

@Component
public class HexagonDTOFactory {
    @Autowired
    HexagonsRepository hexagonsRepository;

    HexagonDTO buildWithEntity(HexagonsEntity entity){
        HexagonDTO hexagonDTO = HexagonDTO.builder()
                .name(entity.getName())
                .edges(buildEdgesByEntity(entity))
                .build();
        return hexagonDTO;
    }

    Map<Integer, String> buildEdgesByEntity(HexagonsEntity entity){

        Map<Integer, String> edgeDTOMap = new HashMap<>();

        if (!StringUtils.isEmpty(hexagonsRepository.findFirstByNameEquals(entity.getEdge0()))){
            Map<Integer, String> map = new HashMap<>();
            edgeDTOMap.put(0, entity.getEdge0());
        }
        if (!StringUtils.isEmpty(hexagonsRepository.findFirstByNameEquals(entity.getEdge1()))){
            Map<Integer, String> map = new HashMap<>();
            edgeDTOMap.put(1, entity.getEdge1());
        }
        if (!StringUtils.isEmpty(hexagonsRepository.findFirstByNameEquals(entity.getEdge2()))){
            Map<Integer, String> map = new HashMap<>();
            edgeDTOMap.put(2, entity.getEdge2());
        }
        if (!StringUtils.isEmpty(hexagonsRepository.findFirstByNameEquals(entity.getEdge3()))){
            Map<Integer, String> map = new HashMap<>();
            edgeDTOMap.put(3, entity.getEdge3());
        }
        if (!StringUtils.isEmpty(hexagonsRepository.findFirstByNameEquals(entity.getEdge4()))){
            Map<Integer, String> map = new HashMap<>();
            edgeDTOMap.put(4, entity.getEdge4());
        }
        if (!StringUtils.isEmpty(hexagonsRepository.findFirstByNameEquals(entity.getEdge5()))){
            Map<Integer, String> map = new HashMap<>();
            edgeDTOMap.put(5, entity.getEdge5());
        }
        return edgeDTOMap;

    }

    EdgeDTO buildEdge(Integer borderNumber, String neighborName) {
        EdgeDTO edgeDTO = EdgeDTO.builder()
                .edge(borderNumber)
                .neighbour(neighborName)
                .build();
        return edgeDTO;
    }
}
