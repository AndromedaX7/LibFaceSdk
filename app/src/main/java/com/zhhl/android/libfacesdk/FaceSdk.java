package com.zhhl.android.libfacesdk;

import android.app.Activity;
import android.content.Intent;
import android.os.Build;
import android.util.Log;

import com.tencent.authsdk.AuthConfig;
import com.tencent.authsdk.AuthSDKApi;
import com.zhhl.android.libfacesdk.sign.Signature;
import com.zhhl.android.libfacesdk.sign.UploadData;
import com.zhhl.android.libfacesdk.sign.UserInfo;

import java.lang.ref.WeakReference;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;

/**
 * Created by miao on 2018/11/21.
 */
public class FaceSdk {
    private IdentityCallback identityCallback;

    private Signature signature;
    private AuthConfig.Builder authConfigBuilder;

    private static FaceSdk sdk;
    private ILocal iLocal;
    private String appkey;
    private WeakReference<Activity> registerActivity;
    private AppKeyAuthErrorCallback mCallback;
    private Callback callback = new Callback();

    private static final String IDENTITY_SDK_URL_SANDBOX = "https://iauth-sandbox.wecity.qq.com/new/cgi-bin/";
    private static final String IDENTITY_SDK_URL = "https://iauth.wecity.qq.com/new/cgi-bin/";
    private static final String APP_ID = "4920";


    private static String getUrl() {
        if (BuildConfig.isSandBox) {
            return IDENTITY_SDK_URL_SANDBOX;
        } else {
            return IDENTITY_SDK_URL;
        }
    }

    private FaceSdk(String appKey, AppKeyAuthErrorCallback callback) {
        this.mCallback = callback;
        this.iLocal = HttpTools.build(ILocal.class);
        this.appkey = appKey;

        System.out.println(Build.CPU_ABI);
    }

    /**
     * I 初始化face sdk
     *
     * @param AppKey appkey
     * @return face sdk
     */
    public static FaceSdk init(String AppKey, AppKeyAuthErrorCallback callback) {
        if (sdk == null) sdk = new FaceSdk(AppKey, callback);
        sdk.getSign();
        return sdk;
    }

    /**
     * II 注册回调参数
     *
     * @param activity 流程执行完 退回到 {@link WeakReference#get()}
     * @param callback 流程执行完数据回传到此接口
     */
    public void registerCallback(WeakReference<Activity> activity, IdentityCallback callback) {
        identityCallback = callback;
        authConfigBuilder = new AuthConfig.Builder(getUrl(), APP_ID, activity.get().getPackageName());
        registerActivity = activity;
    }

    /**
     * III 校验用户
     *
     * @param name   用户姓名
     * @param idCode 用户身份证号
     */
    public void authUser(String name, String idCode) {
        getSign();
        selectUserInfo(name, idCode);
    }

    private void getSign() {
        iLocal.getSign()
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeOn(Schedulers.io())
                .subscribe(this::signResp, this::err, this::complete)
                .isDisposed();
    }

    private void complete() {

    }

    private void err(Throwable throwable) {
        throwable.printStackTrace();
    }

    private void signResp(Signature signature) {
        this.signature = signature;
    }

    private void selectUserInfo(String name, String idCard) {
        iLocal.cherkAppKey(appkey, idCard, name, BuildConfig.lib_version)
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeOn(Schedulers.io())
                .subscribe((obj) -> userResp(obj, name, idCard), this::err, this::complete)
                .isDisposed();
    }

    private void userResp(UserInfo user, String name, String idCard) {
        if (user.getSucc() == 0) {
            if (user.getType().equals("1")) {
                authConfigBuilder.signature(signature.getSign()).idcard(idCard).name(name).sceneID("");
//            authConfigBuilder.signature(signature.getSign()).name("").idcard("").sceneID(SENCE_ID);
            } else {
                authConfigBuilder
                        .signature(signature.getSign())
                        .idcard(user.getData().getId())
                        .name(user.getData().getName())
                        .pickey(user.getData().getToken())
                        .sceneID("");
            }
            AuthSDKApi.startMainPage(registerActivity.get(), authConfigBuilder.build(), callback);
            getSign();
//        } else if(user.getSucc()==2){
//            mCallback.callback("Face-sdk-lib版本异常:"+user.getMsg());
        } else {
            mCallback.callback(user.getMsg());
        }
    }


    private class Callback implements com.tencent.authsdk.callback.IdentityCallback {
        @Override
        public void onIdentityResult(Intent data) {
            String token = data.getStringExtra(AuthSDKApi.EXTRA_TOKEN);
            boolean identityStatus = data.getBooleanExtra(AuthSDKApi.EXTRA_IDENTITY_STATUS, false);
            identityCallback.result(identityStatus, token);
            iLocal.upload(appkey, token, identityStatus)
                    .observeOn(AndroidSchedulers.mainThread())
                    .subscribeOn(Schedulers.io())
                    .subscribe(this::sendResp, FaceSdk.this::err, FaceSdk.this::complete)
                    .isDisposed();
        }

        private void sendResp(UploadData uploadData) {
            Log.e("send", "sendResp: " + uploadData.getMsg());
        }
    }


//    /**
//     * IV 流程结束 通过token 获取用户信息
//     *
//     * @param token token
//     * @return 用户信息
//     */
//    public Observable<UserInfo> getUserInfo(String token) {
//        if (openPermission != 0) {
//            Log.e("error", "请申请有效的appkey");
//            return null;
//        }
//        return iLocal.getUserInfoByToken(token);
//    }
}
