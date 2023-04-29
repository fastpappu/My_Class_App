package abidahsoftware.co.in.myclassapp;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.MenuItem;

import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.android.material.navigation.NavigationBarView;

public class MyClassViewActivity extends AppCompatActivity {
    BottomNavigationView BottomNavigationViewBar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_my_class_view);
        BottomNavigationViewBar = findViewById(R.id.BottomNavigationViewBar);

        BottomNavigationViewBar.setOnItemSelectedListener(new NavigationBarView.OnItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                int id = item.getItemId();
                if (id == R.id.home_item){

                }else if (id == R.id.Activity_item_nav){

                }else if (id == R.id.search_button){
                    
                }else if (id == R.id.student_item_nav){
                    
                } else  {
                    
                }
                return false;
            }
        });

    }
}