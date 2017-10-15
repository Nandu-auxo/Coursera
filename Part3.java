
public class Part3 {
    public boolean twoOccurences(String a,String b){
        int firstIndex = b.indexOf(a);
        if(firstIndex == -1) return false;
        int secondIndex = b.indexOf(a,firstIndex+a.length());
        if(secondIndex == -1) return false;
        return true;
    }
    
    public String lastPart(String stringa, String stringb){
        String result="";
        int startIndex = stringb.indexOf(stringa);
        if(startIndex == -1) return stringb;
        result = stringb.substring(startIndex+stringa.length(),stringb.length());
        return result;
    }
    public void testing(){
        String[] first = {"by","a","atg"}, second = {"A story by Abby Long","banana","ctgtatgta"};
        for(int i = 0; i< 3 ; i++){
            boolean result = twoOccurences(first[i],second[i]);
            System.out.println("String 1: "+first[i]+" "+"String 2: "+second[i]+" : "+result);
        }
        String[] first1 = {"an","zoo"}, second1 = {"banana","forest"};
        for(int i=0;i<2;i++){
            String result = lastPart(first1[i],second1[i]);
            System.out.println("The part of the string after "+first1[i]+" in "+second1[i]+" is "+result);
        }
    }
}

