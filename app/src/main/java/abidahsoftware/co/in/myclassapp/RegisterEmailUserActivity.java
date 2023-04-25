package abidahsoftware.co.in.myclassapp;


import androidx.appcompat.app.AppCompatActivity;

import android.app.Dialog;
import android.content.Intent;
import android.os.Bundle;
import android.util.Patterns;
import android.view.View;
import android.view.Window;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;



import com.google.android.material.textfield.TextInputEditText;
import com.google.android.material.textfield.TextInputLayout;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

import java.util.Objects;

public class RegisterEmailUserActivity extends AppCompatActivity {

    TextView login_mobile_activity3, login_mobile_activity5;

    FirebaseAuth auth;

    Dialog dialog;

    Button register_btn;
    TextInputLayout input_edt_lay_1, password_lay_custom, confirm_password_lay;
    TextInputEditText edt_name, password_edt, password_edt_con;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register_email_user);

        register_btn = findViewById(R.id.register_btn);

        login_mobile_activity5 = findViewById(R.id.login_mobile_activity5);

        auth = FirebaseAuth.getInstance();

        input_edt_lay_1 = findViewById(R.id.input_edt_lay_1);
        password_lay_custom = findViewById(R.id.password_lay_custom);
        confirm_password_lay = findViewById(R.id.confirm_password_lay);

        edt_name = findViewById(R.id.edt_name);
        password_edt = findViewById(R.id.password_edt);
        password_edt_con = findViewById(R.id.password_edt_con);

        login_mobile_activity5.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getApplicationContext(),LoginUserEmailActivity.class);
                startActivity(intent);
            }
        });


        login_mobile_activity3 = findViewById(R.id.login_mobile_activity3);
        login_mobile_activity3.setOnClickListener(view -> {
            Intent intent = new Intent(getApplicationContext(), LoginMobileActivity.class);
            startActivity(intent);
            finish();
        });

        register_btn.setOnClickListener(view -> {
            String email = Objects.requireNonNull(edt_name.getText()).toString();
            String password = Objects.requireNonNull(password_edt.getText()).toString();
            String con_password = Objects.requireNonNull(password_edt_con.getText()).toString();

            if (Patterns.EMAIL_ADDRESS.matcher(email).matches()) {
                input_edt_lay_1.setError(null);
                input_edt_lay_1.setErrorEnabled(false);
                if (password.matches(con_password) && password.length() >= 8) {
                    password_lay_custom.setError(null);
                    password_lay_custom.setErrorEnabled(false);
                    confirm_password_lay.setError(null);
                    confirm_password_lay.setErrorEnabled(false);
                    InputMethodManager imm = (InputMethodManager) getSystemService(INPUT_METHOD_SERVICE);
                    imm.hideSoftInputFromWindow(getCurrentFocus().getWindowToken(), 0);
                    dialog = new Dialog(RegisterEmailUserActivity.this);
                    dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
                    dialog.setContentView(R.layout.dialog_wait);
                    dialog.setCanceledOnTouchOutside(false);
                    dialog.show();
                    auth.createUserWithEmailAndPassword(email, password)
                            .addOnCompleteListener(task -> {
                                if (task.isSuccessful()) {
                                    Toast.makeText(RegisterEmailUserActivity.this, "Register Successfully", Toast.LENGTH_SHORT).show();
                                    FirebaseUser user = auth.getCurrentUser();
                                    if (user != null) {
                                        dialog.dismiss();
                                        Intent intent = new Intent(getApplicationContext(), AddClassActivity.class);
                                        startActivity(intent);
                                        finish();
                                    }
                                }else {
                                    dialog.dismiss();
                                    Toast.makeText(RegisterEmailUserActivity.this, "User Already Exists!", Toast.LENGTH_SHORT).show();
                                }
                            });
                } else if (password.length()<8) {
                    password_lay_custom.setError("Password minimum 8 characters long");
                    password_lay_custom.requestFocus();
                }else {
                    confirm_password_lay.setError("Password doesn't match");
                    confirm_password_lay.requestFocus();
                }
            }else {
                input_edt_lay_1.setError("Please Enter valid Email Address");
                input_edt_lay_1.requestFocus();
            }

        });

    }
    @Override
    protected void onStart() {
        super.onStart();
        FirebaseUser currentUser = FirebaseAuth.getInstance().getCurrentUser();
        if (currentUser != null) {
            startActivity(new Intent(RegisterEmailUserActivity.this, AddClassActivity.class));
            finish();
        }
    }
}