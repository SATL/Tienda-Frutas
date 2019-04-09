package controllers;

import com.opencsv.CSVReader;
import dommain.Product;

import java.io.IOException;
import java.io.Reader;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public abstract class CSVRecordLoader<T> {

    protected List<String[]> loadRecord(String filePath) throws IOException {
        Reader reader = Files.newBufferedReader(Paths.get(filePath));
        CSVReader csvReader = new CSVReader(reader, ',', '"', 1);
        return csvReader.readAll();
    }

    public List<T> parseCSV(String filePath) throws IOException {
        List<T> map = new ArrayList<>();

        for (String[] record : this.loadRecord(filePath)) {
                map.add(parseRow(record));
        }
        return map;
    }

    protected abstract  T parseRow(String[] row);
}
