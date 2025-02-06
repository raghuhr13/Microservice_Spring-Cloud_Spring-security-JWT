package com.hr.scms.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.gateway.route.RouteLocator;
import org.springframework.cloud.gateway.route.builder.RouteLocatorBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.reactive.EnableWebFluxSecurity;
import org.springframework.security.config.web.server.SecurityWebFiltersOrder;
import org.springframework.security.config.web.server.ServerHttpSecurity;
import org.springframework.security.web.server.SecurityWebFilterChain;

@Configuration
@EnableWebFluxSecurity
public class ScmsAPIGatewayConfig {
	
	@Autowired
	private JwtAuthenticationFilter jwtAuthenticationFilter;

	
	@Bean
	RouteLocator gateWay(RouteLocatorBuilder builder) {
		
		RouteLocator configuredRouterLocator=builder.routes().route(p-> p.path("/get")
				.filters(f-> f.addRequestHeader("My-header", "Manually ").addRequestParameter("Param", "MyValue"))
				.uri("http://httpbin.org:80"))
				.route(p-> p.path("/auth/**").uri("lb://SCMS-authorisation-microservice"))
		        .route(p-> p.path("/SCMS-first-microservice/**").uri("lb://SCMS-first-microservice"))
		        .route(p-> p.path("/SCMS-second-microservice/**").uri("lb://SCMS-second-microservice"))
		        .route(p-> p.path("/SCMS-limit-microservice/**").uri("lb://SCMS-limit-microservice"))
		        .build();
		
		return configuredRouterLocator;
		
	}
	
	@Bean
	SecurityWebFilterChain securityFilterChain(ServerHttpSecurity httpSecurity) throws Exception {
		return httpSecurity.csrf(ServerHttpSecurity.CsrfSpec::disable)
				 .authorizeExchange(exchange -> exchange
						    .pathMatchers("/auth/**").permitAll()
	                        .pathMatchers("/SCMS-first-microservice/**").authenticated() // Secure these routes
	                        .pathMatchers("/SCMS-second-microservice/**").authenticated()
	                        .pathMatchers("/SCMS-limit-microservice/**").authenticated()
	                        .anyExchange().permitAll()) // Allow other routes
				            .httpBasic(Customizer.withDefaults())
				            .addFilterAt(jwtAuthenticationFilter, SecurityWebFiltersOrder.AUTHENTICATION)
				            .build();
	}
 
}
