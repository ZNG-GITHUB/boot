package com.zng.system.user.entity;

import com.zng.common.entity.CommonEntity;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;

import static javax.persistence.GenerationType.TABLE;

/**
 * Created by John.Zhang on 2017/12/25.
 */
@Entity
@Table(name = "tab_sys_permission")
public class SysPermission extends CommonEntity{
    @Id
    @Column(name = "permission_id")
    @GeneratedValue(strategy = TABLE, generator = "sequence_generator")
    @GenericGenerator(
            name = "sequence_generator",
            strategy = "org.hibernate.id.enhanced.TableGenerator",
            parameters = {
                    @org.hibernate.annotations.Parameter(name = "segment_value", value = "permission"),
                    @org.hibernate.annotations.Parameter(name = "increment_size", value = "1"),
                    @org.hibernate.annotations.Parameter(name = "optimizer", value = "pooled")
            })
    private Long id;

    /**
     * 权限名称
     */
    @Column(name = "permission_name",unique = true,nullable = false)
    private String name;

    /**
     * 访问路径
     */
    @Column(name = "permission_url")
    private String url;

    /**
     * 权限级别
     */
    private PermissionType perType = PermissionType.Other;

    /**
     * url类型
     */
    private String urlType;


    public static enum PermissionType {
        /**
         * 目录，菜单，方法，其他
         */
        MenuGroup,Menu,Method,Other
    }

}
