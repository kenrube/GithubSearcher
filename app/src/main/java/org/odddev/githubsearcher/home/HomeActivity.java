package org.odddev.githubsearcher.home;

import android.databinding.DataBindingUtil;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import org.odddev.githubsearcher.R;
import org.odddev.githubsearcher.core.di.Injector;
import org.odddev.githubsearcher.databinding.HomeActivityBinding;
import org.odddev.githubsearcher.home.repo.Repo;

import java.util.List;

import javax.inject.Inject;

public class HomeActivity extends AppCompatActivity implements IHomeView {

    private HomeActivityBinding binding;

    @Inject
    protected HomePresenter presenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        Injector.getAppComponent().inject(this);

        binding = DataBindingUtil.setContentView(this, R.layout.home_activity);
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
    public void showRepos(List<Repo> repos) {
        //
    }

    @Override
    public void showError(String error) {
        //
    }
}
