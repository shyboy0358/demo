package com.example.demo.dto;

import java.io.Serializable;

public class BenefitClaimDTO implements Serializable {

    private static final long serialVersionUID = 1L;

    private Long benefitId;
    private Long memberId;
    private Long familyId;
    private String studentCode;
    private String goodsId;

    public Long getBenefitId() { return benefitId; }
    public void setBenefitId(Long benefitId) { this.benefitId = benefitId; }

    public Long getMemberId() { return memberId; }
    public void setMemberId(Long memberId) { this.memberId = memberId; }

    public Long getFamilyId() { return familyId; }
    public void setFamilyId(Long familyId) { this.familyId = familyId; }

    public String getStudentCode() { return studentCode; }
    public void setStudentCode(String studentCode) { this.studentCode = studentCode; }

    public String getGoodsId() { return goodsId; }
    public void setGoodsId(String goodsId) { this.goodsId = goodsId; }
}
