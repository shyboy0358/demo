package com.example.demo.dto;

import com.example.demo.entity.MemberBenefit;
import com.example.demo.entity.MemberLevel;

import java.io.Serializable;
import java.util.List;

public class MemberLevelDetailDTO implements Serializable {

    private static final long serialVersionUID = 1L;

    private MemberLevel level;
    private List<MemberBenefit> benefits;

    public MemberLevelDetailDTO() {}

    public MemberLevelDetailDTO(MemberLevel level, List<MemberBenefit> benefits) {
        this.level = level;
        this.benefits = benefits;
    }

    public MemberLevel getLevel() { return level; }
    public void setLevel(MemberLevel level) { this.level = level; }

    public List<MemberBenefit> getBenefits() { return benefits; }
    public void setBenefits(List<MemberBenefit> benefits) { this.benefits = benefits; }
}
