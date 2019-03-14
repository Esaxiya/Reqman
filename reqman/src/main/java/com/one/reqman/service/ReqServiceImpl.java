package com.one.reqman.service;


import com.one.reqman.domain.Req;
import com.one.reqman.repository.ReqRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * Req实体类的服务层实现、业务逻辑、事务处理都在此处
 */
@Service
public class ReqServiceImpl implements ReqService {

    /**
     * 定一个属性、req持久化对象
     */
    private final ReqRepository reqRepository;

    @Autowired
    public ReqServiceImpl(ReqRepository reqRepository) {
        this.reqRepository = reqRepository;
    }

    @Override
    public Req get(Long id) {
        return null;
    }

    @Override
    @Transactional
    public Req save(Req req){
        reqRepository.save(req);
        return req;
    }

    @Override
    @Transactional
    public Req update(Req req) {
        return reqRepository.save(req);
    }

    @Override
    @Transactional
    public void delete(Long id) {
        reqRepository.delete(reqRepository.getOne(id));
    }

    @Override
    public List<Req> getAllReqs() {
        return reqRepository.findAll();
    }

}
