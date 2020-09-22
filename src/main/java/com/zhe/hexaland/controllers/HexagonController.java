package com.zhe.hexaland.controllers;

import com.zhe.hexaland.dtos.*;
import com.zhe.hexaland.services.HexagonServices;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@CrossOrigin
@RestController
public class HexagonController {
    @Autowired
    HexagonServices hexagonServices;

    @GetMapping(value = "/getByName")
    public RespDTO getByName(@RequestParam("name") String name) throws Exception {

        HexagonDTO hexagonDTO = hexagonServices.getByName(name);


        return RespDTO.ok("success", hexagonDTO);
    }

    @GetMapping(value = "/getAll")
    public RespDTO getAll() {
        return RespDTO.ok("success", hexagonServices.getAll());
    }

    @PostMapping("/add")
    public RespDTO add(@RequestBody addDTO dto){
        hexagonServices.addSingle(dto.getNeighbour(), dto.getEdgeNumber());
        return RespDTO.ok("success", null);
    }

    @PostMapping("/addOne")
    public RespDTO addone(@RequestBody addDTO dto ){
        hexagonServices.addOne(dto.getRow(), dto.getCol());
        return RespDTO.ok("success", null);
    }

    @PostMapping("/deleteOne")
    public RespDTO deleteOne(@RequestBody deleteDTO dto) {
        if( hexagonServices.deleteOne(dto.getHexagonName())){
            return RespDTO.ok("success", null);
        } else {
            return RespDTO.error("error - maybe the hexagon doesn't exist");
        }

    }

    @PostMapping("/batchAdd")
    public RespDTO batchAdd(@RequestBody batchAddDTO dto){
        hexagonServices.batchAdd(dto.getStartRow(), dto.getEndRow(), dto.getStartCol(), dto.getEndCol());
        return RespDTO.ok("success", null);
    }


}
