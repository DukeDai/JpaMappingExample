package com.dukedai.jpamapping.domain.repository;

import com.dukedai.jpamapping.domain.entity.ProductEntity;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * Created by duke on 2019/1/20
 */
public interface ProductRepository extends JpaRepository<ProductEntity, Long> {
}
