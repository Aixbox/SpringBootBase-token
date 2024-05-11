package com.aixbox.usercenter.common;

import lombok.Data;

import java.io.Serializable;

/**
 * 分页数据，用于给请求对象或其他对象继承
 *
 * @author 魔王Aixbox
 * @version 1.0
 */
@Data
public class PageRequest implements Serializable {


    /**
     * 页大小
     */
    protected int pageSize;


    /**
     * 当前是第几页
     */
    protected int pageNum;
}
