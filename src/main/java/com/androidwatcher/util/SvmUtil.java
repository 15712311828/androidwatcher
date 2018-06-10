package com.androidwatcher.util;

import jxl.Cell;
import jxl.NumberCell;
import jxl.Sheet;
import jxl.Workbook;
import libsvm.svm;
import com.androidwatcher.util.svm.*;
import libsvm.svm_model;
import lombok.SneakyThrows;
import org.apache.commons.io.FileUtils;

import java.io.File;
import java.io.FileInputStream;
import java.io.InputStream;
import java.net.URL;
import java.nio.charset.Charset;
import java.util.ArrayList;
import java.util.List;

public class SvmUtil {

    public static void main(String[] args) {
        //XlsxToTxt();
        train();
        //predict();
    }

    @SneakyThrows
    public static void XlsxToTxt(){
            InputStream inputStream = new FileInputStream("data.xls");
            Workbook workbook = Workbook.getWorkbook(inputStream);
            Sheet sheet = workbook.getSheet(0);
            int rsColumns = sheet.getColumns();
            int rsRows = sheet.getRows();
            List<String> datas=new ArrayList<>();
            for (int i = 0; i < rsRows; i++)
            {
                String data="";
                for (int j = 0; j < rsColumns; j++)
                {
                    NumberCell cell = (NumberCell)sheet.getCell(j, i);
                    if(j<rsColumns-1) {
                        data += " "+j+":"+cell.getValue();
                    }
                    else{
                        data=cell.getContents()+data;
                    }
                }
                datas.add(data);
            }
            FileUtils.writeLines(new File("data.txt"),datas);
        }

    @SneakyThrows
    public static void train(){
        String[] str_trained = {"-g","2.0","-c","32","-t","2","-m","500.0","-h","0","data.txt"};
        SvmTrain.main(str_trained);
    }

    @SneakyThrows
    public static double predict(String data){
        String url = SvmUtil.class.getResource("/")+"data.txt.model";
        if(url.startsWith("file:/")){
            url=url.substring(6);
        }
        System.out.println(url);
        svm_model svm_model = svm.svm_load_model(url);
        double predict = SvmPredict.predict(data, svm_model);
        return predict;
    }
}
