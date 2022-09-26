package QLTonKho.repositories.impl;

import QLTonKho.connectdb.MongoDBClient;
import QLTonKho.constants.Constants;
import QLTonKho.entities.ProductEntity;
import io.vertx.core.Future;
import io.vertx.core.Vertx;
import io.vertx.core.json.JsonObject;
import io.vertx.ext.mongo.MongoClient;
import java.util.List;
import org.bson.types.ObjectId;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import QLTonKho.repositories.ProductRepository;

public class ProductRepositoryImpl implements ProductRepository {

  private static final Logger LOGGER = LoggerFactory.getLogger(ProductRepositoryImpl.class);
  private static MongoClient mongoClient;

  public ProductRepositoryImpl(Vertx vertx) {
    MongoDBClient mongoDBClient = new MongoDBClient();
    mongoClient = mongoDBClient.client(vertx);
  }

  @Override
  public Future<List<ProductEntity>> getAllProduct() {
    Future<List<ProductEntity>> future = Future.future();
    mongoClient.find(Constants.COLLECTION_PRODUCTS, new JsonObject(), res -> {
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
  public Future<ProductEntity> findProductById(String id) {
    Future<ProductEntity> future = Future.future();
    mongoClient.findOne(Constants.COLLECTION_PRODUCTS, new JsonObject().put(Constants._ID, id), null, res -> {
      JsonObject json = res.result();
      ProductEntity entity;

      if (res.succeeded()) {
        if(json != null){
          entity = json.mapTo(ProductEntity.class);
          LOGGER.info("findById:{} success, result: {}",id, entity);
          future.complete(entity);
        }else {
          LOGGER.error("findById: Id {} not found",id);
          //future.fail(new Exception("Find by id not found"));
          future.fail(new Exception("Find by id not found"));
        }
      } else {
        LOGGER.error("findById:{} fail", id);
        future.fail(res.cause());
      }
    });
    return future;
  }

  @Override
  public Future<Void> insertProduct(ProductEntity entity) {
    Future<Void> future = Future.future();
    entity.setId(ObjectId.get().toHexString());
    JsonObject query = JsonObject.mapFrom(entity);

    mongoClient.insert(Constants.COLLECTION_PRODUCTS, query,res -> {
      if(res.succeeded()){
        future.complete();
        LOGGER.info("Insert Product success: {}", entity.getId());
      }else {
        future.fail(res.cause());
        LOGGER.info("Insert Product fail: {}", query);
      }
    });
    return future;
  }

  @Override
  public Future<ProductEntity> updateProduct(String id,ProductEntity entity) {
    Future<ProductEntity> future = Future.future();
    JsonObject query = new JsonObject().put(Constants._ID, id);
    JsonObject jsonUpdate = new JsonObject().put(Constants.DOCUMENT_SET, JsonObject.mapFrom(entity));

    mongoClient.updateCollection(Constants.COLLECTION_PRODUCTS, query, jsonUpdate,res -> {
      if(res.succeeded()){
        mongoClient.findOne(Constants.COLLECTION_PRODUCTS,query, null,resAfter ->{
          if (resAfter.succeeded()) {
            future.complete(resAfter.result().mapTo(ProductEntity.class));
          }else {
            future.fail(res.cause());
          }
        });
        LOGGER.info("Update Product success: {}", jsonUpdate);
      }else {
        future.fail(res.cause());
        LOGGER.info("Update Product fail: {}", jsonUpdate);
      }
    });
    return future;
  }

  @Override
  public Future<Void> deleteProduct(String id) {
    Future<Void> future = Future.future();
    mongoClient.findOneAndDelete(Constants.COLLECTION_PRODUCTS, new JsonObject().put(Constants._ID, id),  res -> {
      if(res.succeeded()){
        future.complete();
        LOGGER.info("Delete product success id: {}", id);
      }else {
        future.fail(res.cause());
        LOGGER.info("Delete product fail id: {}", id);
      }
    }) ;
    return future;
  }
}
