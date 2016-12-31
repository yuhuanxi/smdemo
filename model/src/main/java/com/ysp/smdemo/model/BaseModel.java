package com.ysp.smdemo.model;

import javax.persistence.Column;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.MappedSuperclass;
import java.io.Serializable;
import java.util.Date;

/**
 * @author: shipeng.yu
 * @time: 2016年12月18日 下午3:39
 * @version: 1.0
 * @since: 1.0
 * @description:
 */
@MappedSuperclass
public class BaseModel implements Serializable {

    /**
     * 主键自增长
     */
    @Id
    @GeneratedValue
    private Long id;

    /**
     * 插入时的时间戳
     */
    @Column(columnDefinition = " TIMESTAMP  NOT NULL")
    private Date createdTs;

    /**
     * 更新时的时间戳
     */
    @Column(columnDefinition = " TIMESTAMP  NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP")
    private Date updatedTs;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Date getCreatedTs() {
        return createdTs == null ? null : (Date) createdTs.clone();
    }

    public void setCreatedTs(Date createdTs) {
        if (createdTs == null)
            this.createdTs = null;
        else
            this.createdTs = (Date) createdTs.clone();
    }

    public Date getUpdatedTs() {
        return updatedTs == null ? null : (Date) updatedTs.clone();
    }

    public void setUpdatedTs(Date updatedTs) {
        if (updatedTs == null)
            this.updatedTs = null;
        else
            this.updatedTs = (Date) updatedTs.clone();
    }
}
