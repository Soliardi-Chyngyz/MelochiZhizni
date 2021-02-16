package com.example.melochizhizni.ui.mainActivity;

import android.annotation.SuppressLint;
import android.os.Build;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.TextView;

import com.example.melochizhizni.Prefs;
import com.example.melochizhizni.R;
import com.example.melochizhizni.data.models.CumulativePoints;
import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.firestore.FirebaseFirestore;

import androidx.annotation.NonNull;
import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.view.menu.MenuBuilder;
import androidx.appcompat.widget.Toolbar;
import androidx.core.content.ContextCompat;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.navigation.ui.AppBarConfiguration;
import androidx.navigation.ui.NavigationUI;

import static com.example.melochizhizni.data.ConstantKeys.TRANSFER_YES;

public class MainActivity extends AppCompatActivity {
    private NavController navController;
    private AppBarConfiguration appBarConfiguration;
    private TextView text, titleText;
    private MainActivityViewModel vModel;
    private FirebaseFirestore fb;

    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    @Override

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = findViewById(R.id.my_toolbar);
        setSupportActionBar(toolbar);

        vModel = new ViewModelProvider(this).get(MainActivityViewModel.class);
        fb = FirebaseFirestore.getInstance();

        /*getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN);*/
        Window window = getWindow();
        window.clearFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);
        window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS);
        window.setStatusBarColor(ContextCompat.getColor(this, R.color.purple_500)); // statusBarColor

        initView();
        initNavController();
        getFirestoreData();


        if (!Prefs.instance.getShowState())
            navController.navigate(R.id.boardFragment);

        if (FirebaseAuth.getInstance().getCurrentUser() == null)
            navController.navigate(R.id.authFragment);

    }

    @SuppressLint("SetTextI18n")
    private void getFirestoreData() {
        String phoneNumber = Prefs.instance.getNumber();
        vModel.setData(fb, phoneNumber);
        vModel.getData().observe(this, cumulativePoints -> {
            text.setText(cumulativePoints.getPoints() + " баллов");

        });
    }

    private void initView() {
        titleText = findViewById(R.id.tab_text_title);
        text = findViewById(R.id.tab_text);
    }

    @SuppressLint("RestrictedApi")
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.bar_menu, menu);
        if (menu instanceof MenuBuilder) {
            MenuBuilder menuBuilder = (MenuBuilder) menu;
            menuBuilder.setOptionalIconsVisible(true);
        }
        return true;
    }

    private void initNavController() {
        BottomNavigationView navView = findViewById(R.id.nav_view);
        appBarConfiguration = new AppBarConfiguration.Builder(
                R.id.boardFragment, R.id.navigation_main, R.id.navigation_category,
                R.id.navigation_basket, R.id.authFragment)
                .build();
        navController = Navigation.findNavController(this, R.id.nav_host_fragment);
        NavigationUI.setupActionBarWithNavController(this, navController, appBarConfiguration);
        NavigationUI.setupWithNavController(navView, navController);

        navController.addOnDestinationChangedListener((controller, destination, arguments) -> {
            if (destination.getId() == R.id.authFragment || destination.getId() == R.id.boardFragment) {
                navView.setVisibility(View.GONE);
                getSupportActionBar().hide();
            } else if (destination.getId() == R.id.navigation_category || destination.getId() == R.id.itemFragment
                    || destination.getId() == R.id.catCatRecyclerFragment) {
                getSupportActionBar().hide();
            } else if(destination.getId() == R.id.navigation_main) {
                titleText.setText("Главное меню");
                getSupportActionBar().show();
            } else if (destination.getId() == R.id.navigation_basket){
                titleText.setText("Карзина");
                getSupportActionBar().show();
            } else {
                navView.setVisibility(View.VISIBLE);
                getSupportActionBar().show();
            }
        });
    }

    @Override
    public boolean onSupportNavigateUp() {
        return NavigationUI.navigateUp(navController, appBarConfiguration) || super.onSupportNavigateUp();
    }

    @SuppressLint("NonConstantResourceId")
    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId()){
            case R.id.selected_menu:
                Bundle bundle = new Bundle();
                bundle.putBoolean(TRANSFER_YES, true);
                navController.navigate(R.id.action_navigation_main_to_navigation_category, bundle);
                return true;
            case R.id.barcode_menu:
                navController.navigate(R.id.action_navigation_main_to_cumulativePointsFragment);
                return true;
            case R.id.basket_menu:
                navController.navigate(R.id.action_navigation_main_to_navigation_basket);
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }
}