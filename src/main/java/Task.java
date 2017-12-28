import java.util.ArrayList;
import java.util.List;

/**
 * Created by denglob on 12/28/17.
 */
public class Task {
    private String id;
    private List<Task> parentTasks = new ArrayList<Task>();
    private List<Task> childTasks = new ArrayList<Task>();
    private boolean done;
    private boolean free = true;
    private int executionTime;
    private int startTime;
    private List<Integer> branches = new ArrayList<Integer>();

    public Task(String id) {
        this.id = id;
    }

    public List<Task> getParentTasks() {
        return parentTasks;
    }

    public void setParentTasks(List<Task> parentTasks) {
        this.parentTasks = parentTasks;
    }

    public List<Task> getChildTasks() {
        return childTasks;
    }

    public void setChildTasks(List<Task> childTasks) {
        this.childTasks = childTasks;
    }

    public boolean isDone() {
        return done;
    }

    public void setDone(boolean done) {
        this.done = done;
    }

    public boolean isFree() {
        return free;
    }

    public void setFree(boolean free) {
        this.free = free;
    }

    public int getExecutionTime() {
        return executionTime;
    }

    public void setExecutionTime(int executionTime) {
        this.executionTime = executionTime;
    }

    public List<Integer> getBranches() {
        return branches;
    }

    public void setBranches(List<Integer> branches) {
        this.branches = branches;
    }

    public void updateStartTime(int newStartTime) {
        if (newStartTime > startTime) {
            startTime = newStartTime;
        }
    }

    public void addBranch(int branch) {
        branches.add(branch);
    }

    public void addChild(Task task) {
        childTasks.add(task);
    }

    public void addParent(Task task) {
        parentTasks.add(task);
        int pos = task.getChildTasks().indexOf(this);
        updateStartTime(task.executionTime + task.branches.get(pos));
    }
}
