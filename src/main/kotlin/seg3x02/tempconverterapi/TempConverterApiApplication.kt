package seg3x02.tempconverterapi

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication


import org.springframework.context.annotation.Bean
import org.springframework.security.config.annotation.web.builders.HttpSecurity
import org.springframework.security.core.userdetails.User
import org.springframework.security.core.userdetails.UserDetailsService
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder
import org.springframework.security.crypto.password.PasswordEncoder
import org.springframework.security.provisioning.InMemoryUserDetailsManager
import org.springframework.security.web.SecurityFilterChain

import org.springframework.security.core.authority.SimpleGrantedAuthority


@SpringBootApplication
class TempConverterApiApplication{

	@Bean
	fun PasswordEncoder(): PasswordEncoder{
		return BCryptPasswordEncoder()
	}

	@Bean
	fun configureS(http: HttpSecurity): SecurityFilterChain{
		http
			.csrf().disable() 
			.authorizeRequests()
			.anyRequest().authenticated() 
			.and()
			.httpBasic() 
		return http.build()
	}


	@Bean
	fun userPassDetails(PasswordEncoder: PasswordEncoder): UserDetailsService{
		val user1 = User.withUsername("user1")
			.password(PasswordEncoder.encode("pass1"))
			.authorities(SimpleGrantedAuthority("ROLE_USER"))
			.build()


		val user2 = User.withUsername("user2")
			.password(PasswordEncoder.encode("pass2"))
			.authorities(SimpleGrantedAuthority("ROLE_USER"))
			.build()

		return InMemoryUserDetailsManager(user1, user2)
	}




}

fun main(args: Array<String>) {
	runApplication<TempConverterApiApplication>(*args)
}
