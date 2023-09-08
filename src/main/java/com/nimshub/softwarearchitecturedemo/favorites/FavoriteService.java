package com.nimshub.softwarearchitecturedemo.favorites;

import com.nimshub.softwarearchitecturedemo.cart.Cart;
import com.nimshub.softwarearchitecturedemo.cart.CartUpdateRequest;
import com.nimshub.softwarearchitecturedemo.exceptions.ResourceNotFoundException;
import com.nimshub.softwarearchitecturedemo.product.Product;
import com.nimshub.softwarearchitecturedemo.product.ProductRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @Author Nirmala : 08:September:2023
 * This service implements all methods required to handle the business logic of Favorite
 */
@Service
@RequiredArgsConstructor
public class FavoriteService {
    private final FavoriteRepository favoriteRepository;
    private final ProductRepository productRepository;

    public void addToFavorites(FavoritesUpdateRequest request){

        Favorite favorite = favoriteRepository.findByUserId(request.getUserId())
                .orElseThrow(()-> new ResourceNotFoundException("Favorites not found not found for user [%s]"
                        .formatted(request.getUserId())));

        List<Product> productList = favorite.getProductList();
        Product product = productRepository.findByProductId(request.getProductId())
                .orElseThrow(()-> new ResourceNotFoundException("Product not found"));

        productList.add(product);
        favorite.setProductList(productList);
    }

    public void removeFromFavorites(FavoritesUpdateRequest request){
        Favorite favorite = favoriteRepository.findByUserId(request.getUserId())
                .orElseThrow(()-> new ResourceNotFoundException("Cart not found for user [%s]"
                        .formatted(request.getUserId())));

        List<Product> productList = favorite.getProductList();
        Product product = productRepository.findByProductId(request.getProductId())
                .orElseThrow(()-> new ResourceNotFoundException("Product not found"));
        productList.remove(product);
        favorite.setProductList(productList);
    }
}