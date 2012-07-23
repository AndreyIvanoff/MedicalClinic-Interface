package ru.hospital.injection;

import com.google.inject.AbstractModule;
import com.google.inject.Guice;
import com.google.inject.Injector;
import com.google.inject.name.Names;
import com.google.inject.servlet.GuiceServletContextListener;
import com.google.inject.servlet.ServletScopes;
import com.sun.jersey.api.core.PackagesResourceConfig;
import com.sun.jersey.api.json.JSONConfiguration;
import com.sun.jersey.guice.JerseyServletModule;
import com.sun.jersey.guice.spi.container.servlet.GuiceContainer;
import java.util.HashMap;
import java.util.Map;
import java.util.logging.Logger;
import ru.hospital.filter.InjectInfo;
import ru.hospital.filter.LoginFilter;

public class GuiceConfig extends GuiceServletContextListener {

	private static final Logger LOG = Logger.getLogger(GuiceConfig.class.getName());

	/**
	 * Модуль для инициализации Jersey.
	 */
	public static class JerseyModule extends JerseyServletModule {
		@Override
		protected void configureServlets() {
			Map<String, String> params = new HashMap<String, String>();

			params.put(PackagesResourceConfig.PROPERTY_PACKAGES, "ru.hospital.api");
			params.put(JSONConfiguration.FEATURE_POJO_MAPPING, "true");

			//все REST лежат в пакете api и все запросы к ним проходят через наш фильтр
			serve("/api/*").with(GuiceContainer.class, params);
			filter("/api/*").through(LoginFilter.class);
		}
	}

	public static class HelpersModule extends AbstractModule {
		@Override
		protected void configure() {
			bind(InjectInfo.class).annotatedWith(Names.named("info")).toProvider(LoginFilter.class).in(ServletScopes.REQUEST);
		}
	}


	@Override
	public Injector getInjector() {
		try {
			return Guice.createInjector(new JerseyModule(), new HelpersModule());
		} finally {
			LOG.info("Guice initialization complete.");
		}
	}
}