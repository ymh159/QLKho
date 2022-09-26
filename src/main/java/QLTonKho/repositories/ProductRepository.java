package QLTonKho.repositories;

import QLTonKho.entities.ProductEntity;
import io.vertx.core.Future;
import java.util.List;

public interface ProductRepository {
  Future<List<ProductEntity>> getAllProduct();
  Future<ProductEntity> findProductById(String id);
  Future<Void> insertProduct(ProductEntity entity);
  Future<ProductEntity> updateProduct(String id, ProductEntity entity);
  Future<Void> deleteProduct(String id);
}
