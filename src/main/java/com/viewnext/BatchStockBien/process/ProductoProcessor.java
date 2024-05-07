package com.viewnext.BatchStockBien.process;

import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;

@Component
public class ProductoProcessor {

	@Bean
	public ProductoItemProcessor stockProcessor() {
		return new ProductoItemProcessor();
	}

}
