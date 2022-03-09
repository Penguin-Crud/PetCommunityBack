package com.petCommunity.PetCommunityBack.Services;

import java.io.Serializable;
import java.util.List;

public interface GenericCrudService<T, Pk extends Serializable> {
    T save(T t);

    T getById(Pk id);
    T update(T t, Pk id);
    List<T> getAll();
    void deleteId(Pk id);
}
