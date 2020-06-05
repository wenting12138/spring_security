package com.wen.security.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author wenting
 * @version 创建时间：2020/6/5 16:25
 * @ClassName 类名称
 * @Description 类描述
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class UerDto {

    private String id;
    private String username;
    private String password;
    private String fullname;
    private String mobile;

}
