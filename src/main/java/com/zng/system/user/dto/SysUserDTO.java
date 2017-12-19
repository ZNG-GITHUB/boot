package com.zng.system.user.dto;

import com.zng.system.user.entity.SysUser;

/**
 * Created by John.Zhang on 2017/12/19.
 */
public class SysUserDTO {

    private Long id;
    private String userCode;
    private String userName;
    private Long phone;
    private String email;
    private String QQ;
    private String address;
    private String sign;
    private String descrbe;
    private Integer isActive;
    private Integer isLocked;

    public SysUserDTO() {
    }

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

    public String getDescrbe() {
        return descrbe;
    }

    public void setDescrbe(String descrbe) {
        this.descrbe = descrbe;
    }

    public Integer getIsActive() {
        return isActive;
    }

    public void setIsActive(Integer isActive) {
        this.isActive = isActive;
    }

    public Integer getIsLocked() {
        return isLocked;
    }

    public void setIsLocked(Integer isLocked) {
        this.isLocked = isLocked;
    }

    public static SysUserDTO toDTO(SysUser user){
        SysUserDTO dto = null;
        if(user != null){
            dto = new SysUserDTO();
            dto.setId(user.getId());
            dto.setUserCode(user.getUserCode());
            dto.setUserName(user.getUserName());
            dto.setAddress(user.getAddress());
            dto.setDescrbe(user.getDescr());
            dto.setPhone(user.getPhone());
            dto.setEmail(user.getEmail());
            dto.setQQ(user.getQQ());
            dto.setSign(user.getSign());
            dto.setIsActive(user.getIsActive());
            dto.setIsLocked(user.getIsLocked());
        }
        return dto;
    }
}
