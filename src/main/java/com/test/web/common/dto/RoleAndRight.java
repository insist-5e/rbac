package com.test.web.common.dto;

import com.test.web.entity.Right;
import com.test.web.entity.Role;
import lombok.Data;

import javax.validation.constraints.NotNull;

@Data
public class RoleAndRight {
    @NotNull
    private Role role;
    @NotNull
    private Right right;
}
