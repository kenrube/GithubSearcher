package org.odddev.githubsearcher.home;

import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.design.widget.Snackbar;
import android.support.v4.view.MenuItemCompat;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.SearchView;
import android.text.TextUtils;
import android.view.Menu;
import android.view.MenuItem;

import org.odddev.githubsearcher.R;
import org.odddev.githubsearcher.core.di.Injector;
import org.odddev.githubsearcher.databinding.HomeActivityBinding;
import org.odddev.githubsearcher.home.repo.Repo;
import org.odddev.githubsearcher.home.repo.ReposAdapter;

import java.util.List;

import javax.inject.Inject;

public class HomeActivity extends AppCompatActivity implements IHomeView {

    private HomeActivityBinding binding;

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

        final MenuItem searchItem = menu.findItem(R.id.action_search);
        final SearchView searchView = (SearchView) MenuItemCompat.getActionView(searchItem);

        searchView.setOnQueryTextListener(queryTextListener);

        if (!TextUtils.isEmpty(searchKeyword)) {
            searchItem.expandActionView();
            searchView.setQuery(searchKeyword, true);
            searchView.clearFocus();
        }

        return super.onCreateOptionsMenu(menu);
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
    }

    @Override
    public void showKeyword(String keyword) {
        searchKeyword = keyword;
    }

    @Override
    public void showRepos(List<Repo> repos) {
        adapter.setRepos(repos);
    }

    @Override
    public void showError(String error) {
        Snackbar.make(binding.getRoot(), error, Snackbar.LENGTH_LONG).show();
    }

    private SearchView.OnQueryTextListener queryTextListener = new SearchView.OnQueryTextListener() {
        @Override
        public boolean onQueryTextSubmit(String query) {
            presenter.getRepos(query);
            return true;
        }

        @Override
        public boolean onQueryTextChange(String newText) {
            presenter.getRepos(newText);
            return true;
        }
    };
}
