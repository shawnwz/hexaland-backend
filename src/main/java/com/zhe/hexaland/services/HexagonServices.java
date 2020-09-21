package com.zhe.hexaland.services;

import com.zhe.hexaland.dtos.HexagonDTO;
import com.zhe.hexaland.entities.HexagonsEntity;
import com.zhe.hexaland.repositories.HexagonsRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class HexagonServices {

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
            String type = null;
            Integer row = null, col = null;
            switch(edge){
                case 0:
                    if(entity.getType().equalsIgnoreCase("X")){
                        type = "X";
                        row = entity.getRow() + 1;
                        col = entity.getCol();
                    } else if (entity.getType().equalsIgnoreCase("Y")){
                        type = "Y";
                        row = entity.getRow() + 1;
                        col = entity.getCol();
                    }
                    break;
                case 1:
                    if(entity.getType().equalsIgnoreCase("X")){
                        type = "Y";
                        row = entity.getRow();
                        col = entity.getCol();
                    } else if (entity.getType().equalsIgnoreCase("Y")){
                        type = "X";
                        row = entity.getRow() + 1;
                        col = entity.getCol() + 1;
                    }
                    break;
                case 2:
                    if(entity.getType().equalsIgnoreCase("X")){
                        type = "Y";
                        row = entity.getRow() - 1;
                        col = entity.getCol();
                    } else if (entity.getType().equalsIgnoreCase("Y")){
                        type = "X";
                        row = entity.getRow();
                        col = entity.getCol() + 1;
                    }
                    break;
                case 3:
                    if(entity.getType().equalsIgnoreCase("X")){
                        type = "X";
                        row = entity.getRow() - 1;
                        col = entity.getCol();
                    } else if (entity.getType().equalsIgnoreCase("Y")){
                        type = "Y";
                        row = entity.getRow() - 1;
                        col = entity.getCol();
                    }
                    break;
                case 4:
                    if(entity.getType().equalsIgnoreCase("X")){
                        type = "Y";
                        row = entity.getRow() - 1;
                        col = entity.getCol() - 1;
                    } else if (entity.getType().equalsIgnoreCase("Y")){
                        type = "X";
                        row = entity.getRow();
                        col = entity.getCol();
                    }
                    break;
                case 5:
                    if(entity.getType().equalsIgnoreCase("X")){
                        type = "Y";
                        row = entity.getRow();
                        col = entity.getCol() - 1;
                    } else if (entity.getType().equalsIgnoreCase("Y")){
                        type = "X";
                        row = entity.getRow() + 1;
                        col = entity.getCol();
                    }
                    break;
                default:
                    type = "X";
                    row = 0;
                    col = 0;

            }
            addOne(type, row, col);

        }
    }

    public void addOne(String type, Integer row, Integer col) {
        String name = genName(type, row, col);
        if (hexagonsRepository.existsByNameEquals(name)) {
            HexagonsEntity entity = hexagonsRepository.findFirstByNameEquals(name);
            entity.setStatus(true);
            hexagonsRepository.save(entity);
        } else {
            HexagonsEntity entity = genEntity(type, row, col);
            hexagonsRepository.save(entity);
        }


    }

    public void deleteOne(String name) {
        if(hexagonsRepository.existsByNameEquals(name)){
            HexagonsEntity entity = hexagonsRepository.findFirstByNameEquals(name);
            hexagonsRepository.delete(entity);
        } else {
            return;
        }
    }


    private String genName(String type, Integer row, Integer col){
        StringBuilder sb = new StringBuilder();
        sb.append(type);
        sb.append("-");
        sb.append(String.valueOf(row));
        sb.append("-");
        sb.append(String.valueOf(col));
        return sb.toString();
    }

    private HexagonsEntity genEntity(String type, Integer row, Integer col) {
        HexagonsEntity entity = HexagonsEntity.builder()
                .name(genName(type, row, col))
                .createTime(System.currentTimeMillis())
                .status(true)
                .type(type)
                .row(row)
                .col(col)
                .build();
        String X = "X";
        String Y = "Y";
        String r = String.valueOf(row);
        String c = String.valueOf(col);
        String r_add_1 = String.valueOf(row+1);
        String r_sub_1 = String.valueOf(row-1);
        String c_add_1 = String.valueOf(col+1);
        String c_sub_1 = String.valueOf(col-1);
        String edge0, edge1, edge2, edge3, edge4, edge5;
        StringBuffer sb = new StringBuffer();
        switch(type){
            case "X":
                sb.delete(0, sb.length());
                edge0 = sb.append(X).append("-").append(r_add_1).append("-").append(c).toString();
                sb.delete(0, sb.length());
                edge1 = sb.append(Y).append("-").append(r).append("-").append(c).toString();
                sb.delete(0, sb.length());
                edge2 = sb.append(Y).append("-").append(r_sub_1).append("-").append(c).toString();
                sb.delete(0, sb.length());
                edge3 = sb.append(X).append("-").append(r_sub_1).append("-").append(c).toString();
                sb.delete(0, sb.length());
                edge4 = sb.append(Y).append("-").append(r_sub_1).append("-").append(c_sub_1).toString();
                sb.delete(0, sb.length());
                edge5 = sb.append(Y).append("-").append(r).append("-").append(c_sub_1).toString();
                break;
            case "Y":
                sb.delete(0, sb.length());
                edge0 = sb.append(Y).append("-").append(r_add_1).append("-").append(c).toString();
                sb.delete(0, sb.length());
                edge1 = sb.append(X).append("-").append(r_add_1).append("-").append(c_add_1).toString();
                sb.delete(0, sb.length());
                edge2 = sb.append(X).append("-").append(r).append("-").append(c_add_1).toString();
                sb.delete(0, sb.length());
                edge3 = sb.append(Y).append("-").append(r_sub_1).append("-").append(c).toString();
                sb.delete(0, sb.length());
                edge4 = sb.append(X).append("-").append(r).append("-").append(c).toString();
                sb.delete(0, sb.length());
                edge5 = sb.append(X).append("-").append(r_add_1).append("-").append(c).toString();
                break;
            default:
                edge0=null;
                edge1=null;
                edge2=null;
                edge3=null;
                edge4=null;
                edge5=null;

            entity.setEdge0(edge0);
            entity.setEdge1(edge1);
            entity.setEdge2(edge2);
            entity.setEdge3(edge3);
            entity.setEdge4(edge4);
            entity.setEdge5(edge5);


            return entity;

        }
        entity.setEdge0(edge0);
        entity.setEdge1(edge1);
        entity.setEdge2(edge2);
        entity.setEdge3(edge3);
        entity.setEdge4(edge4);
        entity.setEdge5(edge5);

        return entity;

    }


}
