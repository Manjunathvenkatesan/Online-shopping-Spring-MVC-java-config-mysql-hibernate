package com.touhid.onlineshop.controller;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.HandlerExceptionResolver;
import org.springframework.web.servlet.ModelAndView;

import com.touhid.onlineshop.exception.CustomError;
import com.touhid.onlineshop.model.Authorities;
import com.touhid.onlineshop.model.BillingAddress;
import com.touhid.onlineshop.model.Cart;
import com.touhid.onlineshop.model.Customer;
import com.touhid.onlineshop.model.ShippingAddress;
import com.touhid.onlineshop.model.Users;
import com.touhid.onlineshop.service.AuthoritiesService;
import com.touhid.onlineshop.service.BillingAddressService;
import com.touhid.onlineshop.service.CartService;
import com.touhid.onlineshop.service.CustomerService;
import com.touhid.onlineshop.service.ShippingAddressService;
import com.touhid.onlineshop.service.UsersService;
import com.touhid.onlineshop.validator.PasswordValidator;


@Controller
public class RegisterController implements HandlerExceptionResolver{

	@Autowired
	private CustomerService customerService;
	@Autowired
	private BillingAddressService billingAddressService;
	
	@Autowired
	private ShippingAddressService shippingAddressService;
	
	@Autowired
	private UsersService usersService;
	
	@Autowired
	private AuthoritiesService authoritiesService;
	
	@Autowired
	private CartService cartService;
	
	@Autowired
	private PasswordValidator passwordValidator;
	
	@RequestMapping("/register")
	public String registerCustomer(Model model){
		
		
		
		
		Customer customer=new Customer();
		BillingAddress billingAddress=new BillingAddress();
		ShippingAddress shippingAddress=new ShippingAddress();
		customer.setBillingAddress(billingAddress);
		customer.setShippingAddress(shippingAddress);
		
		
		model.addAttribute("customer",customer);
		
		return "registerCustomer";
	}
	
	
	
	@RequestMapping(value="/register",method=RequestMethod.POST)
	public String registerCustomerPost(@Valid@ModelAttribute("customer")Customer customer,BindingResult result, @RequestParam(name="update",required=false) String update,@RequestParam(name="oldUserId",required=false)Long oldUserId ,Model model,HttpServletRequest request){
		
	
		if (result.hasErrors()) {
			return "registerCustomer";
		}
		
		List<Customer> customerList=customerService.getAllCustomers();
		
		for (Customer customer2 : customerList) {
			
			if (customer2.getCustomerEmailAddress().equals(customer.getCustomerEmailAddress())) {
				model.addAttribute("emailMsg","Email already exists");
				return "registerCustomer";
			}
			
			if (customer2.getUsername().equals(customer.getUsername())) {
				model.addAttribute("usernameMsg","Username already exists");
				return "registerCustomer";
			}
		}
		
		//System.out.println("--------------------------update:"+update+" ------------"+oldUserId);
		
		
		
		
		if (update!=null && update.equalsIgnoreCase("update")) {
			
			
			
			Customer oldCustomer=customerService.getCustomerBycustomerId(oldUserId);
			
			customer.getBillingAddress().setBillingAddressId(oldCustomer.getBillingAddress().getBillingAddressId());
			customer.getShippingAddress().setShippingAddressId(oldCustomer.getShippingAddress().getShippingAddressId());
			billingAddressService.addBillingAddress(customer.getBillingAddress());
			shippingAddressService.addShippingAddress(customer.getShippingAddress());
			
			
			String oldUsername=oldCustomer.getUsername();
			
			oldCustomer.setBillingAddress(customer.getBillingAddress());
			oldCustomer.setShippingAddress(customer.getShippingAddress());
			oldCustomer.setEnabled(true);
			oldCustomer.setBillingAddress(customer.getBillingAddress());
			oldCustomer.setShippingAddress(customer.getShippingAddress());
			oldCustomer.setUsername(customer.getUsername());
			oldCustomer.setPassword(customer.getPassword());
			oldCustomer.setCustomerEmailAddress(customer.getCustomerEmailAddress());
			oldCustomer.setCustomerName(customer.getCustomerName());
			oldCustomer.setCustometPhoneNumber(customer.getCustometPhoneNumber());
			
			
			customerService.addCustomer(oldCustomer);
		    
			Users users=usersService.findUserByusername(oldUsername);
			users.setCustomerId(oldUserId);
			users.setEnabled(true);
			users.setPassword(oldCustomer.getPassword());
			users.setUsername(oldCustomer.getUsername());
			
			usersService.addUsers(users);
			
			
			Authorities authorities=authoritiesService.findAuthoritiesByusername(oldUsername);
			authorities.setAuthorityType("ROLE_USER");
			authorities.setUsername(oldCustomer.getUsername());
			
			
			
			authoritiesService.addAuthorities(authorities);
			
			
			Cart cart=oldCustomer.getCart();
			cart.setCustomer(oldCustomer);
			cart.setGrandTotal(0);
			
			cartService.addCart(cart);
			
			oldCustomer.setCart(cart);
			customerService.addCustomer(oldCustomer);
			
			autoLogin(oldCustomer,request);
			
			return "updateCustomerSuccess";
					 
		}
		
		else {
			billingAddressService.addBillingAddress(customer.getBillingAddress());
			shippingAddressService.addShippingAddress(customer.getShippingAddress());
			
			customer.setEnabled(true);
			customerService.addCustomer(customer);
		    
			Users users=new Users();
			users.setCustomerId(customer.getCustomerId());
			users.setEnabled(true);
			users.setPassword(customer.getPassword());
			users.setUsername(customer.getUsername());
			
			usersService.addUsers(users);
			
			
			Authorities authorities=new Authorities();
			authorities.setAuthorityType("ROLE_USER");
			authorities.setUsername(customer.getUsername());
			
			
			
			authoritiesService.addAuthorities(authorities);
			
			
			Cart cart=new Cart();
			cart.setCustomer(customer);
			cart.setGrandTotal(0);
			
			cartService.addCart(cart);
			
			customer.setCart(cart);
			customerService.addCustomer(customer);
			
			
			autoLogin(customer,request);
		}
		
		return "registerCustomerSuccess";
	}



	private void autoLogin(Customer customer,HttpServletRequest request) {
	
		String username=customer.getUsername();
		String password=customer.getPassword();
		
		
		
		// generate session if one doesn't exist
        request.getSession();
        
        
        Authentication authentication=new UsernamePasswordAuthenticationToken(username, password);
        SecurityContextHolder.getContext().setAuthentication(authentication);
         
		
	}
	
	
	
	@RequestMapping("/customer/update")
	public String customerUpdate(){
		
		return "updateCustomer";
	}
	
	
	@RequestMapping(value="/customer/update",method=RequestMethod.POST)
	public String customerUpdatePost(@RequestParam("username")String username,@RequestParam("password")String password,Model model){
		
		Customer customer=customerService.findCustomerByusernameAndpassword(username, password);
		
		if (customer==null) {
			
			return "updateCustomer";
		}
		
		model.addAttribute("customer",customer);
		
		
		model.addAttribute("update","update");
		return "registerCustomer";
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
	
	@InitBinder
	public void initBinder(WebDataBinder binder) {
		binder.addValidators(passwordValidator);
	}
}
