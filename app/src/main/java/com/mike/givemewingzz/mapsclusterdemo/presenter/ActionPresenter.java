package com.mike.givemewingzz.mapsclusterdemo.presenter;

public interface ActionPresenter {

    void onItemClicked(final int position);

    void onResultsFetch();

    void onResume();

    void onDestroy();

}
