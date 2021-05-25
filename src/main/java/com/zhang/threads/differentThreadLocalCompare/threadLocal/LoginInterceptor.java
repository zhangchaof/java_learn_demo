package com.zhang.threads.differentThreadLocalCompare.threadLocal;

/**
 * @ClassName LoginInterceptor
 * @Description: TODO
 * @Author clark
 * @Date 2021/4/29 17:09
 **/
public class LoginInterceptor {
    /**
     * 模拟拦截方法
     */
    public void userInterceptor() {
        UserInfo userInfo = getUserFromRedis();
        //将用户信息塞入ThreadLocal中
        ThreadLocalHolder.setUser(userInfo);
    }

    /**
     * 模拟从redis中获取信息，这里写死直接返回
     *
     * @return
     */
    public UserInfo getUserFromRedis() {
        UserInfo userInfo = new UserInfo();
        userInfo.setId(1L);
        userInfo.setUserName("zs");
        return userInfo;
    }
}

