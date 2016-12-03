package org.odddev.githubsearcher.home;

import android.content.Intent;
import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.design.widget.Snackbar;
import android.support.v4.view.MenuItemCompat;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.SearchView;
import android.support.v7.widget.helper.ItemTouchHelper;
import android.text.TextUtils;
import android.view.Menu;
import android.view.MenuItem;

import org.odddev.githubsearcher.R;
import org.odddev.githubsearcher.core.di.Injector;
import org.odddev.githubsearcher.core.state.ListViewState;
import org.odddev.githubsearcher.core.utils.TouchHelper;
import org.odddev.githubsearcher.databinding.HomeActivityBinding;
import org.odddev.githubsearcher.home.repo.Repo;
import org.odddev.githubsearcher.home.repo.ReposAdapter;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

public class HomeActivity extends AppCompatActivity implements IHomeView, TouchHelper.Callback {

    private static final String KEYWORD_KEY = "KEYWORD_KEY";

    private HomeActivityBinding binding;

    private Snackbar connectionErrorSnackbar;

    private SearchView searchView;
    private String searchKeyword;

    @Inject
    HomePresenter presenter;

    @Inject
    ReposAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        Injector.getAppComponent().inject(this);

        binding = DataBindingUtil.setContentView(this, R.layout.home_activity);
        binding.setListViewState(ListViewState.EMPTY);

        if (savedInstanceState != null) {
            searchKeyword = savedInstanceState.getString(KEYWORD_KEY);
        }

        initToolbar();
        initRecyclerView();
    }

    @Override
    protected void onStart() {
        super.onStart();
        presenter.attachView(this);
    }

    @Override
    protected void onStop() {
        presenter.detachView(this);
        super.onStop();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.search, menu);

        MenuItem searchItem = menu.findItem(R.id.action_search);
        searchView = (SearchView) MenuItemCompat.getActionView(searchItem);

        searchView.setOnQueryTextListener(queryTextListener);

        if (!TextUtils.isEmpty(searchKeyword)) {
            searchItem.expandActionView();
            searchView.setQuery(searchKeyword, true);
            searchView.clearFocus();
        }

        return super.onCreateOptionsMenu(menu);
    }

    @Override
    protected void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        outState.putString(KEYWORD_KEY, searchView.getQuery().toString());
    }

    private void initToolbar() {
        setSupportActionBar(binding.toolbar);
        ActionBar actionBar = getSupportActionBar();
        if (actionBar != null) {
            actionBar.setDisplayShowTitleEnabled(true);
            actionBar.setTitle(getString(R.string.app_name));
        }
    }

    private void initRecyclerView() {
        binding.repos.setHasFixedSize(true);
        binding.repos.setLayoutManager(
                new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false));
        binding.repos.setAdapter(adapter);

        ItemTouchHelper.Callback callback = new TouchHelper(this);
        ItemTouchHelper helper = new ItemTouchHelper(callback);
        helper.attachToRecyclerView(binding.repos);
    }

    private void getRepos(String searchKeyword) {
        if (adapter.getItemCount() == 0) {
            binding.setListViewState(ListViewState.LOADING);
        }
        presenter.getRepos(searchKeyword);
    }

    @Override
    public void onMove(int fromPosition, int toPosition) {
        adapter.swapRepos(fromPosition, toPosition);
    }

    @Override
    public void onSwiped(int position) {
        adapter.removeRepo(position);
    }

    @Override
    public void showRepos(List<Repo> repos) {
        binding.setListViewState(ListViewState.FILLED);
        adapter.setRepos(repos);
    }

    @Override
    public void showConnectionError() {
        binding.setListViewState(ListViewState.ERROR);
        connectionErrorSnackbar = Snackbar.make(binding.getRoot(), R.string.error_connection,
                Snackbar.LENGTH_INDEFINITE)
                .setAction(R.string.home_connection_on, v ->
                        startActivity(new Intent(android.provider.Settings.ACTION_WIFI_SETTINGS)));
        connectionErrorSnackbar.show();
    }

    @Override
    public void showConnected() {
        if (connectionErrorSnackbar != null && connectionErrorSnackbar.isShown()) {
            connectionErrorSnackbar.dismiss();
        }

        getRepos(searchKeyword);
    }

    @Override
    public void showIncorrectInputError() {
        binding.setListViewState(ListViewState.ERROR);
        adapter.setRepos(new ArrayList<>());
    }

    @Override
    public void showError(String error) {
        binding.setListViewState(ListViewState.ERROR);
        Snackbar.make(binding.getRoot(), error, Snackbar.LENGTH_LONG).show();
    }

    private SearchView.OnQueryTextListener queryTextListener = new SearchView.OnQueryTextListener() {
        @Override
        public boolean onQueryTextSubmit(String query) {
            return false;
        }

        @Override
        public boolean onQueryTextChange(String newText) {
            searchKeyword = newText;
            getRepos(searchKeyword);
            return true;
        }
    };
}
