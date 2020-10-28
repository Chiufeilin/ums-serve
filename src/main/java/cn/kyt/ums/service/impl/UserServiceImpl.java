package cn.kyt.ums.service.impl;

import cn.kyt.ums.entity.User;
import cn.kyt.ums.exception.ServiceException;
import cn.kyt.ums.mapper.UserMapper;
import cn.kyt.ums.service.UserService;
import cn.kyt.ums.utils.*;
import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DuplicateKeyException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class UserServiceImpl implements UserService {
    private final Logger logger = LoggerFactory.getLogger(UserService.class);
    @Autowired
    private UserMapper userMapper;
    @Autowired
    UserService userService;

    @Transactional
    public void createUser(Long companyId, User user) {
        User u = userMapper.findOneUserByLoginName(user.getPhone());
        if (u != null){
            boolean isExists = userMapper.findUserWithCompany(companyId, u.getUserId());
            if (isExists){
                throw new ServiceException("手机号" + user.getPhone() + "已经存在");
            }
            userMapper.linkUserWithCompany(companyId, u.getUserId());
            logger.info("手机号已存在，开放当前医院权限,医院ID:{0}", companyId);
            return;
        }
        user.userId(IDUtils.getId()).createDate(DateUtils.now()).updateDate(DateUtils.now());
        user.setLoginName(user.getPhone());
        user.setPassword(MD5Utils.string2MD5("123456"));
        user.setCompanyId(companyId);
        try {
            userMapper.createUser(user);

        } catch (DuplicateKeyException e) {
            throw new ServiceException("手机号" + user.getPhone() + "已经存在");
        }
        userMapper.linkUserWithCompany(companyId, user.getUserId());
    }


    @Transactional
    public void deleteUser(Long companyId, Long userId) {
        userMapper.deleteUser(userId);
    }

    @Override
    public User findOneUser(Long companyId, Long userId) {
        User user = userMapper.findOneUser(userId);
        return user;
    }

    public User findOneUser(Long userId) {
        User user = userMapper.findOneUser(userId);
        return user;
    }

    @Transactional
    public void updateUser(Long companyId, User user) {
        User oldUser = userMapper.findOneUser(user.getUserId());

        if (StringUtils.isNotEmpty(user.getPhone())){
            if (!StringUtils.equals(user.getPhone(),oldUser.getPhone())){
                User existed = userMapper.findOneUserByPhoneNumber(user.getPhone());
                if (existed!=null && existed.getUserId().longValue()!=user.getUserId().longValue()){
                    throw new ServiceException("手机号码已被其他用户使用!");
                }
                userMapper.changeUserPhone(user.getUserId(),user.getPhone());
            }
        }

        BeanCopyUtils.copyWithoutNullProperties(user, oldUser);
        oldUser.setLoginName(user.getPhone());
        oldUser.setPhone(user.getPhone());
        oldUser.setUpdateDate(DateUtils.now());

        userMapper.updateUser(oldUser);
    }

    public Page<User> findUsers(Long companyId) {
        List<User> users = userMapper.findUsers(companyId);
        int count = userMapper.countUsers(companyId);
        Page<User> page = new Page<User>();
        page.setContent(users);
        page.setTotalItems(count);
        return page;
    }

    @Override
    public void changeUserPassword(Long userId, String oldPassword, String newPassword) {
        if(userId != null){
            User user = userMapper.findOneUser(userId);
            if(!user.getPassword().equals(oldPassword)){
                throw new ServiceException("密码不正确");
            }
            user.setPassword(newPassword);
            userMapper.changePassword(user);
        }
    }

    @Override
    public List<User> findUsersForExport(Long companyId) {
        return userMapper.findUsersForExport(companyId);
    }

    @Override
    public void changeUserPhone(Long userId, String phone) {
        User existed = userMapper.findOneUserByPhoneNumber(phone);
        if (existed!=null && existed.getUserId().longValue()!=userId.longValue()){
            throw new ServiceException("手机号码已被其他用户使用!");
        }
        userMapper.changeUserPhone(userId,phone);
    }

    @Override
    public void resetUserPassword(Long userId, Long companyId) {
        if(userId != null){
            User user = userMapper.findOneUser(userId);
            user.setPassword(MD5Utils.string2MD5("123456"));
            userMapper.changePassword(user);
        } else {
            throw new ServiceException("用户不能为空");
        }
    }

}
