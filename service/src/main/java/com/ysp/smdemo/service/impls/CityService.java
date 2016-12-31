package com.ysp.smdemo.service.impls;

import com.ysp.smdemo.dao.interfaces.ICityDao;
import com.ysp.smdemo.model.City;
import com.ysp.smdemo.service.interfaces.ICityService;
import org.springframework.stereotype.Service;

/**
 * @author: shipeng.yu
 * @time: 2016年12月27日 下午8:51
 * @version: 1.0
 * @since: 1.0
 * @description:
 */
@Service
public class CityService extends BaseService<City> implements ICityService {

    public CityService(ICityDao baseDao) {
        super(baseDao);
    }
}
