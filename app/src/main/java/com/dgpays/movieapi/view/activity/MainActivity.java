package com.dgpays.movieapi.view.activity;

import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageButton;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.SearchView;
import androidx.navigation.NavController;
import androidx.navigation.NavOptions;
import androidx.navigation.Navigation;
import androidx.navigation.fragment.NavHostFragment;

import com.dgpays.movieapi.R;
import com.dgpays.movieapi.cammon.helper.Instance;
import com.dgpays.movieapi.view.callback.MovieUiController;

public class MainActivity extends AppCompatActivity implements MovieUiController {

    public static int selectedFragmentIndex;
    NavController controller;
    NavOptions navOptions;
    NavHostFragment navHostFragment;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        controller = Navigation.findNavController(this, R.id.nav_host_fragment);
        navOptions = new NavOptions.Builder()
                .setLaunchSingleTop(true)
                .setPopUpTo(controller.getGraph().getStartDestination(), false)
                .build();
        navHostFragment = (NavHostFragment) getSupportFragmentManager().findFragmentById(R.id.nav_host_fragment);
        NavController navController = Navigation.findNavController(this, R.id.nav_host_fragment);

    }


    @Override
    public void onChangeFragment(int fragmentIndex, int movieId) {
        manageUi(fragmentIndex, movieId);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.toolbar_menu, menu);
        MenuItem searchBar = menu.findItem(R.id.action_search);
        SearchView searchView = (SearchView) searchBar.getActionView();
        searchView.setQueryHint("Type here to search");
        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                Bundle searchItem = new Bundle();
                searchItem.putString("searchItem", query);
                controller.navigate(R.id.navMovieSearch, searchItem);
                return false;
            }

            @Override
            public boolean onQueryTextChange(String newText) {
                return false;
            }
        });
        MenuItem homePageButton = menu.findItem(R.id.action_home);
        ImageButton imageButton = (ImageButton) homePageButton.getActionView();
        imageButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                controller.navigate(R.id.navHome);

            }
        });
        return super.onCreateOptionsMenu(menu);
    }

    private void manageUi(int fragmentIndex, int movieId) {
        getSupportActionBar().setTitle("");
        selectedFragmentIndex = fragmentIndex;
        if (fragmentIndex == Instance.THIS.CONSTANT.FRAGMENT_MOVIE_DETAIL) {
            Bundle bundle = new Bundle();
            bundle.putInt("message", movieId);
            controller.navigate(R.id.navMovieDetail, bundle);
        }
    }
}
