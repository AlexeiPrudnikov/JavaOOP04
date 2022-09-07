import java.io.BufferedReader;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;

public class JsonFile extends FileFormats {

    @Override
    public void importFromFile(PlaningTasks pt, String file) {
        try (FileReader fr = new FileReader(file)) {
            String line;
            BufferedReader bufferedReader = new BufferedReader(fr);
            boolean openBlock = false;
            int i = 0;
            String[] fields = new String[3];
            while ((line = bufferedReader.readLine()) != null) {
                if (line.equals("  {")) {
                    fields = new String[3];
                    openBlock = true;
                } else if (line.equals("  }") || line.equals("  },")) {
                    i = 0;
                    addTaskToPlan(fields, pt);
                    openBlock = false;
                }
                if (openBlock && !line.equals("  {")) {
                    switch (i) {
                        case 0:
                            fields[0] = line.substring(line.indexOf("priority") + 12, line.length() - 2);
                            i++;
                            break;
                        case 1:
                            fields[1] = line.substring(line.indexOf("id") + 5, line.length() - 1);
                            i++;
                            break;
                        case 2:
                            fields[2] = line.substring(line.indexOf("date") + 8, line.length() - 1);
                            i++;
                            break;
                    }
                }
            }
        } catch (IOException exception) {
            System.out.println(exception.getMessage());

        } catch (Exception ex) {
            System.out.println(ex.getMessage());
        }
    }

    private void writeTask(FileWriter fileWriter, Task task) throws IOException {
        DateFormat df = new SimpleDateFormat("dd.MM.yyyy");
        fileWriter.write("  {\n");
        fileWriter.write("    \"priority\": \"" + task.getClass().getName() + "\",\n");
        fileWriter.write("    \"id\": " + task.getId() + ",\n");
        fileWriter.write("    \"date\": \"" + df.format(task.getDate().getTime()) + "\"\n");
        fileWriter.write("  }");
    }

    @Override
    public void exportToFile(PlaningTasks pt) {
        ArrayList<Task> export = pt.getTasks();
        try (FileWriter fileWriter = new FileWriter("export.json", false)) {
            if (export.size() == 1) {
                writeTask(fileWriter, export.get(0));
            } else {
                fileWriter.write("[\n");
                for (int i = 0; i < export.size(); i++) {
                    writeTask(fileWriter, export.get(i));
                    if (i != export.size() - 1) {
                        fileWriter.write(",\n");
                    }
                }
                fileWriter.write("\n");
                fileWriter.write("]\n");
            }
        } catch (IOException ex) {
            System.out.println(ex.getMessage());
        }
    }
}
