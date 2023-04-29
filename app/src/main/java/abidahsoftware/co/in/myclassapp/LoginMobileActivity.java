package abidahsoftware.co.in.myclassapp;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.FirebaseException;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.auth.PhoneAuthCredential;
import com.google.firebase.auth.PhoneAuthProvider;

import java.util.concurrent.TimeUnit;

public class LoginMobileActivity extends AppCompatActivity {

    EditText phone;
    Button btn_gen_otp;
    FirebaseAuth mAuth;

    ProgressBar bar;

    TextView start_email_activity;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register_mobile_user);
        phone = findViewById(R.id.phone);
        btn_gen_otp = findViewById(R.id.btn_gen_otp);
        mAuth = FirebaseAuth.getInstance();
        bar = findViewById(R.id.bar);

        start_email_activity = findViewById(R.id.start_email_activity);
        start_email_activity.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(), RegisterEmailUserActivity.class);
                startActivity(intent);
                finish();
            }
        });

        btn_gen_otp.setOnClickListener(v -> {
            if (!phone.getText().toString().trim().isEmpty()) {
                bar.setVisibility(View.VISIBLE);
                btn_gen_otp.setVisibility(View.INVISIBLE);

                if ((phone.getText().toString().trim()).length() == 10) {
                    PhoneAuthProvider.getInstance().verifyPhoneNumber(
                            "+91" + phone.getText().toString(), 60,
                            TimeUnit.SECONDS, LoginMobileActivity.this,
                            new PhoneAuthProvider.OnVerificationStateChangedCallbacks() {
                                @Override
                                public void onVerificationCompleted(@NonNull PhoneAuthCredential phoneAuthCredential) {
                                    bar.setVisibility(View.GONE);
                                    btn_gen_otp.setVisibility(View.VISIBLE);
                                }

                                @Override
                                public void onVerificationFailed(@NonNull FirebaseException e) {
                                    bar.setVisibility(View.GONE);
                                    btn_gen_otp.setVisibility(View.VISIBLE);
                                    Toast.makeText(LoginMobileActivity.this, e.getMessage(), Toast.LENGTH_SHORT).show();
                                }

                                @Override
                                public void onCodeSent(@NonNull String backendOtp, @NonNull PhoneAuthProvider.ForceResendingToken forceResendingToken) {
                                    bar.setVisibility(View.GONE);
                                    btn_gen_otp.setVisibility(View.VISIBLE);
                                    Intent intent = new Intent(getApplicationContext(), VerifyOtpActivity.class);
                                    intent.putExtra("mobile", phone.getText().toString());
                                    intent.putExtra("otp", backendOtp);
                                    startActivity(intent);
                                    Toast.makeText(LoginMobileActivity.this, "OTP Sent", Toast.LENGTH_SHORT).show();
                                    finish();
                                }
                            }
                    );
                } else {
                    Toast.makeText(this, "invalid number", Toast.LENGTH_SHORT).show();
                }
            } else {
                Toast.makeText(this, "Phone number can not be Blank", Toast.LENGTH_SHORT).show();
            }

        });

    }

    @Override
    protected void onStart() {
        super.onStart();
        FirebaseUser currentUser = FirebaseAuth.getInstance().getCurrentUser();
        if (currentUser != null) {
            Intent i = new Intent(getApplicationContext(), MyClassHomeViewActivity.class);
            startActivity(i);
            finish();
        }
    }

}
