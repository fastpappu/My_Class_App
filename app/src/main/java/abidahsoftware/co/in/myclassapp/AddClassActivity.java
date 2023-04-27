package abidahsoftware.co.in.myclassapp;

import androidx.activity.result.ActivityResultCallback;
import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.AppCompatImageView;


import android.app.Dialog;
import android.content.ContentResolver;
import android.content.Intent;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Bundle;


import android.provider.MediaStore;
import android.service.autofill.UserData;
import android.view.View;
import android.view.Window;
import android.webkit.MimeTypeMap;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.android.material.textfield.TextInputEditText;
import com.google.android.material.textfield.TextInputLayout;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.OnProgressListener;
import com.google.firebase.storage.StorageReference;
import com.google.firebase.storage.UploadTask;

import java.nio.charset.StandardCharsets;
import java.util.Arrays;
import java.util.Objects;
import java.util.UUID;

public class AddClassActivity extends AppCompatActivity {

    TextInputLayout input_name_person_lay, input_name_class_lay, input_auto_state_lay, input_auto_city_lay,
            input_address_lay, input_staff_lay, input_subject_lay_1, input_subject_lay_2,
            input_subject_lay_3, input_subject_lay_4, input_contact_lay, input_msg_lay;
    TextInputEditText edt_name_person, edt_name_class, edt_name_address, edt_name_staff,
            edt_name_subject_1, edt_name_subject_2, edt_name_subject_3, edt_name_subject_4, edt_contact, edt_msg;

    AutoCompleteTextView Auto_Complete_City, Auto_Complete_state;
    ArrayAdapter<CharSequence> indianStateAdapter;
    ArrayAdapter<CharSequence> indianCityAdapter;
    Button data_submit;
    TextView upload_text,select_text;
    AppCompatImageView image_upload_view;
    ActivityResultLauncher<String> mTakePhoto;
    Uri imageUri;
    Dialog dialog;

