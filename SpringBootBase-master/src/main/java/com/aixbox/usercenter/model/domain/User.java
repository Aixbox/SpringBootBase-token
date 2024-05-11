package com.aixbox.usercenter.model.domain;

import com.baomidou.mybatisplus.annotation.*;
import lombok.Data;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;
import java.io.Serializable;
import java.util.Date;

/**
 * 基础用户表
 * @TableName user
 */
@TableName(value ="user")
@Data
public class User implements Serializable {
    /**
     * id
     */
    @TableId(type = IdType.AUTO)
    private Long id;

    /**
     * 用户名
     */
    private String userName;

    /**
     * 用户昵称
     */
    @Size(max = 10, message = "用户名长度不能超过10个字符")
    @Pattern(regexp = "[a-zA-Z\u4e00-\u9fa5]+", message = "用户名必须包含英文或汉字")
    private String nickName;

    /**
     * 密码
     */
    @NotNull(message = "密码不能为空")
    @Size(min = 8, max = 20, message = "密码长度应在8到20个字符之间")
    private String password;

    /**
     * 邮箱
     */
    private String email;

    /**
     * 手机号
     */
    @NotBlank(message = "电话号码不能为空")
    @Pattern(regexp = "^1[3-9]\\d{9}$", message = "请输入正确的中国大陆手机号码")
    private String phone;

    /**
     * 是否删除
     */
    @TableLogic
    private Integer isDelete;

    /**
     * 创建时间
     */
    private Date createTime;

    /**
     * 更新时间
     */
    private Date updateTime;

    /**
     * qq号
     */
    private String qqAccount;

    /**
     * 微信号
     */
    private String wxAccount;

    /**
     * 用户头像
     */
    private String avatarUrl;

    /**
     * 性别
     */
    private Integer gender;

    /**
     * 用户角色 普通角色-0 管理员-1
     */
    private Integer userRole;

    /**
     * 扼要介绍
     */
    private String profile;

    /**
     * 状态 0-正常
     */
    private Integer userStatus;

    /**
     * 标签列表
     */
    private String tags;

    @TableField(exist = false)
    private static final long serialVersionUID = 1L;

    @Override
    public boolean equals(Object that) {
        if (this == that) {
            return true;
        }
        if (that == null) {
            return false;
        }
        if (getClass() != that.getClass()) {
            return false;
        }
        User other = (User) that;
        return (this.getId() == null ? other.getId() == null : this.getId().equals(other.getId()))
            && (this.getUserName() == null ? other.getUserName() == null : this.getUserName().equals(other.getUserName()))
            && (this.getNickName() == null ? other.getNickName() == null : this.getNickName().equals(other.getNickName()))
            && (this.getPassword() == null ? other.getPassword() == null : this.getPassword().equals(other.getPassword()))
            && (this.getEmail() == null ? other.getEmail() == null : this.getEmail().equals(other.getEmail()))
            && (this.getPhone() == null ? other.getPhone() == null : this.getPhone().equals(other.getPhone()))
            && (this.getIsDelete() == null ? other.getIsDelete() == null : this.getIsDelete().equals(other.getIsDelete()))
            && (this.getCreateTime() == null ? other.getCreateTime() == null : this.getCreateTime().equals(other.getCreateTime()))
            && (this.getUpdateTime() == null ? other.getUpdateTime() == null : this.getUpdateTime().equals(other.getUpdateTime()))
            && (this.getQqAccount() == null ? other.getQqAccount() == null : this.getQqAccount().equals(other.getQqAccount()))
            && (this.getWxAccount() == null ? other.getWxAccount() == null : this.getWxAccount().equals(other.getWxAccount()))
            && (this.getAvatarUrl() == null ? other.getAvatarUrl() == null : this.getAvatarUrl().equals(other.getAvatarUrl()))
            && (this.getGender() == null ? other.getGender() == null : this.getGender().equals(other.getGender()))
            && (this.getUserRole() == null ? other.getUserRole() == null : this.getUserRole().equals(other.getUserRole()))
            && (this.getProfile() == null ? other.getProfile() == null : this.getProfile().equals(other.getProfile()))
            && (this.getUserStatus() == null ? other.getUserStatus() == null : this.getUserStatus().equals(other.getUserStatus()))
            && (this.getTags() == null ? other.getTags() == null : this.getTags().equals(other.getTags()));
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((getId() == null) ? 0 : getId().hashCode());
        result = prime * result + ((getUserName() == null) ? 0 : getUserName().hashCode());
        result = prime * result + ((getNickName() == null) ? 0 : getNickName().hashCode());
        result = prime * result + ((getPassword() == null) ? 0 : getPassword().hashCode());
        result = prime * result + ((getEmail() == null) ? 0 : getEmail().hashCode());
        result = prime * result + ((getPhone() == null) ? 0 : getPhone().hashCode());
        result = prime * result + ((getIsDelete() == null) ? 0 : getIsDelete().hashCode());
        result = prime * result + ((getCreateTime() == null) ? 0 : getCreateTime().hashCode());
        result = prime * result + ((getUpdateTime() == null) ? 0 : getUpdateTime().hashCode());
        result = prime * result + ((getQqAccount() == null) ? 0 : getQqAccount().hashCode());
        result = prime * result + ((getWxAccount() == null) ? 0 : getWxAccount().hashCode());
        result = prime * result + ((getAvatarUrl() == null) ? 0 : getAvatarUrl().hashCode());
        result = prime * result + ((getGender() == null) ? 0 : getGender().hashCode());
        result = prime * result + ((getUserRole() == null) ? 0 : getUserRole().hashCode());
        result = prime * result + ((getProfile() == null) ? 0 : getProfile().hashCode());
        result = prime * result + ((getUserStatus() == null) ? 0 : getUserStatus().hashCode());
        result = prime * result + ((getTags() == null) ? 0 : getTags().hashCode());
        return result;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(getClass().getSimpleName());
        sb.append(" [");
        sb.append("Hash = ").append(hashCode());
        sb.append(", id=").append(id);
        sb.append(", userName=").append(userName);
        sb.append(", nickName=").append(nickName);
        sb.append(", password=").append(password);
        sb.append(", email=").append(email);
        sb.append(", phone=").append(phone);
        sb.append(", isDelete=").append(isDelete);
        sb.append(", createTime=").append(createTime);
        sb.append(", updateTime=").append(updateTime);
        sb.append(", qqAccount=").append(qqAccount);
        sb.append(", wxAccount=").append(wxAccount);
        sb.append(", avatarUrl=").append(avatarUrl);
        sb.append(", gender=").append(gender);
        sb.append(", userRole=").append(userRole);
        sb.append(", profile=").append(profile);
        sb.append(", userStatus=").append(userStatus);
        sb.append(", tags=").append(tags);
        sb.append(", serialVersionUID=").append(serialVersionUID);
        sb.append("]");
        return sb.toString();
    }
}