package com.ysp.smdemo.service.impls;

import com.ysp.smdemo.dao.interfaces.IUserDao;
import com.ysp.smdemo.model.User;
import com.ysp.smdemo.service.interfaces.IUserService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * @author: shipeng.yu
 * @time: 2016年12月13日 上午10:19
 * @version: 1.0
 * @since: 1.0
 * @description:
 */
@Service
public class UserService extends BaseService<User> implements IUserService {

    private static final Logger LOG = LoggerFactory.getLogger(UserService.class);

    public UserService(IUserDao baseDao) {
        super(baseDao);
        System.out.println("user service 构造方法开始");
    }

    @Override
    @Transactional // 测试事物,insertBatch 的时候会报错,所以这里回一起回滚
    public int insertBatch(List<User> users) {
        int insert = baseDao.insert(users.get(0));
        LOG.info("insert:{}", insert);
        return baseDao.insertBatch(users);
    }

}
