package QLTonKho.eb;

import QLTonKho.constants.Constants;
import QLTonKho.entities.ProductEntity;
import QLTonKho.services.ProductService;
import QLTonKho.services.impl.ProductServiceImpl;
import io.vertx.core.AbstractVerticle;
import io.vertx.core.eventbus.EventBus;
import io.vertx.core.json.JsonArray;
import io.vertx.core.json.JsonObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class EventBusProduct extends AbstractVerticle {

  private static final Logger LOGGER = LoggerFactory.getLogger(EventBusProduct.class);

  @Override
  public void start() throws Exception {
    ProductService productService = new ProductServiceImpl(vertx);
    EventBus eb = vertx.eventBus();

    //  event get product
    eb.consumer(Constants.EVENT_GET_PRODUCT, message -> {
      String id;
      if(message.body()!= null){
        id = message.body().toString();
      }else {
        id ="";
      }
      productService.getProduct(id).setHandler(event -> {
        if(event.succeeded()){
        JsonArray jsonArray = new JsonArray(event.result());
        message.reply(jsonArray);
        LOGGER.info("event get {}",jsonArray);
        }else{
          message.reply("event get fail");
          LOGGER.info("event get fail");
        }
      });
    });

    // event insert product
    eb.consumer(Constants.EVENT_INSERT_PRODUCT, message -> {
      JsonObject json = new JsonObject(message.body().toString());
      ProductEntity entity = json.mapTo(ProductEntity.class);
      productService.insertProduct(entity).setHandler(event -> {
        if (event.succeeded()) {
          LOGGER.info("insert success");
          message.reply("insert success");
        } else {
          LOGGER.info("insert fail");
          message.reply("insert fail");
        }
      });
    });

    // event insert product
    eb.consumer(Constants.EVENT_UPDATE_PRODUCT, message -> {
      JsonObject json = new JsonObject(message.body().toString());
      ProductEntity entity = json.mapTo(ProductEntity.class);
      productService.updateProduct(entity.getId(),entity).setHandler(event -> {
        if (event.succeeded()) {
          LOGGER.info("update success");
          message.reply("update success");
        } else {
          LOGGER.info("update fail");
          message.reply("update fail");
        }
      });
    });

    // event insert product
    eb.consumer(Constants.EVENT_DELETE_PRODUCT, message -> {
      productService.deleteProduct(message.body().toString()).setHandler(event -> {
        if (event.succeeded()) {
          LOGGER.info("delete success");
          message.reply("delete success");
        } else {
          LOGGER.info("delete fail");
          message.reply("delete fail");
        }
      });
    });
  }

}
