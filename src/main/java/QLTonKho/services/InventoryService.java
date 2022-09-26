package QLTonKho.services;

import QLTonKho.dto.InventoryDTO;
import io.vertx.core.Future;
import java.util.List;

public interface InventoryService {
  Future<List<InventoryDTO>> getAllInventory();
}
