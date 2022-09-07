import java.io.BufferedReader;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;

public class XMLFile extends FileFormats {
    @Override
    public void importFromFile(PlaningTasks pt, String file) {
        try (FileReader fr = new FileReader(file)) {
            String line;
            BufferedReader bufferedReader = new BufferedReader(fr);
            boolean openXml = false;
            boolean openBlock = false;
            int i = 0;
            String[] fields = new String[3];

            while ((line = bufferedReader.readLine()) != null) {
                if (!openXml && line.equals("<tasks>")) {
                    openXml = true;
                }
                if (openXml) {
                    if (line.equals("\t<task>")) {
                        fields = new String[3];
                        openBlock = true;
                    } else if (line.equals("\t</task>")) {
                        i = 0;
                        addTaskToPlan(fields, pt);
                        openBlock = false;
                    }
                    if (openBlock && !line.equals("\t<task>")) {
                        switch (i) {
                            case 0:
                                fields[0] = line.substring(line.indexOf("<priority>") + 10, line.indexOf("</priority>"));
                                i++;
                                break;
                            case 1:
                                fields[1] = line.substring(line.indexOf("<id>") + 4, line.indexOf("</id>"));
                                i++;
                                break;
                            case 2:
                                fields[2] = line.substring(line.indexOf("<date>") + 6, line.indexOf("</date>"));
                                i++;
                                break;
                        }
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
        fileWriter.write("\t<task>\n");
        fileWriter.write("\t\t<priority>" + task.getClass().getName() + "</priority>\n");
        fileWriter.write("\t\t<id>" + task.getId() + "</id>\n");
        fileWriter.write("\t\t<date>" + df.format(task.getDate().getTime()) + "</date>\n");
        fileWriter.write("\t</task>\n");
    }

    @Override
    public void exportToFile(PlaningTasks pt) {
        ArrayList<Task> export = pt.getTasks();
        try (FileWriter fileWriter = new FileWriter("export.xml", false)) {
            fileWriter.write("<tasks>");

            fileWriter.write("\n");
            for (int i = 0; i < export.size(); i++) {
                writeTask(fileWriter, export.get(i));
            }
            fileWriter.write("</tasks>");
        } catch (IOException ex) {
            System.out.println(ex.getMessage());
        }
    }
}
