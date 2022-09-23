package services.impl;

import entities.ProductEntity;
import io.vertx.core.Future;
import io.vertx.core.Vertx;
import java.util.ArrayList;
import java.util.List;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import repositories.ProductRepository;
import repositories.impl.ProductRepositoryImpl;
import services.ProductService;
import utils.SetFutureUtils;

public class ProductServiceImpl implements ProductService {

  public final ProductRepository productRepository;
  private static final Logger LOGGER = LoggerFactory.getLogger(ProductServiceImpl.class);
  public ProductServiceImpl(Vertx vertx) {
    productRepository = new ProductRepositoryImpl(vertx);
  }

  @Override
  public Future<List<ProductEntity>> getProduct(String id) {
    Future<List<ProductEntity>> future = Future.future();

    // Nếu truyền vào param id thì findById
    if (id != null && !id.isEmpty()) {
      productRepository.findProductById(id).setHandler(res -> {
        if (res.succeeded()) {
          List<ProductEntity> listProduct = new ArrayList<>();
          listProduct.add(res.result());
          future.complete(listProduct);
        } else {
          future.fail(new Exception("Find by id not found"));
        }
      });
    } else {
      productRepository.getAllProduct().setHandler(res -> {
        if (res.succeeded()) {
          List<ProductEntity> listProduct = res.result();
          future.complete(listProduct);
        } else {
          future.fail(res.cause());
        }
      });
    }
    return future;
  }

  @Override
  public Future<Void> insertProduct(ProductEntity entity) {
    Future<Void> future = Future.future();
    productRepository.insertProduct(entity).setHandler(res -> {
      if (res.succeeded()) {
        future.complete();
      } else {
        future.fail(res.cause());
      }
    });
    return future;
  }

  @Override
  public Future<ProductEntity> updateProduct(String id, ProductEntity entity) {
    Future<ProductEntity> future = Future.future();
    SetFutureUtils setFuture = new SetFutureUtils();
    productRepository.updateProduct(id, entity).setHandler(res -> {
      if (res.succeeded()) {
        future.complete(entity);
      } else {
        future.fail(res.cause());
      }
    });

    return future;
  }

  @Override
  public Future<Void> deleteProduct(String id) {
    Future<Void> future = Future.future();
    productRepository.deleteProduct(id).setHandler(res -> {
      if (res.succeeded()) {
        future.complete();
      } else {
        future.fail(res.cause());
      }
    });
    return future;
  }
}
