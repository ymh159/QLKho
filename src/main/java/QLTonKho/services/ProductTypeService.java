package QLTonKho.services;

import QLTonKho.entities.ProductTypeEntity;
import io.vertx.core.Future;
import java.util.List;

public interface ProductTypeService {
  Future<List<ProductTypeEntity>> getProductType(String id);
  Future<Void> insertProductType(ProductTypeEntity entity);
  Future<ProductTypeEntity> updateProductType(String id, ProductTypeEntity entity);
  Future<Void> deleteProductType(String id);
}
