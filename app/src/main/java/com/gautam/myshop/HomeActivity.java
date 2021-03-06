package com.gautam.myshop;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;

//import com.google.android.gms.ads.AdView;
import com.gautam.myshop.Model.Users;
import com.gautam.myshop.Prevalent.Prevalent;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.snackbar.Snackbar;

import android.os.SystemClock;
import android.text.TextUtils;
import android.view.MenuItem;
import android.view.View;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.core.view.GravityCompat;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.navigation.ui.AppBarConfiguration;
import androidx.navigation.ui.NavigationUI;

import com.google.android.material.navigation.NavigationView;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import androidx.drawerlayout.widget.DrawerLayout;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import io.paperdb.Paper;

import android.view.Menu;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.Toast;

public class HomeActivity extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener {

    private AppBarConfiguration mAppBarConfiguration;
    private FrameLayout frameLayout;
    private static final int HOME_FRAGMENT=0;
    private static final int CART_FRAGMENT=1;

    private static final int ACCOUNT_FRAGMENT=5;
    private  NavigationView navigationView;
    private ImageView actionBarLogo;


    private static int currentFragment;



    private ProgressDialog loadingBar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {






        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayShowTitleEnabled(false);

        actionBarLogo=findViewById(R.id.actionbar_logo);
        frameLayout=findViewById(R.id.main_framelayout);
        setFragment(new HomeFragment(),HOME_FRAGMENT);
        DrawerLayout drawer = findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle=new ActionBarDrawerToggle(HomeActivity.this,drawer,toolbar,0,0);
        drawer.addDrawerListener(toggle);
        toggle.syncState();

         navigationView = findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(HomeActivity.this);
        //navigationView.setNavigationItemSelectedListener((NavigationView.OnNavigationItemSelectedListener) this);
        navigationView.getMenu().getItem(0).setChecked(true);
        navigationView.getMenu().getItem(3).setChecked(false);


       // AdView adView=new AdView(this);


        // Passing each menu ID as a set of Ids because each
        // menu should be considered as top level destinations.
      //  mAppBarConfiguration = new AppBarConfiguration.Builder(
            //    R.id.nav_home, R.id.nav_gallery, R.id.nav_slideshow,
              //  R.id.nav_tools, R.id.nav_share, R.id.nav_send)
            //    .setDrawerLayout(drawer)
              //  .build();
        //NavController navController = Navigation.findNavController(this, R.id.nav_host_fragment);
        //NavigationUI.setupActionBarWithNavController(this, navController, mAppBarConfiguration);
       // NavigationUI.setupWithNavController(navigationView, navController);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        if(currentFragment==HOME_FRAGMENT) {
            getMenuInflater().inflate(R.menu.home, menu);


        }
        return true;
    }
    @Override
    public boolean onOptionsItemSelected(MenuItem item)
    {


        int id=item.getItemId();
        if(id==R.id.main_search_icon)
        {
            return true;
        }
        else if(id==R.id.main_notification_icon)
        {
        return true;
        }

        else if(id==R.id.main_cart_icon)
        {
            myCart();
            return true;
        }

            return super.onOptionsItemSelected(item);
    }

    private void myCart() {
        actionBarLogo.setVisibility(View.GONE);
        getSupportActionBar().setDisplayShowTitleEnabled(true);
        getSupportActionBar().setTitle("MY CART");
        invalidateOptionsMenu();
        setFragment(new MyCartFragment(),CART_FRAGMENT);
        navigationView.getMenu().getItem(3).setChecked(true);
        navigationView.getMenu().getItem(0).setChecked(false);
    }


    public boolean onNavigationItemSelected(MenuItem item)
    {

        int id=item.getItemId();



        if(id==R.id.my_mall)

        {
            getSupportActionBar().setDisplayShowTitleEnabled(false);
            actionBarLogo.setVisibility(View.VISIBLE);
            invalidateOptionsMenu();
             setFragment(new HomeFragment(),HOME_FRAGMENT);
            navigationView.getMenu().getItem(0).setChecked(true);
            navigationView.getMenu().getItem(3).setChecked(false);
        }

        else if(id==R.id.my_order)
        {

        }

       else if(id==R.id.my_rewards)
        {

        }
        else if(id==R.id.my_cart)
        {
myCart();
        }

        else if(id==R.id.my_whishlist)
        {

        }
        else if(id==R.id.my_account)
        {
            gotoFragment("My Account ",new MyAccountFragment(),ACCOUNT_FRAGMENT);

        }
        else if(id==R.id.my_signout)
        {
            Intent intent = new Intent(HomeActivity.this, Main2Activity.class);
            startActivity(intent);

            Paper.book().destroy();


        }
        DrawerLayout drawerLayout=findViewById(R.id.drawer_layout);
        drawerLayout.closeDrawer(GravityCompat.START);
        return true;

    }
    private void setFragment(Fragment fragment,int fragmentNo)


    {

            currentFragment = fragmentNo;
            FragmentTransaction fragmentTransaction = getSupportFragmentManager().beginTransaction();
            fragmentTransaction.setCustomAnimations(R.anim.fade_in, R.anim.fade_out);
            fragmentTransaction.replace(frameLayout.getId(), fragment);
            fragmentTransaction.commit();

    }
    private void gotoFragment(String title ,Fragment fragment,int fragmentNO)
    {
        actionBarLogo.setVisibility(View.GONE);
        getSupportActionBar().setDisplayShowTitleEnabled(true);
        getSupportActionBar().setTitle(title);
        invalidateOptionsMenu();
        setFragment(fragment,fragmentNO);
        if(fragmentNO==CART_FRAGMENT)
        {
            navigationView.getMenu().getItem(3).setChecked(true);
        }
    }










}
