package edu.mamontova;/*
  @author tanus
  @project lab5
  @class ItemStore
  @version 1.0.0
  @since 19.04.2025 - 23.05
*/
import java.util.*;
import java.util.concurrent.atomic.AtomicLong;

public class ItemStore {
    private final Map<Long, Item> store = new HashMap<>();
    private final AtomicLong idGen = new AtomicLong(1);

    public List<Item> getAll() {
        return new ArrayList<>(store.values());
    }

    public Item getById(long id) {
        return store.get(id);
    }

    public Item create(Item item) {
        long id = idGen.getAndIncrement();
        item.id = id;
        store.put(id, item);
        return item;
    }

    public Item update(Item item) {
        if (!store.containsKey(item.id)) return null;
        store.put(item.id, item);
        return item;
    }

    public boolean delete(long id) {
        return store.remove(id) != null;
    }
}
