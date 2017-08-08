package com.mw.safetywifi.model

import android.support.v7.widget.LinearLayoutManager
import com.mvvm.lux.framework.base.BaseViewModel
import com.mvvm.lux.framework.config.MarkAble
import com.mvvm.lux.framework.http.ProgressSubscriber
import com.mvvm.lux.framework.http.RxHelper
import com.mvvm.lux.framework.manager.dialogs.config.ServiceTask
import com.mw.safetywifi.R
import com.mw.safetywifi.databinding.ShopFragmentBinding
import com.mw.safetywifi.http.RetrofitHelper
import com.mw.safetywifi.model.response.OtherListResponse
import com.mw.safetywifi.ui.home.adapter.ShopAdapter
import io.reactivex.annotations.NonNull

/**
 * @Description
 * @Author luxiao418
 * @Email luxiao418@pingan.com.cn
 * @Date 08/08/2017 7:10 PM
 * @Version
 */
class ShopViewModel:BaseViewModel() {

    lateinit var layoutManager : LinearLayoutManager

    fun initData(mBinding: ShopFragmentBinding) {
        RetrofitHelper.init()
                .getOtherList()
                .compose(RxHelper.handleErrTransformer())
                .subscribe(object : ProgressSubscriber<OtherListResponse>(ServiceTask.create(mBinding.root.context as MarkAble)) {
                    override fun onNext(@NonNull otherListResponse: OtherListResponse) {
                        val list = otherListResponse.list
                        val homeAdapter = ShopAdapter(R.layout.shop_adapter, list)
                        mBinding.rvFind.adapter = homeAdapter
                    }
                })
    }
}