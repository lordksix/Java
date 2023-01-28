package csvProject;

import edu.duke.*;
import org.apache.commons.csv.*;


public class WhichCountriesExport {
    public void listExporters(CSVParser parser, String exportsOfInterest){
        for(CSVRecord record: parser){
            String export = record.get("Exports");
            if(export.contains(exportsOfInterest)){ //export.indexOf(exportsOfInterest) != -1
                String country = record.get("Country");
                System.out.println(country);
            }
        }
    }
    
    public void whoExportsCoffee(){
        FileResource fr = new FileResource();
        CSVParser parser = fr.getCSVParser();
        listExporters(parser, "coffee");
    }

    public CSVParser tester(){
        FileResource fr = new FileResource();
        CSVParser parser = fr.getCSVParser();
        return parser;
    }

    public String countryInfo(CSVParser parser, String country){
        String info="";
        for(CSVRecord record: parser){
            String countryRecord= record.get("Country");
            if (countryRecord.contains(country)){
                String export = record.get("Exports");
                String value = record.get("Value (dollars)");
                System.out.print(country + ": ");
                System.out.print(" "+ export);
                System.out.println(": "+ value);
            }
        }
        return info;
    }

    public void listExportersTwoproducts(CSVParser parser, String exportItem1,String exportItem2){
        for(CSVRecord record: parser){
            String export = record.get("Exports");
            if(export.contains(exportItem1)&&export.contains(exportItem2)){ //export.indexOf(exportsOfInterest) != -1
                String country = record.get("Country");
                System.out.println(country);
            }
        }
    }

    public void numberOfExpoters(CSVParser parser, String exportItem){
        int counter = 0;
        for(CSVRecord record: parser){
            String export = record.get("Exports");
            if(export.contains(exportItem)){ //export.indexOf(exportsOfInterest) != -1
                counter++;
            }
        }
        System.out.println(counter);
    }

    public void bigExporters(CSVParser parser, String amount ){
        for(CSVRecord record: parser){
            String counAmount = record.get("Value (dollars)");
            if(counAmount.length()>amount.length()){ //export.indexOf(exportsOfInterest) != -1
                String country = record.get("Country");
                System.out.println(country + " "+ counAmount);
            }
        }
    }

    public static void main(String[] args) {
        WhichCountriesExport hw = new WhichCountriesExport();
        //hw.whoExportsCoffee();
        CSVParser parser = hw.tester();
        //hw.countryInfo(parser, "Nauru");
        //hw.listExportersTwoproducts(parser, "gold", "diamonds");
        hw.numberOfExpoters(parser, "sugar");
        //hw.listExporters(parser, "gold");
        //hw.bigExporters(parser, "$999,999,999,999");
    }
}
