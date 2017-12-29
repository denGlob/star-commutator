import java.util.*;

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
        main.tasks = main.createTasks(TASK_COUNT);
        main.configureTasks(main.tasks);
        main.processors = main.createProcessors(PROC_COUNT, main.theMostContiniousExecution(main.tasks));
        main.execute();
        main.showResults();
    }

    private void showResults() {
        for (Processor processor : processors) {
            System.out.println(processor.getExecutedTasks().toString());
        }
    }

    private int theMostContiniousExecution(List<Task> tasks) {
        int result = 0;
        for (Task task : tasks) {
            if (result < (task.getStartTime() + task.getExecutionTime())) {
                result = task.getStartTime() + task.getExecutionTime();
            }
        }
        return result;
    }

    private void execute() {
        int cycleCounter = theMostContiniousExecution(tasks);
GLOBAL:    for (int i = 0; i < cycleCounter; ++i) {
            for (Processor pr : processors) {
                if (pr.getWorkedTime() == i) {
                    pr.setFree(true);
                }
            }
TASK:       for (Task task : tasks) {
                if (task.isFree()) {
                    Map<Integer, Processor> workedTimeProc = new HashMap<Integer, Processor>();
                    for (Processor processor : processors) {
                        if (processor.isFree()) {
                            workedTimeProc.put(processor.getWorkedTime(), processor);
                        }
                    }
                    if (workedTimeProc.isEmpty()) {
                        continue GLOBAL;
                    }
                    int procKeyForSet = -1;
                    int minGap = Integer.MAX_VALUE;
                    for (Integer workedTime : workedTimeProc.keySet()) {
                            if (workedTimeProc.get(workedTime).isCanBeProccessed(task) &&
                                    workedTime <= task.getStartTime()) {
                                int gap = task.getStartTime() - workedTime;
                                if (gap < minGap) {
                                    minGap = gap;
                                    procKeyForSet = workedTime;
                                }
                            }
                    }
                    if (procKeyForSet == -1) {
                        continue TASK;
                    }
                    Processor processor = workedTimeProc.get(procKeyForSet);
                    if (processor.isFree()) {
                        processor.setWorkedTime(task.getStartTime() + task.getExecutionTime());
                        processor.addStartPoint(task.getStartTime());
                        processor.addexecutedTask(i, task.getId());
                        task.setFree(false);
                        processor.setFree(false);
                    }
                }
            }
        }
    }

    private void configureTasks(List<Task> tasks) {
        confConcreateTask(tasks.get(0), 6, new Integer[]{1},
                            new Task[]{tasks.get(4)}, null);
        confConcreateTask(tasks.get(1), 2, new Integer[]{3},
                            new Task[]{tasks.get(5)}, null);
        confConcreateTask(tasks.get(2), 3, new Integer[]{1},
                            new Task[]{tasks.get(6)}, null);
        confConcreateTask(tasks.get(3), 4, new Integer[]{2},
                            new Task[]{tasks.get(6)}, null);

        confConcreateTask(tasks.get(4), 8, new Integer[]{1, 2},
                new Task[]{tasks.get(7), tasks.get(8)}, new Task[]{tasks.get(0)});
        confConcreateTask(tasks.get(5), 3, new Integer[]{3},
                new Task[]{tasks.get(9)}, new Task[]{tasks.get(1)});
        confConcreateTask(tasks.get(6), 2, new Integer[]{1},
                new Task[]{tasks.get(10)}, new Task[]{tasks.get(2), tasks.get(3)});

        confConcreateTask(tasks.get(7), 5, new Integer[]{3},
                new Task[]{tasks.get(11)}, new Task[]{tasks.get(4)});
        confConcreateTask(tasks.get(8), 2, new Integer[]{1, 3},
                new Task[]{tasks.get(11), tasks.get(12)}, new Task[]{tasks.get(4)});
        confConcreateTask(tasks.get(9), 6, new Integer[]{1},
                new Task[]{tasks.get(13)}, new Task[]{tasks.get(5)});
        confConcreateTask(tasks.get(10), 7, new Integer[]{1},
                new Task[]{tasks.get(13)}, new Task[]{tasks.get(6)});

        confConcreateTask(tasks.get(11), 2, new Integer[]{2},
                new Task[]{tasks.get(14)}, new Task[]{tasks.get(7), tasks.get(8)});
        confConcreateTask(tasks.get(12), 5, new Integer[]{1},
                new Task[]{tasks.get(15)}, new Task[]{tasks.get(8)});
        confConcreateTask(tasks.get(13), 9, new Integer[]{2},
                new Task[]{tasks.get(15)}, new Task[]{tasks.get(9), tasks.get(10)});

        confConcreateTask(tasks.get(14), 3, null,
                null, new Task[]{tasks.get(11)});
        confConcreateTask(tasks.get(15), 7, null,
                null, new Task[]{tasks.get(12), tasks.get(13)});
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

    private List<Processor> createProcessors(int procNumber, int lastStartpoint) {
        List<Processor> result = new ArrayList<Processor>();
        for (int i = 0; i < procNumber; ++i) {
            result.add(new Processor("P" + i, lastStartpoint));
        }
        return result;
    }

}
