package com.mike.givemewingzz.mapsclusterdemo.base;

import android.os.Build;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.view.Gravity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.mike.givemewingzz.mapsclusterdemo.R;
import com.mike.givemewingzz.mapsclusterdemo.service.OttoHelper;
import com.mike.givemewingzz.mapsclusterdemo.ui.ClusterMapFragment;
import com.mike.givemewingzz.mapsclusterdemo.ui.MapListFragment;
import com.oguzdev.circularfloatingactionmenu.library.FloatingActionButton;
import com.oguzdev.circularfloatingactionmenu.library.FloatingActionMenu;
import com.oguzdev.circularfloatingactionmenu.library.SubActionButton;
import com.transitionseverywhere.Fade;
import com.transitionseverywhere.Slide;
import com.transitionseverywhere.TransitionManager;
import com.transitionseverywhere.TransitionSet;

import butterknife.BindView;
import butterknife.ButterKnife;

public class BaseActivity extends AppCompatActivity implements View.OnClickListener {

    private static final String TAG = BaseActivity.class.getSimpleName();

    private Fragment mapFragment;
    private Fragment listFragment;

    private ClusterMapFragment clusterMapFragment;
    private FloatingActionMenu actionMenu;

    public static final String IN_LIST_TAG = "IN_LIST_TAG";
    public static final String IN_MAP_TAG = "IN_MAP_TAG";
    public static final String IN_SEARCH_TAG = "IN_SEARCH_TAG";
    public static final String MAIN_ACTION_TAG = "MAIN_ACTION_TAG";

    @BindView(R.id.mapFragment)
    ViewGroup mapBaseHolder;

    @BindView(R.id.listFragment)
    ViewGroup listBaseHolder;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_base);

        ButterKnife.bind(this);

        // Setup floating action button
        setupFloatingActionButton();
        // End //

        clusterMapFragment = ClusterMapFragment.newInstance();
        FragmentTransaction clusterT = getSupportFragmentManager().beginTransaction();
        clusterT.replace(R.id.mapFragment, clusterMapFragment).commit();

        // Create list instance
        listFragment = MapListFragment.newInstance();
        FragmentTransaction listFragmentTransaction = getSupportFragmentManager().beginTransaction();
        listFragmentTransaction.replace(R.id.listFragment, listFragment).commit();

        setSlideAnimation(mapBaseHolder);
        setFadeAnimation(mapBaseHolder);

        // Hide List Fragment by default
        getSupportFragmentManager().beginTransaction().hide(listFragment).commit();

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        // Toggle visibility for map_abs_layout and list
        if (item.getItemId() == R.id.maps_item) {

            setSlideAnimation(mapBaseHolder);
            setFadeAnimation(mapBaseHolder);

            getSupportFragmentManager().beginTransaction().show(clusterMapFragment).commit();
            getSupportFragmentManager().beginTransaction().hide(listFragment).commit();

        } else {

            setSlideAnimation(listBaseHolder);
            setFadeAnimation(listBaseHolder);

            getSupportFragmentManager().beginTransaction().hide(clusterMapFragment).commit();
            getSupportFragmentManager().beginTransaction().show(listFragment).commit();
        }

        return super.onOptionsItemSelected(item);
    }

    @Override
    protected void onStop() {
        super.onStop();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
    }

    @Override
    protected void onPause() {
        // UnRegister Otto
        OttoHelper.unregister(this);
        super.onPause();
    }

    @Override
    protected void onResume() {
        // Register Otto
        OttoHelper.register(this);
        super.onResume();
    }

    private void setSlideAnimation(ViewGroup container) {

        if (Build.VERSION.SDK_INT >= 21) {

            // Shared element transitions
            // getWindow().setSharedElementExitTransition(TransitionInflater.from(this).inflateTransition(R.transition.shared_transition));
            // End //

            // Trans //
            // Transition for Landing page when it slides in.
            TransitionManager.beginDelayedTransition(container,
                    new TransitionSet()
                            .addTransition(new Slide(Gravity.END).setDuration(500)));
            // Trans //

        } else {

            TransitionManager.beginDelayedTransition(container,
                    new TransitionSet()
                            .addTransition(new Slide(Gravity.END).setDuration(500)));

        }

    }

    private void setFadeAnimation(ViewGroup container) {

        if (Build.VERSION.SDK_INT >= 21) {

            // Trans //
            // Transition for Landing page when it slides in.
            TransitionManager.beginDelayedTransition(container,
                    new TransitionSet()
                            .addTransition(new Fade().setDuration(1000)));
            // Trans //

        } else {

            TransitionManager.beginDelayedTransition(container,
                    new TransitionSet()
                            .addTransition(new Fade().setDuration(1000)));

        }

    }

    private void setupFloatingActionButton() {

        ImageView imageView = new ImageView(this);
        imageView.setBackgroundResource(R.drawable.ic_settings_black_48dp);
        imageView.setTag(MAIN_ACTION_TAG);
        imageView.setOnClickListener(this);

        FloatingActionButton actionButton = new FloatingActionButton.Builder(this).setContentView(imageView).build();

        ImageView mapsView = new ImageView(this);
        mapsView.setBackgroundResource(R.drawable.common_google_signin_btn_icon_light);

        ImageView arrangeInGrid = new ImageView(this);
        arrangeInGrid.setBackgroundResource(R.drawable.ic_menu_list);

        ImageView searchImage = new ImageView(this);
        searchImage.setBackgroundResource(android.R.drawable.ic_search_category_default);

        SubActionButton.Builder itemBuilder = new SubActionButton.Builder(this);

        SubActionButton inMap = itemBuilder.setContentView(mapsView).build();
        SubActionButton inList = itemBuilder.setContentView(arrangeInGrid).build();
        SubActionButton inSearch = itemBuilder.setContentView(searchImage).build();

        inMap.setPadding(4, 4, 4, 4);
        inList.setPadding(4, 4, 4, 4);
        inSearch.setPadding(4, 4, 4, 4);

        inMap.setTag(IN_MAP_TAG);
        inList.setTag(IN_LIST_TAG);
        inSearch.setTag(IN_SEARCH_TAG);

        inMap.setOnClickListener(this);
        inList.setOnClickListener(this);
        inSearch.setOnClickListener(this);

        actionMenu = new FloatingActionMenu.Builder(this)
                .addSubActionView(inMap)
                .addSubActionView(inList)
                .addSubActionView(inSearch)
                .attachTo(actionButton)
                .build();

        // End of setup

    }

    @Override
    public void onClick(View v) {

        if (v.getTag().equals(IN_LIST_TAG)) {

            setSlideAnimation(mapBaseHolder);
            setFadeAnimation(mapBaseHolder);

            if (actionMenu.isOpen()) {
                actionMenu.close(true);
            }

            getSupportFragmentManager().beginTransaction().hide(clusterMapFragment).commit();
            getSupportFragmentManager().beginTransaction().show(listFragment).commit();

        } else if (v.getTag().equals(IN_MAP_TAG)) {

            setSlideAnimation(listBaseHolder);
            setFadeAnimation(listBaseHolder);

            if (actionMenu.isOpen()) {
                actionMenu.close(true);
            }

            getSupportFragmentManager().beginTransaction().show(clusterMapFragment).commit();
            getSupportFragmentManager().beginTransaction().hide(listFragment).commit();
        } else if (v.getTag().equals(IN_SEARCH_TAG)) {
            // Todo : open search tab and create request
        }
    }
}
