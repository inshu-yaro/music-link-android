package com.junkers.musiclink.app;

import android.app.ProgressDialog;
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

    @Inject private UserManager mUserManager;
    private ProgressDialog mDialog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        mDialog = new ProgressDialog(this, ProgressDialog.THEME_HOLO_DARK);
        mDialog.setMessage("Loading...");
        mDialog.setCancelable(false);
    }

    public void handleLoginButtonClick(View v) {
        mDialog.show();
        mUserManager.login(this, new Callback<User>() {
            @Override
            public void onSuccess(User user) {
                mDialog.dismiss();
                setResult(RESULT_OK);
                finish();
            }

            @Override
            public void onFailure() {
                mDialog.hide();
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
