package org.odddev.githubsearcher.core.network;

import org.odddev.githubsearcher.home.repo.ReposResponse;

import retrofit2.http.GET;
import retrofit2.http.Query;
import rx.Single;

/**
 * @author kenrube
 * @since 01.12.16
 */

public interface IServerApi {

    @GET("/search/repositories")
    Single<ReposResponse> getRepos(@Query("q") String keyword);
}
