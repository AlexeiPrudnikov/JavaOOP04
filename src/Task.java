import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.GregorianCalendar;

public abstract class Task {
    private int id;
    private Calendar date;
    protected int countDays;
    private Calendar endDate;


    public Task(int id, Calendar date) {
        this.id = id;

        //date = new GregorianCalendar(date.)
        this.date = date;
       // endDate = new GregorianCalendar();

    }

    public int getId() {
        return id;
    }

    public Calendar getDate() {
        return date;
    }

    public int getCountDays() {
        return countDays;
    }

    public Calendar getEndDate() {
        return endDate;
    }

    public void setEndDate(){
        endDate = new GregorianCalendar(date.get(Calendar.YEAR), date.get(Calendar.MONTH), date.get(Calendar.DAY_OF_MONTH));
        endDate.add(Calendar.DATE, countDays);
    }

    @Override
    public String toString() {
        StringBuilder result = new StringBuilder();
        result.append(id);
        result.append(": ");
        DateFormat df = new SimpleDateFormat("dd.MM.yyyy");
        result.append(df.format(date.getTime()));
        result.append(" -> ");
        result.append(df.format(endDate.getTime()));
        return result.toString();
    }
    @Override
    public boolean equals(Object obj) {
        Task t = (Task) obj;
        if (this.id == t.getId()) return true;
        return false;
    }
}
