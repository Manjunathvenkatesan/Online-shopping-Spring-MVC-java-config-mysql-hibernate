package com.touhid.onlineshop.model;

import java.io.Serializable;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Transient;
import javax.validation.constraints.NotNull;

import org.hibernate.validator.constraints.NotEmpty;
import org.hibernate.validator.constraints.Range;
import org.springframework.web.multipart.MultipartFile;

import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
public class Product implements Serializable{

	
	private static final long serialVersionUID = 10L;
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private long productId;
	@NotEmpty
	private String productName;
	@NotEmpty
	private String productBrand;
	@NotEmpty
	private String productModel;
	
	@Range(min=0)
	@NotNull
	private Double productPrice;
	
	@Range(min=0)
	@NotNull
	private Integer unitInStock;
	private String productCategory;
	@NotEmpty
	private String productDescription;
	
	
	private String productStatus;
	
	@Range(min=0)
	@NotNull
	private  Double discount;
	@Transient
	private MultipartFile productImage;
	
	@Transient
	private Map<String, String> categoryList;
	
	
	@OneToMany(mappedBy="product",cascade=CascadeType.ALL,fetch=FetchType.EAGER)
	@JsonIgnore
	private List<CartItem> cartItems;
	
	
	
	public Map<String, String> getCategoryList() {
		return categoryList;
	}


	public List<CartItem> getCartItems() {
		return cartItems;
	}


	public void setCartItems(List<CartItem> cartItems) {
		this.cartItems = cartItems;
	}


	public Product(String productName, String productBrand, String productModel, double productPrice, int unitInStock,
			String productCategory, String productDescription, String productStatus, double discount,
			MultipartFile productImage) {
		super();
		this.productName = productName;
		this.productBrand = productBrand;
		this.productModel = productModel;
		this.productPrice = productPrice;
		this.unitInStock = unitInStock;
		this.productCategory = productCategory;
		this.productDescription = productDescription;
		this.productStatus = productStatus;
		this.discount = discount;
		this.productImage = productImage;
	}
	
	
	public Product() {
		
		
	    categoryList=new HashMap<>();
		categoryList.put("Laptop", "Laptop");
		categoryList.put("Mobile", "Mobile");
		categoryList.put("Camera", "Camera");
		categoryList.put("TV", "TV");
		categoryList.put("Refrigerator", "Refrigerator");
		categoryList.put("Tablet", "Tablet");
		categoryList.put("Micro Oven", "MicroOven");
		categoryList.put("DVD Player", "DVDPlayer");
		categoryList.put("Fan", "Fan");
		categoryList.put("Printer", "Printer");
		categoryList.put("Desktop", "Desktop");
		categoryList.put("Washing Machine", "Washing Machine");
		categoryList.put("ipad", "ipad");
		categoryList.put("Game console", "Game console");
		categoryList.put("Router", "Router");
	}


	public long getProductId() {
		return productId;
	}


	public void setProductId(long productId) {
		this.productId = productId;
	}


	public String getProductName() {
		return productName;
	}


	public void setProductName(String productName) {
		this.productName = productName;
	}


	public String getProductBrand() {
		return productBrand;
	}


	public void setProductBrand(String productBrand) {
		this.productBrand = productBrand;
	}


	public String getProductModel() {
		return productModel;
	}


	public void setProductModel(String productModel) {
		this.productModel = productModel;
	}


	


	

	public String getProductCategory() {
		return productCategory;
	}


	public void setProductCategory(String productCategory) {
		this.productCategory = productCategory;
	}


	public String getProductDescription() {
		return productDescription;
	}


	public void setProductDescription(String productDescription) {
		this.productDescription = productDescription;
	}


	public String getProductStatus() {
		return productStatus;
	}


	public void setProductStatus(String productStatus) {
		this.productStatus = productStatus;
	}


	


	public Double getProductPrice() {
		return productPrice;
	}


	public void setProductPrice(Double productPrice) {
		this.productPrice = productPrice;
	}


	public Integer getUnitInStock() {
		return unitInStock;
	}


	public void setUnitInStock(Integer unitInStock) {
		this.unitInStock = unitInStock;
	}


	public Double getDiscount() {
		return discount;
	}


	public void setDiscount(Double discount) {
		this.discount = discount;
	}


	public MultipartFile getProductImage() {
		return productImage;
	}


	public void setProductImage(MultipartFile productImage) {
		this.productImage = productImage;
	}


	


	

	
	
	
	
	
}
