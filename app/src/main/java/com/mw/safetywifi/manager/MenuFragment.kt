package cn.magicwindow.toutiao.menu

import android.support.v4.app.Fragment
import android.support.v4.app.FragmentManager
import com.mw.safetywifi.R
import com.mw.safetywifi.ui.home.fragment.FindFragment
import com.mw.safetywifi.ui.home.fragment.MessageFragment
import com.mw.safetywifi.ui.home.fragment.MineFragment

/**
 * Created by Tony Shen on 2017/7/5.
 */
class MenuManager private constructor(private val fragmentManager: FragmentManager) {
    var curType: MenuType? = null
        private set

    enum class MenuType constructor(val title: String, val isRemoved: Boolean) {
        HOME("首页", false),
        FIND("发现", false),
        MSG("消息", false),
        MINE("我的", false)
    }

    init {
        curType = MenuType.HOME
    }

    fun show(type: MenuType): Boolean {
        if (curType == type) {
            return true
        } else {
            hide(curType)
        }

        var fragment: Fragment? = fragmentManager.findFragmentByTag(type.title)
        if (fragment == null) {
            fragment = create(type)
            if (fragment == null) {
                return false
            }
        }

        fragmentManager.beginTransaction().show(fragment).commitAllowingStateLoss()
        curType = type
        return true
    }

    private fun create(type: MenuType): Fragment? {
        var fragment: Fragment? = null

        when (type) {
            MenuManager.MenuType.MINE -> fragment = MineFragment()

            MenuManager.MenuType.FIND -> fragment = FindFragment()

            MenuManager.MenuType.MSG -> fragment = MessageFragment()

        }
        fragmentManager.beginTransaction().add(R.id.content, fragment, type.title).commitAllowingStateLoss()
        return fragment
    }

    private fun hide(type: MenuType?) {
        val fragment = fragmentManager.findFragmentByTag(type?.title)
        if (fragment != null) {
            if (type!!.isRemoved) {
                fragmentManager.beginTransaction().remove(fragment).commitAllowingStateLoss()
            } else {
                val ft = fragmentManager.beginTransaction()
                // ft.addToBackStack(type.getTitle());
                ft.hide(fragment)
                ft.commitAllowingStateLoss()
            }
        }
    }

    /**
     * 判断某个fragment是否存在
     * @param type
     *
     * @return
     */
    fun isFragmentExist(type: MenuType): Boolean {

        return fragmentManager.findFragmentByTag(type.title)!=null
    }

    /**
     * 返回菜单的总数
     *
     * @return
     */
    val menuCount: Int
        get() {

            return MenuType.values().size
        }

    companion object {

        private var instance: MenuManager? = null

        @JvmStatic
        fun getInstance(fragmentManager: FragmentManager): MenuManager {
            if (instance == null) {
                instance = MenuManager(fragmentManager)
            }

            return instance as MenuManager
        }
    }
}