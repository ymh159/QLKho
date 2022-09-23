package utils;

import entities.ProductEntity;
import io.vertx.core.AsyncResult;
import io.vertx.core.Future;

public class SetFutureUtils {
  public void setFutureVoid(AsyncResult res, Future<Object> future) {
    if (res.succeeded()) {
      future.complete();
    } else {
      future.fail(res.cause());
    }
  }
}
