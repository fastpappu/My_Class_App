package abidahsoftware.co.in.myclassapp;


import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.AppCompatButton;

import android.content.Intent;
import android.os.Bundle;
import android.util.Patterns;


import android.widget.TextView;
import android.widget.Toast;



import com.google.android.material.textfield.TextInputEditText;
import com.google.android.material.textfield.TextInputLayout;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;


import java.util.Objects;

public class LoginUserEmailActivity extends AppCompatActivity {

    TextInputLayout input_email_lay, login_password_lay;
    TextInputEditText password_edt_log, edt_email;
    TextView login_mobile_activity3;
    AppCompatButton login_btn;
    FirebaseAuth auth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login_user_email);

        input_email_lay = findViewById(R.id.input_email_lay);
        login_password_lay = findViewById(R.id.login_password_lay);
        password_edt_log = findViewById(R.id.password_edt_log);
        edt_email = findViewById(R.id.edt_email);
        login_mobile_activity3 = findViewById(R.id.login_mobile_activity3);

        login_mobile_activity3.setOnClickListener(view -> {
            Intent i = new Intent(getApplicationContext(),LoginMobileActivity.class);
            startActivity(i);
        });

        login_btn = findViewById(R.id.login_btn);
        auth = FirebaseAuth.getInstance();

        login_btn.setOnClickListener(view -> {
            String email = Objects.requireNonNull(edt_email.getText()).toString();
            String password = Objects.requireNonNull(password_edt_log.getText()).toString();

            if (Patterns.EMAIL_ADDRESS.matcher(email).matches() && password.length() >= 8) {
                input_email_lay.setError(null);
                input_email_lay.setErrorEnabled(false);
                login_password_lay.setError(null);
                login_password_lay.setErrorEnabled(false);
                auth.signInWithEmailAndPassword(email, password)
                        .addOnCompleteListener(task -> {
                            if (task.isSuccessful()) {
                                Toast.makeText(LoginUserEmailActivity.this, "Login Successful", Toast.LENGTH_SHORT).show();
                                FirebaseUser user = auth.getCurrentUser();
                                if (user != null) {
                                    Intent i = new Intent(getApplicationContext(), AddClassActivity.class);
                                    startActivity(i);
                                    finish();
                                }
                            } else {
                                Toast.makeText(LoginUserEmailActivity.this, "Login Failed, Invalid Password Or Email", Toast.LENGTH_SHORT).show();

                            }
                        });
            } else if (!Patterns.EMAIL_ADDRESS.matcher(email).matches()) {
                input_email_lay.setError("Enter Valid Email address");
                input_email_lay.requestFocus();
            } else {
                login_password_lay.setError("Password At Least 8 Digit long");
                login_password_lay.requestFocus();
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
        }
    }
}