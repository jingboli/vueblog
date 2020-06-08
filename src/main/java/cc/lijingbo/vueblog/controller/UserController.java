package cc.lijingbo.vueblog.controller;


import cc.lijingbo.vueblog.domain.ResponseWrapper;
import cc.lijingbo.vueblog.entity.User;
import cc.lijingbo.vueblog.service.UserService;
import cn.hutool.core.lang.Assert;
import org.apache.ibatis.executor.resultset.ResultSetWrapper;
import org.apache.shiro.authz.annotation.RequiresAuthentication;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

/**
 * <p>
 *  前端控制器
 * </p>
 *
 * @author lijingbo
 * @since 2020-06-05
 */
@RestController
@RequestMapping("/user")
public class UserController {

    @Autowired
    private UserService userService;

    @GetMapping("/{id}")
    public Object test(@PathVariable("id") Long id){
        User user = userService.getById(id);
        Assert.notNull(user,"没有该用户");
        return ResponseWrapper.succ(user);
    }

    @RequiresAuthentication
    @GetMapping("/index")
    public ResponseWrapper index(){
        User user = userService.getById(1L);
        return ResponseWrapper.succ(user);
    }

    public ResponseWrapper save(@Validated @RequestBody User user){
        return ResponseWrapper.succ(user);
    }
}
