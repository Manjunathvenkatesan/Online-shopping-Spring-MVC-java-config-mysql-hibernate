package com.touhid.onlineshop.controller;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.web.bind.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.servlet.HandlerExceptionResolver;
import org.springframework.web.servlet.ModelAndView;

import com.touhid.onlineshop.exception.CustomError;
import com.touhid.onlineshop.model.Cart;
import com.touhid.onlineshop.model.CartItem;
import com.touhid.onlineshop.model.Customer;
import com.touhid.onlineshop.model.Product;
import com.touhid.onlineshop.service.CartItemService;
import com.touhid.onlineshop.service.CartService;
import com.touhid.onlineshop.service.CustomerService;
import com.touhid.onlineshop.service.ProductService;


@Controller
@RequestMapping("/rest/cart")
public class CartItemController  implements HandlerExceptionResolver{

	@Autowired
	CartService cartService;
	
	@Autowired
	private CustomerService customerService;
	
	@Autowired
	ProductService productService;
	
	
	@Autowired
	CartItemService  cartItemService; 
	//GET CART FOR REST SERVICE
	
	@RequestMapping("/{cartId}")
	public @ResponseBody Cart getCartById(@PathVariable("cartId")int cartId){
		
		return cartService.getCartById(cartId);
	}
	
	
	
	
	
	//ADD PRODUCT IN THE CART
	
	@RequestMapping(value="/add/{productId}",method=RequestMethod.PUT)
	@ResponseStatus(value=HttpStatus.NO_CONTENT)
	public void addItem(@PathVariable("productId")int productId,@AuthenticationPrincipal User activeUser){
		
		Customer customer=customerService.findCustomerByUsername(activeUser.getUsername());
		Cart cart=customer.getCart();
		Product product=productService.getProductById(productId);
		
		
		List<CartItem> cartItems=cart.getCartItems(); 
		
		
	
		
		//IF PRODUCT ALREDAY EXIST IN CART THEN INCRESE ITS QUANTITY
	
		for(int i=0;i<cartItems.size();i++){
			
			if (product.getProductId()==cartItems.get(i).getProduct().getProductId()) {
				CartItem cartItem=cartItems.get(i);
				cartItem.setQuantity(cartItem.getQuantity()+1);
				cartItem.setTotalPrice(product.getProductPrice()*cartItem.getQuantity());
				cartItemService.addCartItem(cartItem);
				
				return;
			}
		}
		
		
		//IF PRODUCT IS NEW 
		
		
		CartItem cartItem=new CartItem();
		cartItem.setProduct(product);
		cartItem.setQuantity(1);
		cartItem.setTotalPrice(product.getProductPrice()*cartItem.getQuantity());
		cartItem.setCart(cart);
		cartItemService.addCartItem(cartItem);
		
		
	}
	
	//REMOVE A CARTITEM FROM CART
	
	@RequestMapping(value="/remove/{productId}",method=RequestMethod.PUT)
	@ResponseStatus(value=HttpStatus.OK)
	public void removeItem(@PathVariable("productId") int productId){
		System.out.println("--------------hahah----------------");
		CartItem cartItem=cartItemService.getCartItemByProduct(productId);
		
		System.out.println("C:"+cartItem.getTotalPrice());
		System.out.println(cartItem);
		cartItemService.deleteCartItem(cartItem);
		System.out.println("c:hpoihpoifihihitih-----------");
	}
	
	
	//REMOVE CART/CLEAR CART
	
	@RequestMapping(value="/{cartId}",method=RequestMethod.DELETE)
	@ResponseStatus(value=HttpStatus.OK)
	public void clearCart(@PathVariable(value="cartId")int cartId){
		
		Cart cart=cartService.getCartById(cartId);
		cartItemService.removeAllCartItems(cart);
		
	}

	
	//EXCEPTION HANDLING
	
	@ExceptionHandler(IllegalArgumentException.class)
	@ResponseStatus(value=HttpStatus.INTERNAL_SERVER_ERROR,reason="")
	public void handleClientErrors(Exception e){}
	
	
	@ExceptionHandler(Exception.class)
	@ResponseStatus(value=HttpStatus.BAD_REQUEST,reason="Illegal request,please verify your payload")
	public void handleServerErrors(Exception e){}
	
	
	
	
	
	
	public ModelAndView resolveException(HttpServletRequest request, HttpServletResponse response, Object handler,
			Exception ex) {
		ModelAndView modelAndView=new ModelAndView();
		CustomError error=new CustomError();
	
		if (ex instanceof IllegalArgumentException) {
			
			error.setMessage("Internal server error");
			modelAndView.addObject("customError", error);
			modelAndView.setViewName("error_page");
			return  modelAndView;
			
		}
		error.setMessage("Your request is not valid.Please Enter a valid request.");
		modelAndView.addObject("customError", error);
		modelAndView.setViewName("error_page");
		
		return modelAndView;
	}
}
