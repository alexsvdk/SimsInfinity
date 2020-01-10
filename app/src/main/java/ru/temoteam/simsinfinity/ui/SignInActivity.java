package ru.temoteam.simsinfinity.ui;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;

import com.firebase.ui.auth.AuthUI;
import com.google.firebase.auth.FirebaseAuth;

import java.util.Arrays;
import java.util.List;

import ru.temoteam.simsinfinity.R;

public class SignInActivity extends AppCompatActivity {

static int RC_SIGN_IN = RESULT_OK;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (FirebaseAuth.getInstance().getCurrentUser()!=null)
            startActivity(new Intent(SignInActivity.this,MainActivity.class));
        setContentView(R.layout.activity_sign_in);
    }

    @Override
    protected void onResume() {
        super.onResume();
        auth();
    }


    void auth(){
        FirebaseAuth mAuth = FirebaseAuth.getInstance();
        if (mAuth.getCurrentUser()!=null)
            startActivity(new Intent(SignInActivity.this,MainActivity.class));

        else {

            List<AuthUI.IdpConfig> providers = Arrays.asList(
                    new AuthUI.IdpConfig.EmailBuilder().build(),
                    new AuthUI.IdpConfig.PhoneBuilder().build(),
                    new AuthUI.IdpConfig.GoogleBuilder().build());

            startActivityForResult(
                    AuthUI.getInstance()
                            .createSignInIntentBuilder()
                            .setAvailableProviders(providers)
                            .setTheme(R.style.AppTheme)
                            .build(),
                    RC_SIGN_IN);
        }
    }
}
