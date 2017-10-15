import edu.duke.*;
public class Part3Extended {
    
    public int findStopCodon(String dna,int startIndex,String stopCodon){
        
        int newIndex = startIndex;
        int indexStopCodon= dna.indexOf(stopCodon,startIndex);
        if(indexStopCodon == -1) 
            return dna.length();
        else if((indexStopCodon - startIndex)%3 != 0) 
            newIndex=indexStopCodon+1;
        else 
            return indexStopCodon;
        while(true)
        {   
            indexStopCodon = dna.indexOf(stopCodon,newIndex);
            if(indexStopCodon == -1) return dna.length();
            else if((indexStopCodon - startIndex)%3 != 0) newIndex=indexStopCodon+1;
            else return indexStopCodon;
        }
        
   }
   
   public String findGene(String dna,int startIndex){
       
       int length = dna.length();
       if(startIndex == -1) return "";
       int min = -1;
       int indexOfTaa = findStopCodon(dna,startIndex,"TAA");
       int indexOfTag = findStopCodon(dna,startIndex,"TAG");
       int indexOfTga = findStopCodon(dna,startIndex,"TGA");
       if(indexOfTaa == length && indexOfTag == length && indexOfTga == length) 
           return "";
                
       if(indexOfTaa<=indexOfTag) 
           min=indexOfTaa;
       else 
           min=indexOfTag;
           
       if(indexOfTga<min) 
           min=indexOfTga;
           
       if(min == length-4) 
           return dna.substring(startIndex);
           
       if(min == -1) 
           return "";
           
       return dna.substring(startIndex,min+3);
    }
    
    public int countOf(String dna , String toCount){
        
        int startIndex=0,indexOfToCount=-1,countOfToCount=0;
        do{
           indexOfToCount = dna.indexOf(toCount,startIndex);
           if(indexOfToCount == -1 || indexOfToCount == dna.length()) 
               break;
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
        System.out.println("genes of length more than 60 :");
        for(String gene : sr.data()){
            if(gene.length()>60){
                System.out.println(gene);
                count9++;
            }
        }    
        System.out.println("Count of genes of length >60: "+count9);
        System.out.println("genes of cg ratio>0.35");
        for(String gene : sr.data()){
            if(cgRatio(gene)>0.35){
                System.out.println(gene);   
                count35++;
            }
        }
        System.out.println("Count of genes of cgRation>0.35: "+count35);
        for(String gene : sr.data()){
             if(longest<gene.length()) longest = gene.length();
            }
        System.out.println("length of longest gene :"+longest);  
  
    }
    
    public int countCtg(String dna){
    int count=0,startIndex=0,index=-1;
    int length = dna.length();
    do{
        index = dna.indexOf("CTG",startIndex);
        if(index == -1 ||  index > length-3) break;
        count++; startIndex = index+3;
      }while(index!=-1);
    return count;
    }
    
    public void getAllGene(){
       FileResource fr = new FileResource("GRch38dnapart.fa");
       String dna = fr.asString();
       StorageResource sr= new StorageResource();
       int startIndex = dna.indexOf("ATG");
       while(startIndex != -1){
           String gene = findGene(dna,startIndex);
           if(gene == "") {
               startIndex = dna.indexOf("ATG",startIndex+3);
               if(startIndex == -1) 
                    break;
           } 
           else{
                sr.add(gene);
                startIndex = dna.indexOf("ATG",startIndex+gene.length());
               }
        }
        System.out.println("The number of genes : "+sr.size());
        processGenes(sr);
        System.out.println(countCtg(dna));
    }
    
}
