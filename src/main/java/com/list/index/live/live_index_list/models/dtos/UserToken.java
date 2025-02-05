package com.list.index.live.live_index_list.models.dtos;

import com.list.index.live.live_index_list.models.User;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class UserToken {
    private String token;
    private User user;
}
