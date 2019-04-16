package net.playground.casclient;

import org.jasig.cas.client.session.SingleSignOutFilter;
import org.jasig.cas.client.session.SingleSignOutHttpSessionListener;
import org.jasig.cas.client.validation.Cas30ServiceTicketValidator;
import org.jasig.cas.client.validation.TicketValidator;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Primary;
import org.springframework.context.event.EventListener;
import org.springframework.security.cas.ServiceProperties;
import org.springframework.security.cas.authentication.CasAuthenticationProvider;
import org.springframework.security.cas.web.CasAuthenticationEntryPoint;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.web.AuthenticationEntryPoint;
import org.springframework.security.web.authentication.logout.LogoutFilter;
import org.springframework.security.web.authentication.logout.SecurityContextLogoutHandler;

import javax.annotation.PostConstruct;
import javax.servlet.http.HttpSessionEvent;

@SpringBootApplication
public class CasClientApplication {

    @PostConstruct
    public void initSsl(){
        System.setProperty("javax.net.ssl.trustStore", Thread.currentThread().getContextClassLoader().getResource("trustStore.jks").getPath());
        System.setProperty("javax.net.ssl.trustStorePassword", "welcome1");
		/*
		javax.net.ssl.HttpsURLConnection.setDefaultHostnameVerifier(
			(hostname,sslSession) -> {
				if (hostname.equals("localhost")) {
					return true;
				}
				return false;
			});
			*/
    }

    public static void main(String[] args) {
        SpringApplication.run(CasClientApplication.class, args);
    }

    @Bean
    public ServiceProperties serviceProperties() {
        ServiceProperties serviceProperties = new ServiceProperties();
        serviceProperties.setService("http://localhost:9000/cas-client/login/cas");
        serviceProperties.setSendRenew(false);
        return serviceProperties;
    }

    @Bean
    @Primary
    public AuthenticationEntryPoint authenticationEntryPoint(ServiceProperties sP) {
        CasAuthenticationEntryPoint entryPoint = new CasAuthenticationEntryPoint();
        entryPoint.setLoginUrl("https://localhost:6443/cas/login");
        entryPoint.setServiceProperties(sP);
        return entryPoint;
    }

    @Bean
    public TicketValidator ticketValidator() {
        return new Cas30ServiceTicketValidator("https://localhost:6443/cas");
    }

    @Bean
    public CasAuthenticationProvider casAuthenticationProvider() {
        CasAuthenticationProvider provider = new CasAuthenticationProvider();
        provider.setServiceProperties(serviceProperties());
        provider.setTicketValidator(ticketValidator());
        provider.setUserDetailsService(
                s -> new User("admin",
                        "welcome1",
                        true,
                        true,
                        true,
                        true,
                        AuthorityUtils.createAuthorityList("ROLE_ADMIN"))
        );
        provider.setKey("CAS_PROVIDER_LOCALHOST_9000");
        return provider;
    }

    @Bean
    public SecurityContextLogoutHandler securityContextLogoutHandler() {
        return new SecurityContextLogoutHandler();
    }

    @Bean
    public LogoutFilter logoutFilter() {
        LogoutFilter logoutFilter = new LogoutFilter(
                "https://localhost:6443/cas/logout", securityContextLogoutHandler());
        logoutFilter.setFilterProcessesUrl("/logout/cas");
        return logoutFilter;
    }

    @Bean
    public SingleSignOutFilter singleSignOutFilter() {
        SingleSignOutFilter singleSignOutFilter = new SingleSignOutFilter();
        singleSignOutFilter.setCasServerUrlPrefix("https://localhost:6443/cas");
        singleSignOutFilter.setIgnoreInitConfiguration(true);
        return singleSignOutFilter;
    }

    @EventListener
    public SingleSignOutHttpSessionListener singleSignOutHttpSessionListener(HttpSessionEvent event) {
        return new SingleSignOutHttpSessionListener();
    }
}
