package com.touhid.onlineshop.serviceimpl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.touhid.onlineshop.dao.ProductDao;
import com.touhid.onlineshop.model.Product;
import com.touhid.onlineshop.service.ProductService;

@Service

public class ProductServiceImpl implements ProductService {

	private static final int PAGE_ELEMENT_SIZE_ADMIN=12;
	
	private static final int PAGE_ELEMENT_SIZE_CUSTOMER=12;
	
	@Autowired
	private ProductDao productDao;
	
	@Transactional()
	@Override
	public void addProduct(Product product) {
		
		productDao.save(product);
		
	}

	
	@Transactional(readOnly = true)
	@Override
	public Product getProductById(long productId) {
		
		return productDao.findOne(productId);
	}
	@Transactional()
	@Override
	public void updateProduct(Product product) {
		
		productDao.save(product);
		
	}
	@Transactional()
	@Override
	public void deleteProduct(long productId) {
		
		productDao.delete(productId);
		
	}
	@Transactional(readOnly = true)
	@Override
	public List<Product> getAllProductByCategory(String category) {
	 
		return productDao.findAllProductsByproductCategory(category);
	}
	@Transactional(readOnly = true)
	@Override
	public List<Product> getAllProduct() {
		
		return (List<Product>) productDao.findAll();
	}

	@Transactional(readOnly = true)
	@Override
	public Page<Product> getAllProduct(Integer pageNumber) {
		
		
		PageRequest pageRequest=new PageRequest(pageNumber-1, PAGE_ELEMENT_SIZE_ADMIN);
		
		return productDao.findAll(pageRequest);
	}
	
	@Transactional(readOnly = true)
	@Override
	public Page<Product> getAllProductByCategory(Integer pageNumber,String category) {
		
		Pageable pageable=createPageRequest(pageNumber-1,PAGE_ELEMENT_SIZE_CUSTOMER);
		
		
		
		return productDao.findAllProductByproductCategory(category,pageable);
	}
	
	
	private Pageable createPageRequest(int pageNumber,int size) {
	    return new PageRequest(pageNumber,size);
	}


	@Transactional(readOnly = true)
	@Override
	public Page<Product> getAllProductByBrandOrModel(Integer pageNumber, String searchTerm,String category) {
		Pageable pageable=createPageRequest(pageNumber-1, PAGE_ELEMENT_SIZE_CUSTOMER);
		return productDao.findAllProductByBrandOrModel(searchTerm, category, pageable);
		
		
	}


	@Transactional(readOnly = true)
	@Override
	public Page<Product> getAllProductByBrandOrModelOrCategory(Integer pageNumber, String searchTerm) {
		
		Pageable pageable=createPageRequest(pageNumber-1, PAGE_ELEMENT_SIZE_ADMIN);
		
		
		return productDao.findAllProductByBrandOrModelorCategory(searchTerm, pageable);
	}

	
}
