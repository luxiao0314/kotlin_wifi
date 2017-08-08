package com.mw.safetywifi.model

import android.support.v7.widget.GridLayoutManager
import android.view.View
import com.mvvm.lux.framework.base.BaseViewModel
import com.mvvm.lux.framework.config.MarkAble
import com.mvvm.lux.framework.http.ProgressSubscriber
import com.mvvm.lux.framework.http.RxHelper
import com.mvvm.lux.framework.manager.dialogs.config.ServiceTask
import com.mw.safetywifi.R
import com.mw.safetywifi.databinding.HomeFragmentBinding
import com.mw.safetywifi.http.RetrofitHelper
import com.mw.safetywifi.model.response.GuessListResponse
import com.mw.safetywifi.ui.home.adapter.HomeAdapter
import io.reactivex.annotations.NonNull

/**
 * @Description
 * @Author luxiao418
 * @Email luxiao418@pingan.com.cn
 * @Date 08/08/2017 3:21 PM
 * @Version
 */
class HomeViewModel : BaseViewModel() {

    lateinit var layoutManager : GridLayoutManager
    lateinit var footerView: View
    lateinit var headerView: View

    fun initData(mBinding: HomeFragmentBinding) {
        RetrofitHelper.init()
                .getGuessList()
                .compose(RxHelper.handleErrTransformer())
                .subscribe(object : ProgressSubscriber<GuessListResponse>(ServiceTask.create(mBinding.root.context as MarkAble)) {
                    override fun onNext(@NonNull guessListResponse: GuessListResponse) {
                        val list = guessListResponse.list
                        val homeAdapter = HomeAdapter(R.layout.home_adapter, list)
                        homeAdapter.addHeaderView(headerView)
                        homeAdapter.addFooterView(footerView)
                        mBinding.rvHome.adapter = homeAdapter
                    }
                })
    }


}