package com.booking.yoya.service.jwt;

import java.util.HashSet;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.DisabledException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.booking.yoya.dao.UserDao;
import com.booking.yoya.model.User;
import com.booking.yoya.model.jwt.JwtRequest;
import com.booking.yoya.model.jwt.JwtResponse;
import com.booking.yoya.util.JwtUtil;

@Service
public class JwtService implements UserDetailsService {

	@Autowired
	private JwtUtil jwtUtil;

	@Autowired
	private UserDao userDao;

	@Autowired
	private AuthenticationManager authenticationManager;

	public JwtResponse createJwtToken(JwtRequest jwtRequest) throws Exception {
		String userName = jwtRequest.getUserName();
		String userPassword = jwtRequest.getUserPassword();
		try {
			authenticate(userName, userPassword);
		} catch (Exception e) {
			String message = "Incorrect username and password";
			return new JwtResponse(null, "", message, false);
		}

		UserDetails userDetails = loadUserByUsername(userName);

		String newGeneratedToken = jwtUtil.generateToken(userDetails);

		User user = userDao.findById(userName).get();
		String message = "LoggedIn successfully";
		return new JwtResponse(user, newGeneratedToken, message, true);
	}

	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {

		User user = userDao.findById(username).get();

		if (user != null) {
			return new org.springframework.security.core.userdetails.User(user.getUserName(), user.getUserPassword(),
					getAuthority(user));

//        Driver driver = driverDao.findByDriverPhonenumber(username).get();
//        System.out.println("driver = "+driver);
//        if (driver != null) {
//            return new org.springframework.security.core.userdetails.User(
//            		driver.getPhoneNumber(),
//            		driver.getPassword(),
//                    getAuthority()
//            );
		} else {
			throw new UsernameNotFoundException("User not found with username: " + username);
		}

	}

	private Set getAuthority(User user) {
		Set<SimpleGrantedAuthority> authorities = new HashSet<>();
		user.getRole().forEach(role -> {
			authorities.add(new SimpleGrantedAuthority("ROLE_" + role.getRoleName()));
		});
		return authorities;
	}

	private Set getAuthority() {
		Set<SimpleGrantedAuthority> authorities = new HashSet<>();

		authorities.add(new SimpleGrantedAuthority("ROLE_DRIVER"));

		return authorities;
	}

	private void authenticate(String userName, String userPassword) throws Exception {
		try {
			authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(userName, userPassword));
		} catch (DisabledException e) {
			throw new Exception("USER_DISABLED", e);
		} catch (BadCredentialsException e) {
			throw new Exception("INVALID_CREDENTIALS", e);
		}
	}
}
