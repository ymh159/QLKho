package QLTonKho.repositories.impl;

import QLTonKho.connectdb.MongoDBClient;
import QLTonKho.constants.Constants;
import QLTonKho.entities.ProductTypeEntity;
import io.vertx.core.Future;
import io.vertx.core.Vertx;
import io.vertx.core.json.JsonObject;
import io.vertx.ext.mongo.MongoClient;
import java.util.List;
import org.bson.types.ObjectId;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import QLTonKho.repositories.ProductTypeRepository;

public class ProductTypeRepositoryImpl implements ProductTypeRepository {
  private static final Logger LOGGER = LoggerFactory.getLogger(ProductRepositoryImpl.class);
  private static MongoClient mongoClient;

  public ProductTypeRepositoryImpl(Vertx vertx) {
    MongoDBClient mongoDBClient = new MongoDBClient();
    mongoClient = mongoDBClient.client(vertx);
  }

  @Override
  public Future<List<ProductTypeEntity>> getALlProductType() {
    Future<List<ProductTypeEntity>> future = Future.future();
    mongoClient.find(Constants.COLLECTION_PRODUCTS_TYPE, new JsonObject(), res -> {
      List<JsonObject> data = res.result();
      List<ProductTypeEntity> entity;
      if (res.succeeded()) {
        entity = data.stream().map(item ->
            item.mapTo(ProductTypeEntity.class)
        ).toList();
        LOGGER.info("getAllProductType:{}", entity);
        future.complete(entity);
      } else {
        LOGGER.error("getAllProductType fail");
        future.fail(res.cause());
      }
    });
    return future;
  }

  @Override
  public Future<ProductTypeEntity> findProductTypeById(String id) {
    Future<ProductTypeEntity> future = Future.future();
    mongoClient.findOne(Constants.COLLECTION_PRODUCTS_TYPE, new JsonObject().put(Constants._ID, id), null, res -> {
      JsonObject json = res.result();
      ProductTypeEntity entity;
      if (res.succeeded()) {
        if(json != null){
          entity = json.mapTo(ProductTypeEntity.class);
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
  public Future<Void> insertProductType(ProductTypeEntity entity) {
    Future<Void> future = Future.future();
    entity.setId(ObjectId.get().toHexString());
    JsonObject query = JsonObject.mapFrom(entity);
    mongoClient.insert(Constants.COLLECTION_PRODUCTS_TYPE, query,res -> {
      if(res.succeeded()){
        future.complete();
        LOGGER.info("Insert Product Type success: {}", entity.getId());
      }else {
        future.fail(res.cause());
        LOGGER.info("Insert Product Type fail: {}", query);
      }
    });
    return future;
  }

  @Override
  public Future<ProductTypeEntity> updateProductType(String id, ProductTypeEntity entity) {
    Future<ProductTypeEntity> future = Future.future();
    JsonObject query = new JsonObject().put(Constants._ID, id);
    JsonObject jsonUpdate = new JsonObject().put(Constants.DOCUMENT_SET, JsonObject.mapFrom(entity));

    mongoClient.updateCollection(Constants.COLLECTION_PRODUCTS_TYPE, query, jsonUpdate,res -> {
      if(res.succeeded()){
        mongoClient.findOne(Constants.COLLECTION_PRODUCTS_TYPE,query, null,resAfter ->{
          if (resAfter.succeeded()) {
            future.complete(resAfter.result().mapTo(ProductTypeEntity.class));
          }else {
            future.fail(res.cause());
          }
        });
        LOGGER.info("Update ProductType success: {}", jsonUpdate);
      }else {
        future.fail(res.cause());
        LOGGER.info("Update ProductType fail: {}", jsonUpdate);
      }
    });
    return future;
  }

  @Override
  public Future<Void> deleteProductType(String id) {
    Future<Void> future = Future.future();
    mongoClient.findOneAndDelete(Constants.COLLECTION_PRODUCTS_TYPE, new JsonObject().put(Constants._ID, id),  res -> {
      if(res.succeeded()){
        future.complete();
        LOGGER.info("Delete productType success id: {}", id);
      }else {
        future.fail(res.cause());
        LOGGER.info("Delete productType fail id: {}", id);
      }
    }) ;
    return future;
  }
}
