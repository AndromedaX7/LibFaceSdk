# Add project specific ProGuard rules here.
# You can control the set of applied configuration files using the
# proguardFiles setting in build.gradle.
#
# For more details, see
#   http://developer.android.com/guide/developing/tools/proguard.html

# If your project uses WebView with JS, uncomment the following
# and specify the fully qualified class name to the JavaScript interface
# class:
#-keepclassmembers class fqcn.of.javascript.interface.for.webview {
#   public *;
#}

# Uncomment this to preserve the line number information for
# debugging stack traces.
#-keepattributes SourceFile,LineNumberTable

# If you keep the line number information, uncomment this to
# hide the original source file name.
#-renamesourcefileattribute SourceFile


-optimizationpasses 5
-dontusemixedcaseclassnames
-dontskipnonpubliclibraryclassmembers
-dontskipnonpubliclibraryclasses
-dontpreverify
-verbose
-optimizations !code/simplification/arithmetic,!field/*,!class/merging/*
-dontwarn android.support.**
-dontwarn android.support.**
#-ignorewarnings

-keep class com.zhhl.android.libfacesdk.sign.** {*;}
#-keepclassmembers class com.zhhl.android.libfacesdk.FaceSdk {
# private  <init>(java.lang.String);
# }
-keep class com.zhhl.android.libfacesdk.AppKeyAuthErrorCallback{*;}
-keep class com.zhhl.android.libfacesdk.IdentityCallback {*;}
#-keep class com.zhhl.android.libfacesdk.ILocal{*;}
#-keep class com.zhhl.android.libfacesdk.HttpTools{*;}
-keep class com.zhhl.android.libfacesdk.c{*;}
-keep class com.zhhl.android.libfacesdk.FaceSdk{
public *;
}
-keepclassmembers class com.zhhl.android.libfacesdk.FaceSdk {
#    private static final java.lang.String IDENTITY_SDK_URL;
#    private static final java.lang.String APP_ID;
#    public <init>(java.lang.String);
#    public static com.zhhl.android.libfacesdk.FaceSdk init(java.lang.String);
#    public void registerCallback(java.lang.ref.WeakReference,com.zhhl.android.libfacesdk.IdentityCallback);
#    public void authUser(java.lang.String,java.lang.String) ;
public *;
 }

# For BatteryStats -dontwarn android.os.** -dontwarn android.app.**
-keep class android.support.** { *; }
-keep class android.support.** { *; }
-keep class android.** {    <fields>;    <methods>; }
-keep class com.android.** {    <fields>;    <methods>; }
-keep public class * extends android.app.Activity
-keep public class * extends android.app.Application
-keep public class * extends android.content.BroadcastReceiver
-keepattributes *Annotation*
-keepattributes InnerClasses
-keepattributes Signature
-keepclasseswithmembernames class * {    native <methods>; }
-keepclasseswithmembers class * {    public <init>(android.content.Context, android.util.AttributeSet); }
-keepclasseswithmembers class * {   public <init>(android.content.Context, android.util.AttributeSet, int); }
-keepclassmembers class * extends android.app.Activity {   public void *(android.view.View); }
-keepclassmembers enum * {    public static **[] values();    public static ** valueOf(java.lang.String); }
-assumenosideeffects class android.util.Log {    public static *** e(...);    public static *** w(...);    public static *** i(...);    public static *** d(...);    public static *** v(...); }
 #不混淆资源类
 -keep class **.R$* { *; }
# AuthSdk
 -keep class com.tencent.authsdk.AuthSDKApi { *; }
 -keep class com.tencent.authsdk.callback.IdentityCallback { *; }
 -keep class com.tencent.youtufacetrack.** { *; }
 -keep class com.tencent.authsdk.IDCardInfo {*;}
 -keep class com.tencent.authsdk.IDCardInfo$Builder { *; }