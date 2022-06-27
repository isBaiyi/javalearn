package com.baiyi.entity;

/**
 * @Author: BaiYi
 * @Description:
 * @Date: 2022/5/14 14:14
 */
public class User1 {
    private Integer id;
    private String name;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return "User1{" +
                "id=" + id +
                ", name='" + name + '\'' +
                '}';
    }

    public void print() {
        System.out.println("另外一个自定义类加载器加载类调用方法");
    }
}
