import edu.duke.*;
public class Part3 {
public int countOf(String dna , String toCount){
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

public void processGenes(StorageResource sr){
    int count9 =0, count35=0,longest=0;
    System.out.println("genes of length more than 9 :");
    for(String gene : sr.data()){
       if(gene.length()>9){
          System.out.println(gene);
          count9++;
       }
    } 
    System.out.println("Count of genes of length >9: "+count9);
    System.out.println("genes of cg ratio>0.35");
    for(String gene : sr.data()){
       if(cgRatio(gene)>0.35){
         System.out.println(gene);   
         count35++;
        }
    }
    System.out.println("Count of genes of cgRation>0.35"+count35);
    for(String gene : sr.data()){
       if(longest<gene.length()) longest = gene.length();
    }
    System.out.println("length of longest gene :"+longest);  
  
  }
  
  public void testProcessGenes(){
    StorageResource sr = new StorageResource();
    sr.add("CGTAAATCTG");
    sr.add("CGCGCGCGCG");
    sr.add("CGTA");
    sr.add("ATCGATTTAAGAAT");
    processGenes(sr);
    }
}
