package com.ysp.smdemo.service.impls;

import com.ysp.smdemo.common.exceptions.DataNotFoundException;
import com.ysp.smdemo.dao.interfaces.IBaseDao;
import com.ysp.smdemo.model.BaseModel;
import com.ysp.smdemo.service.interfaces.IBaseService;

import java.util.List;
import java.util.Map;

/**
 * @author: shipeng.yu
 * @time: 2016年12月27日 下午9:42
 * @version: 1.0
 * @since: 1.0
 * @description:
 */
public class BaseService<T extends BaseModel> implements IBaseService<T> {

    IBaseDao<T> baseDao;

    public BaseService(IBaseDao<T> baseDao) {
        this.baseDao = baseDao;
    }

    @Override
    public int insert(T t) {
        return baseDao.insert(t);
    }

    @Override
    public int insertBatch(List<T> list) {
        return baseDao.insertBatch(list);
    }

    @Override
    public int update(T t) {
        return baseDao.update(t);
    }

    @Override
    public T selectOne(Map<String, Object> params) throws DataNotFoundException {
        return baseDao.selectOne(params);
    }

    @Override
    public List<T> select(Map<String, Object> params) throws DataNotFoundException {
        return baseDao.select(params);
    }

    @Override
    public int delete(Map<String, Object> params) {
        return baseDao.delete(params);
    }

    @Override
    public long count(Map<String, Object> params) {
        return baseDao.count(params);
    }

    @Override
    public int deleteById(Long id) {
        return baseDao.deleteById(id);
    }
}
