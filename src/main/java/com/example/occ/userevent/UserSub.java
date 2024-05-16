package com.example.occ.userevent;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class UserSub {
    private int userId;
    private String name;
    private String userHandle;

    
    public UserSub(int userId, String name, String userHandle) {
        this.userId = userId;
        this.name = name;
        this.userHandle = userHandle;
    }
}
