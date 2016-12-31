package com.ysp.smdemo.dao.impls;

import com.ysp.smdemo.dao.interfaces.IUserDao;
import com.ysp.smdemo.dao.mappers.IUserMapper;
import com.ysp.smdemo.model.User;
import org.springframework.stereotype.Repository;

/**
 * @author: shipeng.yu
 * @time: 2016年10月03日 上午10:20
 * @version: 1.0
 * @since: 1.0
 * @description:
 */
@Repository
public class UserDao extends BaseDao<User> implements IUserDao {

    public UserDao(IUserMapper userMapper) {
        super(userMapper);
    }

}
