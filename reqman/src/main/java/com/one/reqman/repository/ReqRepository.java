package com.one.reqman.repository;

import com.one.reqman.domain.Req;
import org.springframework.data.jpa.repository.JpaRepository;


/**
 * 这将由Spring自动实现为名为ReqRepository的Bean
 * CRUD refers Create, Read, Update, Delete
 * CRUD指的是创建，读取，更新，删除
 *
 * CrudRepository： 继承 Repository，实现了一组 CRUD 相关的方法
 * PagingAndSortingRepository：继承 CrudRepository，实现了一组分页排序相关的方法
 * JpaRepository： 继承 PagingAndSortingRepository，实现一组 JPA 规范相关的方法
 */
public interface ReqRepository extends JpaRepository<Req,Long> {

}
