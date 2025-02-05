package com.list.index.live.live_index_list.services;

import com.list.index.live.live_index_list.models.Educator;
import com.list.index.live.live_index_list.models.User;
import com.list.index.live.live_index_list.models.dtos.UserEducator;

public interface UserService {

    User addUser(User user);

    User getUser(Integer userId);

    boolean isUser(Integer userId);

    Integer verifyUser(User user);

    UserEducator combineUserAndEducator(User user, Educator educator);
}