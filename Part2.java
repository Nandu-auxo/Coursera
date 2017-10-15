import java.lang.*;
public class Part2 {
    public String findSimpleGene(String dna, String startCodon, String stopCodon){
       String gene = "";
       int startIndex = dna.indexOf(startCodon);
       int endIndex = dna.indexOf(stopCodon,startIndex+3);
       if(startIndex == -1 || endIndex == -1) return "";
       gene = dna.substring(startIndex,endIndex+3);
       if((gene.length())%3 != 0) return "";
       return gene;
    }
    
   public void testSimpleGene(){
       String[] a = {"ATCTGTAACTAC","ATCATGCAAGTT","ATACGT","GATGCGATAAGT",
                       "GATGCGTAAGTC"};
       String gene = "";
       for(String temp:a){
           System.out.println("The dna : "+temp);
           gene = findSimpleGene(temp,"ATG","TAA");
           System.out.println("The gene in dna "+temp+" is "+gene);
        }
       
    }

}
