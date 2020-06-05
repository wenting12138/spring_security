package com.wen.security.model;

import lombok.Data;

/**
 * @author wenting
 * @version 创建时间：2020/6/5 17:23
 * @ClassName 类名称
 * @Description 类描述
 */
@Data
public class Permission {

    private String id;
    private String code;
    private String description;
    private String url;

}
