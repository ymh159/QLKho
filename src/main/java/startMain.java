import entities.ProductEntity;
import io.vertx.core.Vertx;

public class startMain {

  public static void main(String[] args) {
    Vertx vertx = Vertx.vertx();
    vertx.deployVerticle(new TestDB());
  }
}
