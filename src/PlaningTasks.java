import java.util.ArrayList;
import java.util.Calendar;
import java.util.Iterator;

public class PlaningTasks {
    ArrayList<Task> tasks;

    public PlaningTasks() {
        this.tasks = new ArrayList<>();
    }

    public ArrayList<Task> getTasks() {
        return tasks;
    }

    public void addTask(Task task) {
        if (!tasks.contains(task)) {
            tasks.add(task);
        } else {
            System.out.println("Задание с id = " + task.getId() + " существует");
        }
    }

    public String pastTasks() {
        StringBuilder result = new StringBuilder();
        for (Task task : tasks) {
            if (Calendar.getInstance().after(task.getEndDate())) {
                result.append(task.toString());
                result.append("\n");
            }
        }
        return result.toString();
    }

    public void sortByID() {
        ComparatorByID cID = new ComparatorByID();
        tasks.sort(cID);
    }

    public void sortByEndDate() {
        ComparatorByEndDate comparatorByEndDate = new ComparatorByEndDate();
        tasks.sort(comparatorByEndDate);
    }

    public void executeTaskByID(int id) {
        Iterator<Task> iterator = tasks.iterator();
        while (iterator.hasNext()) {
            if (iterator.next().getId() == id) {
                iterator.remove();
                return;
            }
        }
    }

    @Override
    public String toString() {
        StringBuilder result = new StringBuilder();
        for (Task task : tasks) {
            result.append(task.toString());
            result.append("\n");
        }
        return result.toString();
    }

}
