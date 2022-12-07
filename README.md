# logger


## 1.注解使用
* 1，在logger需要初始化的地方
```xml
@InitJointPoint(mFilePath = "",mFileName = "logger_cache",isDebug = false)
mFilePath：日志缓存的相对路径
mFileName：日志缓存文件名
isDebug：是否开启logger调试
```
* 2，在logger需要释放的地方
```xml
@ReleaseJointPoint
```
* 3，在log日志收集的地方埋点
```xml
@LogJointPoint(type = "MSG",open = true)
type：日志类型
open：是否开启收集
```
* 4，在log文件上传的地方
```xml
@UploadJointPoint
举例：
public <T> void uploadLogger(T value,String url,String token,String userId);
初始化两个参数：
value：true(上传使能)  false(禁止上传) 
url：上传服务器地址
token：从服务器上拿到的
userId：
参数变动：
value：从T--->File(转变成需要上传的文件)
url：不变
token：不变
userId：不变
```
* 5,举例 （智能云）
```xml
1> 在主页的onCreate方法上初始化日志
@InitJointPoint(mFilePath = "/logger",mFileName = "EC_logger_cache",isDebug = true)
@Override
public void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
}
2> 在主页的onDestory方法上释放日志
@ReleaseJointPoint
@Override
public void onDestroy() {
}
3> 项目所有的日志最终会走到这个方法
@LogJointPoint(type = "MSG",open = true)
public void CallBack_WriteLogText(String logMsg) {
    
}
4> 在获取到后台地址后确定上传日志
@UploadJointPoint
public <T> void uploadLogger(T value,String url,String token,String userId){
  if(value==null) return;
}
```

## 2.项目引用
* 1，root build.gradle中
```groovy
classpath 'com.hujiang.aspectjx:gradle-android-plugin-aspectjx:2.0.8'
```
* 2，module build.gradle中
```groovy
apply plugin: 'android-aspectjx'
implementation 'com.github.zhuyidian.lib_Instrument:logger:V1.1.8'
```

## 3.项目说明
* 1，目前logger不支持Android10
* 2，日志收集
* 3，日志文件压缩
* 4，日志压缩文件上传
* 5，logger采用AOP切面编程思想

## 4.问题分析
* 1，Permission denied
![Image text](https://github.com/zhuyidian/lib_logger/blob/main/imgfiles/PermissionDenied.png)
```
问题分析：
（Target不一致导致）如果logger是支持Android10,但是外部项目不支持Android10，那么会出现这个错误。但是不会影响功能使用，只是提示错误而已。
解决办法：
logger和主项目都不支持Android10，或者都支持Android10即可。
```
