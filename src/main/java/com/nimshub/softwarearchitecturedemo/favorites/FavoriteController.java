package com.nimshub.softwarearchitecturedemo.favorites;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @Author Nirmala : 08:September:2023
 * This controller implements all end points required to handle the requests of Favrioute
 */
@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/Favrioutes")
public class FavoriteController {
    private final FavoriteService favoriteService;
    @PutMapping("add")
    public ResponseEntity<String> addToCart(@RequestBody FavoritesUpdateRequest request){
        favoriteService.addToFavorites(request);
        return new ResponseEntity<>("Successfully added to the favorites", HttpStatus.OK);
    }

    @PutMapping("remove")
    public ResponseEntity<String> removeFromCart(@RequestBody FavoritesUpdateRequest request){
        favoriteService.removeFromFavorites(request);
        return new ResponseEntity<>("Successfully removed from the favorites", HttpStatus.OK);
    }
}