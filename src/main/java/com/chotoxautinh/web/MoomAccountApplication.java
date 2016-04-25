package com.chotoxautinh.web;

import java.net.URISyntaxException;
import java.util.Collections;

import org.apache.catalina.Context;
import org.apache.jasper.servlet.JasperInitializer;
import org.apache.log4j.Logger;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.context.embedded.tomcat.TomcatContextCustomizer;
import org.springframework.boot.context.embedded.tomcat.TomcatEmbeddedServletContainerFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ImportResource;
import org.springframework.stereotype.Controller;
import org.springframework.web.servlet.view.UrlBasedViewResolver;
import org.springframework.web.servlet.view.tiles3.TilesConfigurer;
import org.springframework.web.servlet.view.tiles3.TilesView;

@Controller
@EnableAutoConfiguration
@ImportResource("classpath:config.xml")
//@Configuration@PropertySource(value = "classpath:elasticsearch.properties")
//@EnableElasticsearchRepositories(basePackages = "com.chotoxautinh.server.repository")
public class MoomAccountApplication {

	private final static Logger logger = Logger.getLogger(MoomAccountApplication.class);

	@Bean 
	public UrlBasedViewResolver tilesViewResolver(){
		UrlBasedViewResolver tilesViewResolver = new UrlBasedViewResolver();
		tilesViewResolver.setViewClass(TilesView.class);
		return tilesViewResolver;
	}

	@Bean
	public TilesConfigurer tilesConfigurer(){ 
		String[] definitions = new String[] {
				"/tiles/definitions.xml"
		};

		TilesConfigurer tilesConfigurer = new TilesConfigurer();
		tilesConfigurer.setDefinitions(definitions);

		return tilesConfigurer;
	}

	@Bean
	public TomcatEmbeddedServletContainerFactory factory() {
		TomcatEmbeddedServletContainerFactory tomcatEmbeddedServletContainerFactory = new TomcatEmbeddedServletContainerFactory();
		tomcatEmbeddedServletContainerFactory.addContextCustomizers(new TomcatContextCustomizer() {
			@Override
			public void customize(Context context) {
				context.addServletContainerInitializer(new JasperInitializer(), Collections.<Class<?>> emptySet());
			}
		});
		return tomcatEmbeddedServletContainerFactory;
	}

	/*@Resource
	private Environment environment;
	@Bean
	public Client client() {
		TransportClient client = new TransportClient();
		TransportAddress address = new InetSocketTransportAddress(environment.getProperty("elasticsearch.host"), Integer.parseInt(environment.getProperty("elasticsearch.port")));
		client.addTransportAddress(address);        
		return client;
	}

	@Bean
	public ElasticsearchOperations elasticsearchTemplate() {
		return new ElasticsearchTemplate(client());
	}*/

	public static void main( String[] args ) throws URISyntaxException {
		SpringApplication.run(MoomAccountApplication.class, args);
	}
}
