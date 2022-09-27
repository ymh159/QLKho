package QLTonKho;

import io.vertx.core.AbstractVerticle;
import io.vertx.core.Future;
import QLTonKho.router.VerticleRouter;
import io.vertx.core.Vertx;

public class StartVerticle extends AbstractVerticle {

  public static void main(String[] args) {
    Vertx vertx = Vertx.vertx();
    vertx.deployVerticle(new StartVerticle());
  }

  @Override
  public void start(Future<Void> startFuture) throws Exception {
    VerticleRouter verRouter = new VerticleRouter();
    vertx.createHttpServer()
        .requestHandler(verRouter.getRouter(vertx))
        .listen(8081);
  }

}
