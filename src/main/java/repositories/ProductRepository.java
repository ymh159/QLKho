package repositories;

import entities.ProductEntity;
import io.vertx.core.Future;
import io.vertx.core.Vertx;
import java.util.List;

public interface ProductRepository {

  Future<List<ProductEntity>> getALl();

  Future<ProductEntity> findById(String id);

  Future<String> insertProduct(ProductEntity entity);

  Future<String> deleteProduct(String id);

}
