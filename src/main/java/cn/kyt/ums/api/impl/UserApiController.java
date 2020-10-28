package cn.kyt.ums.api.impl;

import cn.kyt.ums.api.UserApi;
import cn.kyt.ums.entity.User;
import cn.kyt.ums.exception.ServiceException;
import cn.kyt.ums.service.UserService;
import cn.kyt.ums.utils.ClientUtils;
import cn.kyt.ums.utils.DateUtils;
import cn.kyt.ums.utils.Page;
import cn.kyt.ums.utils.excel.ExcelUtil;
import io.swagger.annotations.ApiParam;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;
import java.util.LinkedHashMap;
import java.util.List;

@javax.annotation.Generated(value = "io.swagger.codegen.languages.SpringCodegen", date = "2020-10-28T13:20:46.077Z")

@RestController
@Controller
public class UserApiController implements UserApi {
    static Logger logger = LoggerFactory.getLogger(UserApiController.class);

    @Autowired
    UserService userService;

    @Override
    public ResponseEntity createUser(@Valid @PathVariable Long companyId,
                                     @Valid @RequestBody User user) {
        user.setCreateBy(ClientUtils.getCurrentUserId());
        user.setUpdateBy(ClientUtils.getCurrentUserId());
        try {
            userService.createUser(companyId, user);
        } catch (Exception ex) {
            throw new ServiceException("该手机号码已被注册,请重新输入!");
        }
        return new ResponseEntity<Void>(HttpStatus.OK);
    }


    public ResponseEntity<Void> deleteUser(@PathVariable("companyId") Long companyId,
                                           @PathVariable("userId") Long userId) {
        Long currentUserId = ClientUtils.getCurrentUserId();
        if (currentUserId.equals(userId)) {
            throw new ServiceException("不能删除自己的账号");
        }
        User user = userService.findOneUser(userId);
        userService.deleteUser(companyId, userId);
        return new ResponseEntity<Void>(HttpStatus.OK);
    }

    @Override
    public ResponseEntity<User> findOneUser(@PathVariable(value = "companyId", required = true) Long companyId,
                                            @PathVariable(value = "userId", required = true) Long userId) {
        User user = userService.findOneUser(companyId, userId);
        return ResponseEntity.ok(user);
    }

    public ResponseEntity changePassword(@PathVariable(value = "userId", required = true) Long userId,
                                         @RequestParam(value = "oldPassword", required = true) String oldPassword,
                                         @RequestParam(value = "newPassword", required = true) String newPassword) {
        userService.changeUserPassword(userId, oldPassword, newPassword);
        User user = userService.findOneUser(userId);
        logger.info("{} 变更密码", user.getUserName());
        return new ResponseEntity<Void>(HttpStatus.OK);
    }

    @Override
    public ResponseEntity<Void> changeUserPhone(@ApiParam(value = "用户Id", required = true) @PathVariable(value = "userId", required = true) Long userId,
                                                @ApiParam(value = "用户实体" ,required=true ) @Valid @RequestBody User body) {
        userService.changeUserPhone(userId, body.getPhone());
        User user = userService.findOneUser(userId);
        logger.info("{} 变更手机号码", user.getUserName());
        return new ResponseEntity<Void>(HttpStatus.OK);
    }


    public ResponseEntity<Page<User>> findUsers(HttpServletRequest request,
                                                @RequestParam(value = "q", required = false) String q,
                                                @RequestParam(value = "page", required = true) Integer page,
                                                @RequestParam(value = "pageSize", required = true) Integer pageSize,
                                                @PathVariable(value = "companyId", required = true) Long companyId) {
        Page<User> users = userService.findUsers(companyId);
        return ResponseEntity.ok(users);
    }

    public ResponseEntity<Void> resetPassword(@PathVariable(value = "userId", required = true) Long userId, @PathVariable Long companyId) {
        userService.resetUserPassword(userId, companyId);
        return new ResponseEntity<Void>(HttpStatus.OK);
    }

    public ResponseEntity<Void> updateUser(@PathVariable("companyId") Long companyId,
                                           @Valid @PathVariable("userId") Long userId,
                                           @Valid @RequestBody User body) {
        body.setUpdateBy(ClientUtils.getCurrentUserId());
        body.setUpdateDate(DateUtils.now());
        body.setUserId(userId);
        userService.updateUser(companyId, body);
        return new ResponseEntity<Void>(HttpStatus.OK);
    }

    @Override
    public ResponseEntity<Void> exportUsers(HttpServletRequest request, HttpServletResponse response,
                                            @PathVariable("companyId") Long companyId,
                                            @RequestParam(value = "q", required = false) String q) {
        String fileName = "用户列表";
        List<User> userList = userService.findUsersForExport(companyId);
        LinkedHashMap<String, String> headers = new LinkedHashMap<String, String>();
        headers.put("userName", "用户名称");
        headers.put("email", "电子邮件");
        headers.put("phone", "电话");
        headers.put("orgName", "科室");
        headers.put("roleName", "角色");
        try {
            ExcelUtil.processResponseHeader(request, response, "用户");
            ExcelUtil.exportExcel(headers, userList, response.getOutputStream());
        } catch (Exception e) {
            logger.error(e.getMessage(), e);
        }
        return new ResponseEntity<Void>(HttpStatus.OK);
    }

    @Override
    public ResponseEntity<Void> logOut(@ApiParam(value = "单位ID", required = true) @PathVariable(required = true) Long companyId, @ApiParam(value = "用户极光ID", required = true) @Valid @PathVariable String registrationId) {
        return new ResponseEntity<Void>(HttpStatus.OK);
    }
}
