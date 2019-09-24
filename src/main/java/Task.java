public class Task {

    private String name;
    private int urgencyScore;
    private int importanceScore;

    public Task() {
    }

    public Task(String name, int urgencyScore, int importanceScore) {
        this.name = name;
        this.urgencyScore = urgencyScore;
        this.importanceScore = importanceScore;
    }

    public Task(String name) {
        this.name = name;
        this.importanceScore = 0;
        this.urgencyScore = 0;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getImportanceScore() {
        return importanceScore;
    }

    public void setImportanceScore(int importanceScore) {
        this.importanceScore = importanceScore;
    }

    public int getUrgencyScore() {
        return urgencyScore;
    }

    public void setUrgencyScore(int urgencyScore) {
        this.urgencyScore = urgencyScore;
    }

    @Override
    public String toString() {
        return "{\"name\":\"" + name +"\",\"urgencyScore\":" + urgencyScore + ",\"importanceScore\":" + importanceScore + "}";
    }
}
