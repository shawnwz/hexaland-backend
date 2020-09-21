package com.zhe.hexaland.entities;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.Objects;

@Entity
@Table(name = "hexagons")
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class HexagonsEntity {
    private int id;
    private String edge0;
    private String edge1;
    private String edge2;
    private String edge3;
    private String edge4;
    private String edge5;
    private String name;
    private Boolean status;
    private Long createTime;
    private String type;
    private Integer row;
    private Integer col;

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "hexagons_id_seq")
    @SequenceGenerator(name = "hexagons_id_seq", sequenceName = "hexagons_id_seq", allocationSize = 1)
    @Column(name = "id", nullable = false)
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    @Basic
    @Column(name = "edge_0", nullable = true, length = 20)
    public String getEdge0() {
        return edge0;
    }

    public void setEdge0(String edge0) {
        this.edge0 = edge0;
    }

    @Basic
    @Column(name = "edge_1", nullable = true, length = 20)
    public String getEdge1() {
        return edge1;
    }

    public void setEdge1(String edge1) {
        this.edge1 = edge1;
    }

    @Basic
    @Column(name = "edge_2", nullable = true, length = 20)
    public String getEdge2() {
        return edge2;
    }

    public void setEdge2(String edge2) {
        this.edge2 = edge2;
    }

    @Basic
    @Column(name = "edge_3", nullable = true, length = 20)
    public String getEdge3() {
        return edge3;
    }

    public void setEdge3(String edge3) {
        this.edge3 = edge3;
    }

    @Basic
    @Column(name = "edge_4", nullable = true, length = 20)
    public String getEdge4() {
        return edge4;
    }

    public void setEdge4(String edge4) {
        this.edge4 = edge4;
    }

    @Basic
    @Column(name = "edge_5", nullable = true, length = 20)
    public String getEdge5() {
        return edge5;
    }

    public void setEdge5(String edge5) {
        this.edge5 = edge5;
    }

    @Basic
    @Column(name = "name", nullable = true, length = 20)
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Basic
    @Column(name = "status", nullable = true)
    public Boolean getStatus() {
        return status;
    }

    public void setStatus(Boolean status) {
        this.status = status;
    }

    @Basic
    @Column(name = "create_time", nullable = true)
    public Long getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Long createTime) {
        this.createTime = createTime;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        HexagonsEntity that = (HexagonsEntity) o;
        return id == that.id &&
                Objects.equals(edge0, that.edge0) &&
                Objects.equals(edge1, that.edge1) &&
                Objects.equals(edge2, that.edge2) &&
                Objects.equals(edge3, that.edge3) &&
                Objects.equals(edge4, that.edge4) &&
                Objects.equals(edge5, that.edge5) &&
                Objects.equals(name, that.name) &&
                Objects.equals(status, that.status) &&
                Objects.equals(createTime, that.createTime);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, edge0, edge1, edge2, edge3, edge4, edge5, name, status, createTime);
    }

    @Basic
    @Column(name = "type", nullable = true, length = 10)
    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    @Basic
    @Column(name = "row", nullable = true)
    public Integer getRow() {
        return row;
    }

    public void setRow(Integer row) {
        this.row = row;
    }

    @Basic
    @Column(name = "col", nullable = true)
    public Integer getCol() {
        return col;
    }

    public void setCol(Integer col) {
        this.col = col;
    }
}
