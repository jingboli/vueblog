package cc.lijingbo.vueblog.controller;

import cc.lijingbo.vueblog.domain.ResponseWrapper;
import cc.lijingbo.vueblog.entity.LoginDto;
import cc.lijingbo.vueblog.entity.User;
import cc.lijingbo.vueblog.service.UserService;
import cc.lijingbo.vueblog.shiro.utils.JwtUtils;
import cn.hutool.core.lang.Assert;
import cn.hutool.crypto.SecureUtil;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authz.annotation.RequiresAuthentication;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletResponse;

@RestController
public class AccountController {

    @Autowired
    private JwtUtils jwtUtils;

    @Autowired
    private UserService userService;

    @CrossOrigin
    @PostMapping("/login")
    public ResponseWrapper login(@Validated @RequestBody LoginDto loginDto, HttpServletResponse response) {
        User user = userService.getOne(new QueryWrapper<User>().eq("username", loginDto.getUsername()));
        Assert.notNull(user, "用户不存在");
        if (!user.getPassword().equals(SecureUtil.md5(loginDto.getPassword()))) {
            return ResponseWrapper.fail("密码错误");
        }
        String jwt = jwtUtils.generateToken(user.getId());
        response.setHeader("Authorization", jwt);
        response.setHeader("Access-Control-Expose-Headers", "Authorization");
        return ResponseWrapper.succ(user);
    }

    @GetMapping("/logout")
    @RequiresAuthentication
    public ResponseWrapper logout() {
        SecurityUtils.getSubject().logout();
        return ResponseWrapper.succ(null);
    }

}
