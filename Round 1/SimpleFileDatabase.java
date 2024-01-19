import java.io.*;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class SimpleFileDatabase {

    private static final String TABLE_METADATA_FILE = "tableMetaData.txt";
    private static final String TABLE_FILE = "table.txt";

    public static void main(String[] args) {
        createTable("CREATE TABLE (col1 INTEGER, col2 STRING, col3 STRING, col4 INTEGER)");
        insertData("INSERT INTO (col1, col2, col3, col4) VALUES (1, 'value1', 'value3', 4)");
        insertData("INSERT INTO (col4, col2, col3, col1) VALUES (5, 'value2', 'value4', 2)");
    }

    public static void createTable(String createStatement) {
        List<String> columns = parseCreateStatement(createStatement);
        writeMetadata(columns);
    }

    public static void insertData(String insertStatement) {
        Map<String, String> colValueMap = parseInsertStatement(insertStatement);
        writeData(colValueMap);
    }

    private static List<String> parseCreateStatement(String createStatement) {
        String[] parts = createStatement
                .replace("CREATE TABLE (", "")
                .replace(")", "")
                .split(", ");
        return Arrays.asList(parts);
    }

    private static void writeMetadata(List<String> columns) {
        try (BufferedWriter bffWriter = new BufferedWriter(new FileWriter(TABLE_METADATA_FILE))) {
            for (String column : columns) {
                bffWriter.write(column);
                bffWriter.newLine();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        createTableFile();
    }

    private static Map<String, String> parseInsertStatement(String insertStatement) {
        String[] parts = insertStatement
                .replace("INSERT INTO (", "")
                .replace(") VALUES (", ",")
                .replace(")", "")
                .split(",");

        HashMap<String, String> colValueMap = new HashMap<>();
        int maxLength = parts.length/2;
        for (int i = 0; i < maxLength; i++) {
            colValueMap.put(parts[i].trim(), parts[i+maxLength].trim());
        }
        return colValueMap;
    }

    private static void writeData(Map<String, String> colValueMap) {
        try (BufferedReader bffReader = new BufferedReader(new FileReader(TABLE_FILE));
             BufferedWriter bffWriter = new BufferedWriter(new FileWriter(TABLE_FILE, true))) {
            String[] headers = bffReader.readLine().split(";");
            StringBuilder row = new StringBuilder();
            for (String header: headers) {
                row.append(colValueMap.get(header.trim())).append(";");
            }
            bffWriter.write(row.toString());
            bffWriter.newLine();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private static void createTableFile() {
        try (BufferedReader bffReader = new BufferedReader(new FileReader(TABLE_METADATA_FILE));
             BufferedWriter bffWriter = new BufferedWriter(new FileWriter(TABLE_FILE))) {

            StringBuilder header = new StringBuilder();
            String line;
            while ((line = bffReader.readLine()) != null) {
                header.append(line.split(" ")[0]).append(";");
            }

            // Writing header to the TABLE_FILE
            bffWriter.write(header.toString());
            bffWriter.newLine();

        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
