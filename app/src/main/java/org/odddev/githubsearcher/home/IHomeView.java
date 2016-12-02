package org.odddev.githubsearcher.home;

import org.odddev.githubsearcher.core.layers.view.IView;
import org.odddev.githubsearcher.home.repo.Repo;

import java.util.List;

/**
 * @author kenrube
 * @since 02.12.16
 */

interface IHomeView extends IView {

    void showKeyword(String keyword);

    void showRepos(List<Repo> repos);

    void showError(String error);
}
