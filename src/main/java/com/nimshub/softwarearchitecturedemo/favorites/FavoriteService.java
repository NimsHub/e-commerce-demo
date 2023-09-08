package com.nimshub.softwarearchitecturedemo.favorites;

public interface FavoriteService {
    void addToFavorites(FavoritesUpdateRequest request);

    void removeFromFavorites(FavoritesUpdateRequest request);
}
