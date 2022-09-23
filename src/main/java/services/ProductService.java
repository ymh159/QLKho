package services;

import entities.ProductEntity;
import io.vertx.core.Future;
import java.util.List;

public interface ProductService {
  Future<List<ProductEntity>> getProduct(String id);
  Future<Void> insertProduct(ProductEntity entity);
  Future<ProductEntity> updateProduct(String id, ProductEntity entity);
  Future<Void> deleteProduct(String id);
}
