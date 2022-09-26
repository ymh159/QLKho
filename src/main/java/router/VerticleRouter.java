package router;

import constants.Constants;
import io.vertx.core.AbstractVerticle;
import io.vertx.core.Vertx;
import io.vertx.ext.web.Router;
import router.handlers.ApplicationHandlers;

public class VerticleRouter extends AbstractVerticle {

  public Router getRouter(Vertx vertx) {
    ApplicationHandlers applicationHandlers = new ApplicationHandlers(vertx);

    Router router = Router.router(vertx);
    router.get(Constants.PATH_PRODUCTS).handler(applicationHandlers::getProduct);             // get all product
    router.get(Constants.PATH_PRODUCTS + "/:_id").handler(applicationHandlers::getProduct);   // get product by id
    router.post(Constants.PATH_PRODUCTS).handler(applicationHandlers::insertProduct);         // insert product
    router.put(Constants.PATH_PRODUCTS).handler(applicationHandlers::updateProduct);          // update product
    router.delete(Constants.PATH_PRODUCTS).handler(applicationHandlers::deleteProduct);       // delete product
    router.get(Constants.PATH_PRODUCT_TYPE).handler(applicationHandlers::getProductType);             // get all product type
    router.get(Constants.PATH_PRODUCT_TYPE + "/:_id").handler(applicationHandlers::getProductType);   // get product type by id
    router.post(Constants.PATH_PRODUCT_TYPE).handler(applicationHandlers::insertProductType);         // insert product type
    router.put(Constants.PATH_PRODUCT_TYPE).handler(applicationHandlers::updateProductType);          // update product type
    router.delete(Constants.PATH_PRODUCT_TYPE).handler(applicationHandlers::deleteProductType);       // delete product type
    return router;
  }
}
