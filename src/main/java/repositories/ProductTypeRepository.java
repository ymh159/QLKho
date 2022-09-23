package repositories;

import entities.ProductEntity;
import entities.ProductTypeEntity;
import io.vertx.core.Future;
import java.util.List;

public interface ProductTypeRepository {
    Future<List<ProductTypeEntity>> getALlProductType();
    Future<ProductTypeEntity> findProductTypeById(String id);
    Future<Void> insertProductType(ProductTypeEntity entity);
    Future<ProductTypeEntity> updateProductType(String id, ProductTypeEntity entity);
    Future<Void> deleteProductType(String id);

}
