package service;

import java.util.List;

public interface CRUD {

    void create(Object o);
    List read(Object o);
    void update(Object o);
    void delete(Object o);
}
