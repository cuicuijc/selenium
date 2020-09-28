package webDriver3.chapter12;

import com.selenium.Log.Log;
import org.apache.poi.hssf.usermodel.HSSFDateUtil;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

public class ExcelUtil {


    public static Object[][] getTestData(String excelFilePath, String sheetName) throws IOException {
        File file=new File(excelFilePath);
        FileInputStream inputStream=new FileInputStream(file);

        Workbook workbook;

        String fileExtensionName=excelFilePath.substring(excelFilePath.indexOf("."));

        if(fileExtensionName.equals(".xlsx")){
            workbook=new XSSFWorkbook(inputStream);
        }else{
            workbook=new HSSFWorkbook(inputStream);
        }

        Sheet sheet=workbook.getSheet(sheetName);

        int rowCount=sheet.getLastRowNum()-sheet.getFirstRowNum();
        
        List<Object[]> records=new ArrayList<Object[]>();
        SimpleDateFormat sFormat=new SimpleDateFormat("yyyy-mm-dd");
        Cell cell;

        for(int i=1;i<rowCount;i++){
            Row row=sheet.getRow(i);
            String feiles[]=new String[row.getLastCellNum()];
            for(int j=0;j<row.getLastCellNum();j++){
                cell=row.getCell(j);
                if(cell!=null){
                    if(cell.getCellType()==0){
                        if(HSSFDateUtil.isCellDateFormatted(cell)){
                            feiles[j]=sFormat.format(cell.getDateCellValue());
                        }
                    }
                    if(cell.getCellType()==1){
                        row.getCell(j).setCellType(XSSFCell.CELL_TYPE_STRING);
                        feiles[j]=row.getCell(j).getStringCellValue();
                    }
                }

            }

            records.add(feiles);
        }

        Object object[][]=new Object[records.size()][];
        for(int i=0;i<records.size();i++){
            object[i]=records.get(i);
        }

        return object;
    }

}
