package com.mw.safetywifi.model

import android.support.v7.widget.LinearLayoutManager
import com.mvvm.lux.framework.base.BaseViewModel
import com.mvvm.lux.framework.config.MarkAble
import com.mvvm.lux.framework.http.ProgressSubscriber
import com.mvvm.lux.framework.http.RxHelper
import com.mvvm.lux.framework.manager.dialogs.config.ServiceTask
import com.mw.safetywifi.R
import com.mw.safetywifi.databinding.FriendFragmentBinding
import com.mw.safetywifi.http.RetrofitHelper
import com.mw.safetywifi.model.response.FriendsListResponse
import com.mw.safetywifi.ui.home.adapter.FriendAdapter

/**
 * @Description
 * @Author luxiao418
 * @Email luxiao418@pingan.com.cn
 * @Date 09/08/2017 5:29 PM
 * @Version
 */
class FriendViewModel:BaseViewModel() {
    lateinit var layoutManager : LinearLayoutManager

    fun initData(mBinding: FriendFragmentBinding) {
        RetrofitHelper.init()
                .getFriendList()
                .compose(RxHelper.handleVirtualData(FriendsListResponse::class.java))
                .subscribe(object : ProgressSubscriber<FriendsListResponse>(ServiceTask.create(mBinding.root.context as MarkAble)) {
                    override fun onNext(t: FriendsListResponse) {
                        val list = t.list
                        val friendAdapter = FriendAdapter(R.layout.friend_adapter, list)
                        mBinding.rvFriend.adapter = friendAdapter
                    }

                })
    }
}