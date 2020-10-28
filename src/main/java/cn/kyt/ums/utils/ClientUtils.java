package cn.kyt.ums.utils;

import java.util.Map;

public class ClientUtils {

    static  ThreadLocal<Long> userIds = new ThreadLocal<>();
    private static Map<String, Object> currentUserInfo;

    public static void setCurrentUserId(Long userId){
        userIds.set(userId);
    }

    public static final Long getCurrentUserId(){
        return userIds.get();
    }

    public static void setCurrentUserInfo(Map<String, Object> currentUserInfo) {
        ClientUtils.currentUserInfo = currentUserInfo;
    }

    public static Map<String, Object> getCurrentUserInfo() {
        return currentUserInfo;
    }

}
