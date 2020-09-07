import java.io.*;
import java.util.*;

public class Solution {
    public static void main(String[] args) throws IOException {
        System.out.println("Введите название файла (text.txt)");
        String data = "";

        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));

        String fileName = null;

        try {

            fileName = reader.readLine();

        } catch (IOException e) {

            System.out.println("Ошибка ввода");
            fileName = reader.readLine();
            reader.close();
        }

        try {

            Scanner in = new Scanner(new File(fileName));

            while (in.hasNext())
                // data - строка с исходным текстом
                data += in.nextLine() + " ";

            in.close();

        } catch (FileNotFoundException e) {

            System.out.println("Путь к файлу неверен!");
            System.exit(1);

        }

        //String[] dataArray = data.split("\\s*(\\s|,|!|\\.|\\?|:|;|\\(|\\))\\s*");
        // Замена всех символов, кроме букв на пробелы.
        String[] dataArray = data.replaceAll("[^A-Za-zА-Яа-я]", " ").split("\\s+");
        // лист для сортировки и вывода.
        ArrayList<String> result = new ArrayList<>();

        for (String temp : dataArray) {
            if (!(temp.equals("") || temp.equals("–"))) {
                result.add(temp);
            }
        }
        Collections.sort(result);
        for (String temp : result) {
            System.out.println(temp);
        }
        // Вызов метода для печати общей статистики и нахождения часто встречаемых слов
        getStatistic(result);
    }

    public static void getStatistic(ArrayList<String> result) {
        Map<String, Integer> number = new HashMap<>();
        for (String key : result) {
            number.put(key, 0);
        }
        for (String key : result) {
            if (number.containsKey(key)) {
                number.put(key, number.get(key) + 1);
            }
        }
        printUnique(number);
        printMax(number);
    }
    private static void printUnique(Map<String, Integer> number) {
        System.out.println("Уникальные слова с количеством повторов");
        for (Map.Entry<String, Integer> entry : number.entrySet()) {
            System.out.println(entry.getKey() + " : " + entry.getValue());
        }
    }
    private static void printMax(Map<String, Integer> number) {
        Iterator<Integer> iterator = number.values().iterator();
        int max = 0;
        while (iterator.hasNext()) {
            int a = iterator.next();
            if (a > max) {
                max = a;
            }
        }
        System.out.println("Самые частые слова");
        for (Map.Entry<String, Integer> entry : number.entrySet()) {
            if (entry.getValue() == max) {
                System.out.println(entry.getKey() + " : " + entry.getValue());
            }
        }

    }

}

