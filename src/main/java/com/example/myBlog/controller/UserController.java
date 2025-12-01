package com.example.myBlog.controller;

import com.example.myBlog.common.Result;
import com.example.myBlog.entity.User;
import com.example.myBlog.service.UserService;
import jakarta.servlet.http.HttpSession;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.DigestUtils;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/user")
public class UserController {
    @Autowired
    private UserService userService;
    @GetMapping("/{id}")
    public User getUserByID(@PathVariable long id){
        return userService.getUserById(id);
    }

    @PostMapping("/login")
    public Result<User> login(@RequestBody User user, HttpSession session) {
        // 1. 拿着用户名去数据库查
        // (注意：这里假设你 UserMapper 里写了 selectByUserName 方法，如果没写要去补上)
        User dbUser = userService.getUserByUsername(user.getUserName());


        if (dbUser == null) {
            return Result.error(400, "用户不存在");
        }


        //把输入的密码变成乱码再与数据库里的进行比较
        String inputMd5 = DigestUtils.md5DigestAsHex(user.getPassword().getBytes());

        if (!dbUser.getPassword().equals(inputMd5)) {
            return Result.error(400, "密码错误");
        }

        // 关键一步：登录成功，把用户信息存入 Session
        // 这就像是发了一张“门禁卡”，卡号就是 SessionID
        session.setAttribute("currentUser", dbUser);

        return Result.success(dbUser); // 把用户信息返回给前端
    }

    @PostMapping("/register")
    public Result<String> register(@RequestBody @Valid User user,HttpSession session){
        User dbUser = userService.getUserByUsername(user.getUserName());
        if (dbUser != null) {
            return Result.error(400, "用户已存在");
        }

        //写入数据库
        userService.insertUser(user);
        //注册完自动登录
        session.setAttribute("currentUser", user);
        return Result.success("注册成功,已为你自动登录");
    }


}
