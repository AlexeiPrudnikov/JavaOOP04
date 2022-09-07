import java.util.Calendar;

public class HighPriorityTask extends Task {
    public HighPriorityTask(int id, Calendar date) {
        super(id, date);
        super.countDays = 2;
        setEndDate();
    }
}
