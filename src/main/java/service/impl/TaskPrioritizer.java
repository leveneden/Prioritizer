package service.impl;

import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import persistence.impl.TaskPersister;
import service.CRUD;

import java.util.List;

@RequiredArgsConstructor
public class TaskPrioritizer implements CRUD {

    @NonNull
    private TaskPersister taskPersister;

    @Override
    public void create(Object o) {

    }

    @Override
    public List read(Object o) {
        return null;
    }

    @Override
    public void update(Object o) {

    }

    @Override
    public void delete(Object o) {

    }
}
