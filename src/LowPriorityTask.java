import java.util.Calendar;

public class LowPriorityTask extends Task {

    public LowPriorityTask(int id, Calendar date) {
        super(id, date);
        super.countDays = 15;
        setEndDate();
    }
}
