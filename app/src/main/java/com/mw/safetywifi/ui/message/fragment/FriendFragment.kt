package com.mw.safetywifi.ui.find.fragment

import android.databinding.DataBindingUtil
import android.support.v7.widget.LinearLayoutManager
import android.view.LayoutInflater
import android.view.ViewGroup
import com.mvvm.lux.framework.base.BindingFragment
import com.mvvm.lux.framework.manager.recycler.itemDecoration.DividerItemDecoration
import com.mvvm.lux.widget.utils.DisplayUtil
import com.mw.safetywifi.R
import com.mw.safetywifi.databinding.FriendFragmentBinding
import com.mw.safetywifi.model.FriendViewModel

/**
 * @Description
 * @Author luxiao418
 * @Email luxiao418@pingan.com.cn
 * @Date 08/08/2017 3:27 PM
 * @Version
 */
class FriendFragment : BindingFragment<FriendFragmentBinding>() {
    override fun createDataBinding(inflater: LayoutInflater?, container: ViewGroup?): FriendFragmentBinding{
        return DataBindingUtil.inflate(inflater, R.layout.friend_fragment,container,false)
    }

    override fun initView() {
        mBinding.rvFriend.addItemDecoration(DividerItemDecoration(mBinding.root.context, LinearLayoutManager.HORIZONTAL,
                DisplayUtil.dp2px(activity, 0.5f), resources.getColor(R.color.line_color)))
        val viewModel = FriendViewModel()
        viewModel.layoutManager = LinearLayoutManager(activity)
        viewModel.initData(mBinding)
        mBinding.viewModel = viewModel
    }
}