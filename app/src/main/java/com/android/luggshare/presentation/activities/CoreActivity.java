package com.android.luggshare.presentation.activities;

import android.app.FragmentManager;
import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.view.GravityCompat;

import com.android.luggshare.R;
import com.android.luggshare.presentation.screens.login.activities.LoginActivity;
import com.android.luggshare.presentation.screens.signup.activities.SignUpActivity;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

import butterknife.ButterKnife;
import butterknife.Unbinder;

public abstract class CoreActivity extends AppCompatActivity {

    //region /* region====================================== Interface ========================================= */
    //endregion /* region====================================== Interface ====================================== */

    //region /* ================================== Constant Variable =========================================== */
    //endregion  /* ================================== Constant Variable ======================================= */

    //region /* =================================== Class Variable ============================================= */
    protected abstract int getLayoutResourceId();

    private Unbinder unbinder;

    // Firebase instance variables
    private FirebaseAuth mFirebaseAuth;
    private FirebaseUser mFirebaseUser;
    String mUsername, mPhotoUrl;
    //endregion /* =================================== Class Variable =========================================== */

    //region /* ================================ Getter - Setter Method ======================================== */
    //endregion /* ================================ Getter - Setter Method ===================================== */

    //region /* ================================== Life Cycle Method =========================================== */
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(getLayoutResourceId());

        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);

        unbinder = ButterKnife.bind(this);

        // Initialize Firebase Auth
        /*mFirebaseAuth = FirebaseAuth.getInstance();
        mFirebaseUser = mFirebaseAuth.getCurrentUser();
        if (mFirebaseUser == null) {
            // Not signed in, launch the Sign In activity
            startActivity(new Intent(this, LoginActivity.class));
            finish();
            return;
        } else {
            mUsername = mFirebaseUser.getDisplayName();
            if (mFirebaseUser.getPhotoUrl() != null) {
                mPhotoUrl = mFirebaseUser.getPhotoUrl().toString();
            }
        }*/
    }

    @Override
    protected void onResume() {
        super.onResume();

    }

    @Override
    protected void onPause() {
        super.onPause();

    }

    @Override
    protected void onDestroy() {
        super.onDestroy();

        unbinder.unbind();
    }
    //endregion /* ================================== Life Cycle Method ======================================== */

    //region /* ============================= Implemented Interface Method ===================================== */
    //endregion /* ============================= Implemented Interface Method ================================== */

    //region /* ============================= Implemented Abstract Method ====================================== */
    //endregion /* ============================= Implemented Abstract Method =================================== */

    //region /* ==================================== OnClick Methods =========================================== */
    //endregion /* ==================================== OnClick Methods ======================================== */

    //region /* =================================== User Define Methods ======================================== */
    public void navigateToIntent(Class<? extends CoreActivity> activity) {
        navigateToIntent(activity, null, false);
    }

    public void navigateToIntent(Class<? extends CoreActivity> activity, Bundle bundle) {
        navigateToIntent(activity, bundle, false);
    }

    public void navigateToIntent(Class<? extends CoreActivity> activity, boolean clearPrevious) {
        navigateToIntent(activity, null, clearPrevious);
    }

    protected void navigateToIntent(Class<? extends CoreActivity> activity, Bundle bundle, boolean clearPrevious) {
        //navigate to apply campaign fragment
        Intent intent = new Intent(CoreActivity.this, activity);
        if (bundle != null)
            intent.putExtras(bundle);

        if (clearPrevious)
            intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);

        startActivity(intent);
        finish();
    }

    @Override
    public void onBackPressed() {

        FragmentManager childFragmentManager = getFragmentManager();
        if (childFragmentManager.getBackStackEntryCount() > 1) {
            childFragmentManager.popBackStack();
        } else {
            //TODO: call dashboard activity or popup to close app
            super.onBackPressed();
        }
        overridePendingTransition(R.anim.slide_in_right, R.anim.slide_out_right);

    }
    //endregion /* =================================== User Define Methods ===================================== */

}
