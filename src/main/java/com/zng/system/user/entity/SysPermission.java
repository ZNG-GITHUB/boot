package com.zng.system.user.entity;

import com.zng.common.entity.CommonEntity;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;

import java.util.Set;

import static javax.persistence.GenerationType.TABLE;

/**
 * Created by John.Zhang on 2017/12/25.
 */
@Entity
@Table(name = "tab_sys_permission")
public class SysPermission extends CommonEntity{
    @Id
    @Column(name = "permission_id")
    @GeneratedValue(strategy = TABLE, generator = "sequence_generator_permission")
    @GenericGenerator(
            name = "sequence_generator_permission",
            strategy = "org.hibernate.id.enhanced.TableGenerator",
            parameters = {
                    @org.hibernate.annotations.Parameter(name = "segment_value", value = "permission"),
                    @org.hibernate.annotations.Parameter(name = "increment_size", value = "1"),
                    @org.hibernate.annotations.Parameter(name = "optimizer", value = "pooled")
            })
    private Long id;

    /**
     * 上级权限
     */
    @ManyToOne
    @JoinColumn(name = "p_id")
    private SysPermission parentPermission;

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
    @Column(name = "per_type")
    private PermissionType perType = PermissionType.Other;

    /**
     * url类型
     */
    @Column(name = "url_type")
    private String urlType;

    /**
     * 是否隐藏
     */
    @Column(name = "is_hided")
    private Boolean isHided;

    public enum PermissionType {
        /**
         * 目录，菜单，方法，其他
         */
        MenuGroup,Menu,Method,Other
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public SysPermission getParentPermission() {
        return parentPermission;
    }

    public void setParentPermission(SysPermission parentPermission) {
        this.parentPermission = parentPermission;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public PermissionType getPerType() {
        return perType;
    }

    public void setPerType(PermissionType perType) {
        this.perType = perType;
    }

    public String getUrlType() {
        return urlType;
    }

    public void setUrlType(String urlType) {
        this.urlType = urlType;
    }

    public Boolean getHided() {
        return isHided;
    }

    public void setHided(Boolean hided) {
        isHided = hided;
    }

}
