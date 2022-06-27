package com.baiyi.entity;

/**
 * @Author: BaiYi
 * @Description:
 * @Date: 2022/5/14 14:14
 */
public class User {
    private Integer id;
    private String name;

    private byte[] bytes = new byte[1024 * 100];

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

    public User() {
    }

    public User(Integer id, String name) {
        this.id = id;
        this.name = name;
    }

    @Override
    public String toString() {
        return "User{" +
                "id=" + id +
                ", name='" + name + '\'' +
                '}';
    }
}

