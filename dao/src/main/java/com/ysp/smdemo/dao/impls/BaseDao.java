package com.ysp.smdemo.dao.impls;

import com.ysp.smdemo.dao.IMapper;
import com.ysp.smdemo.dao.interfaces.IBaseDao;
import com.ysp.smdemo.model.BaseModel;

import java.util.List;
import java.util.Map;

/**
 * @author: shipeng.yu
 * @time: 2016年12月27日 下午7:53
 * @version: 1.0
 * @since: 1.0
 * @description:
 */
public class BaseDao<T extends BaseModel> implements IBaseDao<T> {

    IMapper<T> mapper;

    public BaseDao(IMapper<T> mapper) {
        this.mapper = mapper;
    }

    @Override
    public int insert(T o) {
        return mapper.insert(o);
    }

    @Override
    public int insertBatch(List<T> list) {
        return mapper.insertBatch(list);
    }

    @Override
    public int update(T o) {
        return mapper.update(o);
    }

    @Override
    public int deleteById(Long id) {
        return mapper.deleteById(id);
    }

    @Override
    public long count(Map params) {
        return mapper.count(params);
    }

    @Override
    public int delete(Map params) {
        return mapper.delete(params);
    }

    @Override
    public List select(Map params) {
        return mapper.select(params);
    }

    @Override
    public T selectOne(Map params) {
        return (T) mapper.selectOne(params);
    }
}
