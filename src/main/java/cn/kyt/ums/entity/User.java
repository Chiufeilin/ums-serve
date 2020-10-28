package cn.kyt.ums.entity;

import cn.kyt.ums.utils.excel.ExcelCell;
import com.fasterxml.jackson.annotation.JsonProperty;
import io.swagger.annotations.ApiModelProperty;

import java.util.Date;

public class User {
    @JsonProperty("companyId")
    private Long companyId;

    @JsonProperty("userId")
    private Long userId;

    @JsonProperty("createBy")
    private Long createBy;

    @JsonProperty("createDate")
    private Date createDate;

    @JsonProperty("updateBy")
    private Long updateBy;

    @JsonProperty("updateDate")
    private Date updateDate;

    @JsonProperty("loginName")
    private String loginName;

    @JsonProperty("password")
    private String password;

    @ExcelCell(index = 0, valid = @ExcelCell.Valid(allowNull = false))
    @JsonProperty("userName")
    private String userName;

    @ExcelCell(index = 1, valid = @ExcelCell.Valid(allowNull = true))
    @JsonProperty("email")
    private String email;

    @ExcelCell(index = 2, valid = @ExcelCell.Valid(allowNull = false))
    @JsonProperty("phone")
    private String phone;

    @JsonProperty("lastLogin")
    private Date lastLogin;

    @ApiModelProperty(value = "CompanyId")
    public Long getCompanyId() {
        return companyId;
    }

    public void setCompanyId(Long companyId) {
        this.companyId = companyId;
    }

    @ApiModelProperty(value = "UserId")
    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long aUserId) {
        this.userId = aUserId;
    }

    public User userId(Long userId) {
        this.userId = userId;
        return this;
    }

    @ApiModelProperty(value = "CreateBy")
    public Long getCreateBy() {
        return createBy;
    }

    public void setCreateBy(Long aCreateBy) {
        this.createBy = aCreateBy;
    }

    public User createBy(Long createBy) {
        this.createBy = createBy;
        return this;
    }

    @ApiModelProperty(value = "CreateDate")
    public Date getCreateDate() {
        return createDate;
    }

    public void setCreateDate(Date aCreateDate) {
        this.createDate = aCreateDate;
    }

    public User createDate(Date createDate) {
        this.createDate = createDate;
        return this;
    }

    @ApiModelProperty(value = "UpdateBy")
    public Long getUpdateBy() {
        return updateBy;
    }

    public void setUpdateBy(Long aUpdateBy) {
        this.updateBy = aUpdateBy;
    }

    public User updateBy(Long updateBy) {
        this.updateBy = updateBy;
        return this;
    }

    @ApiModelProperty(value = "UpdateDate")
    public Date getUpdateDate() {
        return updateDate;
    }

    public void setUpdateDate(Date aUpdateDate) {
        this.updateDate = aUpdateDate;
    }

    public User updateDate(Date updateDate) {
        this.updateDate = updateDate;
        return this;
    }

    @ApiModelProperty(value = "LoginName")
    public String getLoginName() {
        return loginName;
    }

    public void setLoginName(String aLoginName) {
        this.loginName = aLoginName;
    }

    public User loginName(String loginName) {
        this.loginName = loginName;
        return this;
    }

    @ApiModelProperty(value = "Password")
    public String getPassword() {
        return password;
    }

    public void setPassword(String aPassword) {
        this.password = aPassword;
    }

    public User password(String password) {
        this.password = password;
        return this;
    }

    @ApiModelProperty(value = "UserName")
    public String getUserName() {
        return userName;
    }

    public void setUserName(String aUserName) {
        this.userName = aUserName;
    }

    public User userName(String userName) {
        this.userName = userName;
        return this;
    }

    @ApiModelProperty(value = "Email")
    public String getEmail() {
        return email;
    }

    public void setEmail(String aEmail) {
        this.email = aEmail;
    }

    public User email(String email) {
        this.email = email;
        return this;
    }

    @ApiModelProperty(value = "Phone")
    public String getPhone() {
        return phone;
    }

    public void setPhone(String aPhone) {
        this.phone = aPhone;
    }

    public User phone(String phone) {
        this.phone = phone;
        return this;
    }

    @ApiModelProperty(value = "LastLogin")
    public Date getLastLogin() {
        return lastLogin;
    }

    public void setLastLogin(Date aLastLogin) {
        this.lastLogin = aLastLogin;
    }

    public User lastLogin(Date lastLogin) {
        this.lastLogin = lastLogin;
        return this;
    }

    @Override
    public String toString() {
        return super.toString();
    }
}
