package abidahsoftware.co.in.myclassapp;

import androidx.appcompat.app.AppCompatActivity;


import android.os.Bundle;

import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;

import com.google.android.material.textfield.TextInputEditText;
import com.google.android.material.textfield.TextInputLayout;

public class AddClassActivity extends AppCompatActivity {

    TextInputLayout input_name_person_lay,input_name_class_lay,input_auto_state_lay,input_auto_city_lay,
            input_address_lay,input_staff_lay,input_subject_lay_1,input_subject_lay_2,
            input_subject_lay_3,input_subject_lay_4;
    TextInputEditText edt_name_person, edt_name_class,edt_name_address,edt_name_staff,
            edt_name_subject_1,edt_name_subject_2,edt_name_subject_3,edt_name_subject_4;

    AutoCompleteTextView Auto_Complete_City, Auto_Complete_state;
    ArrayAdapter<CharSequence> indianStateAdapter;
    ArrayAdapter<CharSequence> indianCityAdapter;

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

        edt_name_person = findViewById(R.id.edt_name_person);
        edt_name_class = findViewById(R.id.edt_name_class);
        edt_name_address = findViewById(R.id.edt_name_address);
        edt_name_staff = findViewById(R.id.edt_name_staff);
        edt_name_subject_1 = findViewById(R.id.edt_name_subject_1);
        edt_name_subject_2 = findViewById(R.id.edt_name_subject_2);
        edt_name_subject_3 = findViewById(R.id.edt_name_subject_3);
        edt_name_subject_4 = findViewById(R.id.edt_name_subject_4);

        Auto_Complete_City = findViewById(R.id.Auto_Complete_City);
        Auto_Complete_state = findViewById(R.id.Auto_Complete_state);


        indianStateAdapter = ArrayAdapter.createFromResource(AddClassActivity.this, R.array.array_indian_states,
                R.layout.dropdown_item_chooser);
        indianStateAdapter.setDropDownViewResource(android.R.layout.simple_selectable_list_item);
        Auto_Complete_state.setAdapter(indianStateAdapter);

        indianCityAdapter = ArrayAdapter.createFromResource(AddClassActivity.this, R.array.array_maharashtra_districts,
                R.layout.dropdown_item_chooser);
        indianCityAdapter.setDropDownViewResource(android.R.layout.simple_selectable_list_item);
        Auto_Complete_City.setAdapter(indianCityAdapter);





    }
}