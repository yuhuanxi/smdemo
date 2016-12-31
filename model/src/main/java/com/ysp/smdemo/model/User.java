package com.ysp.smdemo.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import org.hibernate.validator.constraints.Email;
import org.hibernate.validator.constraints.Length;
import org.hibernate.validator.constraints.NotEmpty;

import javax.persistence.Column;

/**
 * @author: shipeng.yu
 * @time: 2016年12月18日 下午10:42
 * @version: 1.0
 * @since: 1.0
 * @description:
 */
@Data
@EqualsAndHashCode
@NoArgsConstructor
@AllArgsConstructor
//@Entity(name = User.TABLE_NAME)
public class User extends BaseModel {

    public static final String TABLE_NAME = "user";

    @Column(name = "username", length = 30, nullable = false)
    private String username;

    @NotEmpty(message = "密码不能为空")
    @Length(min = 6, message = "密码长度不能小于6位")
    @Column(nullable = false)
    private String password;

    @Email(regexp = "^\\w+@[0-9a-zA-Z_]+?\\.[a-zA-Z]{2,5}$", message = "邮箱格式不正确")
    private String email;
}
