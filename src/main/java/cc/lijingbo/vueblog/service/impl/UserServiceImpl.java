package cc.lijingbo.vueblog.service.impl;

import cc.lijingbo.vueblog.entity.User;
import cc.lijingbo.vueblog.mapper.UserMapper;
import cc.lijingbo.vueblog.service.UserService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author lijingbo
 * @since 2020-06-05
 */
@Service
public class UserServiceImpl extends ServiceImpl<UserMapper, User> implements UserService {

}
