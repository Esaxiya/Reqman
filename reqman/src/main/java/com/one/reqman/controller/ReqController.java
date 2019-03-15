package com.one.reqman.controller;

import com.alibaba.fastjson.JSON;
import com.one.reqman.domain.Req;
import com.one.reqman.repository.ReqRepository;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

/**
 * Req控制器、处理增删改查相关视图映射
 */
@RestController
public class ReqController {

    private static final Log logger = LogFactory.getLog(ReqController.class);

    /**
     * 定义私有req服务层对象、支持sever业务处理、事务处理
     */
    private final ReqRepository reqRepository;

    @Autowired
    public ReqController(ReqRepository reqRepository) {
        this.reqRepository = reqRepository;
    }

    /**
     * 保存一个接口对象
     */
    @RequestMapping("/save")
    public String saveReq(Req req) {
        reqRepository.save(req);
        return "success";
    }

    /**
     * 更新一个接口对象
     */
    @RequestMapping("/update")
    public String update(Req req) {
        reqRepository.save(req);
        return "success";
    }

    /**
     * 删除一个接口对象
     */
    @RequestMapping("/delete/{id}")
    public String deleteReq(@PathVariable Long id){
        reqRepository.delete(reqRepository.getOne(id));
        return "success";
    }

    /**
     * 查找一个接口对象
     */
    @RequestMapping("/findOne/{id}")
    public Req findOne(@PathVariable Long id){
        Optional<Req> op = reqRepository.findById(id);
        if(op.isPresent()){
            return reqRepository.findById(id).get();
        }
        logger.error("id = {"+id+"} 的Req对象不存在");
        return null;
    }

    /**
     *
     * 查找全部接口
     *
     *Json 格式数据返回
     *
     * layui格式哦
     * {
     *   "code": 0,
     *   "msg": "",
     *   "count": 1000,
     *   "data": [{}, {}]
     * }
     *
     *  com.alibaba.fastjson.JSONObject;  用于构建json对象并返回
     */
    @RequestMapping("/findAll")
    public String findAll(){
        List<Req> reqs = reqRepository.findAll();
        return JSON.toJSONString(reqs);
    }
}
