import edu.duke.*;
import org.apache.commons.csv.*;
import java.io.File;
public class NamesData {
    public void totalBirths(FileResource fr){
    int countBoyName=0, countGirlName=0;
    int totalBirth = 0,girlBirth = 0,boyBirth=0;
    for(CSVRecord record : fr.getCSVParser(false)){
        int numBorn = Integer.parseInt(record.get(2));
        totalBirth += numBorn;
        if(record.get(1).contains("F")) {girlBirth += numBorn;countGirlName++;}
        else {boyBirth += numBorn; countBoyName++;}
        }
    System.out.println("The number of children born : "+ totalBirth);
    System.out.println("The number of boys born : "+ boyBirth);
    System.out.println("The number of girls born : "+girlBirth);
    System.out.println("Number of boys' names: "+countBoyName);
    System.out.println("Number of girls' names: "+countGirlName);
    }
    
    public void testTotalBirths(){
    FileResource fr = new FileResource("us_babynames_by_year/yob1900.csv");
    totalBirths(fr);
    }
    
    public int getRank(int year,String name, String gender){
    int rank = 0;
    int flag = 0;
    FileResource fr = new FileResource("us_babynames_by_year/yob"+Integer.toString(year)+".csv");
    for(CSVRecord record : fr.getCSVParser()){
        if(record.get(1).contains(gender)){
            rank++;
        }
        if(record.get(0).contains(name) && record.get(1).contains(gender)) {
            flag=1;
            break;
        }
       }
    if(flag == 0) return -1;
    return rank;
    }
    
    public void testGetRank(){
    int rank = getRank(1960,"Emily","F");
    System.out.println("The rank is :"+ rank);
    }
    
    public String getName(int year, int rank, String gender){
    int count = 0;
    int flag = 0;
    String name = "";
    FileResource fr = new FileResource("us_babynames_by_year/yob"+Integer.toString(year)+".csv");
    for(CSVRecord record : fr.getCSVParser()){
        if(record.get(1).contains(gender)){
            count++;
            if(count == rank){
                flag=1;
                name = record.get(0);
                break;
                }
            }
        }
    if(flag == 0) return "NO NAME";    
    else return name;
    }
    
    public void testGetName(){
    System.out.println(getName(2012,2,"M"));
    }
    
    public void whatIsNameInYear(String name, int year, int newYear, String gender){
    int rank = getRank(year,name,gender);
    String newName = getName(newYear,rank,gender);
    System.out.println(name+" born in "+year+" will be "+newName+" in year "+newYear);
    }
    
    public int yearOfHighestRank(String name,String gender){
    DirectoryResource dr = new DirectoryResource();
    int bestRank=Integer.MAX_VALUE;
    int bestYear=0;
    for(File file: dr.selectedFiles()){
        FileResource fr = new FileResource(file);
        int year = Integer.parseInt(file.getName().substring(3,7));
        int rankThisYear=getRank(year,name,gender);
        if(rankThisYear == -1) continue;
        if(rankThisYear<bestRank) {
            bestRank = rankThisYear;
            bestYear = year;
            }
        }
    return bestYear;
    }
    
    public double getAverageRank(String name,String gender){
        DirectoryResource dr = new DirectoryResource();
        double rankCount=0;
        int count=0;
        for(File file: dr.selectedFiles()){
            count++;
            FileResource fr = new FileResource(file);
            int year = Integer.parseInt(file.getName().substring(3,7));
            int rankThisYear=getRank(year,name,gender);
            if(rankThisYear == -1) continue;
            rankCount += rankThisYear; 
            }
        return rankCount/count;
        }
        
    public int getTotalBirthsRankedHigher(int year,String name,String gender){
        int totalBirthsRankedHigher = 0;
        FileResource fr = new FileResource("us_babynames_by_year/yob"+Integer.toString(year)+".csv");
        for(CSVRecord record: fr.getCSVParser(false)){
            if(!(record.get(0).contains(name)) && record.get(1).contains(gender) ){
                    totalBirthsRankedHigher += Integer.parseInt(record.get(2)); 
            }
            else if (record.get(0).contains(name) && !(record.get(1).contains(gender)))
                    totalBirthsRankedHigher += Integer.parseInt(record.get(2));
           
        }
        return totalBirthsRankedHigher;
    }
}
