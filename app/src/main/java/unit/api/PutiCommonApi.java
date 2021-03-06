package unit.api;

import com.puti.education.bean.EventType;
import com.puti.education.netFrame.response.ResponseInfo;

import okhttp3.RequestBody;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Query;
import rx.Observable;
import unit.base.BaseResponseInfo;
import unit.base.PutiBaseModel;
import unit.entity.EventBase;

/**
 * Created by lei on 2018/6/4.
 */

public interface PutiCommonApi {
    @POST("Common/Login")//登录
    Observable<BaseResponseInfo> login(@Body RequestBody route);

    @GET("Common/verify")//请求验证码
    Observable<BaseResponseInfo> queryVerify(@Query("refer")int refer);

    @GET("Common/getConfig")//获取APP配置信息
    Observable<BaseResponseInfo> getAppConfig(@Query("Uid") String uid);

    @GET("Teacher/MyEventSummary")//查询首页统计信息
    Observable<BaseResponseInfo>getHomeCountInfo();

    @GET("Common/MyMsgs")//获取消息列表
    Observable<BaseResponseInfo>getMessageList(@Query("status")int status,
                                               @Query("pageIndex") int pageIndex,
                                               @Query("pageSize")int pageSize
                                               );

    @POST("Common/ChangePwd")//修改密码
    Observable<BaseResponseInfo> updatePsw(@Query("oldPwd")String oldPwd,
                                           @Query("newPwd")String newPwd,
                                           @Query("confirmNewPwd")String confirmNewPwd
                                           );

    @POST("Common/Logout")//退出登录
    Observable<BaseResponseInfo> logout();

    @GET("Common/commitSuggestion")//提交有奖反馈
    Observable<BaseResponseInfo> commitSuggestion (@Body RequestBody route);

    @GET("Common/EventType")//获取事件类型
    Observable<BaseResponseInfo> getEventType(@Query("areaUID") String areaUid);

    @GET("Common/School/Places")//获取地点
    Observable<BaseResponseInfo> getAddress(@Query("type") int type);

    @POST("Common/Events/Add") //教师新增事件
    Observable<BaseResponseInfo> addEvent(@Body RequestBody route);

    @GET("Common/Events/List")//查询事件列表
    Observable<BaseResponseInfo> queryEvent(@Query("classUID")String classUID,
//                                            @Query("studentName")String studentName,
//                                            @Query("eventTypeName")String eventTypeName,
                                            @Query("status")int status,
                                            @Query("pageIndex")int pageIndex,
                                            @Query("pageSize")int pageSize);

    @GET("Common/Portrait")//查询学生画像
    Observable<BaseResponseInfo> queryPortrait(@Query("studentUID")String studentUID,
                                               @Query("termUID")String termUID);

    @GET("Common/StudentInfo")//查询学生基本信息
    Observable<BaseResponseInfo> queryStuInfo(@Query("studentUID")String studentUID,
                                               @Query("termUID")String termUID);

    @GET("Common/AcademicLevel")//查询学生专业水平信息
    Observable<BaseResponseInfo> queryStuAcademicLevel(@Query("studentUID")String studentUID,
                                              @Query("termUID")String termUID);

    @GET("Common/MentalHealth")//查询学生身心健康信息
    Observable<BaseResponseInfo> queryStuMentalHealth(@Query("studentUID")String studentUID,
                                                   @Query("termUID")String termUID);
    @GET("Common/Activity")//查询学生实践信息
    Observable<BaseResponseInfo> queryStuActivity(@Query("studentUID")String studentUID,
                                                   @Query("termUID")String termUID);

    @GET("Common/MoralCharacter")//查询学生思想品德信息
    Observable<BaseResponseInfo> queryStuCharacter(@Query("studentUID")String studentUID,
                                                   @Query("termUID")String termUID);

    @GET("Common/Teacher/Detail")//查询教师基础信息
    Observable<BaseResponseInfo> queryTeacherInfo(@Query("teacherUID")String teacherUID);

    @GET("Common/Classes/CoursInfo")//获取谋学期的课表
    Observable<BaseResponseInfo> queryCoursInfo(@Query("classUID")String classUID,
                                                @Query("termUID")String termUID);

    @GET("Common/Events/EventDetail")//获取学生事件详情
    Observable<BaseResponseInfo> getEventDetail(@Query("eventUID")String eventUID);

    @GET("Common/Events/InvolvedDetail")//获取学生涉事详情
    Observable<BaseResponseInfo> getInvolvedDetail(@Query("event2InvolvedUID")String event2InvolvedUID);

    @GET("Common/TeacherBook")//学校教师通讯录
    Observable<BaseResponseInfo> getTeacherBook(@Query("pageIndex")int pageIndex,
                                                @Query("pageSize") int pageSize,
                                                @Query("teacherName")String teacherName);


    @GET("Common/ParentBook")//学校教师通讯录
    Observable<BaseResponseInfo> getParentBook( @Query("pageIndex")int pageIndex,
                                                @Query("pageSize") int pageSize,
                                                @Query("studentName")String studentName,
                                                @Query("classUID")String classUID);

    @POST("Common/Msg/Checked ")//清空个人信息
    Observable<BaseResponseInfo> clearMsg(@Body RequestBody route);
}
