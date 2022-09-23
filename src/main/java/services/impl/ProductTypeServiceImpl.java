package services.impl;

import entities.ProductTypeEntity;
import entities.ProductTypeEntity;
import io.vertx.core.Future;
import io.vertx.core.Vertx;
import java.util.ArrayList;
import java.util.List;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import repositories.ProductTypeRepository;
import repositories.impl.ProductTypeRepositoryImpl;
import services.ProductTypeService;

public class ProductTypeServiceImpl implements ProductTypeService {
  public final ProductTypeRepository productTypeRepository;
  private static final Logger LOGGER = LoggerFactory.getLogger(ProductServiceImpl.class);
  public ProductTypeServiceImpl(Vertx vertx) {
    productTypeRepository = new ProductTypeRepositoryImpl(vertx);
  }

  @Override
  public Future<List<ProductTypeEntity>> getProductType(String id) {
    Future<List<ProductTypeEntity>> future = Future.future();

    // Nếu truyền vào param id thì findById
    if (id != null && !id.isEmpty()) {
      productTypeRepository.findProductTypeById(id).setHandler(res -> {
        if (res.succeeded()) {
          List<ProductTypeEntity> listProduct = new ArrayList<>();
          listProduct.add(res.result());
          future.complete(listProduct);
        } else {
          future.fail(new Exception("Find by id not found"));
        }
      });
    } else {
      productTypeRepository.getALlProductType().setHandler(res -> {
        if (res.succeeded()) {
          List<ProductTypeEntity> listProductType = res.result();
          future.complete(listProductType);
        } else {
          future.fail(res.cause());
        }
      });
    }
    return future;
  }

  @Override
  public Future<Void> insertProductType(ProductTypeEntity entity) {
    return null;
  }

  @Override
  public Future<ProductTypeEntity> updateProductType(String id, ProductTypeEntity entity) {
    return null;
  }

  @Override
  public Future<Void> deleteProductType(String id) {
    return null;
  }
}
