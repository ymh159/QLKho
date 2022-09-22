package repositories.impl;

import com.hazelcast.logging.ILogger;
import connectdb.MongoDBClient;
import constants.Constants;
import entities.ProductEntity;
import io.vertx.core.Future;
import io.vertx.core.Vertx;
import io.vertx.core.json.Json;
import io.vertx.core.json.JsonObject;
import io.vertx.ext.mongo.MongoClient;
import java.util.List;
import org.bson.types.ObjectId;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import repositories.ProductRepository;

public class ProductRepositoryImpl implements ProductRepository {

  private static final Logger LOGGER = LoggerFactory.getLogger(ProductRepositoryImpl.class);
  private static MongoClient mongoClient;

  public ProductRepositoryImpl(Vertx vertx) {
    MongoDBClient mongoDBClient = new MongoDBClient();
    mongoClient = mongoDBClient.client(vertx);
  }

  @Override
  public Future<List<ProductEntity>> getALl() {
    Future<List<ProductEntity>> future = Future.future();
    mongoClient.find(Constants.PRODUCTS, new JsonObject(), res -> {
      List<JsonObject> data = res.result();
      List<ProductEntity> entity;

      if (res.succeeded()) {
        entity = data.stream().map(item ->
            item.mapTo(ProductEntity.class)
        ).toList();
        LOGGER.info("getAllProduct:{}", entity);
        future.complete(entity);
      } else {
        LOGGER.error("getAllProduct fail");
        future.fail(res.cause());
      }
    });
    return future;
  }

  @Override
  public Future<ProductEntity> findById(String id) {
    Future<ProductEntity> future = Future.future();

    mongoClient.findOne(Constants.PRODUCTS, new JsonObject().put("id", id), null, res -> {
      JsonObject json = res.result();
      ProductEntity entity;

      if (res.succeeded()) {
        entity = json.mapTo(ProductEntity.class);
        future.complete();
        LOGGER.info("findById:{}, result: {}",id, entity);
      } else {
        future.fail(res.cause());
        LOGGER.error("findById:{} fail", id);
      }
    });
    return future;
  }

  @Override
  public Future<String> insertProduct(ProductEntity entity) {
    Future<String> future = Future.future();
    JsonObject query = JsonObject.mapFrom(entity);

    entity.set_id(ObjectId.get().toHexString());

    mongoClient.insert(Constants.PRODUCTS, query,result -> {
      if(result.succeeded()){
        future.complete(entity.getProductID());
        LOGGER.info("Insert Product success: {}", entity.getProductID());
      }else {
        future.cause();
        LOGGER.info("Insert Product fail: {}", query);
      }
    });
    return future;
  }

  @Override
  public Future<String> deleteProduct(String id) {
    Future<String> future = Future.future();

    mongoClient.findOneAndDelete(Constants.PRODUCTS, new JsonObject().put("productID", id),  res -> {
      if(res.succeeded()){
        LOGGER.info("delete success");
      }else {
        LOGGER.info("delete fail");
      }
    }) ;
    return null;
  }
}
