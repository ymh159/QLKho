package QLTonKho;

import QLTonKho.services.impl.ProductServiceImpl;
import io.vertx.core.AbstractVerticle;
import io.vertx.core.Future;
import QLTonKho.router.VerticleRouter;
import io.vertx.core.Vertx;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class startVerticle extends AbstractVerticle {
  private static final Logger LOGGER = LoggerFactory.getLogger(ProductServiceImpl.class);

  public static void main(String[] args) {
    Vertx vertx = Vertx.vertx();
    vertx.deployVerticle(new startVerticle());
  }

  @Override
  public void start(Future<Void> startFuture) throws Exception {
    VerticleRouter verRouter = new VerticleRouter();
    vertx.createHttpServer()
        .requestHandler(verRouter.getRouter(vertx))
        .listen(8080);
  }
}
