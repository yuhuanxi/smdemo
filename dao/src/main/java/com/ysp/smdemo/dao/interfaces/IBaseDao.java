package com.ysp.smdemo.dao.interfaces;

import java.util.List;
import java.util.Map;

/**
 * @author: shipeng.yu
 * @time: 2016年12月22日 上午10:27
 * @version: 1.0
 * @since: 1.0
 * @description:
 */
public interface IBaseDao<T> {

    int insert(T t);

    int insertBatch(List<T> list);

    int update(T t);

    T selectOne(Map<String, Object> params);

    List<T> select(Map<String, Object> params);

    int delete(Map<String, Object> params);

    long count(Map<String, Object> params);

    int deleteById(Long id);
}
