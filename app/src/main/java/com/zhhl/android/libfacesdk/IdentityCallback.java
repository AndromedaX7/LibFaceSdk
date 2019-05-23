package com.zhhl.android.libfacesdk;


/**
 * 验证回调接口
 */
public interface IdentityCallback {
    /**
     * @param success if  success  return  true
     * @param token   token ,可通过此token 获取用户信息
     */
    void result(boolean success, String token);

}
