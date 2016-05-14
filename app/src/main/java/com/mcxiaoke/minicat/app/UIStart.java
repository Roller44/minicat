package com.mcxiaoke.minicat.app;

import android.app.Activity;
import android.os.Bundle;
import com.mcxiaoke.minicat.AppContext;
import com.mcxiaoke.minicat.controller.UIController;

/**
 * @author mcxiaoke
 * @version 1.5 2012.02.27
 */
public class UIStart extends Activity {

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setVisible(false);//此 Activity 被设为不可见
        checkLogin();

    }

    @Override
    protected void onResume() {
        super.onResume();
    }

    private void checkLogin() {
        //检查是否已经登陆过，如果有，则打开 TL，否则进入登陆界面
        if (AppContext.isVerified()) {
            UIController.showHome(this);
        } else {
            UIController.showLogin(this);
        }
        finish();
    }

}
