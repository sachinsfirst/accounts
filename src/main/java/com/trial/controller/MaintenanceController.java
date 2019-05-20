package com.trial.controller;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.Random;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.trial.model.User;
import com.trial.service.UserService;


@Controller
@RequestMapping("/")
public class MaintenanceController {
	
	@Autowired
	private UserService userService;

	@GetMapping("/")
	public String signin(Model model,HttpServletRequest request, HttpServletResponse response) {
		try {
			
			model.addAttribute("captcha", processRequest(request, response));
		} catch (ServletException | IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} 
		
		return "signin";
	}
	
	@GetMapping("/logout")
	public String logout(HttpServletRequest request) {
		request.getSession().setAttribute("user", null);
		return "signin";
	}
	
	@PostMapping("/submitlogin")
	public String submitLogin(Model model,
			@RequestParam(value = "userName", required = false, defaultValue = "userName") String userName,
			@RequestParam(value = "password", required = false, defaultValue = "password") String password,
			@RequestParam("captcha") String captcha,
			HttpServletRequest request) {
		model.addAttribute("userName", userName);
		model.addAttribute("password", password);

		User user = userService.findByUsername(userName);
		// UsernamePasswordAuthenticationToken usernamePasswordAuthenticationToken = new
		// UsernamePasswordAuthenticationToken(userDetails, password,
		// userDetails.getAuthorities());
		if (null != user) {
			
			String sessionCaptcha = request.getSession().getAttribute("dns_security_code").toString();
			
			boolean valid = false;
			
			// TODO - display captcha image instead of Text
			if(captcha.equalsIgnoreCase(sessionCaptcha)) {
				valid = true;
			} else {
				model.addAttribute("errormsg", "Invalid captcha !!");
				return "signin";
			}
			
			if (valid) {
				
				if(user.isDisabled()) {
					model.addAttribute("errormsg", "User disabled!! Please contact Admin");
					return "signin";
				}
				
				int loginAttempts = user.getNoOfLoginAttempt();
				if(loginAttempts>=4) {
					user.setDisabled(true);
					userService.saveUser(user);
				}
				
				if(	userService.authenticate(user, password)) {
					request.getSession().setAttribute("user", user);
					return "user/users";
				} else {
					model.addAttribute("errormsg", "Invalid user name or password !!");
					user.setNoOfLoginAttempt(user.getNoOfLoginAttempt()+1);
					userService.saveUser(user);
					return "signin";
				}
			}			
		}
		model.addAttribute("errormsg", "User does not exist in the system !!");
		return "signin";	
	}
	
	private String processRequest(HttpServletRequest request, HttpServletResponse response)
	        throws ServletException, IOException {


	    response.setContentType("image/jpg");
	    /*
	     * Define number characters contains the captcha image, declare global
	     */
	    int iTotalChars = 6;

	    /*
	     * Size image iHeight and iWidth, declare globl
	     */
	    int iHeight = 40;
	    int iWidth = 150;

	    /*
	     * font style
	     */
	    Font fntStyle1 = new Font("Arial", Font.BOLD, 30);
	    Font fntStyle2 = new Font("Verdana", Font.BOLD, 20);

	    /*
	     * Possible random characters in the image
	     */
	    Random randChars = new Random();
	    String sImageCode = (Long.toString(Math.abs(randChars.nextLong()), 36)).substring(0, iTotalChars);

	    /*
	     * BufferedImage is used to create a create new image
	     */
	    /*
	     * TYPE_INT_RGB - does not support transpatency, TYPE_INT_ARGB - support transpatency
	     */
	    BufferedImage biImage = new BufferedImage(iWidth, iHeight, BufferedImage.TYPE_INT_RGB);
	    Graphics2D g2dImage = (Graphics2D) biImage.getGraphics();

	    // Draw background rectangle and noisey filled round rectangles
	    int iCircle = 15;
	    //g2dImage.fillRect(0, 0, iWidth, iHeight);
	    for (int i = 0; i < iCircle; i++) {
	        g2dImage.setColor(new Color(randChars.nextInt(255), randChars.nextInt(255), randChars.nextInt(255)));
	        int iRadius = (int) (Math.random() * iHeight / 2.0);
	        int iX = (int) (Math.random() * iWidth - iRadius);
	        int iY = (int) (Math.random() * iHeight - iRadius);
	        //g2dImage.fillRoundRect(iX, iY, iRadius * 2, iRadius * 2,100,100);
	    }
	    g2dImage.setFont(fntStyle1);
	    for (int i = 0; i < iTotalChars; i++) {
	        g2dImage.setColor(new Color(randChars.nextInt(255), randChars.nextInt(255), randChars.nextInt(255)));
	        if (i % 2 == 0) {
	            g2dImage.drawString(sImageCode.substring(i, i + 1), 25 * i, 24);
	        } else {
	            g2dImage.drawString(sImageCode.substring(i, i + 1), 25 * i, 35);
	        }
	    }

	    /*
	     * create jpeg image and display on the screen
	     */
	   // OutputStream osImage = response.getOutputStream();
	  //  ImageIO.write(biImage, "jpeg", osImage);
	    //osImage.close();

	    /*
	     * Dispose function is used destory an image object
	     */
	    g2dImage.dispose();

	    request.getSession().setAttribute("dns_security_code", sImageCode);
	    //System.out.println("Captcha Page :"+session.getAttribute("dns_security_code"));
	    
	    return sImageCode;

	}


	
}
