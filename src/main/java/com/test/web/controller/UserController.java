package com.test.web.controller;

import com.test.web.common.dto.UserAndRole;
import com.test.web.common.utils.Result;
import com.test.web.entity.User;
import com.test.web.service.RightService;
import com.test.web.service.RoleService;
import com.test.web.service.UserService;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import javax.validation.constraints.NotBlank;

@RestController
@RequestMapping("/user")
public class UserController {
    @Resource
    UserService userService;
    @Resource
    RightService rightService;
    @Resource
    RoleService roleService;
    @PutMapping("/insert")
    public Result insertUser(@Validated @RequestBody User user){
        int flag = userService.insertUser(user);
        if(flag >= 1) return Result.succ(null);
        return Result.fail("插入失败");
    }
    @DeleteMapping("/delete")
    public Result deleteUser(@NotBlank @RequestParam String name){
        User user = new User();
        user.setName(name);
        int flag = userService.deleteUser(user);
        if(flag >= 0) return Result.succ(null);
        return Result.fail("删除失败");
    }

    @GetMapping("/select/right")
    public Result selectRightByUser(@NotBlank @RequestParam String name){
        User user = new User();
        user.setName(name);
        return Result.succ(rightService.selectRightByUser(user));
    }

    @PutMapping("/insert/role")
    public Result insertUserByRole(@Validated @RequestBody UserAndRole userAndRole){
        int flag = roleService.insertRoleByUser(userAndRole.getUser(),userAndRole.getRole());
        if(flag >= 1) return Result.succ(null);
        return Result.fail("插入失败");
    }

    @GetMapping("/select/role")
    public Result selectRoleByUser(@NotBlank @RequestParam String name){
        User user = new User();
        user.setName(name);
        return Result.succ(roleService.selectRoleByUser(user));
    }

    @DeleteMapping("/delete/role")
    public Result deleteRoleByUser(@NotBlank @RequestParam String name){
        User user = new User();
        user.setName(name);
        int flag = roleService.deleteRoleByUser(user);
        if(flag >= 0) return Result.succ(null);
        return Result.fail("删除失败");
    }
}
