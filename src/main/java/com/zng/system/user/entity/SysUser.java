package com.zng.system.user.entity;

import com.zng.common.entity.CommonEntity;
import org.hibernate.annotations.GenericGenerator;
import javax.persistence.*;
import java.io.Serializable;
import java.util.Set;
import java.util.UUID;

import static javax.persistence.GenerationType.TABLE;

/**
 * Created by John.Zhang on 2017/12/18.
 */
@Entity
@Table(name = "tab_sys_user")
public class SysUser extends CommonEntity implements Serializable{

    @Id
    @Column(name = "user_id")
    @GeneratedValue(strategy = TABLE, generator = "sequence_generator_user")
    @GenericGenerator(
            name = "sequence_generator_user",
            strategy = "org.hibernate.id.enhanced.TableGenerator",
            parameters = {
                    @org.hibernate.annotations.Parameter(name = "segment_value", value = "user"),
                    @org.hibernate.annotations.Parameter(name = "increment_size", value = "1"),
                    @org.hibernate.annotations.Parameter(name = "optimizer", value = "pooled")
            })
    private Long id;

    /**
     * 账户
     */
    @Column(name = "user_code",nullable = false,unique = true,length = 50)
    private String userCode;

    /**
     * 用户名
     */
    @Column(name = "user_name",nullable = false,length = 50)
    private String userName;

    /**
     * 密码
     */
    @Column(name = "user_pass",nullable = false)
    private String password;

    /**
     * 联系方式
     */
    @Column(name = "user_phone",length = 11)
    private Long phone;

    /**
     * 邮箱
     */
    @Column(name = "user_email",length = 50)
    private String email;

    /**
     * QQ号
     */
    @Column(name = "user_qq",length = 15)
    private String QQ;

    /**
     * 地址
     */
    @Column(name = "user_address")
    private String address;


    /**
     * 签名
     */
    @Column(name = "user_sign")
    private String sign;

    /**
     * 简介
     */
    @Column(name = "user_describe")
    private String descr;

    /**
     * 激活状态
     */
    @Column(name = "is_active",nullable = false,length = 1)
    private Integer isActive = 1;

    /**
     * 隐藏状态
     */
    @Column(name = "is_hided",nullable = false,length = 1)
    private Integer isHided = 0;

    /**
     * 锁定状态
     */
    @Column(name = "is_locked",nullable = false,length = 1)
    private IsLocked isLocked = IsLocked.UNLOCKED;

    /**
     * 盐加密
     */
    @Column(name = "user_salt",nullable = false)
    private String salt = UUID.randomUUID().toString();

    @ManyToMany(fetch = FetchType.LAZY)
    @JoinTable(name = "tab_sys_user_role",joinColumns = @JoinColumn(name = "user_id"),inverseJoinColumns = @JoinColumn(name = "role_id"))
    private Set<SysRole> roles;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getUserCode() {
        return userCode;
    }

    public void setUserCode(String userCode) {
        this.userCode = userCode;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public Long getPhone() {
        return phone;
    }

    public void setPhone(Long phone) {
        this.phone = phone;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getQQ() {
        return QQ;
    }

    public void setQQ(String QQ) {
        this.QQ = QQ;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getSign() {
        return sign;
    }

    public void setSign(String sign) {
        this.sign = sign;
    }

    public String getDescr() {
        return descr;
    }

    public void setDescr(String descr) {
        this.descr = descr;
    }

    public Integer getIsActive() {
        return isActive;
    }

    public void setIsActive(Integer isActive) {
        this.isActive = isActive;
    }

    public Integer getIsHided() {
        return isHided;
    }

    public void setIsHided(Integer isHided) {
        this.isHided = isHided;
    }

    public IsLocked getIsLocked() {
        return isLocked;
    }

    public void setIsLocked(IsLocked isLocked) {
        this.isLocked = isLocked;
    }

    public Set<SysRole> getRoles() {
        return roles;
    }

    public void setRoles(Set<SysRole> roles) {
        this.roles = roles;
    }

    public String getSalt() {
        return salt;
    }

    public void setSalt(String salt) {
        this.salt = salt;
    }

    public static enum IsLocked{
        UNLOCKED("正常"),LOCKED("锁定");

        IsLocked(String type){
            this.type = type;
        }

        private String type;

        public String getType() {
            return type;
        }

        public void setType(String type) {
            this.type = type;
        }

        public static IsLocked getValue(Integer i){
            switch (i){
                case 0:
                    return UNLOCKED;
                case 1:
                    return LOCKED;
                default:
                    return null;
            }
        }
    }
}
