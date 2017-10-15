import edu.duke.*;
public class Part1 {
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
    
   public void testFindStopCodon(){
       String dna = "CATGAAAGTTAAGTAAG";
       String stopCodon = "TAA";
       int startIndex = dna.indexOf("ATG");
       int index = findStopCodon(dna,startIndex,stopCodon);
       if(index != dna.length()) {
           System.out.println("The gene is "+dna.substring(startIndex,index+3));
        }
       else System.out.println("no gene");
    }
    
   public void testFindGene(){
       String[] dna = {"ATCGTAAC","ATGTAA","ATGCTCTAGTAAG","ATGCCTTT"};
       for(int i=0;i<4;i++){
           System.out.println("String:"+dna[i]);
           System.out.println("Gene:"+findGene(dna[i],dna[i].indexOf("ATG")));
        }
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
    
    public StorageResource getAllGene(String dna){
        StorageResource store = new StorageResource();
        int length = dna.length();
       int startIndex = dna.indexOf("ATG");
       while(true){
           String gene = findGene(dna,startIndex);
           if(gene == "") break;
           store.add(gene);
           startIndex = startIndex+gene.length();
           startIndex = dna.indexOf("ATG",startIndex);
        }
        return store;
    }
    
    public void getAllGene(){
    String dna = "ATGCTCTAGATGCTGTAAG";
    StorageResource allGenes = getAllGene(dna);
    for(String gene: allGenes.data())
        System.out.println(gene);
    }
}
