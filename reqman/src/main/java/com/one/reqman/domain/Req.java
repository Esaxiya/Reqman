package com.one.reqman.domain;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

/**
 * 该实体类表示的是HTTP接口测试的接口Request请求实体类
 * <p>
 *     实体类的主要职责是存储和管理系统内部的信息.
 *     它也可以有行为，甚至很复杂的行为，但这些行为必须与它所代表的实体对象密切相关
 *     实体类主要是作为数据管理和业务逻辑处理层面上存在的类别.
 *     实体类是用于对必须存储的信息和相关行为建模的类.
 *     实体类表示系统中的信息存储，它们一般用于表示系统所管理的核心概念.
 * </p>
 * {@link Entity} 存储Request对象，注释为JPA实体;这告诉Hibernate从这个类中创建一个表
 *
 */
@Entity
public class Req {

    /**
     * 请求的编号
     */
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    /**
     * 请求的名称
     */
    private String name;

    /**
     * 请求的方法
     */
    private String method;

    /**
     * 请求的路径
     */
    private String uri;

    /**
     * 请求的参数
     */
    private String params;

    public Req() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getMethod() {
        return method;
    }

    public void setMethod(String method) {
        this.method = method;
    }

    public String getUri() {
        return uri;
    }

    public void setUri(String uri) {
        this.uri = uri;
    }

    public String getParams() {
        return params;
    }

    public void setParams(String params) {
        this.params = params;
    }

    @Override
    public String toString() {
        return "Req{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", method='" + method + '\'' +
                ", uri='" + uri + '\'' +
                ", params='" + params + '\'' +
                '}';
    }
}
