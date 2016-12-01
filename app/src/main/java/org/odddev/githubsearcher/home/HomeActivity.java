package org.odddev.githubsearcher.home;

import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.design.widget.Snackbar;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;

import org.odddev.githubsearcher.R;
import org.odddev.githubsearcher.core.di.Injector;
import org.odddev.githubsearcher.databinding.HomeActivityBinding;
import org.odddev.githubsearcher.home.repo.Repo;
import org.odddev.githubsearcher.home.repo.ReposAdapter;

import java.util.List;

import javax.inject.Inject;

public class HomeActivity extends AppCompatActivity implements IHomeView {

    private HomeActivityBinding binding;

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
    public void showRepos(List<Repo> repos) {
        adapter.setRepos(repos);
    }

    @Override
    public void showError(String error) {
        Snackbar.make(binding.getRoot(), error, Snackbar.LENGTH_INDEFINITE).show();
    }
}
