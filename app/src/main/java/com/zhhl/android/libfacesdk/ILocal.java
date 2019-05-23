package com.zhhl.android.libfacesdk;

import com.zhhl.android.libfacesdk.sign.Signature;
import com.zhhl.android.libfacesdk.sign.UploadData;
import com.zhhl.android.libfacesdk.sign.UserInfo;

import io.reactivex.Observable;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.POST;

/**
 * Created by miao on 2018/11/21.
 */
public interface ILocal {

    //    @POST("/Faceplus/GetSignature")
////    Observable<Signature> getSign();

//
   @POST("/Faceplus/GetSignNoOcr")
    Observable<Signature> getSign();

//    @POST("/Faceplus/CheckUser")
//    @FormUrlEncoded
//    Observable<User> selectUserInfo(@Field("idCard") String idCard);


//    @POST("/Faceplus/GetDetectInfo")
//    @FormUrlEncoded
//    Observable<UserInfo> getUserInfoByToken(@Field("token") String token);


    ///Faceplus
    @POST("/Faceplus/checkClient")
    @FormUrlEncoded
    Observable<UserInfo> cherkAppKey(@Field("appKey") String appKey, @Field("idCard") String idCard, @Field("name") String name, @Field("ver") int version);

///Faceplus
    @POST("/Faceplus/sendResult")
    @FormUrlEncoded
    Observable<UploadData> upload(@Field("appKey") String appkey, @Field("token") String token, @Field("bool") boolean bool);
}
