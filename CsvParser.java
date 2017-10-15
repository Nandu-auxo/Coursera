
import edu.duke.*;
import org.apache.commons.csv.*;
public class CsvParser {
    public String countryInfo(CSVParser parser, String country){
    String found = "NOT FOUND";
    for(CSVRecord record : parser){
        String countries = record.get("Country");
        if(countries.contains(country)){
            found = country;
            String exports = record.get("Exports");
            String dollar = record.get("Value (dollars)");
            found = found + " : " + exports + " : " + dollar;
             }
        }
        return found;
    }
    
    public void listExportersTwoProducts(CSVParser parser, String exportItem1,String exportItem2){
    System.out.println("the countries are:");
    for(CSVRecord record: parser){
        String country = "";
        String exports = record.get("Exports");
        if(exports.contains(exportItem1) && exports.contains(exportItem2)) {
                country = record.get("Country");
                System.out.println(" "+country+"");
            }
        }
    }
    
    public int numberOfExporters(CSVParser parser, String export){
    int count = 0;
    for(CSVRecord record : parser){
        String exports = record.get("Exports");
        if(exports.contains(export)) count++;
        }
     return count;
    }
    
    public void bigExporters(CSVParser parser, String amount){
        int length = amount.length();
        String country = "";
        System.out.println("Countries having more than "+amount);
        for(CSVRecord record : parser){
            String amountInDollar = record.get("Value (dollars)");
            if(amountInDollar.length()> length){
             country = record.get("Country");
             System.out.println(country+ " "+amountInDollar);
            }
        }
    }
    
    public void tester(){
        FileResource fr = new FileResource();
        CSVParser parser = fr.getCSVParser();
      /*  String found = countryInfo(parser,"Nauru");
        System.out.println(found);
       /* parser = fr.getCSVParser();
        listExportersTwoProducts(parser,"cotton","flowers");
        /*parser = fr.getCSVParser();
        System.out.println("The number of countries having export of cocoa: "+numberOfExporters(parser,"cocoa"));
        /*parser = fr.getCSVParser(); */       
        bigExporters(parser, "$999,999,999,999");
    }
}
