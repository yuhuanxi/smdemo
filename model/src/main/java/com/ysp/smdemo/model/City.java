package com.ysp.smdemo.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import javax.persistence.Column;

/**
 * @author: shipeng.yu
 * @time: 2016年12月27日 下午8:49
 * @version: 1.0
 * @since: 1.0
 * @description:
 */
@Data
@EqualsAndHashCode
@NoArgsConstructor
@AllArgsConstructor
//@Entity(name = "city")
public class City extends BaseModel {

    @Column(name = "city_name")
    private String cityName;

    @Column(name = "city_code")
    private String cityCode;
}
