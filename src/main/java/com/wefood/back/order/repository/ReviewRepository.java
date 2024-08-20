package com.wefood.back.order.repository;

import com.wefood.back.order.dto.response.ReviewGetResponse;
import com.wefood.back.order.entity.Review;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ReviewRepository extends JpaRepository<Review, Long> {

    List<ReviewGetResponse> findByUserId(Long userId);

    ReviewGetResponse queryById(Long id);

    List<ReviewGetResponse> queryByProductId(Long orderId);

}
