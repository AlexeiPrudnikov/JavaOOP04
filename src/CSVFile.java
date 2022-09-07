import java.io.BufferedReader;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;

public class CSVFile extends FileFormats {

    @Override
    public void importFromFile(PlaningTasks pt, String file) {
        try (FileReader fr = new FileReader(file)) {
            String line;
            BufferedReader bufferedReader = new BufferedReader(fr);
            while ((line = bufferedReader.readLine()) != null) {
                String[] fields = line.split(";");
                addTaskToPlan(fields, pt);
            }
        } catch (IOException exception) {
            System.out.println(exception.getMessage());

        } catch (Exception ex) {
            System.out.println(ex.getMessage());
        }
    }

    @Override
    public void exportToFile(PlaningTasks pt) {
        ArrayList<Task> export = pt.getTasks();
        DateFormat df = new SimpleDateFormat("dd.MM.yyyy");
        try (FileWriter fileWriter = new FileWriter("export.csv", false)) {
            for (Task task : export) {
                fileWriter.write(task.getClass().getName() + ";" + task.getId() + ";"
                        + df.format(task.getDate().getTime()) + ";" + "\n");
            }
        } catch (IOException ex) {
            System.out.println(ex.getMessage());
        }
    }
}
