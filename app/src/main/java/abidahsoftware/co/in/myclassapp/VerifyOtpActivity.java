package abidahsoftware.co.in.myclassapp;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.AppCompatButton;
import androidx.appcompat.widget.AppCompatEditText;

import android.content.Intent;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.google.android.material.textview.MaterialTextView;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.auth.PhoneAuthCredential;
import com.google.firebase.auth.PhoneAuthProvider;

import java.util.Objects;

public class VerifyOtpActivity extends AppCompatActivity {

    AppCompatButton login_btn;

    AppCompatEditText input_otp_1, input_otp_2, input_otp_3, input_otp_4, input_otp_5, input_otp_6;


    String otp;

    ProgressBar bar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_verify_otp);
        input_otp_1 = findViewById(R.id.input_otp_1);
        input_otp_2 = findViewById(R.id.input_otp_2);
        input_otp_3 = findViewById(R.id.input_otp_3);
        input_otp_4 = findViewById(R.id.input_otp_4);
        input_otp_5 = findViewById(R.id.input_otp_5);
        input_otp_6 = findViewById(R.id.input_otp_6);


        bar = findViewById(R.id.bar);

        login_btn = findViewById(R.id.login_btn);


        MaterialTextView materialTextView = findViewById(R.id.OTP_text_view_3);
        materialTextView.setText(String.format(
                "+91-%s", getIntent().getStringExtra("mobile")
        ));

        otp = getIntent().getStringExtra("otp");

        login_btn.setOnClickListener(v -> {

            if (!Objects.requireNonNull(input_otp_1.getText()).toString().isEmpty() && !Objects.requireNonNull(input_otp_2.getText()).toString().isEmpty()
                    && !Objects.requireNonNull(input_otp_3.getText()).toString().isEmpty() && !Objects.requireNonNull(input_otp_4.getText()).toString().isEmpty()
                    && !Objects.requireNonNull(input_otp_5.getText()).toString().isEmpty() && !Objects.requireNonNull(input_otp_6.getText()).toString().isEmpty()) {
                String sentCode = input_otp_1.getText().toString() +
                        input_otp_2.getText().toString() +
                        input_otp_3.getText().toString() +
                        input_otp_4.getText().toString() +
                        input_otp_5.getText().toString() +
                        input_otp_6.getText().toString();
                if (otp != null) {
                    bar.setVisibility(View.VISIBLE);
                    login_btn.setVisibility(View.INVISIBLE);

                    PhoneAuthCredential phoneAuthCredential = PhoneAuthProvider.getCredential(
                            otp, sentCode
                    );
                    FirebaseAuth.getInstance().signInWithCredential(phoneAuthCredential)
                            .addOnCompleteListener(task -> {
                                bar.setVisibility(View.GONE);
                                login_btn.setVisibility(View.VISIBLE);

                                if (task.isSuccessful()) {
                                    Intent intent = new Intent(getApplicationContext(), AddClassActivity.class);
                                    intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
                                    startActivity(intent);
                                    finish();
                                } else {
                                    Toast.makeText(VerifyOtpActivity.this, "OTP Invalid", Toast.LENGTH_SHORT).show();
                                }
                            });
                } else {
                    Toast.makeText(VerifyOtpActivity.this, "please check internet connection", Toast.LENGTH_SHORT).show();
                }
                Toast.makeText(VerifyOtpActivity.this, "successfully verified", Toast.LENGTH_SHORT).show();
            } else {
                Toast.makeText(VerifyOtpActivity.this, "OTP can not be Blank", Toast.LENGTH_SHORT).show();
            }
        });
        numberOtpMove();


    }

    private void numberOtpMove() {
        input_otp_1.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                if (!s.toString().trim().isEmpty()) {
                    input_otp_2.requestFocus();
                }
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });
        input_otp_2.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                if (!s.toString().trim().isEmpty()) {
                    input_otp_3.requestFocus();
                }
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });
        input_otp_3.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                if (!s.toString().trim().isEmpty()) {
                    input_otp_4.requestFocus();
                }
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });
        input_otp_4.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                if (!s.toString().trim().isEmpty()) {
                    input_otp_5.requestFocus();
                }
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });
        input_otp_5.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                if (!s.toString().trim().isEmpty()) {
                    input_otp_6.requestFocus();
                }
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });

    }
}