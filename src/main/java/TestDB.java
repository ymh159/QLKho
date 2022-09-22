
import entities.ProductEntity;
import io.vertx.core.AbstractVerticle;
import io.vertx.core.Future;
import java.util.List;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import repositories.ProductRepository;
import repositories.impl.ProductRepositoryImpl;

public class TestDB extends AbstractVerticle {

  private static final Logger LOGGER = LoggerFactory.getLogger(TestDB.class);

  @Override
  public void start() {
    ProductRepository repository = new ProductRepositoryImpl(vertx);
    ProductEntity entity = new ProductEntity("001","123","456","789","123","123");

    repository.insertProduct(entity);
    repository.getALl().setHandler(event -> {
          LOGGER.info("Future {}", event);
        }
    );


  }

}
