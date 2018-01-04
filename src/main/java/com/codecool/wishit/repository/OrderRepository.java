package com.codecool.wishit.repository;

import com.codecool.wishit.model.ProductOrder;
import com.codecool.wishit.model.Status;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface OrderRepository extends JpaRepository<ProductOrder,Long> {

    ProductOrder findByUserIdAndStatus(Long userId, Status status);
    List<ProductOrder> findProductOrderByUserIdAndStatus(Long userId, Status status);


}
