package com.ysp.smdemo.service.interfaces;

import com.ysp.smdemo.common.exceptions.DataNotFoundException;

import java.util.List;
import java.util.Map;

/**
 * @author: shipeng.yu
 * @time: 2016年12月22日 上午10:27
 * @version: 1.0
 * @since: 1.0
 * @description:
 */
public interface IBaseService<T> {

    int insert(T t);

    int insertBatch(List<T> list);

    int update(T t);

    T selectOne(Map<String, Object> params) throws DataNotFoundException;

    List<T> select(Map<String, Object> params) throws DataNotFoundException;

    int delete(Map<String, Object> params);

    long count(Map<String, Object> params);

    int deleteById(Long id);
}
