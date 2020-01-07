package ru.temoteam.simsinfinity.ui;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.telephony.PhoneNumberFormattingTextWatcher;
import android.util.Log;
import android.view.KeyEvent;
import android.view.View;
import android.view.inputmethod.EditorInfo;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.FirebaseApp;
import com.google.firebase.FirebaseException;
import com.google.firebase.FirebaseTooManyRequestsException;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseAuthInvalidCredentialsException;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.auth.PhoneAuthCredential;
import com.google.firebase.auth.PhoneAuthProvider;
import com.google.firebase.auth.PhoneAuthProvider.OnVerificationStateChangedCallbacks;

import java.lang.ref.WeakReference;
import java.util.Arrays;
import java.util.concurrent.TimeUnit;

import ru.temoteam.simsinfinity.R;
import ru.temoteam.simsinfinity.util.UsPhoneNumberFormatter;

public class SignInActivity extends AppCompatActivity implements
        View.OnClickListener, TextView.OnEditorActionListener {

    static String TAG = "SignIn";

    EditText phoneET, codeET;
    TextView warningTW;
    Button signIn;
    RelativeLayout rl2;
    String verID;
    PhoneAuthProvider.ForceResendingToken resendToken;
    FirebaseAuth mAuth = FirebaseAuth.getInstance();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_in);

        phoneET = findViewById(R.id.phone);
        codeET = findViewById(R.id.code);
        signIn = findViewById(R.id.btn_signUp);
        rl2 = findViewById(R.id.relativeLayout2);
        warningTW = findViewById(R.id.tw_warning);


        phoneET.addTextChangedListener(new PhoneNumberFormattingTextWatcher());
        phoneET.setOnEditorActionListener(this);
        signIn.setOnClickListener(this);

    }

    @Override
    public void onClick(View v) {
        action();
    }

    @Override
    public boolean onEditorAction(TextView v, int actionId, KeyEvent event) {
        if (actionId == EditorInfo.IME_NULL
                && event.getAction() == KeyEvent.ACTION_DOWN)
            action();
        return true;
    }

    public void action(){
        if (rl2.getVisibility()==View.VISIBLE) signIn();
        else sendCode();
    }

    public void sendCode(){
        rl2.setVisibility(View.VISIBLE);
        PhoneAuthProvider.getInstance().verifyPhoneNumber(
                phoneET.getText().toString(),        // Phone number to verify
                60,                 // Timeout duration
                TimeUnit.SECONDS,   // Unit of timeout
                this,               // Activity (for callback binding)
                callbacks);        // OnVerificationStateChangedCallbacks
    }

    public void signIn(){
        if (codeET.length()==6){
        PhoneAuthCredential credential = PhoneAuthProvider.getCredential(verID, codeET.getText().toString());
        signInWithPhoneAuthCredential(credential);
        }

    }

    OnVerificationStateChangedCallbacks callbacks = new OnVerificationStateChangedCallbacks() {
        @Override
        public void onVerificationCompleted(@NonNull PhoneAuthCredential phoneAuthCredential) {
            Log.d(TAG, "onVerificationCompleted:" + phoneAuthCredential);

            signInWithPhoneAuthCredential(phoneAuthCredential);
        }

        @Override
        public void onVerificationFailed(@NonNull FirebaseException e) {
            Log.w(TAG, "onVerificationFailed", e);

            if (e instanceof FirebaseAuthInvalidCredentialsException) {
                warningTW.setVisibility(View.VISIBLE);
                warningTW.setText(R.string.check_your_phone_number);
            } else if (e instanceof FirebaseTooManyRequestsException) {
                // The SMS quota for the project has been exceeded
            }
        }

        @Override
        public void onCodeSent(String verificationId,
                               PhoneAuthProvider.ForceResendingToken forceResendingToken) {
            verID = verificationId;
            resendToken = forceResendingToken;
        }
    };

    private void signInWithPhoneAuthCredential(PhoneAuthCredential credential) {
        mAuth.signInWithCredential(credential)
                .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if (task.isSuccessful()) {
                            // Sign in success, update UI with the signed-in user's information
                            Log.d(TAG, "signInWithCredential:success");

                            FirebaseUser user = task.getResult().getUser();
                            // ...
                        } else {
                            // Sign in failed, display a message and update the UI
                            Log.w(TAG, "signInWithCredential:failure", task.getException());
                            if (task.getException() instanceof FirebaseAuthInvalidCredentialsException) {
                                // The verification code entered was invalid
                            }
                        }
                    }
                });
    }



}
