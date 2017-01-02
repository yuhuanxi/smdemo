package com.ysp.smdemo.controllers;

import com.ysp.smdemo.common.exceptions.DataNotFoundException;
import com.ysp.smdemo.common.utils.ReturnCode;
import com.ysp.smdemo.model.City;
import com.ysp.smdemo.model.User;
import com.ysp.smdemo.service.interfaces.ICityService;
import com.ysp.smdemo.service.interfaces.IUserService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author: shipeng.yu
 * @time: 2016年12月18日 下午11:13
 * @version: 1.0
 * @since: 1.0
 * @description:
 */
@RestController
public class UserCtrl extends BaseController {

    private static final Logger LOG = LoggerFactory.getLogger(UserCtrl.class);

    @Autowired
    IUserService userService;

    @Autowired
    ICityService cityService;

    @PostMapping(value = "/api/v1/user")
    public BaseAjaxResult insertUser(@Validated User user) {

        long result = userService.insert(user);

        if (result > 0) {
            return renderJsonSuccess();
        }

        return renderJsonFail();
    }

    @GetMapping(value = "/api/v1/user/{id}")
    @Cacheable(value = "userCache", key = "#id")
    public BaseAjaxResult getUser(@PathVariable Long id) throws DataNotFoundException {

        Map<String, Object> params = new HashMap<>();
        params.put("id", id);

        LOG.info("select from DB");

        User user = userService.selectOne(params);

        return renderJsonAjaxResult(ReturnCode.SUCCESS.getCode(), ReturnCode.SUCCESS.getMsg(), user);
    }

    @PostMapping(value = "/api/v1/city")
    public BaseAjaxResult insertCity(@Validated City city) {

        long result = cityService.insert(city);

        if (result > 0) {
            return renderJsonSuccess();
        }

        return renderJsonFail();
    }

    @PostMapping(value = "/api/v1/users")
    public BaseAjaxResult addBatch(@Validated User user) {

        List<User> users = new ArrayList<>();

        users.add(user);

        for (int i = 0; i < 10; i++) {
            User user1 = new User();
            user1.setPassword("3333333333333ddd33");
            user1.setUsername("bb");
            users.add(user1);
        }

        long result = userService.insertBatch(users);

        if (result > 0) {
            return renderJsonSuccess();
        }

        return renderJsonFail();
    }

}
