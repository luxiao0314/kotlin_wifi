package com.mw.safetywifi.ui.home.adapter

import com.mvvm.lux.framework.manager.recycler.baserecycleview.BaseQuickAdapter
import com.mvvm.lux.framework.manager.recycler.baserecycleview.BaseViewHolder
import com.mw.safetywifi.BR
import com.mw.safetywifi.model.HomeItemViewModel
import com.mw.safetywifi.model.response.GuessListResponse

/**
 * @Description
 * *
 * @Author luxiao418
 * *
 * @Email luxiao418@pingan.com.cn
 * *
 * @Date 08/08/2017 4:32 PM
 * *
 * @Version
 */
class HomeAdapter(layoutResId: Int, data: List<GuessListResponse.ListBean>) :
        BaseQuickAdapter<GuessListResponse.ListBean, BaseViewHolder>(layoutResId, data) {

    override fun convert(helper: BaseViewHolder, item: GuessListResponse.ListBean, positions: Int) {
        var viewModel = HomeItemViewModel()
        viewModel.img.set(item.img)
        viewModel.title.set(item.title)
        viewModel.desc.set(item.desc)
        helper.mDataBinding.setVariable(BR.viewModel, viewModel)
    }
}
