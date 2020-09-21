package com.zhe.hexaland.controllers;

import com.zhe.hexaland.dtos.HexagonDTO;
import com.zhe.hexaland.dtos.RespDTO;
import com.zhe.hexaland.dtos.addDTO;
import com.zhe.hexaland.services.HexagonServices;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
public class HexagonController {
    @Autowired
    HexagonServices hexagonServices;

    @GetMapping(value = "/getByName")
    public RespDTO getByName(@RequestParam("name") String name) throws Exception {

        HexagonDTO hexagonDTO = hexagonServices.getByName(name);


        return RespDTO.ok("success", hexagonDTO);
    }

    @PostMapping("/add")
    public RespDTO add(@RequestBody addDTO dto){
        hexagonServices.addSingle(dto.getNeighbour(), dto.getEdgeNumber());
        return RespDTO.ok("success", null);
    }

    @PostMapping("/addone")
    public RespDTO addone(@RequestBody addDTO dto ){
        hexagonServices.addOne(dto.getType(), dto.getRow(), dto.getCol());
        return RespDTO.ok("success", null);
    }
}
