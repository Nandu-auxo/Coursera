
import edu.duke.*;
import org.apache.commons.csv.*;
import java.io.File;
public class CSVsecondAssignment {

    public CSVRecord coldestHourInFile(CSVParser parser){
    CSVRecord coldestTemperature = null ;
    double coldest= -Double.MAX_VALUE;
    double temperature = -Double.MAX_VALUE;
    int flag=1;
    for(CSVRecord record : parser){
        temperature = Double.parseDouble(record.get("TemperatureF"));  
        if(temperature == -9999) continue;
        if(flag==1){
        coldest = temperature;
        coldestTemperature = record;
        flag=0;
        }
        if(temperature<coldest) {
            coldest=temperature;
            coldestTemperature = record;
        }
       }
    return coldestTemperature;
    }

 public void testColdestHourInFile(){
     FileResource fr = new FileResource();
     CSVParser parser = fr.getCSVParser();
     CSVRecord record = coldestHourInFile(parser);
     System.out.println("Coldest hour: "+record.get("DateUTC")+":"+record.get("TemperatureF"));
    }
    
 public String fileWithColdestTemperature(){
     CSVRecord coldestInFile = null; 
     CSVRecord coldestFile=null;
     File cold= null;
     double coldest= -Double.MAX_VALUE;
     double temperature = -Double.MAX_VALUE;
     int flag=1;
    DirectoryResource dr = new DirectoryResource();
    for(File file: dr.selectedFiles()){
        FileResource fr = new FileResource(file);
        CSVParser parser = fr.getCSVParser();
        coldestInFile = coldestHourInFile(parser);
        temperature = Double.parseDouble(coldestInFile.get("TemperatureF"));
        if(temperature == -9999) continue;
        if(flag ==1){
            coldest = temperature;
            coldestFile = coldestInFile;
            cold= file;
            flag=0;
           }
        else   {
            
            if(temperature<coldest){
                coldest= temperature;
                coldestFile = coldestInFile;
                cold = file;
            }
           }
       }
       return cold.getName();
    }
    
    public void testFileWithColdestTemperature(){
    String fileName = fileWithColdestTemperature();
    System.out.println("File:"+fileName);
    File file = new File("data/2013/"+fileName);
    FileResource fr = new FileResource(file);
    CSVParser parser = fr.getCSVParser();
    CSVRecord coldestInFile = coldestHourInFile(parser);
    System.out.println("The coldest hour was"+coldestInFile.get("TemperatureF"));
    }
    
    public CSVRecord lowestHumidityInFile(CSVParser parser){
    CSVRecord lowestHumidity = null ;
    double lowest= -Double.MAX_VALUE;
    double humidity = -Double.MAX_VALUE;
    int flag=1;
    for(CSVRecord record : parser){
        String data = record.get("Humidity");
        if(data.contains("N/A")) continue;
        humidity = Double.parseDouble(data);        
        if(flag==1){
        lowest = humidity;
        lowestHumidity = record;
        flag=0;
        }
        if(humidity<lowest) {
            lowest=humidity;
            lowestHumidity = record;
        }
       }
    return lowestHumidity;
    }
    
    public void testLowestHumidityInFile(){
    FileResource fr = new FileResource();
    CSVParser parser = fr.getCSVParser();
    CSVRecord csv = lowestHumidityInFile(parser);
    System.out.println("The lowest humidity was "+csv.get("Humidity")+" on "+csv.get("DateUTC"));
    }
    
    public CSVRecord lowestHumidityInManyFiles(){
        CSVRecord lowestHumidityInThisFile= null;
        CSVRecord lowestHumidityFile= null;
        int flag= 1;
        double lowestHumidity = -Double.MAX_VALUE;
        double humidity = -Double.MAX_VALUE;
        DirectoryResource dr = new DirectoryResource();
        for(File file : dr.selectedFiles()){
        FileResource fr = new FileResource(file);
        CSVParser parser = fr.getCSVParser();
        lowestHumidityInThisFile = lowestHumidityInFile(parser);
        String data = lowestHumidityInThisFile.get("Humidity");
        //if(data == "N/A") continue;
        humidity = Double.parseDouble(data);
        if(flag ==1){
            lowestHumidity = humidity;
            lowestHumidityFile = lowestHumidityInThisFile;
            flag=0;
           }
        else   {
            
            if(humidity<lowestHumidity){
                lowestHumidity= humidity;
                lowestHumidityFile = lowestHumidityInThisFile;
            }
           }       
        
        }
        return lowestHumidityFile;
    }
    
    public void testLowestHumidityInManyFiles(){
    CSVRecord record = lowestHumidityInManyFiles();
    System.out.println("The lowest humidity is "+record.get("Humidity")+" on "+record.get("DateUTC"));
    }
    
    public double averageTemperatureInFile(CSVParser parser){
    double sum = 0;
    double temperature = -Double.MAX_VALUE;
    int count = 0;
    int flag=1;
    for(CSVRecord record : parser){
        temperature = Double.parseDouble(record.get("TemperatureF"));        
        sum = sum+temperature;
        count++;
        }
    return sum/count;
    }  
    
    public void testAverageTemperatureInFile(){
    FileResource fr = new FileResource();
    CSVParser parser = fr.getCSVParser();
    System.out.println("Average temperatur in file is "+averageTemperatureInFile(parser));
    }
    
    public double averageTemperatureWithHighHumidityInFile(CSVParser parser,int value){
    double sum = 0,humidity= -Double.MAX_VALUE;
    double temperature = -Double.MAX_VALUE;
    int count = 0;
    int flag=0;
    for(CSVRecord record : parser){
        humidity = Double.parseDouble(record.get("Humidity"));
        if(humidity>= value){
            flag = 1;
            temperature = Double.parseDouble(record.get("TemperatureF"));        
            sum = sum+temperature;
            count++;
            }
        } 
    if(flag == 0) return -Double.MAX_VALUE;
    return sum/count;
    }
    
    public void testAverageTemperatureWithHighHumidityInFile(){
    FileResource fr = new FileResource();
    CSVParser parser = fr.getCSVParser();
    double average = averageTemperatureWithHighHumidityInFile(parser,80);
    if(average == -Double.MAX_VALUE) 
         System.out.println("No temperatures with that humidity");
    else     
         System.out.println("Average temperature with high humidity in file is "+ average);   
    }
}