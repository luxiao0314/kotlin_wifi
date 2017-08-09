package com.mvvm.lux.framework.base;

import android.support.v7.app.AppCompatActivity;
import android.view.KeyEvent;

import com.mvvm.lux.framework.config.MarkAble;
import com.mvvm.lux.framework.manager.router.Router;

/**
 * Description: BaseActivity
 * Creator: yxc
 * date: 2016/9/7 11:45
 */
public class BaseActivity extends AppCompatActivity implements MarkAble {

    @Override
    public String getInstanceTag() {
        return this.getClass().getSimpleName() + this.hashCode();
    }

    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if ((keyCode == KeyEvent.KEYCODE_BACK)) {
            Router.pop(this);
            return false;
        } else {
            return super.onKeyDown(keyCode, event);
        }
    }
}
