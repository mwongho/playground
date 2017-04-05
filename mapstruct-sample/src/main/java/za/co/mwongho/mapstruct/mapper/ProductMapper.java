package za.co.mwongho.mapstruct.mapper;

import org.mapstruct.InheritInverseConfiguration;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

import za.co.mwongho.mapstruct.dto.ProductDto;
import za.co.mwongho.mapstruct.model.Product;

@Mapper
public interface ProductMapper {
	
	ProductMapper INSTANCE = Mappers.getMapper(ProductMapper.class);
	
    @Mapping(source = "id", target = "code")
	ProductDto productToProductDto(Product product);
    
    @InheritInverseConfiguration
	Product productDtoToProduct(ProductDto productDto);

}
