
public class Part1 {
    
   public String findSimpleGene(String dna){
       String gene = "";
       int startIndex = dna.indexOf("ATG");
       int endIndex = dna.indexOf("TAA",startIndex+3);
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
           gene = findSimpleGene(temp);
           System.out.println("The gene in dna "+temp+" is "+gene);
        }
       
    }
}

