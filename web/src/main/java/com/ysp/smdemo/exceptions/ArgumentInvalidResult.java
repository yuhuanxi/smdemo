package com.ysp.smdemo.exceptions;

import lombok.AllArgsConstructor;
import lombok.Data;

/**
 * @author: shipeng.yu
 * @time: 2016年10月06日 下午3:20
 * @version: 1.0
 * @since: 1.0
 * @description:
 */
@Data
@AllArgsConstructor
public class ArgumentInvalidResult {

    private String field;
    private Object rejectedValue;
    private String defaultMessage;

}
