package com.one.reqman.service;

import com.one.reqman.domain.Req;

import java.util.List;

/**
 * Req实体类的服务接口、定义一些方法声明
 */
public interface ReqService {

    /**
     * 获取单个接口对象
     */
    Req get(Long id);

    /**
     * 保持接口对象
     */
    Req save(Req req);

    /**
     * 更新接口对象
     */
    Req update(Req req);

    /**
     * 删除接口对象
     */
    void delete(Long id);

    /**
     * 获取全部接口对象
     */
    List<Req> getAllReqs();


}