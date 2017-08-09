package com.mw.safetywifi.ui.message.fragment

import android.databinding.DataBindingUtil
import android.support.v4.app.Fragment
import android.view.LayoutInflater
import android.view.ViewGroup
import com.mvvm.lux.framework.base.BindingFragment
import com.mw.safetywifi.R
import com.mw.safetywifi.databinding.MessageFragmentBinding
import com.mw.safetywifi.model.MessageViewModel
import com.mw.safetywifi.ui.find.fragment.FriendFragment
import com.mw.safetywifi.ui.find.fragment.StrangerFragment
import com.mw.safetywifi.ui.message.adapter.MessagePageAdapter

/**
 * @Description
 * @Author luxiao418
 * @Email luxiao418@pingan.com.cn
 * @Date 08/08/2017 3:27 PM
 * @Version
 */
class MessageFragment : BindingFragment<MessageFragmentBinding>() {
    override fun createDataBinding(inflater: LayoutInflater?, container: ViewGroup?): MessageFragmentBinding{
        return DataBindingUtil.inflate(inflater, R.layout.message_fragment,container,false)
    }

    override fun initView() {
        val list = ArrayList<Fragment>()
        list.add(FriendFragment())
        list.add(StrangerFragment())
        mBinding.viewpagerMsg.adapter = MessagePageAdapter(fragmentManager, list)
        mBinding.tablayoutMsg.setViewPager(mBinding.viewpagerMsg)
        val viewModel = MessageViewModel()
        mBinding.viewModel = viewModel
    }
}