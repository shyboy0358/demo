package com.example.demo.dto;

import java.io.Serializable;

public class PointsOperationDTO implements Serializable {

    private static final long serialVersionUID = 1L;

    private Long memberId;
    private Integer points;
    private String type;
    private String description;

    public Long getMemberId() { return memberId; }
    public void setMemberId(Long memberId) { this.memberId = memberId; }

    public Integer getPoints() { return points; }
    public void setPoints(Integer points) { this.points = points; }

    public String getType() { return type; }
    public void setType(String type) { this.type = type; }

    public String getDescription() { return description; }
    public void setDescription(String description) { this.description = description; }
}
