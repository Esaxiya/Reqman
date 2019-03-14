package com.one.reqman.controller;

import com.one.reqman.domain.Req;
import com.one.reqman.repository.ReqRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 *
 */
@RestController
public class ReqController {

    /**
     * 暂时不使用service层、后期添加sever事务处理
     */
    @Autowired
    ReqRepository reqRepository;

    @RequestMapping("/save")
    public String saveReq(Req req){
        reqRepository.save(req);
        return "success";
    }


    /**
     *
     */
    public String update(Req req){
        Req req1 = reqRepository.getOne(req.getId());
        // 待完善
        return "";
    }
}
