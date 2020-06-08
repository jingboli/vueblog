package cc.lijingbo.vueblog.controller;

import cc.lijingbo.vueblog.domain.ResponseWrapper;
import cc.lijingbo.vueblog.entity.Blog;
import cc.lijingbo.vueblog.service.BlogService;

import cc.lijingbo.vueblog.shiro.utils.ShiroUtil;
import cn.hutool.core.bean.BeanUtil;
import cn.hutool.core.lang.Assert;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import org.apache.shiro.authz.annotation.RequiresAuthentication;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;


@RestController
public class BlogController {

    @Autowired
    private BlogService blogService;

    @GetMapping("blogs")
    public ResponseWrapper blogs(@RequestParam(defaultValue = "1") Integer currentPage) {
        Page page = new Page(currentPage, 5);
        IPage pageData = blogService.page(page, new QueryWrapper<Blog>().orderByDesc("created"));
        return ResponseWrapper.succ(pageData);
    }

    @GetMapping("/blog/{id}")
    public ResponseWrapper detail(@PathVariable(name = "id") Long id) {
        Blog blog = blogService.getById(id);
        Assert.notNull(blog, "该博客已被删除");
        return ResponseWrapper.succ(blog);
    }

    @RequiresAuthentication
    @PostMapping("/blog/edit")
    public ResponseWrapper edit(@Validated @RequestBody Blog blog) {
        Blog temp = null;
        if (blog.getId() != null) {
            temp = blogService.getById(blog.getId());
            Assert.isTrue(temp.getUserId().longValue() == ShiroUtil.getProfile().getId().longValue(), "没有权限编辑");
        } else {
            temp = new Blog();
            temp.setUserId(ShiroUtil.getProfile().getId());
            temp.setCreated(LocalDateTime.now());
            temp.setStatus(0);
        }
        BeanUtil.copyProperties(blog, temp, "id", "userId", "created", "status");
        blogService.saveOrUpdate(temp);
        return ResponseWrapper.succ(null);
    }

}
