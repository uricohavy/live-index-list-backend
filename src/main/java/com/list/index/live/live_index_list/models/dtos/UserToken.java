package com.list.index.live.live_index_list.models.dtos;

import com.list.index.live.live_index_list.models.User;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class UserToken {
    private String jwt;
    private String token;
    private User user;

    public UserToken(String jwt, User user) {
        this.jwt = jwt;
        this.user = user;
    }
}
