package router.handlers;

import constants.Constants;
import entities.ProductEntity;
import io.vertx.core.Vertx;
import io.vertx.core.json.Json;
import io.vertx.core.json.JsonObject;
import io.vertx.ext.web.RoutingContext;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import services.ProductService;
import services.impl.ProductServiceImpl;

public class ApplicationHandlers {

  private static final Logger LOGGER = LoggerFactory.getLogger(ApplicationHandlers.class);
  private ProductService productService;

  public ApplicationHandlers(Vertx vertx) {
    productService = new ProductServiceImpl(vertx);
  }

  public void getProduct(RoutingContext routingContext) {
    String productId = routingContext.request().getParam(Constants.ID);

    productService.getProduct(productId).setHandler(res -> {
      if (res.succeeded()) {
        routingContext.response()
            .putHeader(Constants.CONTENT_TYPE, Constants.CONTENT_VALUE_JSON)
            .end(Json.encodePrettily(res.result()));
      } else {
        routingContext.response()
            .putHeader(Constants.CONTENT_TYPE, Constants.CONTENT_VALUE_JSON)
            .end(Json.encodePrettily(res.cause().getMessage()));
      }
    });
  }

  public void insertProduct(RoutingContext routingContext) {
    routingContext.request().bodyHandler(handler -> {
      JsonObject json = handler.toJsonObject();
      ProductEntity entity = json.mapTo(ProductEntity.class);
      productService.insertProduct(entity).setHandler(res -> {
        if (res.succeeded()) {
          routingContext.response()
              .putHeader(Constants.CONTENT_TYPE, Constants.CONTENT_VALUE_JSON)
              .end(Json.encodePrettily(res.result()));
        } else {
          res.cause().getMessage();
        }
      });
    });
  }

  public void updateProduct(RoutingContext routingContext) {
    routingContext.request().bodyHandler(handler -> {
      JsonObject json = handler.toJsonObject();
      ProductEntity entity = json.mapTo(ProductEntity.class);
      String idProduct = entity.getId();
      productService.updateProduct(idProduct, entity).setHandler(res -> {
        if (res.succeeded()) {
          routingContext.response()
              .putHeader(Constants.CONTENT_TYPE, Constants.CONTENT_VALUE_JSON)
              .end(Json.encodePrettily(res.result()));
        } else {
          res.cause().getMessage();
        }
      });
    });
  }

  public void deleteProduct(RoutingContext routingContext) {
    routingContext.request().bodyHandler(handler -> {
      JsonObject json = handler.toJsonObject();
      String idProduct = json.getString(Constants.ID);
      productService.deleteProduct(idProduct).setHandler(res -> {
        if (res.succeeded()) {
          routingContext.response()
              .putHeader(Constants.CONTENT_TYPE, Constants.CONTENT_VALUE_JSON)
              .end(Json.encodePrettily(res.result()));
        } else {
          res.cause().getMessage();
        }
      });
    });
  }

}
