package com.data.repository;

import com.data.entity.CarEntity;
import com.data.entity.VendorEntity;
import com.data.pojo.response.CarBasicDTO;
import com.data.pojo.response.CarDTO;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CarRepository extends JpaRepository<CarEntity, Long>, JpaSpecificationExecutor<CarEntity> {

    List<CarEntity> findByStatus(String status);

    @Query("SELECT SUM(c.salePrice) FROM CarEntity c WHERE c.status = 'SOLD'")
    Double getTotalRevenue();

    @Query("SELECT c.model FROM CarEntity c WHERE c.status = 'SOLD' GROUP BY c.model ORDER BY COUNT(c.id) DESC LIMIT 1")
    String getMostSoldCar();

    void deleteAllByVendor(VendorEntity vendor); // Custom method to delete all cars of a vendor

    @Query("SELECT c FROM CarEntity c " +
            "WHERE LOWER(c.model) LIKE LOWER(CONCAT('%', :keyword, '%')) " +
            "OR LOWER(c.make) LIKE LOWER(CONCAT('%', :keyword, '%')) " +
            "OR LOWER(c.title) LIKE LOWER(CONCAT('%', :keyword, '%'))")
    List<CarEntity> searchByKeyword(@Param("keyword") String keyword);


    @Query("SELECT new com.data.pojo.response.CarDTO(c.id, c.title, c.make, c.model, c.type, c.year, c.condition, c.stockNumber, c.vinNumber, c.regularPrice, c.salePrice, c.requestPrice) FROM CarEntity c")
    List<CarDTO> findAllBasicDetails();

    /**
     * Search cars by matching name, model, or brand (case-insensitive).
     */
    List<CarEntity> findByTitleContainingIgnoreCaseOrModelContainingIgnoreCaseOrMakeContainingIgnoreCase(
            String title, String model, String make);

    /**
     * Filter cars by category, brand, and price range.
     */
    List<CarEntity> findByTypeAndMakeAndRegularPriceBetween(String type, String make, Double minPrice, Double maxPrice);

    /**
     * Fetch cars by type and model.
     */
    List<CarEntity> findByTypeAndModel(String type, String model);

    /**
     * Fetch the top 5 most expensive cars.
     */
    List<CarEntity> findTop5ByOrderByRegularPriceDesc();

    /**
     * Fetch the top 5 most recently added cars.
     */
    List<CarEntity> findTop5ByOrderByIdDesc();

    /**
     * Get a list of all distinct categories.
     */
    @Query("SELECT DISTINCT c.type FROM CarEntity c")
    List<String> findDistinctCategories();

    /**
     * Get a list of all distinct brands (make).
     */
    @Query("SELECT DISTINCT c.make FROM CarEntity c")
    List<String> findDistinctBrands();

    /**
     * Calculate the total revenue from sold cars.
     */
//    @Query("SELECT SUM(c.salePrice) FROM CarEntity c WHERE c.isSold = true")
//    Double sumCarRevenue();

    /**
     * Fetch all cars added by a specific vendor.
     */
    List<CarEntity> findByVendorId(Long vendorId);

    /**
     * Search for cars by VIN number.
     */
    List<CarEntity> findByVinNumber(String vinNumber);

    /**
     * Search cars that match both category and condition.
     */
    List<CarEntity> findByTypeAndCondition(String type, String condition);

    /**
     * Fetch cars by their approval status.
     */
//    List<CarEntity> findByIsApproved(boolean isApproved);

    /**
     * Fetch cars based on availability (not sold).
     */
//    List<CarEntity> findByIsSold(boolean isSold);

    // Fetch top 6 cars by type ordered by createTime descending
    List<CarEntity> findTop6ByTypeOrderByCreateTimeDesc(String type);

    // Fetch top 6 cars by make (brand) ordered by createTime descending
    List<CarEntity> findTop6ByMakeOrderByCreateTimeDesc(String make);

    // Fetch top 6 recently added cars ordered by createTime descending
    List<CarEntity> findByOrderByCreateTimeDesc(Pageable pageable);

    // Fetch top 6 trending cars based on wishlist count (assuming WishlistEntity exists)
    @Query("SELECT c FROM CarEntity c LEFT JOIN WishlistEntity w ON c.id = w.car.id " +
            "GROUP BY c.id ORDER BY COUNT(w.id) DESC")
    List<CarEntity> findTrendingCars(Pageable pageable);

    // Fetch top 6 cars regardless (allcars)
   // List<CarEntity> findTop6ByOrderByCreateTimeDesc();

    @Query("SELECT new com.data.pojo.response.CarBasicDTO(" +
            "c.id, c.title, c.make, c.model, c.type, c.year, " +
            "c.condition, c.stockNumber, c.vinNumber, c.regularPrice, c.salePrice, c.requestPrice, " +
            "c.description, c.priceLabel, c.createTime, c.updateTime, " +
            "new com.data.pojo.response.CarMediaDTO(" +
            "CASE WHEN m.photo1 IS NOT NULL THEN CONCAT('/api/cars/media/', c.id, '/photo1') ELSE null END, " +
            "CASE WHEN m.photo2 IS NOT NULL THEN CONCAT('/api/cars/media/', c.id, '/photo2') ELSE null END, " +
            "CASE WHEN m.photo3 IS NOT NULL THEN CONCAT('/api/cars/media/', c.id, '/photo3') ELSE null END, " +
            "CASE WHEN m.photo4 IS NOT NULL THEN CONCAT('/api/cars/media/', c.id, '/photo4') ELSE null END, " +
            "CASE WHEN m.photo5 IS NOT NULL THEN CONCAT('/api/cars/media/', c.id, '/photo5') ELSE null END, " +
            "m.videoUrl, " +
            "CASE WHEN m.vinReport IS NOT NULL THEN CONCAT('/api/cars/media/', c.id, '/vinReport') ELSE null END" +
            ")" +
            ") FROM CarEntity c LEFT JOIN c.media m " +
            "WHERE c.type = :type " +
            "ORDER BY c.createTime DESC")
    List<CarBasicDTO> findByTypeDTO(@Param("type") String type, Pageable pageable);

}
