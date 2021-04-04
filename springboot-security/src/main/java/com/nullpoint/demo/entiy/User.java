package com.nullpoint.demo.entiy;

import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

/**
 * @author nullpoint
 * @date 2020/6/11
 * @desc
 */
@Data
@TableName("user")
public class User {

    private Integer id;
    private String username;
    private String password;
}
