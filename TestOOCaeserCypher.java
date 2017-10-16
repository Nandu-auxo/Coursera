import edu.duke.*;
public class TestOOCaeserCypher {
    
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
    
    public void breakCaeserCypher(String input){
        int[] freqs = countLetters(input);
        int maxDex = maxIndex(freqs);
        int dkey = maxDex - 4;
        if(maxDex < 4)
            dkey = 26-(4-maxDex);
        OOCaeserCypher cc = new OOCaeserCypher(dkey);
        System.out.println("The decrypted message is :\n"+ cc.decrypt(input));
    }
    
    public void simpleTests(){
       // FileResource fr = new FileResource();
        //String message = fr.asString();
        String message = "Can you imagine life WITHOUT the internet AND computers in your pocket?";
        OOCaeserCypher cc = new OOCaeserCypher(15);
        String encrypted = cc.encrypt(message);
        System.out.println("The encrypted message is " + encrypted);
       // System.out.println("The decrypted message is " + cc.decrypt(encrypted));
       // breakCaeserCypher(message);
    }
}
