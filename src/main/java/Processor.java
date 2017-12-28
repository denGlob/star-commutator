/**
 * Created by denglob on 12/28/17.
 */
public class Processor {
    private String id;
    private int workedTime;
    private boolean free = true;

    public Processor(String id) {
        this.id = id;
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
}
