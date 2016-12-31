package com.ysp.smdemo.dao.impls;

import com.ysp.smdemo.dao.interfaces.ICityDao;
import com.ysp.smdemo.dao.mappers.ICityMapper;
import com.ysp.smdemo.model.City;
import org.springframework.stereotype.Repository;

/**
 * @author: shipeng.yu
 * @time: 2016年12月27日 下午8:53
 * @version: 1.0
 * @since: 1.0
 * @description:
 */
@Repository
public class CityDao extends BaseDao<City> implements ICityDao {

    public CityDao(ICityMapper mapper) {
        super(mapper);
    }

}
