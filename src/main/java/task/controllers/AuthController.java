package task.controllers;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import task.dto.LoginDto;
import task.dto.SignUpDto;
import task.entities.Role;
import task.entities.User;
import task.repositories.RoleRepo;
import task.repositories.UserRepo;
import task.services.JwtService;

@RestController
@RequestMapping("/api/auth")
public class AuthController {

	private final AuthenticationManager authenticationManager;
	private final UserRepo userRepo;
	private final RoleRepo roleRepo;
	private final PasswordEncoder passwordEncoder;

	private final JwtService jwtService;

	@Autowired
	public AuthController(AuthenticationManager authenticationManager, UserRepo userRepo, RoleRepo roleRepo,
			PasswordEncoder passwordEncoder, JwtService jwtService) {
		super();
		this.authenticationManager = authenticationManager;
		this.userRepo = userRepo;
		this.roleRepo = roleRepo;
		this.passwordEncoder = passwordEncoder;
		this.jwtService = jwtService;
	}

	@PostMapping("/signup")
	public ResponseEntity<?> registerUser(@RequestBody SignUpDto signUpDto) {

		if (userRepo.existsByUsername(signUpDto.getUsername())) {
			return new ResponseEntity<>("Username is already taken!", HttpStatus.BAD_REQUEST);
		}

		if (userRepo.existsByEmail(signUpDto.getEmail())) {
			return new ResponseEntity<>("Email is already taken!", HttpStatus.BAD_REQUEST);
		}

		User user = new User();
		user.setUsername(signUpDto.getUsername());
		user.setEmail(signUpDto.getEmail());
		user.setPassword(passwordEncoder.encode(signUpDto.getPassword()));

		Set<Role> roles = roleRepo.findByNameIn(signUpDto.getRoles());

		user.setRoles(new HashSet<>(roles));

		userRepo.save(user);

		return new ResponseEntity<>("User registered successfully", HttpStatus.OK);
	}

	@PostMapping("/signin")
	public String authenticateUser(@RequestBody LoginDto loginDto) {
		Authentication authentication = authenticationManager.authenticate(
				new UsernamePasswordAuthenticationToken(loginDto.getUsernameOrEmail(), loginDto.getPassword()));

//			SecurityContextHolder.getContext().setAuthentication(authentication);
//
//			String token = generateToken(loginDto.getUsernameOrEmail());
//
//			return new ResponseEntity<>("Bearer " + token, HttpStatus.OK);
//		} catch (AuthenticationException e) {
//		
//			return new ResponseEntity<>("Invalid username or password", HttpStatus.UNAUTHORIZED);
//		}
//		------------
//		if (authentication.isAuthenticated())
//			return jwtService.generateToken(loginDto.getUsernameOrEmail());
//		else
//			throw new UsernameNotFoundException("Invalid username");
		if (authentication.isAuthenticated()) {
			UserDetails userDetails = (UserDetails) authentication.getPrincipal();
			Set<GrantedAuthority> authorities = new HashSet<>(userDetails.getAuthorities());
			return jwtService.generateToken(loginDto.getUsernameOrEmail(), authorities);
		} else {
			throw new UsernameNotFoundException("Invalid username");
		}
	}
}
