package org.odddev.githubsearcher.home;

/**
 * @author kenrube
 * @since 04.12.16
 */

public interface IHomeActionsHandler {

    void switchSortOptions();

    void sortBySize();

    void sortByForks();

    void sortByStars();
}
