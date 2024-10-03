package com.example.mockservicebroker.servicebroker;

import java.io.IOException;
import java.io.InputStream;

import com.example.mockservicebroker.ServiceBrokerProps;
import org.yaml.snakeyaml.Yaml;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class CatalogController {

	private final Object catalog;

	public CatalogController(ServiceBrokerProps props) throws IOException {
		Yaml yaml = new Yaml();
		try (InputStream stream = props.catalog().getInputStream()) {
			this.catalog = yaml.load(stream);
		}
	}

	@GetMapping(path = "/v2/catalog")
	public Object catalog() {
		return this.catalog;
	}

}
