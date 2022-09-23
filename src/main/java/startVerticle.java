import io.vertx.core.AbstractVerticle;
import io.vertx.core.Future;
import io.vertx.ext.web.Router;
import router.VerticleRouter;

public class startVerticle extends AbstractVerticle {

  @Override
  public void start(Future<Void> startFuture) throws Exception {
    VerticleRouter verRouter = new VerticleRouter();

    vertx.createHttpServer()
        .requestHandler(verRouter.getRouter(vertx))
        .listen(8080);
  }
}
