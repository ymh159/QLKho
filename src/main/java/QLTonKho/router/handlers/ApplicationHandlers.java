package QLTonKho.router.handlers;

import QLTonKho.constants.Constants;
import QLTonKho.entities.ProductEntity;
import QLTonKho.entities.ProductTypeEntity;
import QLTonKho.services.InventoryService;
import QLTonKho.services.impl.InventoryServiceImpl;
import io.vertx.core.Vertx;
import io.vertx.core.json.Json;
import io.vertx.core.json.JsonObject;
import io.vertx.ext.web.RoutingContext;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import QLTonKho.services.ProductService;
import QLTonKho.services.ProductTypeService;
import QLTonKho.services.impl.ProductServiceImpl;
import QLTonKho.services.impl.ProductTypeServiceImpl;

public class ApplicationHandlers {

  private static final Logger LOGGER = LoggerFactory.getLogger(ApplicationHandlers.class);
  private ProductService productService;
  private ProductTypeService productTypeService;
  private InventoryService inventoryService;

  public ApplicationHandlers(Vertx vertx) {
    productService = new ProductServiceImpl(vertx);
    productTypeService = new ProductTypeServiceImpl(vertx);
    inventoryService = new InventoryServiceImpl(vertx);

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
          routingContext.response()
              .putHeader(Constants.CONTENT_TYPE, Constants.CONTENT_VALUE_JSON)
              .end(Json.encodePrettily(res.cause().getMessage()));
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
          routingContext.response()
              .putHeader(Constants.CONTENT_TYPE, Constants.CONTENT_VALUE_JSON)
              .end(Json.encodePrettily(res.cause().getMessage()));
        }
      });
    });
  }

  public void deleteProduct(RoutingContext routingContext) {
    routingContext.request().bodyHandler(handler -> {
      JsonObject json = handler.toJsonObject();
      String idProduct = json.getString(Constants._ID);
      productService.deleteProduct(idProduct).setHandler(res -> {
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
    });
  }
  public void getProductType(RoutingContext routingContext) {
    String productTypeId = routingContext.request().getParam(Constants._ID);
    productTypeService.getProductType(productTypeId).setHandler(res -> {
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

  public void insertProductType(RoutingContext routingContext) {
    routingContext.request().bodyHandler(handler -> {
      JsonObject json = handler.toJsonObject();
      ProductTypeEntity entity = json.mapTo(ProductTypeEntity.class);
      productTypeService.insertProductType(entity).setHandler(res -> {
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
    });
  }

  public void updateProductType(RoutingContext routingContext) {
    routingContext.request().bodyHandler(handler -> {
      JsonObject json = handler.toJsonObject();
      ProductTypeEntity entity = json.mapTo(ProductTypeEntity.class);
      String idProductType = entity.getId();
      productTypeService.updateProductType(idProductType, entity).setHandler(res -> {
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
    });
  }

  public void deleteProductType(RoutingContext routingContext) {
    routingContext.request().bodyHandler(handler -> {
      JsonObject json = handler.toJsonObject();
      String idProductType = json.getString(Constants._ID);
      productTypeService.deleteProductType(idProductType).setHandler(res -> {
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
    });
  }

  public void getInventory(RoutingContext routingContext) {
    inventoryService.getAllInventory().setHandler(res -> {
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

}
