package cn.kyt.ums.service;

import cn.kyt.ums.entity.User;
import cn.kyt.ums.utils.Page;

import java.util.List;

public interface UserService {

    void createUser(Long companyId, User user);

    void deleteUser(Long companyId, Long userId);

    User findOneUser(Long companyId, Long userId);

    User findOneUser(Long userId);

    void updateUser(Long companyId, User user);

    Page<User> findUsers(Long companyId);

    void changeUserPassword(Long userId, String oldPassword, String newPassword);

    List<User> findUsersForExport(Long companyId);

    void changeUserPhone(Long userId, String phone);

    void resetUserPassword(Long userId, Long companyId);
}
