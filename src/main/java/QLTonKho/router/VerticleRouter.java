package QLTonKho.router;

import QLTonKho.constants.Constants;
import io.vertx.core.AbstractVerticle;
import io.vertx.core.Vertx;
import io.vertx.ext.web.Router;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import QLTonKho.router.handlers.ApplicationHandlers;

public class VerticleRouter extends AbstractVerticle {
  private static final Logger LOGGER = LoggerFactory.getLogger(ApplicationHandlers.class);
  public Router getRouter(Vertx vertx) {
    ApplicationHandlers applicationHandlers = new ApplicationHandlers(vertx);

    Router router = Router.router(vertx);
    // get all product
    router.get(Constants.PATH_PRODUCTS).handler(applicationHandlers::getProduct);
    // get product by id
    router.get(Constants.PATH_PRODUCTS + Constants.PATH_ID).handler(applicationHandlers::getProduct);
    // insert product
    router.post(Constants.PATH_PRODUCTS).handler(applicationHandlers::insertProduct);
    // update product
    router.put(Constants.PATH_PRODUCTS).handler(applicationHandlers::updateProduct);
    // delete product
    router.delete(Constants.PATH_PRODUCTS).handler(applicationHandlers::deleteProduct);
    // get all product type
    router.get(Constants.PATH_PRODUCT_TYPE).handler(applicationHandlers::getProductType);
    // get product type by id
    router.get(Constants.PATH_PRODUCT_TYPE + Constants.PATH_ID).handler(applicationHandlers::getProductType);
    // insert product type
    router.post(Constants.PATH_PRODUCT_TYPE).handler(applicationHandlers::insertProductType);
    // update product type
    router.put(Constants.PATH_PRODUCT_TYPE).handler(applicationHandlers::updateProductType);
    // delete product type
    router.delete(Constants.PATH_PRODUCT_TYPE).handler(applicationHandlers::deleteProductType);
    // get inventory
    router.get(Constants.PATH_INVENTORY).handler(applicationHandlers::getInventory);

    return router;
  }
}
