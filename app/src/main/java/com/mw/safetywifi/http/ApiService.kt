package com.mw.safetywifi.http

import com.mvvm.lux.framework.http.base.BaseResponse
import com.mw.safetywifi.model.response.*
import io.reactivex.Observable
import retrofit2.http.GET

/**
 * @Description
 * *
 * @Author luxiao418
 * *
 * @Email luxiao418@pingan.com.cn
 * *
 * @Date 2017/1/6 10:56
 * *
 * @Version
 */
interface ApiService {

    companion object {
        val BASE_URL = "https://safelywifitest.mlinks.co/"
    }

    @GET("v1/users/list")
    fun getUserList(): Observable<BaseResponse<UserListResponse>>

    @GET("v1/guess/list")
    fun getGuessList(): Observable<BaseResponse<GuessListResponse>>

    @GET("v1/others/list")
    fun getOtherList(): Observable<BaseResponse<OtherListResponse>>

    @GET("v1/comments/list")
    fun getCommentsList(): Observable<BaseResponse<CommentsListResponse>>

    @GET("v1/friend/list")
    fun getFriendList(): Observable<FriendsListResponse>

}
