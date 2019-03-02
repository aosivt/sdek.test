package sdek.supplier.mappers;

import sdek.supplier.models.CanceledOrders;
import sdek.supplier.models.Dictionary;

import java.util.Set;

public interface Mapper {
    Dictionary getById(Long id);
    Set getCollectionData();
}
