package com.myRemax.security.controller;

import com.myRemax.security.JwtAuthenticationRequest;
import com.myRemax.security.JwtAuthenticationResponse;
import com.myRemax.security.util.JwtTokenUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
//import org.springframework.mobile.device.Device;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * 
 * <b>Project/Module:</b> jwt-base-server<br/>
 * 
 * <b>Package:</b> ar.com.jwt.base.server.com.myRemax.security.controller<br/>
 * 
 * <b>Class:</b> AuthenticationRestController<br/>
 * 
 * <b>Description:</b> Authentication REST controller.<br/>
 * 
 * <b>Creation Date:</b> May 23, 2016<br/>
 * 
 * @author Juan P. Holder (juanholder@gmail.com) <br/>
 * <br/>
 */
@RestController()
@RequestMapping(value = "/api/security")
@SuppressWarnings("rawtypes")
public class AuthenticationRestController {

	/*
	 * The token header name to be extracted.
	 */
	//@Value("${jwt.authorisation.header}")
	private String tokenHeader = "Authorization";

	/*
	 * The spring com.myRemax.security authentication manager.
	 */
	@Autowired
	private AuthenticationManager authenticationManager;

	/*
	 * The token util.
	 */
	@Autowired
	private JwtTokenUtil jwtTokenUtil;

	/*
	 * The user details service.
	 */
	@Autowired
	private UserDetailsService userDetailsService;

	public AuthenticationRestController() {
		this.tokenHeader = "Authorization";
	}

	/**
	 * Returns an authentication token.
	 * 
	 //* @param authenticationRequest
	 *            for user/pass extraction.
//	 * @param device
	 *            of the request source.
	 * @return the new token.
	 * @throws AuthenticationException
	 *             if any error occurs.
	 */
	@RequestMapping(value = "/auth", method = RequestMethod.POST)
	public ResponseEntity createAuthenticationToken(@RequestBody JwtAuthenticationRequest authenticationRequest
													/*,Device device*/) {
		HttpStatus httpStatus = HttpStatus.OK;
		//String token = "";
		String token = null;
		try {
			// Perform security
			final Authentication authentication = authenticationManager
					.authenticate(new UsernamePasswordAuthenticationToken(authenticationRequest.getUsername(),
							authenticationRequest.getPassword()));
			SecurityContextHolder.getContext().setAuthentication(authentication);

			// Reload password post-security so we can generate token
			final UserDetails userDetails = userDetailsService.loadUserByUsername(authenticationRequest.getUsername());
			token = jwtTokenUtil.generateToken(userDetails/*, device*/);
		} catch (Exception ex) {
			System.out.println("There was a problem while authenticating an user!");
			System.out.println(ex.getMessage());
			return ResponseEntity.badRequest().body("Authentication failed");
		}
		// Return the token
		return ResponseEntity.ok(new JwtAuthenticationResponse(token));
	}

	/**
	 * Refresh a token.
	 * 
	 * @param request
	 *            to be analised to extract the token.
	 * @return the new token.
	 */
	@RequestMapping(value = "/refresh", method = RequestMethod.GET)
	public ResponseEntity refreshAndGetAuthenticationToken(HttpServletRequest request) {
		String refreshedToken = null;
		try {
			String token = request.getHeader(tokenHeader);
			refreshedToken = jwtTokenUtil.refreshToken(token);
		} catch (Exception ex){
			System.out.println("There was a problem while refreshing a token!");
			System.out.println(ex.getMessage());
			return ResponseEntity.badRequest().body("refreshing token failed");
		}
		return ResponseEntity.ok(new JwtAuthenticationResponse(refreshedToken));

	}

}
