package cn.kyt.ums.mapper;

import cn.kyt.ums.entity.User;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface UserMapper {

    public void createUser(User user);

    public void deleteUser(Long userId);

    public void updateUser(User user);

    public User findOneUser(Long userId);

    public List<User> findUsers(Long companyId);

    public int countUsers(Long companyId);

    public void linkUserWithCompany(Long companyId, Long userId);

    public User findOneUserByPhoneNumber(String phoneNumber);

    public List<User> findUsersForExport(Long companyId);

    public void changeUserPhone(Long userId, String phone);

    void changePassword(User user);

    User findOneUserByLoginName(String loginName);

    boolean findUserWithCompany(Long companyId, Long userId);

}
