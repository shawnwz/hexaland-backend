package com.zhe.hexaland.services;

import com.zhe.hexaland.dtos.HexagonDTO;
import com.zhe.hexaland.entities.HexagonsEntity;
import com.zhe.hexaland.repositories.HexagonsRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class HexagonServices {
    public static final String PREFIX = "H";
    public static final String SEP = "_";
    @Autowired
    private HexagonsRepository hexagonsRepository;
    @Autowired
    private HexagonDTOFactory hexagonDTOFactory;

    public HexagonDTO getByName(String name){
        HexagonsEntity entity = hexagonsRepository.findFirstByNameEqualsAndStatusEquals(name, true);
        if (null == entity) {
            return null;
        } else {
            HexagonDTO hexagonDTO = hexagonDTOFactory.buildWithEntity(entity);
            return hexagonDTO;
        }
    }

    public void addSingle (String neighbourName, Integer edge) {
        HexagonsEntity entity = hexagonsRepository.findFirstByNameEqualsAndStatusEquals(neighbourName, true);
        if (entity!=null) {

            Integer row = null, col = null;
            switch(edge){
                case 0:
                    row = entity.getRow() - 1;
                    col = entity.getCol();
                    break;
                case 1:
                    row = entity.getRow() - 1;
                    col = entity.getCol() + 1;
                    break;
                case 2:
                    row = entity.getRow();
                    col = entity.getCol() + 1;
                    break;
                case 3:
                    row = entity.getRow() + 1;
                    col = entity.getCol();
                    break;
                case 4:
                    row = entity.getRow() + 1;
                    col = entity.getCol() - 1;
                    break;
                case 5:
                    row = entity.getRow();
                    col = entity.getCol() - 1;
                    break;
                default:
                    row = 0;
                    col = 0;

            }
            addOne(row, col);

        }
    }

    public void addOne(Integer row, Integer col) {
        String name = genName(row, col);
        if (hexagonsRepository.existsByNameEquals(name)) {
            HexagonsEntity entity = hexagonsRepository.findFirstByNameEquals(name);
            entity.setStatus(true);
            hexagonsRepository.save(entity);
        } else {
            HexagonsEntity entity = genEntity(row, col);
            hexagonsRepository.save(entity);
        }
    }

    public void batchAdd(Integer startRow, Integer endRow, Integer startCol, Integer endCol){
        for (Integer r = startRow; r <= endRow; r++) {
            for (Integer c = startCol; c <= endCol; c++) {
                addOne(r, c);
            }
        }
    }

    public List<HexagonDTO> getAll() {
        List<HexagonsEntity> entities = hexagonsRepository.findAllByStatusEquals(true);
        List<HexagonDTO> list = new ArrayList<>();
        entities.forEach( i -> {
                HexagonDTO hexagonDTO = hexagonDTOFactory.buildWithEntity(i);
                list.add(hexagonDTO);
        });
        return list;
    }

    public boolean deleteOne(String name) {
        if(hexagonsRepository.existsByNameEquals(name)){
            HexagonsEntity entity = hexagonsRepository.findFirstByNameEquals(name);
            entity.setStatus(false);
            hexagonsRepository.save(entity);
            return true;
        } else {
            return false;
        }
    }


    private String genName(Integer row, Integer col){
        StringBuilder sb = new StringBuilder();
        sb.append(PREFIX);
        sb.append(SEP);
        sb.append(String.valueOf(row));
        sb.append(SEP);
        sb.append(String.valueOf(col));
        return sb.toString();
    }

    private HexagonsEntity genEntity(Integer row, Integer col) {
        HexagonsEntity entity = HexagonsEntity.builder()
                .name(genName(row, col))
                .createTime(System.currentTimeMillis())
                .status(true)
                .row(row)
                .col(col)
                .build();

        String r = String.valueOf(row);
        String c = String.valueOf(col);
        String r_add_1 = String.valueOf(row+1);
        String r_sub_1 = String.valueOf(row-1);
        String c_add_1 = String.valueOf(col+1);
        String c_sub_1 = String.valueOf(col-1);
        String edge0, edge1, edge2, edge3, edge4, edge5;
        StringBuffer sb = new StringBuffer();

        sb.delete(0, sb.length());
        edge0 = sb.append(PREFIX).append(SEP).append(r_sub_1).append(SEP).append(c).toString();
        sb.delete(0, sb.length());
        edge1 = sb.append(PREFIX).append(SEP).append(r_sub_1).append(SEP).append(c_add_1).toString();
        sb.delete(0, sb.length());
        edge2 = sb.append(PREFIX).append(SEP).append(r).append(SEP).append(c_add_1).toString();
        sb.delete(0, sb.length());
        edge3 = sb.append(PREFIX).append(SEP).append(r_add_1).append(SEP).append(c).toString();
        sb.delete(0, sb.length());
        edge4 = sb.append(PREFIX).append(SEP).append(r_add_1).append(SEP).append(c_sub_1).toString();
        sb.delete(0, sb.length());
        edge5 = sb.append(PREFIX).append(SEP).append(r).append(SEP).append(c_sub_1).toString();

        entity.setEdge0(edge0);
        entity.setEdge1(edge1);
        entity.setEdge2(edge2);
        entity.setEdge3(edge3);
        entity.setEdge4(edge4);
        entity.setEdge5(edge5);

        return entity;

    }

}
