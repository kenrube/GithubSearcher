package org.odddev.githubsearcher.home;

import org.odddev.githubsearcher.home.repo.Repo;

import java.util.List;

import rx.Observable;

/**
 * @author kenrube
 * @since 02.12.16
 */

public interface IHomeProvider {

    Observable<List<Repo>> getRepos(String keyword);
}
