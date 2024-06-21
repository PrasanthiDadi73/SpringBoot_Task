package task.services;

import java.util.Set;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import task.entities.User;
import task.repositories.UserRepo;

@Service
public class CustomUserDetailsService implements UserDetailsService{

	private UserRepo userRepo;
	
	 @Autowired
	    public CustomUserDetailsService(UserRepo userRepo) {
	        this.userRepo = userRepo;
	    }

	 @Override
	    public UserDetails loadUserByUsername(String usernameOrEmail) throws UsernameNotFoundException {
	          User user = userRepo.findByUsernameOrEmail(usernameOrEmail, usernameOrEmail)
	                 .orElseThrow(() ->
	                         new UsernameNotFoundException("User not found with username or email: "+ usernameOrEmail));

	        Set<GrantedAuthority> authorities = user
	                .getRoles()
	                .stream()
	                .map((role) -> new SimpleGrantedAuthority(role.getName())).collect(Collectors.toSet());

	        return new org.springframework.security.core.userdetails.User(user.getEmail(),
	                user.getPassword(),
	                authorities);
	    }
	
//	@Override
//    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
//        Optional<User> person=userRepo.findByUsername(username);
//        return person.map(PersonDetails::new).orElseThrow(()->new UsernameNotFoundException("Invalid username"));
//    }
}
