import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

abstract class FileFormats implements TasksFiles {
    public void addTaskToPlan(String[] fields, PlaningTasks pt) throws ParseException {
        DateFormat df = new SimpleDateFormat("dd.MM.yyyy");
        if (fields.length == 3) {
            Date date = df.parse(fields[2]);
            Calendar calendar = Calendar.getInstance();
            calendar.setTime(date);
            switch (fields[0]) {
                case "LowPriorityTask":
                    pt.addTask(new LowPriorityTask(Integer.valueOf(fields[1]), calendar));
                    break;
                case "MediumPriorityTask":
                    pt.addTask(new MediumPriorityTask(Integer.valueOf(fields[1]), calendar));
                    break;
                case "HighPriorityTask":
                    pt.addTask(new HighPriorityTask(Integer.valueOf(fields[1]), calendar));
                    break;
            }
        }
    }

}
