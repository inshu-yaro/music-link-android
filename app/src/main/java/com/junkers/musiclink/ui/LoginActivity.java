package com.junkers.musiclink.ui;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import com.facebook.Session;
import com.google.inject.Inject;
import com.junkers.musiclink.R;
import com.junkers.musiclink.common.Callback;
import com.junkers.musiclink.managers.UserManager;
import com.junkers.musiclink.models.User;

import roboguice.activity.RoboActivity;

public class LoginActivity extends RoboActivity {
    public static final int REQUEST_CODE = 1;

    @Inject private UserManager userManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
    }

    public void handleLoginButtonClick(View v) {
        userManager.login(this, new Callback<User>() {
            @Override
            public void onSuccess(User user) {
                setResult(RESULT_OK);
                finish();
            }

            @Override
            public void onFailure() {
                Toast.makeText(LoginActivity.this, R.string.login_failed, Toast.LENGTH_SHORT).show();
            }
        });
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        Session.getActiveSession().onActivityResult(this, requestCode, resultCode, data);
    }
}
