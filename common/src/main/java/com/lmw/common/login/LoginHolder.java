package com.lmw.common.login;

public class LoginHolder {
    public static ThreadLocal<LoginUser> loginUser = new ThreadLocal<>();

    public static void setLoginUser(LoginUser user) {
        loginUser.set(user);
    }

    public static LoginUser getLoginUser() {
        return loginUser.get();
    }

    public static void remove() {
        loginUser.remove();
    }
}
