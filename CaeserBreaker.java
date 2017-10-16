
import edu.duke.*;
public class CaeserBreaker {
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
    
    public String decrypt(String encrypted){
        CaeserCypher cc = new CaeserCypher();
        int[] freqs = countLetters(encrypted);
        int maxDex = maxIndex(freqs);
        int dkey = maxDex - 4;
        if(maxDex < 4)
            dkey = 26-(4-maxDex);
        return cc.encrypt(encrypted,26-dkey);
    }
    
    public void testDecrypt(){
        String encrypted = " Gwpv c vbuq pvokki yfve iqqu qc bgbgbgbgbgbgbgbgbu";
        String decrypted = decrypt(encrypted);
        System.out.println(decrypted);
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
    
    public String decryptTwoKeys(String encrypted){
        String firstHalf = halfOfString(encrypted,0);
        String secondHalf = halfOfString(encrypted,1);
        int firstKey = getKey(firstHalf);
        int secondKey = getKey(secondHalf);
        System.out.println("The two keys are "+ firstKey + " and " + secondKey);
        CaeserCypher cc = new CaeserCypher();
        return cc.encryptTwoKeys(encrypted , 26-firstKey, 26-secondKey);
    }
    
    public void testDecryptTwoKeys(){
        //String encrypted = "Akag tjw Xibhr awoa aoee xakex znxag xwko";
        FileResource fr = new FileResource("mysteryTwoKeysPractice.txt");
        String encrypted = fr.asString();
        System.out.println(decryptTwoKeys(encrypted));
    }
}
