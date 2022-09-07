import java.util.Calendar;
import java.util.GregorianCalendar;

public class Main {
    public static void main(String[] args) {
        PlaningTasks pt = new PlaningTasks();
        pt.addTask(new LowPriorityTask(132, new GregorianCalendar(2022, Calendar.SEPTEMBER, 22)));
        pt.addTask(new MediumPriorityTask(456, new GregorianCalendar(2022, Calendar.MARCH, 10)));
        pt.addTask(new MediumPriorityTask(380, new GregorianCalendar(2022, Calendar.AUGUST, 03)));
        pt.addTask(new HighPriorityTask(1145, new GregorianCalendar(2022, Calendar.FEBRUARY, 28)));
        //Попытка добавить с тем же ID
        pt.addTask(new HighPriorityTask(456, new GregorianCalendar(2021, Calendar.DECEMBER, 31)));
        pt.addTask(new LowPriorityTask(1, new GregorianCalendar(2022, Calendar.JANUARY, 07)));
        System.out.println(pt.toString());
        // Сортировка по ID
        pt.sortByID();
        System.out.println("Сортировка по ID");
        System.out.println(pt.toString());
        // Сортировка по дате завершения
        pt.sortByEndDate();
        System.out.println("Сортировка по дате завершения");
        System.out.println(pt.toString());
        //Выполнение задачи, по сути удаление по ID
        System.out.println("Выполнение задачи, по сути удаление по ID (456)");
        pt.executeTaskByID(456);
        System.out.println(pt.toString());
        //Выполнение несуществующей задачи, ошибок нет
        System.out.println("Выполнение несуществующей задачи (нет 12345), ошибок нет");
        pt.executeTaskByID(123456);
        System.out.println(pt.toString());
        // Вывод просроченных заданий
        System.out.println("Вывод просроченных заданий");
        System.out.println(pt.pastTasks());

        // Блок экспорта в разные форматы
        pt.addTask(new LowPriorityTask(144, new GregorianCalendar(2022, Calendar.SEPTEMBER, 6)));
        pt.addTask(new MediumPriorityTask(256, new GregorianCalendar(2022, Calendar.SEPTEMBER, 1)));
        pt.addTask(new MediumPriorityTask(1024, new GregorianCalendar(2022, Calendar.AUGUST, 03)));

        FileFormats csvFile = new CSVFile();
        csvFile.exportToFile(pt);

        pt.executeTaskByID(1);
        pt.executeTaskByID(256);
        FileFormats jsonFile = new JsonFile();
        jsonFile.exportToFile(pt);

        pt.addTask(new HighPriorityTask(455, new GregorianCalendar(2021, Calendar.DECEMBER, 31)));
        pt.addTask(new LowPriorityTask(8, new GregorianCalendar(2021, Calendar.FEBRUARY, 23)));
        FileFormats xmlFile = new XMLFile();
        xmlFile.exportToFile(pt);

        // Блок импорта из разных форматов
        PlaningTasks importPtFromCSV = new PlaningTasks();
        FileFormats importCsv = new CSVFile();
        importCsv.importFromFile(importPtFromCSV, "export.csv");
        System.out.println("Вывод из файла формата csv");
        System.out.println(importPtFromCSV);

        PlaningTasks importPtFromJson = new PlaningTasks();
        FileFormats importJson = new JsonFile();
        importJson.importFromFile(importPtFromJson, "export.json");
        System.out.println("Вывод из файла формата json");
        System.out.println(importPtFromJson);

        PlaningTasks importPtFromXML = new PlaningTasks();
        FileFormats importXml = new XMLFile();
        importXml.importFromFile(importPtFromXML, "export.xml");
        System.out.println("Вывод из файла формата xml");
        System.out.println(importPtFromXML);

    }
}