package QLTonKho.services.impl;

import QLTonKho.dto.InventoryDTO;
import QLTonKho.entities.ProductEntity;
import QLTonKho.repositories.ProductRepository;
import QLTonKho.repositories.impl.ProductRepositoryImpl;
import QLTonKho.services.InventoryService;
import io.vertx.core.Future;
import io.vertx.core.Vertx;
import io.vertx.core.json.JsonObject;
import java.util.ArrayList;
import java.util.List;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class InventoryServiceImpl implements InventoryService {

  private static final Logger LOGGER = LoggerFactory.getLogger(ProductServiceImpl.class);
  public final ProductRepository productRepository;

  public InventoryServiceImpl(Vertx vertx) {
    productRepository = new ProductRepositoryImpl(vertx);
  }

  @Override
  public Future<List<InventoryDTO>> getAllInventory() {
    Future<List<InventoryDTO>> future = Future.future();
    productRepository.getAllProduct().setHandler(event -> {
      List<ProductEntity> entity = event.result();
      List<InventoryDTO> listDTO = new ArrayList<>();
      for (ProductEntity temp : entity
      ) {
        InventoryDTO inventoryDTO = new InventoryDTO(temp.getId(), temp.getName(),
            temp.getType().getName(), temp.getValue(), temp.getPrice(),
            temp.getType().getDescription());
        listDTO.add(inventoryDTO);
      }
      future.complete(listDTO);
    });
    return future;
  }
}
