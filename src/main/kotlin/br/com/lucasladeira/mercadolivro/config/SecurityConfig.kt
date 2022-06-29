package br.com.lucasladeira.mercadolivro.config

import br.com.lucasladeira.mercadolivro.enums.Role
import br.com.lucasladeira.mercadolivro.repositories.CustomerRepository
import br.com.lucasladeira.mercadolivro.security.AuthenticationFilter
import br.com.lucasladeira.mercadolivro.security.AuthorizationFilter
import br.com.lucasladeira.mercadolivro.security.CustomAuthenticationEntryPoint
import br.com.lucasladeira.mercadolivro.security.JwtUtil
import br.com.lucasladeira.mercadolivro.services.UserDetailsCustomService
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.http.HttpMethod
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity
import org.springframework.security.config.annotation.web.builders.HttpSecurity
import org.springframework.security.config.annotation.web.builders.WebSecurity
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter
import org.springframework.security.config.http.SessionCreationPolicy
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder
import org.springframework.web.cors.CorsConfiguration
import org.springframework.web.cors.CorsConfigurationSource
import org.springframework.web.cors.reactive.UrlBasedCorsConfigurationSource
import org.springframework.web.filter.CorsFilter

@Configuration
@EnableWebSecurity
@EnableGlobalMethodSecurity(prePostEnabled = true)
class SecurityConfig(
    private val userDetailsCustomService: UserDetailsCustomService,
    private val customerRepository: CustomerRepository,
    private val jwtUtil: JwtUtil,
    private val customEntryPoint: CustomAuthenticationEntryPoint
): WebSecurityConfigurerAdapter() {

    @Bean
    fun bCryptPasswordEncoder(): BCryptPasswordEncoder{
        return BCryptPasswordEncoder()
    }

    private val PUBLIC_MATCHERS = arrayOf(
        "/h2-console/**"
    )

    private val PUBLIC_POST_MATCHERS = arrayOf(
        "/customers"
    )

    private val CUSTOMERS_MATCHERS = arrayOf(
        "/customers"
    )

    private val ADMIN_MATCHERS = arrayOf(
        "/admin/**"
    )

    override fun configure(http: HttpSecurity){
        http
            .headers().frameOptions().disable() //h2 configuration
            .and().authorizeHttpRequests()
                .antMatchers(*PUBLIC_MATCHERS).permitAll()
                .antMatchers(HttpMethod.POST, *PUBLIC_POST_MATCHERS).permitAll()
                .antMatchers(*ADMIN_MATCHERS).hasAuthority(Role.ADMIN.description)
                .anyRequest().authenticated()
            .and().cors().and().csrf().disable() //desabilitando CORS e CSRF
            .sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS) //desabilitando armazenamento de sessao

        http.addFilter(AuthenticationFilter(authenticationManager(), customerRepository, jwtUtil))
        http.addFilter(AuthorizationFilter(authenticationManager(), userDetailsCustomService, jwtUtil))

        http.exceptionHandling().authenticationEntryPoint(customEntryPoint)
    }

    override fun configure(auth: AuthenticationManagerBuilder){
        auth.userDetailsService(userDetailsCustomService)
            .passwordEncoder(bCryptPasswordEncoder())
    }

    /*
    @Bean
    fun corsConfig(): CorsFilter{
        val source = UrlBasedCorsConfigurationSource()
        val config = CorsConfiguration()
        config.allowCredentials = true
        config.addAllowedOrigin("*")
        config.addAllowedHeader("*")
        config.addAllowedMethod("*")
        source.registerCorsConfiguration("/**", config)
        return CorsFilter(source as CorsConfigurationSource)
    }
    */
     */

    override fun configure(web: WebSecurity?) {
        web!!
            .ignoring().antMatchers("/v2/api-docs",
                "/configuration/ui",
                "/swagger-resources/**",
                "/configuration/**",
                "/swagger-ui.html",
                "/webjars/**",
                "/swagger-ui/**",
                "/v3/api-docs/**")
    }

}