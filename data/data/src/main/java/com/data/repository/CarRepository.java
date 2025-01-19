package com.data.repository;

import com.data.entity.CarEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CarRepository extends JpaRepository<CarEntity, Long> {
    List<CarEntity> findByNameContainingIgnoreCaseOrModelContainingIgnoreCaseOrBrandContainingIgnoreCase(
            String name, String model, String brand);

    List<CarEntity> findByCategoryAndBrandAndPriceBetween(String category, String brand, Double minPrice, Double maxPrice);

    List<CarEntity> findByCategoryAndBrand(String category, String brand);

    List<CarEntity> findTop5ByOrderByPriceDesc();

    List<CarEntity> findTop5ByOrderByIdDesc();

    @Query("SELECT DISTINCT c.category FROM CarEntity c")
    List<String> findDistinctCategories();

    @Query("SELECT DISTINCT c.brand FROM CarEntity c")
    List<String> findDistinctBrands();

    @Query("SELECT SUM(c.price) FROM CarEntity c WHERE c.isSold = true")
    Double sumCarRevenue();

    List<CarEntity> findByVendorId(Long vendorId); // Fetch cars added by a specific vendor

}

