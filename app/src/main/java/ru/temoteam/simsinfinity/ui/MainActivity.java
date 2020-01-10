package ru.temoteam.simsinfinity.ui;

import android.os.Bundle;
import android.view.View;

import com.google.android.material.bottomnavigation.BottomNavigationView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.navigation.ui.AppBarConfiguration;
import androidx.navigation.ui.NavigationUI;

import ru.temoteam.simsinfinity.R;
import ru.temoteam.simsinfinity.UITools;

public class MainActivity extends AppCompatActivity implements UITools {

    BottomNavigationView navView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        navView = findViewById(R.id.nav_view);
        // Passing each menu ID as a set of Ids because each
        // menu should be considered as top level destinations.
        AppBarConfiguration appBarConfiguration = new AppBarConfiguration.Builder(
                R.id.navigation_goals, R.id.navigation_home, R.id.navigation_user)
                .build();
        NavController navController = Navigation.findNavController(this, R.id.nav_host_fragment);
        //NavigationUI.setupActionBarWithNavController(this, navController, appBarConfiguration);
        NavigationUI.setupWithNavController(navView, navController);
    }

    @Override
    public void hideBNV() {
        navView.setVisibility(View.GONE);
    }

    @Override
    public void hiveActionBar() {

    }

    @Override
    public void showBNV() {
        navView.setVisibility(View.VISIBLE);
    }

    @Override
    public void showActionBar() {

    }
}
