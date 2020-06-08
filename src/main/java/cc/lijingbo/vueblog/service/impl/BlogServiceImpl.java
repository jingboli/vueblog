package cc.lijingbo.vueblog.service.impl;

import cc.lijingbo.vueblog.entity.Blog;
import cc.lijingbo.vueblog.mapper.BlogMapper;
import cc.lijingbo.vueblog.service.BlogService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author lijingbo
 * @since 2020-06-07
 */
@Service
public class BlogServiceImpl extends ServiceImpl<BlogMapper, Blog> implements BlogService {

}
