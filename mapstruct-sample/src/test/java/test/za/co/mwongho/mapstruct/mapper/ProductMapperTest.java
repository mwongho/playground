package test.za.co.mwongho.mapstruct.mapper;

import static org.junit.Assert.*;
import static org.hamcrest.CoreMatchers.*;
import org.junit.Test;

import za.co.mwongho.mapstruct.dto.ProductDto;
import za.co.mwongho.mapstruct.mapper.ProductMapper;
import za.co.mwongho.mapstruct.model.Product;

public class ProductMapperTest {

	@Test
	public void productToProductDto() {
		final ProductMapper mapper = ProductMapper.INSTANCE;
		Product product = new Product(1, "Product", "Product 1");
		ProductDto productDto = mapper.productToProductDto(product);
		assertThat(product.getId(), is(productDto.getCode()));
	}

	@Test
	public void productDtoToProduct() {
		final ProductMapper mapper = ProductMapper.INSTANCE;
		ProductDto productDto = new ProductDto(1, "Product", "Product 1");
		Product product = mapper.productDtoToProduct(productDto);
		assertThat(productDto.getCode(), is(product.getId()));
	}

}
