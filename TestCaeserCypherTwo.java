
import edu.duke.*;
public class TestCaeserCypherTwo {
    
    public int[] countLetters(String message){
        String alphabets = "abcdefghijklmnopqrstuvwxyz";
        int[] counts = new int[26];
        for(int k=0; k<message.length();k++){
            char ch = Character.toLowerCase(message.charAt(k));
            int dex = alphabets.indexOf(ch);
            if(dex != -1)
                counts[dex] += 1;
        }
        return counts;
    }
    
    public int maxIndex(int[] values){
        int maxDex= 0;
        for(int k=0; k < values.length; k++)
            if(values[k] > values[maxDex])
                maxDex = k;
        return maxDex;
    }
    
    public String halfOfString(String message, int start){
        String half = "";
        StringBuilder sb = new StringBuilder(half);
        int length = message.length();
        for(int i=start; i<length; i+=2){
            sb.append(message.charAt(i));
        }
        return sb.toString();
    }
    
    public int getKey(String message){
        int[] counts = countLetters(message);
        int indexOfE = maxIndex(counts);
        int key = indexOfE - 4;
        if(indexOfE < 4)
            key = 26-(4-indexOfE);
        return key;
    }
    
    public void breakCaeserCypher(String encrypted){
        String firstHalf = halfOfString(encrypted,0);
        String secondHalf = halfOfString(encrypted,1);
        int firstKey = getKey(firstHalf);
        int secondKey = getKey(secondHalf);
        System.out.println("The two keys are "+ firstKey + " and " + secondKey);
        OOCaeserCypherTwo cc = new OOCaeserCypherTwo(firstKey,secondKey);
        System.out.println("Decrypted message :"+ cc.decrypt(encrypted));
    }
    
    public void simpleTests(){
        FileResource fr = new FileResource("mysteryTwoKeysQuiz.txt");
        String encrypted = fr.asString();
       // String message = "Hfs cpwewloj loks cd Hoto kyg Cyy.";
       // OOCaeserCypherTwo cc = new OOCaeserCypherTwo(12,2);
       // String encrypted = cc.encrypt(message);
       // String encrypted = "Aal uttx hm aal Qtct Fhljha pl Wbdl. Pvxvxlx!";
        //System.out.println("Encrypted :"+ encrypted);
        //String decrypted = cc.decrypt(encrypted);
        //System.out.println("Decrypted :"+ decrypted);
        breakCaeserCypher(encrypted);
    } 
}
