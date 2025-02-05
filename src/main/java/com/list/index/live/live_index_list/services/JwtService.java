package com.list.index.live.live_index_list.services;

import com.list.index.live.live_index_list.models.User;

public interface JwtService {
    public String generateJwt(Integer id);
    public User getUserFromToken(String token);
    public int verifyJwt(String token);
}