package com.test.web.common.dto;

import com.test.web.entity.Role;
import com.test.web.entity.User;
import lombok.Data;

import javax.validation.constraints.NotNull;

@Data
public class UserAndRole {
    @NotNull
    private User user;
    @NotNull
    private Role role;
}
