package com.example.repository;

import com.example.domain.user.model.MUser;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface UserMapper {
    public int insertOne(MUser user);
}
