package com.touhid.onlineshop.controller;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.HandlerExceptionResolver;
import org.springframework.web.servlet.ModelAndView;

import com.touhid.onlineshop.exception.CustomError;
import com.touhid.onlineshop.model.Product;
import com.touhid.onlineshop.service.ProductService;


@Controller
@RequestMapping("/product")
public class ProductController  implements HandlerExceptionResolver{

	@Autowired
	private ProductService productService;
	
	@RequestMapping("/productList/{productCategory}/{pageNumber}")
	public String getProductByCategory(@PathVariable("pageNumber")int pageNumber,@PathVariable("productCategory")String productCategory,Model model){
		
		
		
		Page<Product> page=productService.getAllProductByCategory(pageNumber, productCategory);
		
		
		List<Product> products=new ArrayList<>();
		
		for (Product product : page) {
			products.add(product);
		}
		
		
		int currentPageNumber=page.getNumber()+1;
		int beginIndex=Math.max(1, currentPageNumber-5);
		int endIndex=Math.min(beginIndex+10, page.getTotalPages());
		
		if (products.size()==0) {
			model.addAttribute("msg","No product to show");
		}
		
		model.addAttribute("products",products);
		model.addAttribute("productCategory",productCategory);
		model.addAttribute("totalPages",page.getTotalPages());
		model.addAttribute("currentPageNumber",currentPageNumber);
		model.addAttribute("beginIndex",beginIndex);
		model.addAttribute("endIndex",endIndex);
		
		
		return "productList";
	}
	
	
	
	
	
	
	
	@RequestMapping(value="/productList/{productCategory}/{pageNumber}",method=RequestMethod.POST)
	public String getProductBySearch(@RequestParam("searchTerm")String searchTerm,@PathVariable("pageNumber")int pageNumber,@PathVariable("productCategory")String productCategory,Model model){
		
		
		
		Page<Product> page=productService.getAllProductByBrandOrModel(pageNumber, searchTerm, productCategory);
		
		List<Product> products=new ArrayList<>();
		
		for (Product product : page) {
			products.add(product);
		}
		
		if (products.size()==0) {
			model.addAttribute("msg","No product to show");
		}
		
		int currentPageNumber=page.getNumber()+1;
		int beginIndex=Math.max(1, currentPageNumber-5);
		int endIndex=Math.min(beginIndex+10, page.getTotalPages());
		
		
		model.addAttribute("products",products);
		model.addAttribute("productCategory",productCategory);
		model.addAttribute("totalPages",page.getTotalPages());
		model.addAttribute("currentPageNumber",currentPageNumber);
		model.addAttribute("beginIndex",beginIndex);
		model.addAttribute("endIndex",endIndex);
		
		
		return "productList";
	}
	
	
	
	
	
	
	@RequestMapping("/productList")
	public String getAllProduct(Model model){
		
		List<Product> products=productService.getAllProduct();
		model.addAttribute("products",products);
		
		
		
		return "productList";
	}
	
	
	@RequestMapping("/viewProduct/{productId}")
	public String ProductDetailPage(@PathVariable("productId")long productId, Model model){
		
		Product product=productService.getProductById(productId);
		
		
		model.addAttribute("product",product);
		model.addAttribute("search","search");
		return "productDetail";
	}
	
	
	
	public ModelAndView resolveException(HttpServletRequest request, HttpServletResponse response, Object handler,
			Exception ex) {
		ModelAndView modelAndView=new ModelAndView();
		CustomError error=new CustomError();
	
		
		error.setMessage("Your request is not valid.Please Enter a valid request.");
		modelAndView.addObject("customError", error);
		modelAndView.setViewName("error_page");
		
		return modelAndView;
	}
}
