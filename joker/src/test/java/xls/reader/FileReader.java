package xls.reader;

import config.Application;
import joker.Result;
import joker.domain.DrawResult;
import joker.service.DrawResultService;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.SpringApplicationConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.util.CollectionUtils;

import java.io.*;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;

/**
 * Created by imitsakos on 25/6/2015.
 */

@RunWith(SpringJUnit4ClassRunner.class)
@SpringApplicationConfiguration(classes = Application.class)
public class FileReader {

    private static final Logger logger = LoggerFactory.getLogger(FileReader.class);

    /*@Autowired
    private DrawResultRepository drawResultRepository;*/

    @Autowired
    private DrawResultService drawResultService;

    //@Test
    public void XlsToTxt(){
        List<Result> results = parseXls("Joker_2015.xlsx");
        if (!CollectionUtils.isEmpty(results)) {
            Collections.sort(results, Result.COMPARE_BY_DATE);
            logger.info("{} results for 2015: ", results.size());
            for(Result result : results) {
                DrawResult drawResult = new DrawResult(result.getDrawNum(), result.getNumbers().get(0), result.getNumbers().get(1), result.getNumbers().get(2),
                        result.getNumbers().get(3), result.getNumbers().get(4), result.getJoker(), result.getDate());
                ///drawResultRepository.save(drawResult);
                drawResultService.save(drawResult);
                /*List<Integer> numbers = result.getNumbers();
                Collections.sort(numbers);
                    System.out.println(numbers.get(0) + "," + numbers.get(1) + "," + numbers.get(2) + "," + numbers.get(3) + "," + numbers.get(4) + "," + result.getJoker());
                    //System.out.println(numbers.get(0) + "," + numbers.get(1) + "," + numbers.get(2) + "," + numbers.get(3) + "," + numbers.get(4) + "," + result.getJoker()+ "," + new SimpleDateFormat("dd/MM/yyyy").format(result.getDate()));*/

            }
        }
    }

    //@Test
    public void insertToDb(){

        try {
            File file = new File(Thread.currentThread().getContextClassLoader().getResource("results.txt").getFile());
            FileInputStream fis = new FileInputStream(file);

            DataInputStream in = new DataInputStream(fis);
            BufferedReader br = new BufferedReader(new InputStreamReader(in, "UTF-8"));
            String strLine;


            while ((strLine = br.readLine()) != null) {

                String[] line = strLine.split(",");
                Result result = new Result();
                result.setDrawNum(Long.parseLong(line[0]));
                result.addNumber(Integer.parseInt(line[1]));
                result.addNumber(Integer.parseInt(line[2]));
                result.addNumber(Integer.parseInt(line[3]));
                result.addNumber(Integer.parseInt(line[4]));
                result.addNumber(Integer.parseInt(line[5]));
                result.setJoker(Integer.parseInt(line[6]));
                result.setDate(new SimpleDateFormat("dd/MM/yyyy").parse(line[7]));

                DrawResult drawResult = new DrawResult(result.getDrawNum(), result.getNumbers().get(0), result.getNumbers().get(1), result.getNumbers().get(2),
                        result.getNumbers().get(3), result.getNumbers().get(4), result.getJoker(), result.getDate());
                drawResultService.save(drawResult);

            }

            in.close();
        } catch (Exception e) {
            logger.error("Exception occurred: ", e);
        }

    }

    private List<Result> parseXls(String filename){

        List<Result> results = new ArrayList<>(0);

        try{
            File file = new File(Thread.currentThread().getContextClassLoader().getResource(filename).getFile());
            FileInputStream fis = new FileInputStream(file);

            //Create Workbook instance holding reference to .xlsx file
            XSSFWorkbook workbook = new XSSFWorkbook(fis);

            //Get first/desired sheet from the workbook
            XSSFSheet sheet = workbook.getSheetAt(0);

            //Iterate through each rows one by one
            int rowNum = 0;
            for (Row row : sheet) {
                rowNum++;
                if (rowNum < 4){
                    continue;
                }
                if(rowNum > 107){
                    break;
                }

                Result result = new Result();
                //For each row, iterate through all the columns
                Iterator<Cell> cellIterator = row.cellIterator();

                int cellNum = 0;
                while (cellIterator.hasNext()) {
                    Cell cell = cellIterator.next();
                    cellNum++;

                    if(cellNum == 1){
                        result.setDrawNum(Long.parseLong(cell.getStringCellValue()));
                    }

                    if(cellNum == 2){
                        result.setDate(new SimpleDateFormat("dd/MM/yyyy").parse(cell.getStringCellValue()));
                    }

                    if(cellNum > 2 && cellNum < 8) {
                        result.addNumber(Integer.parseInt(cell.getStringCellValue()));
                    }

                    if(cellNum == 8){
                        result.setJoker(Integer.parseInt(cell.getStringCellValue()));
                    }
                }

                results.add(result);
            }
            fis.close();
        } catch (Exception e) {
            logger.error("Exception occurred: ", e);
        }

        return results;
    }
}
