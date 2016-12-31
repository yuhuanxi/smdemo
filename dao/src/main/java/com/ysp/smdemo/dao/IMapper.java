package com.ysp.smdemo.dao;

import java.util.List;
import java.util.Map;

/**
 * @author: shipeng.yu
 * @time: 2016年09月21日 下午9:50
 * @version: 1.0
 * @since: 1.0
 * @description:
 */
public interface IMapper<T> {

    T selectOne(Map<String, Object> params);

    List<T> select(Map<String, Object> params);

    long count(Map<String, Object> params);

    int insert(T o);

    int insertBatch(List<T> os);

    int update(T o);

    int delete(Map<String, Object> params);

    int deleteById(Long id);
}
