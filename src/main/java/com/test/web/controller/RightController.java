package com.test.web.controller;

import com.test.web.common.utils.Result;
import com.test.web.entity.Right;
import com.test.web.service.RightService;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import javax.validation.constraints.NotBlank;

@RestController
@RequestMapping("/right")
public class RightController {
    @Resource
    RightService rightService;

    @PutMapping("/insert")
    public Result insertRight(@Validated @RequestBody Right right){
        int flag = rightService.insertRight(right);
        if(flag >= 1) return Result.succ(null);
        return Result.fail("插入失败");
    }

    @DeleteMapping("/delete")
    public Result deleteRight(@NotBlank @RequestParam String name){
        Right right = new Right();
        right.setName(name);
        int flag = rightService.deleteRight(right);
        if(flag >= 0) return Result.succ(null);
        return Result.fail("删除失败");
    }
}
