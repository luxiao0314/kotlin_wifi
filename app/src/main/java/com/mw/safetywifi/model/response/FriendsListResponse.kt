package com.mw.safetywifi.model.response

import com.mvvm.lux.framework.http.base.BaseResponse

/**
 * @Description
 * @Author luxiao418
 * @Email luxiao418@pingan.com.cn
 * @Date 09/08/2017 5:40 PM
 * @Version
 */
class FriendsListResponse : BaseResponse<FriendsListResponse>() {

    lateinit var list:List<ListBean>

    class ListBean{
        lateinit var img: String
        lateinit var title: String
        lateinit var desc: String
        lateinit var time: String
    }


}