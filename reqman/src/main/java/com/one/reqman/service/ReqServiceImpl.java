package com.one.reqman.service;


import com.one.reqman.domain.Req;
import com.one.reqman.repository.ReqRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * Req实体类的服务层实现、业务逻辑、事务处理都在此处
 */
@Service
public class ReqServiceImpl implements ReqService {

    @Autowired
    ReqRepository reqRepository;


    public void save(Req req){
        reqRepository.save(req);
    }

}
