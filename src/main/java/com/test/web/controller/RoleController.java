package com.test.web.controller;

import com.test.web.common.dto.RoleAndRight;
import com.test.web.common.utils.Result;
import com.test.web.entity.Role;
import com.test.web.service.RightService;
import com.test.web.service.RoleService;
import com.test.web.service.UserService;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import javax.validation.constraints.NotBlank;

@RestController
@RequestMapping("/role")
public class RoleController {
    @Resource
    UserService userService;
    @Resource
    RightService rightService;
    @Resource
    RoleService roleService;
    @PutMapping("/insert")
    public Result insertRole(@Validated @RequestBody Role role){
        int flag = roleService.insertRole(role);
        if(flag >= 1) return Result.succ(null);
        return Result.fail("插入失败");
    }
    @DeleteMapping("/delete")
    public Result deleteRole(@NotBlank @RequestParam String name){
        Role role = new Role();
        role.setName(name);
        int flag = roleService.deleteRole(role);
        if(flag >= 0) return Result.succ(null);
        return Result.fail("删除失败");
    }

    @GetMapping("/select/user")
    public Result selectUserByRole(@NotBlank @RequestParam String name){
        Role role = new Role();
        role.setName(name);
        return Result.succ(userService.selectUserByRole(role));
    }
    @DeleteMapping("/delete/user")
    public Result deleteUserByRole(@NotBlank @RequestParam String name){
        Role role = new Role();
        role.setName(name);
        int flag = userService.deleteUserByRole(role);
        if(flag >= 0) return Result.succ(null);
        return Result.fail("删除失败");
    }

    @PutMapping("/insert/right")
    public Result insertRoleByRight(@Validated @RequestBody RoleAndRight roleAndRight){
        return Result.succ(rightService.insertRightByRole(roleAndRight.getRole(),roleAndRight.getRight()));
    }
    @GetMapping("/select/right")
    public Result selectRightByRole(@NotBlank @RequestParam String name){
        Role role = new Role();
        role.setName(name);
        return Result.succ(rightService.selectRightByRole(role));
    }

    @DeleteMapping("/delete/right")
    public Result deleteRightByRole(@NotBlank @RequestParam String name){
        Role role = new Role();
        role.setName(name);
        int flag = rightService.deleteRightByRole(role);
        if(flag >= 0) return Result.succ(null);
        return Result.fail("删除失败");
    }
}
