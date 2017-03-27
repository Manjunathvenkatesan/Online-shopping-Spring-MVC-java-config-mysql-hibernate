package com.touhid.onlineshop.controller;

import java.util.Date;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.authentication.logout.SecurityContextLogoutHandler;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.HandlerExceptionResolver;
import org.springframework.web.servlet.ModelAndView;

import com.touhid.onlineshop.exception.CustomError;
import com.touhid.onlineshop.model.Customer;
import com.touhid.onlineshop.model.CustomerContact;
import com.touhid.onlineshop.service.CustomerContactService;
import com.touhid.onlineshop.service.CustomerService;

@Controller
public class HomeController implements HandlerExceptionResolver{

	
	
	
	@Autowired
	private CustomerService customerService;
	
	@Autowired
	private CustomerContactService customerContactService;
	
   @RequestMapping("/")
	public String homePage(){
		
	  
	    
	   
		return "home";
	}
	
   
   @RequestMapping("/login")
   public String login(
           @RequestParam(value="error", required = false)
           String error,
           @RequestParam(value="logout", required = false)
           String logout,
           Model model){

       if(error != null){
           model.addAttribute("error", "Invalid username and password");
       }

       if (logout !=null){
           model.addAttribute("msg", "You have been logged out successfully");
       }

       return "login";
   }
   
   @RequestMapping(value="/logout", method = RequestMethod.GET)
	public String logoutPage (HttpServletRequest request, HttpServletResponse response) {
		Authentication auth = SecurityContextHolder.getContext().getAuthentication();
		if (auth != null){    
			new SecurityContextLogoutHandler().logout(request, response, auth);
		}
		
		cancelCookie(request, response);
		return "redirect:/login?logout";//You can redirect wherever you want, but generally it's a good practice to show login screen again.
	}
   
   
   
   void cancelCookie(HttpServletRequest request, HttpServletResponse response)
   {
     String cookieName = "remember-me";
     Cookie cookie = new Cookie(cookieName, null);
     cookie.setMaxAge(0);
     cookie.setPath(StringUtils.hasLength(request.getContextPath()) ? request.getContextPath() : "/");
     response.addCookie(cookie);
   }
   
   @RequestMapping("/about")
	public String aboutPage(){
		
		return "about";
	}
   
   
   
   @RequestMapping(value="/contact",method=RequestMethod.GET)
  	public String contact(){
  		
  		return "contact";
  	}
   
   
   @RequestMapping(value="/contact",method=RequestMethod.POST)
 	public String contactPost(@AuthenticationPrincipal User activeUser,@RequestParam("message")String message,Model model){
	   Customer customer = customerService.findCustomerByUsername(activeUser.getUsername());
	   
	   CustomerContact customerContact=new CustomerContact();
	   customerContact.setContactInfo(message);
	   customerContact.setCustomer(customer);
	   customerContact.setDate(new Date());
	   
	   customerContactService.addNewMessage(customerContact);
	   
	   model.addAttribute("msg","Message is sent to the green online shop");
	   
 		return "contact";
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
