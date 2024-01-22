package com.radev.project.service.abstraction;


import java.util.List;

public interface CrudService {
    List<?> findAll();
    Object findById(Object id);
    Object create(Object payload);
    Object update(Object payload);
    void delete(Object id);
}
