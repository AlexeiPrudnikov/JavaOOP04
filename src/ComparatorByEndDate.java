import java.util.Comparator;

public class ComparatorByEndDate implements Comparator<Task> {
    @Override
    public int compare(Task o1, Task o2) {
        if (o1.getEndDate().before(o2.getEndDate())) {
            return -1;
        } else if (o1.getEndDate().equals(o2.getEndDate())) {
            return 0;
        }
        return 1;
    }
}
