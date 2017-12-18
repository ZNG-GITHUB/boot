package com.zng.system.user.entity;

import org.hibernate.annotations.GenericGenerator;
import javax.persistence.*;
import static javax.persistence.GenerationType.TABLE;

/**
 * Created by John.Zhang on 2017/12/18.
 */
@Entity
@Table(name = "tab_sys_user")
public class SysUser {

    @Id
    @Column(name = "user_id")
    @GeneratedValue(strategy = TABLE, generator = "sequence_generator")
    @GenericGenerator(
            name = "sequence_generator",
            strategy = "org.hibernate.id.enhanced.TableGenerator",
            parameters = {
                    @org.hibernate.annotations.Parameter(name = "segment_value", value = "user"),
                    @org.hibernate.annotations.Parameter(name = "increment_size", value = "1"),
                    @org.hibernate.annotations.Parameter(name = "optimizer", value = "pooled")
            })
    private Long id;

    @Column(name = "user_code",nullable = false,unique = true,length = 50)
    private String userCode;

    @Column(name = "user_name",nullable = false,length = 50)
    private String userName;

    @Column(name = "user_pass",nullable = false)
    private String password;

    private Integer isActive;

}
