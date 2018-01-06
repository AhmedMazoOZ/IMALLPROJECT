package totchi.apps.a.imall.ResultOnePageModule;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.graphics.Typeface;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

import de.hdodenhof.circleimageview.CircleImageView;
import totchi.apps.a.imall.HomePageModule.Category;
import totchi.apps.a.imall.HomePageModule.CategoryAdapter;
import totchi.apps.a.imall.R;

public class FoodActivity extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener {
    List<Category> categoryList;
    TextView Home;
    Typeface tf1;
    //the recyclerview
    RecyclerView recyclerView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_food);
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        Home = findViewById(R.id.hometv);
        tf1 = Typeface.createFromAsset(getAssets(), "MyriadPro-Regular.otf");
        Home.setTypeface(tf1);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.setDrawerListener(toggle);
        toggle.syncState();

        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        View v = navigationView.getHeaderView(0);
        CircleImageView imageView = (CircleImageView) v.findViewById(R.id.user_imageView);
        TextView textView = (TextView) v.findViewById(R.id.user);

        navigationView.setNavigationItemSelectedListener(this);
        recyclerView = (RecyclerView) findViewById(R.id.recyclerView);
        recyclerView.setHasFixedSize(true);
        //recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.setLayoutManager(new GridLayoutManager(this, 3));

        //initializing the productlist
        categoryList = new ArrayList<>();


        //adding some items to our list
        categoryList.add(
                new Category(
                        1,
                        "FISH",
                        R.drawable.iconibc));

        categoryList.add(
                new Category(
                        2,
                        "SANDWISH",
                        R.drawable.iconjbc));

        categoryList.add(
                new Category(
                        3,
                        "PIZZA",
                        R.drawable.iconkbc));
        categoryList.add(
                new Category(
                        4,
                        "GRILL",
                        R.drawable.iconlbc));
        categoryList.add(
                new Category(
                        5,
                        "SWEETS",
                        R.drawable.iconmbc));


        //creating recyclerview adapter
        CategoryAdapter adapter = new CategoryAdapter(this, categoryList);

        //setting adapter to recyclerview
        recyclerView.setAdapter(adapter);
    }

    @Override
    public void onBackPressed() {
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // handle item selection
        switch (item.getItemId()) {
//            case R.id.menu_search: {
//                startActivity(new Intent(this, SearchActivity.class));
//                return true;
//            }
            case R.id.logout: {
                showConfirmDialog();
                return true;
            }
            default:
                return super.onOptionsItemSelected(item);
        }
    }

    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        // Handle navigation view item clicks here.
        int id = item.getItemId();

        if (id == R.id.nav_aboutUs) {
            //startActivity(new Intent(this, NearestFriendsActivity.class));
        } else if (id == R.id.nav_share) {
            //startActivity(new Intent(this, RequestsActivity.class));
        } else if (id == R.id.nav_setting) {
            //startActivity(new Intent(this, HowToUse.class));

        } else if (id == R.id.logout) {
            showConfirmDialog();
        }
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }

    private void showConfirmDialog() {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setMessage(R.string.dialog_msg);
        builder.setPositiveButton(R.string.yes, new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialog, int id) {
                //connector.LogOut();
            }
        });
        builder.setNegativeButton(R.string.cancel, null);
        AlertDialog alertDialog = builder.create();
        alertDialog.show();
    }
}