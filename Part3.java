
public class Part3 {
    public int findStopCodon(String dna,int startIndex,String stopCodon){
        int length = dna.length();
        int indexStopCodon;
        while(true)
        {   
            indexStopCodon = dna.indexOf(stopCodon,startIndex);
            if(indexStopCodon == -1) return length;
            else if((indexStopCodon - startIndex)%3 != 0) startIndex=indexStopCodon+1;
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
       if(indexOfTaa == length && indexOfTag == length && indexOfTga == length) return "";
       if(indexOfTaa<=indexOfTag) min=indexOfTaa; else min=indexOfTag;
       if(indexOfTga<min) min=indexOfTga;
       if(min == length-3) return dna.substring(startIndex);
       if(min == -1) return "";
       return dna.substring(startIndex,min+3);
    }
    
    public void printAllGene(){
       String dna = "ATGCTCTAGATGCTGTAAG";
       int length = dna.length();
       int startIndex = dna.indexOf("ATG");
       while(true){
           String gene = findGene(dna,startIndex);
           if(gene == "") break;
           System.out.println(gene);
           startIndex = startIndex+gene.length();
           startIndex = dna.indexOf("ATG",startIndex);
        }
    }
    
    public int countGene(String dna){
       int count=0;
       int length = dna.length();
       int startIndex = dna.indexOf("ATG");
       while(true){
           String gene = findGene(dna,startIndex);
           if(gene == "") break;
           count++;
           startIndex = startIndex+gene.length();
           startIndex = dna.indexOf("ATG",startIndex);
        }
        return count;
    }
    
    public void testCountGene(){
        String dna = "ATGCTCTAGATGCTGTAAG";
        int count = countGene(dna);
        System.out.println("count:"+count);
    }
}