    FirebaseStorage firebaseStorage = null;
    DatabaseReference databaseReference = null;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_class);

        input_name_person_lay = findViewById(R.id.input_name_person_lay);
        input_name_class_lay = findViewById(R.id.input_name_class_lay);
        input_auto_state_lay = findViewById(R.id.input_auto_state_lay);
        input_auto_city_lay = findViewById(R.id.input_auto_city_lay);
        input_address_lay = findViewById(R.id.input_address_lay);
        input_staff_lay = findViewById(R.id.input_staff_lay);
        input_subject_lay_1 = findViewById(R.id.input_subject_lay_1);
        input_subject_lay_2 = findViewById(R.id.input_subject_lay_2);
        input_subject_lay_3 = findViewById(R.id.input_subject_lay_3);
        input_subject_lay_4 = findViewById(R.id.input_subject_lay_4);
        input_contact_lay = findViewById(R.id.input_contact_lay);
        input_msg_lay = findViewById(R.id.input_msg_lay);

        edt_name_person = findViewById(R.id.edt_name_person);
        edt_name_class = findViewById(R.id.edt_name_class);
        edt_name_address = findViewById(R.id.edt_name_address);
        edt_contact = findViewById(R.id.edt_contact);
        edt_name_staff = findViewById(R.id.edt_name_staff);
        edt_name_subject_1 = findViewById(R.id.edt_name_subject_1);
        edt_name_subject_2 = findViewById(R.id.edt_name_subject_2);
        edt_name_subject_3 = findViewById(R.id.edt_name_subject_3);
        edt_name_subject_4 = findViewById(R.id.edt_name_subject_4);
        edt_msg = findViewById(R.id.edt_msg);
        Auto_Complete_City = findViewById(R.id.Auto_Complete_City);
        Auto_Complete_state = findViewById(R.id.Auto_Complete_state);

        data_submit = findViewById(R.id.data_submit);
        upload_text = findViewById(R.id.upload_text);
        select_text = findViewById(R.id.select_text);
        image_upload_view = findViewById(R.id.image_upload_view);

        databaseReference = FirebaseDatabase.getInstance().getReference("UserData").child("classInfo");
        firebaseStorage = FirebaseStorage.getInstance();

        mTakePhoto = registerForActivityResult(
                new ActivityResultContracts.GetContent(),
                new ActivityResultCallback<Uri>() {
                    @Override
                    public void onActivityResult(Uri result) {
                        if (result != null) {
                            image_upload_view.setImageURI(result);
                            imageUri = result;
                        }

                    }
                }
        );

        image_upload_view.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mTakePhoto.launch("image/*");
            }
        });


        upload_text.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                uploadImage();
            }
        });



        indianStateAdapter = ArrayAdapter.createFromResource(AddClassActivity.this, R.array.array_indian_states,
                R.layout.dropdown_item_chooser);
        indianStateAdapter.setDropDownViewResource(android.R.layout.simple_selectable_list_item);
        Auto_Complete_state.setAdapter(indianStateAdapter);

        indianCityAdapter = ArrayAdapter.createFromResource(AddClassActivity.this, R.array.array_maharashtra_districts,
                R.layout.dropdown_item_chooser);
        indianCityAdapter.setDropDownViewResource(android.R.layout.simple_selectable_list_item);
        Auto_Complete_City.setAdapter(indianCityAdapter);


        data_submit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String FullName = Objects.requireNonNull(edt_name_person.getText()).toString();
                String ClassName = Objects.requireNonNull(edt_name_class.getText()).toString();
                String Address = Objects.requireNonNull(edt_name_address.getText()).toString();
                String Contact = Objects.requireNonNull(edt_contact.getText()).toString();
                String StaffName = Objects.requireNonNull(edt_name_staff.getText()).toString();
                String Subject_1 = Objects.requireNonNull(edt_name_subject_1.getText()).toString();
                String Subject_2 = Objects.requireNonNull(edt_name_subject_2.getText()).toString();
                String Subject_3 = Objects.requireNonNull(edt_name_subject_3.getText()).toString();
                String Subject_4 = Objects.requireNonNull(edt_name_subject_4.getText()).toString();
                String CityName = Auto_Complete_City.getText().toString();
                String StateName = Auto_Complete_state.getText().toString();
                String Msg = Objects.requireNonNull(edt_msg.getText()).toString();
                String image = imageUri.toString();

                if (!FullName.isEmpty()) {
                    input_name_person_lay.setError(null);
                    input_name_person_lay.setErrorEnabled(false);
                    if (!ClassName.isEmpty()) {
                        input_name_class_lay.setError(null);
                        input_name_class_lay.setErrorEnabled(false);
                        if (!Address.isEmpty()) {
                            input_address_lay.setError(null);
                            input_address_lay.setErrorEnabled(false);
                            if (!Contact.isEmpty() && Contact.length() >= 10) {
                                input_contact_lay.setError(null);
                                input_contact_lay.setErrorEnabled(false);
                                if (!StaffName.isEmpty()) {
                                    input_staff_lay.setError(null);
                                    input_staff_lay.setErrorEnabled(false);
                                    if (!Subject_1.isEmpty()) {
                                        input_subject_lay_1.setError(null);
                                        input_subject_lay_1.setErrorEnabled(false);
                                        if (!Subject_2.isEmpty()) {
                                            input_subject_lay_2.setError(null);
                                            input_subject_lay_2.setErrorEnabled(false);
                                            if (!Subject_3.isEmpty()) {
                                                input_subject_lay_3.setError(null);
                                                input_subject_lay_3.setErrorEnabled(false);
                                                if (!Subject_4.isEmpty()) {
                                                    input_subject_lay_4.setError(null);
                                                    input_subject_lay_4.setErrorEnabled(false);
                                                    if (!CityName.isEmpty()) {
                                                        input_auto_city_lay.setError(null);
                                                        input_auto_city_lay.setErrorEnabled(false);
                                                        if (!StateName.isEmpty()) {
                                                            input_auto_state_lay.setError(null);
                                                            input_auto_state_lay.setErrorEnabled(false);
                                                            if (!Msg.isEmpty()) {
                                                                input_msg_lay.setError(null);
                                                                input_msg_lay.setErrorEnabled(false);
                                                                if (!image.isEmpty()) {

                                                                    String fullName = edt_name_person.getText().toString();
                                                                    String className = edt_name_class.getText().toString();
                                                                    String address = edt_name_address.getText().toString();
                                                                    String contact = edt_contact.getText().toString();
                                                                    String staffName = edt_name_staff.getText().toString();
                                                                    String subject_1 = edt_name_subject_1.getText().toString();
                                                                    String subject_2 = edt_name_subject_2.getText().toString();
                                                                    String subject_3 = edt_name_subject_3.getText().toString();
                                                                    String subject_4 = edt_name_subject_4.getText().toString();
                                                                    String city = Auto_Complete_City.getText().toString();
                                                                    String state = Auto_Complete_state.getText().toString();
                                                                    String Message = edt_msg.getText().toString();
                                                                    String imageUrl = imageUri.toString();

                                                                    DatabaseReference AddData = FirebaseDatabase.getInstance().getReference("UserData").child("classInfo");
                                                                    AddClassJava addClassJava = new AddClassJava(
                                                                            fullName, className, address, contact, staffName, subject_1, subject_2, subject_3, subject_4, city
                                                                            , state, Message, imageUrl);
                                                                    String userData = AddData.push().getKey();
                                                                    assert userData != null;
                                                                    AddData.child(className).setValue(addClassJava);
                                                                    Intent i = new Intent(getApplicationContext(), MyClassViewActivity.class);
                                                                    startActivity(i);
                                                                    finish();
                                                                    Toast.makeText(AddClassActivity.this, "Successfully Submitted", Toast.LENGTH_SHORT).show();


                                                                } else {
                                                                    Toast.makeText(AddClassActivity.this, "select an image", Toast.LENGTH_SHORT).show();
                                                                }

                                                            } else {
                                                                input_msg_lay.setError("Class Information is Important!");
                                                            }
                                                        } else {
                                                            input_auto_state_lay.setError("Please Select State");
                                                        }
                                                    } else {
                                                        input_auto_city_lay.setError("Please Select Your City");
                                                    }
                                                } else {
                                                    input_subject_lay_4.setError("Please Enter Subject Name");
                                                }
                                            } else {
                                                input_subject_lay_3.setError("Please Enter Subject Name");
                                            }
                                        } else {
                                            input_subject_lay_2.setError("Please Enter Your Subject Name");
                                        }

                                    } else {
                                        input_subject_lay_1.setError("Please Enter Subject You Teach");
                                    }

                                } else {
                                    input_staff_lay.setError("Please Enter Your Staff Count");
                                }

                            } else {
                                input_contact_lay.setError("Please Enter Your Contact Details");
                                input_contact_lay.setError("Please Enter 10 Digit Number");
                            }
                        } else {
                            input_address_lay.setError("Please Enter Address");
                        }
                    } else {
                        input_name_class_lay.setError("Please Enter Your Class/Institute Name");
                    }
                } else {
                    input_name_person_lay.setError("Please Enter Your Full Name");
                }
            }
        });
    }

    private void uploadImage() {
        if (imageUri != null) {
            StorageReference reference = firebaseStorage.getReference("ImageDocument/").child("ClassImages"
                    + UUID.randomUUID().toString());
            reference.putFile(imageUri).addOnCompleteListener(new OnCompleteListener<UploadTask.TaskSnapshot>() {
                @Override
                public void onComplete(@NonNull Task<UploadTask.TaskSnapshot> task) {
                    if (task.isSuccessful()) {
                        databaseReference.push().setValue(imageUri.toString());
                        Toast.makeText(AddClassActivity.this, "image upload Successfully", Toast.LENGTH_SHORT).show();
                    }else {
                        Toast.makeText(AddClassActivity.this, "Failed To Upload", Toast.LENGTH_SHORT).show();
                    }
                }
            });
        }
    }


}