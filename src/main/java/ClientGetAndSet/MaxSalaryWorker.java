package ClientGetAndSet;

public class MaxSalaryWorker {
    private String workerName;
    private int salary;

    public MaxSalaryWorker(String workerName, int salary) {
        this.workerName = workerName;
        this.salary = salary;
    }

    public void setSalary(int salary) {
        this.salary = salary;
    }

    public void setWorkerName(String workerName) {
        this.workerName = workerName;
    }

    public int getSalary() {
        return salary;
    }

    public String getWorkerName() {
        return workerName;
    }
}
