package com.example.demo.service;

import com.example.demo.dto.MemberProfileDTO;
import com.example.demo.dto.PointsOperationDTO;
import com.example.demo.entity.Member;
import com.example.demo.entity.PointsRecord;

import java.util.List;

public interface MemberService {

    List<Member> getAllMembers();

    Member getMemberById(Long id);

    MemberProfileDTO getMemberProfile(Long id);

    Member register(Member member);

    Member earnPoints(PointsOperationDTO dto);

    Member spendPoints(PointsOperationDTO dto);

    List<PointsRecord> getPointsHistory(Long memberId);
}
