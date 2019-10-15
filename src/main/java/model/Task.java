package model;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class Task {
    private String name;
    private int urgencyScore, importanceScore;
}
