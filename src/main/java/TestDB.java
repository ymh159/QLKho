
import io.vertx.core.AbstractVerticle;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import repositories.ProductRepository;
import repositories.impl.ProductRepositoryImpl;

public class TestDB extends AbstractVerticle {

  private static final Logger LOGGER = LoggerFactory.getLogger(TestDB.class);

  @Override
  public void start() {
    ProductRepository repository = new ProductRepositoryImpl(vertx);
    //ProductTypeEntity type = new ProductTypeEntity();
    //ProductEntity entity = new ProductEntity("Nami","123","456", type);
    //repository.insertProduct(entity);
//    repository.deleteProduct("002");
    repository.getAllProduct().setHandler(event -> {
          LOGGER.info("Future {}", event);
        }
    );

  }

}
