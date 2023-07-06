package com.ecommerce.api.services;


import com.ecommerce.api.models.dtos.GradeDto;
import com.ecommerce.api.models.dtos.ReviewDto;

import java.util.Collection;

public interface GradeService {

    Collection<GradeDto> getAllCompleteGrades();

    Collection<GradeDto> getAllPartialGrades(); //Brouillons si des champs sont vides ou null dans le repo alors je push l entite

    Collection<GradeDto> getCompleteGradesByAProduct(Long productId);

    Collection<GradeDto> getCompleteGradesByAUser(Long userId);

    Collection<GradeDto> getPartialGradesByAProduct(Long productId);

    Collection<GradeDto> getPartialGradesByAUser(Long userId);

    ReviewDto getCompleteGradeByAUserOnAProduct(Long userId, Long productId); //faire les diff dans l implementation en verifiant si des elements sont null ou pas

    ReviewDto getPartialGradeByAUserOnAProduct(Long userId, Long productId); //for ReviewEntity: this.repo.findAll() {if (reviewEntity.userId == userId && reviewEntity.productId == productId) {return reviewEntity}}}

    void updateAPartialGrade(GradeDto gradeDto);

    void createACompleteGrade(GradeDto gradeDto);

    void createAPartIalGrade(GradeDto gradeDto);

    void deleteAGrade(Long userId, Long productId);


}
