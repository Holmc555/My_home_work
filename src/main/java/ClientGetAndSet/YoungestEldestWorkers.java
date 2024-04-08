package ClientGetAndSet;

public class YoungestEldestWorkers {
    private String workerName;
    private String birthday;

    public YoungestEldestWorkers(String workerName, String birthday) {
        this.workerName = workerName;
        this.birthday = birthday;
    }

    public void setWorkerName(String workerName) {
        this.workerName = workerName;
    }

    public void setBirthday(String birthday) {
        this.birthday = birthday;
    }

    public String getWorkerName() {
        return workerName;
    }

    public String getBirthday() {
        return birthday;
    }
}
