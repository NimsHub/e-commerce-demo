package com.nimshub.softwarearchitecturedemo.favorites;

import lombok.Builder;
import lombok.Data;

import java.util.UUID;

@Data
@Builder
public class FavoritesUpdateRequest {
    private UUID userId;
    private UUID productId;
}
