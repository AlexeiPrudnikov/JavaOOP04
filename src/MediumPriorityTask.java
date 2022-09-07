import java.util.Calendar;

public class MediumPriorityTask extends Task{
    public MediumPriorityTask(int id, Calendar date) {
        super(id, date);
        super.countDays = 7;
        setEndDate();
    }
}
