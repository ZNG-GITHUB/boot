package com.zng.system.user.entity;

import com.zng.common.entity.CommonEntity;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;

import static javax.persistence.GenerationType.TABLE;

/**
 * Created by John.Zhang on 2017/12/25.
 */
@Entity
@Table(name = "tab_sys_role")
public class SysRole extends CommonEntity{

    @Id
    @Column(name = "role_id")
    @GeneratedValue(strategy = TABLE, generator = "sequence_generator")
    @GenericGenerator(
            name = "sequence_generator",
            strategy = "org.hibernate.id.enhanced.TableGenerator",
            parameters = {
                    @org.hibernate.annotations.Parameter(name = "segment_value", value = "role"),
                    @org.hibernate.annotations.Parameter(name = "increment_size", value = "1"),
                    @org.hibernate.annotations.Parameter(name = "optimizer", value = "pooled")
            })
    private Long id;

    /**
     * 角色名称
     */
    @Column(name = "role_name",unique = true,nullable = false)
    private String name;

    /**
     * 角色类型
     */
    @Column(name = "role_type",nullable = false,length = 1)
    private RoleType type = RoleType.Other;


    public static enum RoleType {

        /**
         * 管理员:全部权限
         * 通用：通用菜单
         * 访问：通用菜单访问
         * 其他：无
         */
        Admin,Normal,Visitor,Other

    }

}
