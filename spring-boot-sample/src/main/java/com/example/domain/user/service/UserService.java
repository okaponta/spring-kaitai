package com.example.domain.user.service;

import com.example.domain.user.model.MUser;

import java.util.List;

public interface UserService {
    public void signup(MUser user);

    public List<MUser> getUsers(MUser user);

    public MUser getUserOne(String userId);

    public void updateUserOne(String userId, String password, String userName);

    public void deleteUserOne(String userId);

    public MUser getLoginUser(String userId);
}
