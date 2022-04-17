package com.mixas.mappers;

import com.mixas.dao.entities.ProductEntity;
import com.mixas.domain.model.Product;
import org.mapstruct.Mapper;
import org.springframework.stereotype.Component;

import java.util.List;

@Mapper
@Component
public interface ProductMapper {

    Product entityToModel(ProductEntity productEntity);

    List<Product> entityToModel(List<ProductEntity> productEntityList);

    ProductEntity modelToEntity(Product product);
}
