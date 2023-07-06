package com.ecommerce.api.services;


import com.ecommerce.api.models.dtos.ReviewDto;

import java.util.Collection;

public interface ReviewService {

    Collection<ReviewDto> getAllCompleteReviews();

    Collection<ReviewDto> getAllPartialReviews(); //Brouillons

    Collection<ReviewDto> getCompleteReviewsByAProduct(Long productId);

    Collection<ReviewDto> getCompleteReviewsByAUser(Long userId);

    Collection<ReviewDto> getPartialReviewsByAProduct(Long productId);

    Collection<ReviewDto> getPartialReviewsByAUser(Long userId);

    ReviewDto getCompleteReviewByAUserOnAProduct(Long userId, Long productId); //faire les diff dans l implementation en verifiant si des elements sont null ou pas

    ReviewDto getPartialReviewByAUserOnAProduct(Long userId, Long productId);

    void updateAReview(ReviewDto ReviewDto);

    void createACompleteReview(ReviewDto ReviewDto);

    void createAPartialReview(ReviewDto ReviewDto);

    void deleteAReview(Long ReviewId);

    Boolean exists(String productName);
}
