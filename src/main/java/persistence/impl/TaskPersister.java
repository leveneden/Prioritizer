package persistence.impl;

import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.*;
import persistence.Persister;

import java.io.File;
import java.io.IOException;

@RequiredArgsConstructor
public class TaskPersister implements Persister {

    @NonNull
    @Setter
    String defaultFilePath = "src/main/resources/tasks.json";

    @Override
    public void persist(Object o) {
        ObjectMapper mapper = new ObjectMapper();
        try {
            mapper.writeValue(new File(defaultFilePath), o);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
