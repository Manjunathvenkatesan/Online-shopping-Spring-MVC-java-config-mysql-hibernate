package com.touhid.onlineshop.dao;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.touhid.onlineshop.model.Product;

@Repository

public interface ProductDao extends CrudRepository<Product, Long>,PagingAndSortingRepository<Product, Long>{

	List<Product> findAllProductsByproductCategory(String productCategory);
	
	Page<Product> findAllProductByproductCategory(String productCategory,Pageable pageable);
	
	
	@Query("SELECT t FROM Product t WHERE t.productCategory = :category AND t.productModel LIKE %:searchTerm%  OR  t.productCategory = :category AND t.productBrand LIKE %:searchTerm%")
	Page<Product> findAllProductByBrandOrModel(@Param("searchTerm")String searchTerm,@Param("category")String category,Pageable pageable);
	
	
	@Query("SELECT t FROM Product t WHERE t.productCategory = :searchTerm OR t.productModel LIKE %:searchTerm%  OR   t.productBrand LIKE %:searchTerm%")
	Page<Product> findAllProductByBrandOrModelorCategory(@Param("searchTerm")String searchTerm,Pageable pageable);
}
