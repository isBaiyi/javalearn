package com.baiyi.entity;

import lombok.AllArgsConstructor;
import lombok.Data;

/**
 * @Author: BaiYi
 * @Description:
 * @Date: 2022/4/21 17:44
 */
@AllArgsConstructor
@Data
public class Student {
    private String studentNo;
    private String className;
    private String studentName;
    private String content;
}
