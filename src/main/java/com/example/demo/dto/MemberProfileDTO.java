package com.example.demo.dto;

import com.example.demo.entity.Member;
import com.example.demo.entity.MemberBenefit;
import com.example.demo.entity.MemberLevel;

import java.io.Serializable;
import java.util.List;

public class MemberProfileDTO implements Serializable {

    private static final long serialVersionUID = 1L;

    private Member member;
    private MemberLevel currentLevel;
    private MemberLevel nextLevel;
    private List<MemberBenefit> benefits;
    private Integer pointsToNextLevel;

    public Member getMember() { return member; }
    public void setMember(Member member) { this.member = member; }

    public MemberLevel getCurrentLevel() { return currentLevel; }
    public void setCurrentLevel(MemberLevel currentLevel) { this.currentLevel = currentLevel; }

    public MemberLevel getNextLevel() { return nextLevel; }
    public void setNextLevel(MemberLevel nextLevel) { this.nextLevel = nextLevel; }

    public List<MemberBenefit> getBenefits() { return benefits; }
    public void setBenefits(List<MemberBenefit> benefits) { this.benefits = benefits; }

    public Integer getPointsToNextLevel() { return pointsToNextLevel; }
    public void setPointsToNextLevel(Integer pointsToNextLevel) { this.pointsToNextLevel = pointsToNextLevel; }
}
