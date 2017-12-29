import java.util.*;

/**
 * Created by denglob on 12/28/17.
 */
public class Processor {
    private String id;
    private int workedTime;
    private boolean free = true;
    private int lastStarPoint;
    private List<Integer> startPoints;

    private Map<Integer, String> executedTasks;

    public Processor(String id, int lastStartPoint) {
        this.id = id;
        this.executedTasks = new HashMap<Integer, String>();
        this.startPoints = new ArrayList<Integer>();
        this.lastStarPoint = lastStartPoint;
        addStartPoint(0);
        addStartPoint(lastStartPoint);
    }
    public void addStartPoint(int startPoint) {
        startPoints.add(startPoint);
        Collections.sort(startPoints);
    }

    public boolean isCanBeProccessed(Task task) {
        int nearestBigger = 0;
        for (int value : startPoints) {
            if (value > task.getStartTime()) {
                nearestBigger = value;
            }
        }
        return (nearestBigger - (task.getStartTime() + task.getExecutionTime())) >= 0;
    }

    public void addexecutedTask(Integer startExecution, String taskId) {
        executedTasks.put(startExecution, taskId);
    }

    public int getWorkedTime() {
        return workedTime;
    }

    public void setWorkedTime(int workedTime) {
        this.workedTime = workedTime;
    }

    public boolean isFree() {
        return free;
    }

    public void setFree(boolean free) {
        this.free = free;
    }

    public Map<Integer, String> getExecutedTasks() {
        return executedTasks;
    }
}
