package cn.kyt.ums.api;

import cn.kyt.ums.entity.User;
import cn.kyt.ums.utils.Page;
import io.swagger.annotations.*;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;
import javax.validation.constraints.NotNull;

@javax.annotation.Generated(value = "io.swagger.codegen.languages.SpringCodegen", date = "2020-10-28T13:20:46.077Z")

@Api(value = "系统管理-用户", description = "用户API")
@RequestMapping(value = "/v2")
public interface UserApi {

    @ApiOperation(value = "增加用户", notes = "增加用户.", response = Void.class, tags={ "系统管理-用户", })
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "操作成功", response = Void.class) })
    @RequestMapping(value = "/company/{companyId}/user",
            produces = { "application/json" },
            method = RequestMethod.POST)
    ResponseEntity<Void> createUser(@ApiParam(value = "公司ID", required = true) @Valid @PathVariable Long companyId,
                                    @ApiParam(value = "用户实体", required = true) @Valid @RequestBody User user);

    @ApiOperation(value = "删除用户", notes = "删除用户", response = Void.class, tags={ "系统管理-用户", })
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "操作成功", response = Void.class),
            @ApiResponse(code = 404, message = "用户不存在", response = Void.class) })
    @RequestMapping(value = "/company/{companyId}/user/{userId}",
            produces = { "application/json" },
            method = RequestMethod.DELETE)
    ResponseEntity<Void> deleteUser(@ApiParam(value = "公司ID", required = true) @PathVariable("companyId") Long companyId,
                                    @ApiParam(value = "用户Id", required = true) @PathVariable("userId") Long userId);

    @ApiOperation(value = "查询单个用户", notes = "", response = User.class, tags={ "系统管理-用户", })
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "操作成功", response = User.class) })
    @RequestMapping(value = "/company/{companyId}/user/{userId}",
            produces = { "application/json" },
            method = RequestMethod.GET)
    ResponseEntity<User> findOneUser(@NotNull @ApiParam(value = "公司Id", required = true) @PathVariable(value = "companyId", required = true) Long companyId,
                                     @NotNull @ApiParam(value = "用户Id", required = true) @PathVariable(value = "userId", required = true) Long userId);

    @ApiOperation(value = "修改用户", notes = "修改用户", response = Void.class, tags={ "系统管理-用户", })
    @ApiResponses(value = {
            @ApiResponse(code = 404, message = "对象不存在", response = Void.class) })
    @RequestMapping(value = "/company/{companyId}/user/{userId}",
            produces = { "application/json" },
            method = RequestMethod.PUT)
    ResponseEntity<Void> updateUser(@ApiParam(value = "单位ID", required = true) @PathVariable(required = true) Long companyId,
                                    @ApiParam(value = "用户ID", required = true) @Valid @PathVariable Long userId,
                                    @ApiParam(value = "用户对象", required = true) @Valid @RequestBody User body);

    @ApiOperation(value = "修改密码", notes = "", response = Void.class, tags={ "系统管理-用户", })
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "操作成功", response = Void.class),
            @ApiResponse(code = 400, message = "密码不正确", response = Void.class),
            @ApiResponse(code = 500, message = "修改密码失败", response = Void.class) })
    @RequestMapping(value = "/user/changePassword/{userId}",
            produces = { "application/json" },
            method = RequestMethod.PUT)
    ResponseEntity<Void> changePassword(@ApiParam(value = "用户Id", required = true) @PathVariable(value = "userId", required = true) Long userId,
                                        @ApiParam(value = "", required = true) @RequestParam(value = "oldPassword", required = true) String oldPassword,
                                        @ApiParam(value = "", required = true) @RequestParam(value = "newPassword", required = true) String newPassword);

    @ApiOperation(value = "修改手机号", notes = "", response = Void.class, tags={ "系统管理-用户", })
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "操作成功", response = Void.class) })
    @RequestMapping(value = "/user/changeUserPhone/{userId}",
            produces = { "application/json" },
            method = RequestMethod.PUT)
    ResponseEntity<Void> changeUserPhone(@ApiParam(value = "用户Id", required = true) @PathVariable(value = "userId", required = true) Long userId,
                                         @ApiParam(value = "用户实体", required = true) @Valid @RequestBody User body);

    @ApiOperation(value = "查询用户列表", notes = "", response = User.class, tags={ "系统管理-用户", })
    @ApiResponses(value = {
        @ApiResponse(code = 200, message = "操作成功", response = User.class) })
    @RequestMapping(value = "/company/{companyId}/user",
        produces = { "application/json" },
        method = RequestMethod.GET)
    ResponseEntity<Page<User>> findUsers(HttpServletRequest request,
                                         @NotNull @ApiParam(value = "", required = false) @RequestParam(value = "q", required = false) String q,
                                         @NotNull @ApiParam(value = "", required = true) @RequestParam(value = "page", required = true) Integer page,
                                         @NotNull @ApiParam(value = "", required = true) @RequestParam(value = "pageSize", required = true) Integer pageSize,
                                         @NotNull @ApiParam(value = "公司Id", required = true) @PathVariable(value = "companyId", required = true) Long companyId);

    @ApiOperation(value = "重置密码", notes = "", response = Void.class, tags={ "系统管理-用户", })
    @ApiResponses(value = {
        @ApiResponse(code = 200, message = "操作成功", response = Void.class),
        @ApiResponse(code = 500, message = "操作失败", response = Void.class) })
    @RequestMapping(value = "/company/{companyId}/user/{userId}/resetPassword",
        produces = { "application/json" },
        method = RequestMethod.POST)
    ResponseEntity<Void> resetPassword(@NotNull @ApiParam(value = "用户Id", required = true) @PathVariable(value = "userId", required = true) Long userId,
                                       @PathVariable Long companyId);

    @ApiOperation(value = "导出用户", notes = "导出用户", response = Void.class, tags={ "系统管理-用户", })
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "操作成功", response = Void.class) })
    @RequestMapping(value = "/company/{companyId}/user/export",
            method = RequestMethod.GET)
    ResponseEntity<Void> exportUsers(HttpServletRequest request, HttpServletResponse response, @ApiParam(value = "companyId", required = true) @PathVariable("companyId") Long companyId, @NotNull @ApiParam(value = "", required = false) @RequestParam(value = "q", required = false) String q);

    @ApiOperation(value = "退出登录", notes = "退出登录", response = Void.class, tags={ "系统管理-用户", })
    @ApiResponses(value = {
            @ApiResponse(code = 404, message = "对象不存在", response = Void.class) })
    @RequestMapping(value = "/company/{companyId}/loginOut/{registrationId}",
            produces = { "application/json" },
            method = RequestMethod.PUT)
    ResponseEntity<Void> logOut(@ApiParam(value = "单位ID", required = true) @PathVariable(required = true) Long companyId,
                                @ApiParam(value = "用户极光id", required = true) @Valid @PathVariable String registrationId);

}
