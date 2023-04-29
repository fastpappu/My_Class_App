package abidahsoftware.co.in.myclassapp;


import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;
import android.os.Bundle;
import com.google.android.material.bottomnavigation.BottomNavigationView;


public class MyClassHomeViewActivity extends AppCompatActivity {
    BottomNavigationView BottomNavigationViewBar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_my_class_view);
        BottomNavigationViewBar = findViewById(R.id.BottomNavigationViewBar);

        BottomNavigationViewBar.setOnItemSelectedListener(item -> {
            int id = item.getItemId();
            if (id == R.id.home_item){
                loadFragment(new AddClassInfoFragment(), true);
            }else if (id == R.id.Activity_item_nav){
                loadFragment(new AddActivityFragment(), false);
            }else if (id == R.id.search_button){
                loadFragment(new SearchFragment(),false);
            }else if (id == R.id.student_item_nav){
                loadFragment(new AddStudentFragment(),false);
            } else  {
                loadFragment(new AttendanceFragment(),false);
            }
            return true;
        });
        BottomNavigationViewBar.setSelectedItemId(R.id.home_item);

    }
    public void loadFragment(Fragment fragment, boolean flag){
        FragmentManager fragmentManager =  getSupportFragmentManager();
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
        if (flag)
            fragmentTransaction.add(R.id.ClassFragmentView, fragment);
        else
            fragmentTransaction.replace(R.id.ClassFragmentView, fragment);
        fragmentTransaction.commit();
    }
}