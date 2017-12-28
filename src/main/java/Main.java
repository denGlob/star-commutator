import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * Created by denglob on 12/19/17.
 */
public class Main {

    private static final int TASK_COUNT = 16;
    private static final int PROC_COUNT = 4;


    private List<Task> tasks;
    private List<Processor> processors;

    public static void main(String args[]) {
        Main main = new Main();
        main.createProcessors(PROC_COUNT);
        main.createTasks(TASK_COUNT);

    }

    private void configureTasks(List<Task> tasks) {
        confConcreateTask(tasks.get(0), 6, new Integer[]{1}, new Task[]{tasks.get(4)}, null);
        confConcreateTask(tasks.get(1), 2, new Integer[]{3}, new Task[]{tasks.get(5)}, null);

        tasks.get(2).setExecutionTime(3);
        tasks.get(2).addBranch(1);
        tasks.get(2).addChild(tasks.get(6));

        tasks.get(3).setExecutionTime(3);
        tasks.get(3).addBranch(1);
        tasks.get(3).addChild(tasks.get(6));
    }

    private void confConcreateTask(Task toConfig, int executionTime, Integer[] branches, Task[] children, Task[] parents) {
        toConfig.setExecutionTime(executionTime);
        if (branches != null)
            toConfig.getBranches().addAll(Arrays.asList(branches));
        if (children != null)
            toConfig.getChildTasks().addAll(Arrays.asList(children));
        if (parents != null)
            for (Task parent : parents) {
                toConfig.addParent(parent);
            }
    }

    private List<Task> createTasks(int taskNumber) {
        List<Task> result = new ArrayList<Task>();
        for (int i = 0; i < taskNumber; ++i) {
            result.add(new Task("T" + i));
        }
        return result;
    }

    private List<Processor> createProcessors(int procNumber) {
        List<Processor> result = new ArrayList<Processor>();
        for (int i = 0; i < procNumber; ++i) {
            result.add(new Processor("P" + i));
        }
        return result;
    }

}
