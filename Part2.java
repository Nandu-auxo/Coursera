
/**
 * Write a description of Part2 here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class Part2 {
    public int countOf(String dna , String t){
        int startIndex=0,indexOfToCount=-1,countOfToCount=0;
        do{
           indexOfToCount = dna.indexOf("C",startIndex);
           if(indexOfToCount == -1 || indexOfToCount == dna.length()) break;
           countOfToCount++; startIndex = indexOfToCount+1;
        }while(indexOfToCount !=-1);
        return countOfToCount;
    }
    
    public float cgRatio(String dna){
    int countOfC=countOf(dna,"C");
    int countOfG=countOf(dna,"G");
    float ratio = ((float)countOfC+countOfG)/dna.length();
    return ratio;
    }
    
    public void testCgRatio(){
    float ratio = cgRatio("ATGCCATAG");
    System.out.println("Ratio :"+ratio);
    }
    
    public int countCtg(String dna){
    int count=0,startIndex=0,index=-1;
    do{
        index = dna.indexOf("CTG",startIndex);
        if(index == -1 ||  index > dna.length()-3) break;
        count++; startIndex = index+3;
      }while(index!=-1);
    return count;
    }
    
    public void testCountCtg(){
    int count = countCtg("ATGCTGCTGATACTG");
    System.out.println("count:" + count);
    }
}
