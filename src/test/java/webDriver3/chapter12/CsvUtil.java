package webDriver3.chapter12;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class CsvUtil {
    public static Object[][] getTestData(String fileName) throws IOException {
        List<Object[]> records=new ArrayList<Object[]>();
        String record;

        BufferedReader file=new BufferedReader(new InputStreamReader(new FileInputStream(fileName),"UTF-8"));
        while ((record=file.readLine())!=null){
            String fields[]=record.split("，");
            records.add(fields);
        }
        file.close();


        Object[][] results=new Object[records.size()][];
        for(int i=0;i<records.size();i++){
            results[i]=records.get(i);
        }

        return results;
    }
}
