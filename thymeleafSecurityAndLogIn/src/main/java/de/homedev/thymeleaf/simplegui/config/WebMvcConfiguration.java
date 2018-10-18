
package de.homedev.thymeleaf.simplegui.config;

import java.util.Locale;
import java.util.concurrent.TimeUnit;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.support.ResourceBundleMessageSource;
import org.springframework.http.CacheControl;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
import org.springframework.web.servlet.i18n.CookieLocaleResolver;
import org.springframework.web.servlet.i18n.LocaleChangeInterceptor;
import org.springframework.web.servlet.resource.AbstractResourceResolver;
import org.springframework.web.servlet.resource.ResourceUrlEncodingFilter;
import org.springframework.web.servlet.resource.VersionResourceResolver;

@Configuration
public class WebMvcConfiguration implements WebMvcConfigurer {

	private static final int MAX_CACHE_AGE = 365;

	public WebMvcConfiguration() {
		super();
	}

	@Bean
	public ResourceBundleMessageSource messageSource() {
		final ResourceBundleMessageSource messageSource = new ResourceBundleMessageSource();

		messageSource.setBasename("i18n/Lang");
		messageSource.setDefaultEncoding("UTF-8");
		messageSource.setFallbackToSystemLocale(false);

		return messageSource;
	}

	@Bean
	public LocaleChangeInterceptor localeChangeInterceptor() {
		final LocaleChangeInterceptor localeChangeInterceptor = new LocaleChangeInterceptor();
		localeChangeInterceptor.setParamName("lang");
		return localeChangeInterceptor;
	}

	@Bean(name = "localeResolver")
	public CookieLocaleResolver localeCookieResolver() {
		final CookieLocaleResolver localeResolver = new CookieLocaleResolver();
		localeResolver.setDefaultLocale(Locale.GERMAN);
		localeResolver.setCookieName("lang");
		return localeResolver;
	}

	@Override
	public void addInterceptors(final InterceptorRegistry registry) {
		registry.addInterceptor(localeChangeInterceptor());
	}

	@Override
	public void addResourceHandlers(final ResourceHandlerRegistry registry) {

		final VersionResourceResolver resourceResolver = new VersionResourceResolver().addContentVersionStrategy("/**");
		addHandler(registry, resourceResolver, "/css/**", "classpath:/resources/css/");
		addHandler(registry, resourceResolver, "/fonts/**", "classpath:/resources/fonts/");
		addHandler(registry, resourceResolver, "/images/**", "classpath:/resources/images/");
		addHandler(registry, resourceResolver, "/js/**", "classpath:/resources/js/");
		addHandler(registry, resourceResolver, "/webjars/handlebars/**", "classpath:/META-INF/resources/webjars/handlebars/");
		addHandler(registry, resourceResolver, "/webjars/jquery/**", "classpath:/META-INF/resources/webjars/jquery/");
		addHandler(registry, resourceResolver, "/webjars/jquery-ui/**", "classpath:/META-INF/resources/webjars/jquery-ui/");
		addHandler(registry, resourceResolver, "/webjars/chartjs/**", "classpath:/META-INF/resources/webjars/chartjs/");

	}

	private void addHandler(final ResourceHandlerRegistry registry, final AbstractResourceResolver resourceResolver, final String resourceHandler,
			final String... resourceLocations) {
		registry.addResourceHandler(resourceHandler).addResourceLocations(resourceLocations)
				.setCacheControl(CacheControl.maxAge(MAX_CACHE_AGE, TimeUnit.DAYS).cachePublic()).resourceChain(true).addResolver(resourceResolver);
	}

	@Bean
	public ResourceUrlEncodingFilter resourceUrlEncodingFilter() {
		return new ResourceUrlEncodingFilter();
	}

}
