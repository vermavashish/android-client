/*
 * This project is licensed under the open source MPL V2.
 * See https://github.com/openMF/android-client/blob/master/LICENSE.md
 */

package com.mifos.mifosxdroid;

import android.content.Intent;
import android.os.Bundle;

import com.mifos.api.BaseUrl;
import com.mifos.mifosxdroid.core.MifosBaseActivity;
import com.mifos.mifosxdroid.login.LoginActivity;
import com.mifos.mifosxdroid.passcode.PassCodeActivity;
import com.mifos.mobile.passcode.utils.PassCodeConstants;
import com.mifos.utils.PrefManager;

import java.util.concurrent.TimeUnit;


/**
 * This is the First Activity which can be used for initial checks, inits at app Startup
 */
public class SplashScreenActivity extends MifosBaseActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);
        try {
            TimeUnit.MILLISECONDS.sleep(100);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        if (!PrefManager.isAuthenticated()) {
            PrefManager.setInstanceUrl(BaseUrl.PROTOCOL_HTTPS
                    + BaseUrl.API_ENDPOINT + BaseUrl.API_PATH);
            startActivity(new Intent(SplashScreenActivity.this, LoginActivity.class));
        } else {
            Intent intent = new Intent(SplashScreenActivity.this,
                    PassCodeActivity.class);
            intent.putExtra(PassCodeConstants.PASSCODE_INITIAL_LOGIN, true);
            startActivity(intent);
        }
        finish();
    }
}
