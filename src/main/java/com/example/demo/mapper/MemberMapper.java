package com.example.demo.mapper;

import com.example.demo.entity.Member;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@Mapper
public interface MemberMapper {

    List<Member> findAll();

    Member findById(@Param("id") Long id);

    Member findByUsername(@Param("username") String username);

    int insert(Member member);

    int update(Member member);

    int updatePoints(@Param("id") Long id,
                     @Param("totalPoints") Integer totalPoints,
                     @Param("availablePoints") Integer availablePoints,
                     @Param("levelId") Long levelId);

    int deleteById(@Param("id") Long id);
}
