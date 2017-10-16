
import edu.duke.*;
import java.util.*;
public class UsingHashMap {
    private HashMap<String,Integer> map;
    public UsingHashMap(){
        map = new HashMap<String,Integer>();
    }
    
    public void buildCodonMap(int start, String dna){
        map.clear();
        for(;start + 3 <= dna.length(); start = start+3){
            String codon = dna.substring(start,start+3);
            if(!Character.isLetter(codon.charAt(2))) continue;
            if(map.containsKey(codon)){
                int count = map.get(codon);
                map.put(codon,count+1);
            }
            else
                map.put(codon,1);
        }        
    }
    
    public String getMostCommonCodon(){
        int maxCount = 0;
        String mostCommon = "";
        for(String keys : map.keySet()){
            if(map.get(keys)> maxCount){
                maxCount = map.get(keys);
                mostCommon = keys;
            }
        }
        return mostCommon;        
    }
    
    public void printCodonCounts(int start, int end){
        for(String keys : map.keySet()){
            if(map.get(keys)>=start && map.get(keys)<=end){
                System.out.println("Codon "+ keys + " : "+ map.get(keys));
            }
        }
    }
    
    public void tester(){
        FileResource fr = new FileResource();
        String dna = fr.asString().toUpperCase();
        //String dna = "CGTTCAAGTTCAA";
        for(int i = 0; i< 3 ;i++){
            buildCodonMap(i,dna);
            System.out.println("Number of unique codons for "+ i +" index starting : "+ map.size());
            String mostCommon = getMostCommonCodon();
            System.out.println("The most common codon is "+ mostCommon + " with count as "+ map.get(mostCommon)); 
            System.out.println("The common codons of count between 1 to 7 are :");
            printCodonCounts(1,7);
        }
    }
}
