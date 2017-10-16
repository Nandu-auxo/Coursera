
import edu.duke.*;
public class CaeserCypher {
    public String encrypt(String message, int key){
        StringBuilder sb = new StringBuilder(message);
        String alphabets = "ABCDEFGHIJKLMNOPQRSTUVWXYZ";
        String newString = alphabets.substring(key) + alphabets.substring(0,key);
        for(int i=0;i<sb.length();i++){
            char ch = sb.charAt(i);
            if(Character.isLowerCase(ch))  {
                alphabets= alphabets.toLowerCase();
                newString= newString.toLowerCase();
            }
            else if(Character.isUpperCase(ch))  {
                alphabets= alphabets.toUpperCase();
                newString= newString.toUpperCase(); 
            }
            int index = alphabets.indexOf(ch);
            if(index!=-1){
                char newch = newString.charAt(index);
                sb.setCharAt(i,newch);
            }
        }
        return sb.toString();
    }
    
    public void testCaeser(){
        //FileResource fr = new FileResource();
        String message = "At noon be in the conference room with your hat on for a surprise party. YELL LOUD!";
        int key=15;
        String encrypted = encrypt(message, key);
        System.out.println("key is " + key + "\n" + encrypted);
    }
    
    public String encryptTwoKeys(String message,int key1,int key2){
        String result = "";
        StringBuilder sb = new StringBuilder(result);
        for(int i=0; i<message.length(); i++){
            char ch = message.charAt(i);
            String temp = Character.toString(ch);
            if(i%2 == 0)
                sb.append(encrypt(temp,key1));
            else
                sb.append(encrypt(temp,key2));
        }
        return sb.toString();
    }
    
    public void testEncryptTwoKeys(){
    String message="Top ncmy qkff vi vguv vbg ycpx";
    String encrypted = encryptTwoKeys(message,24,6);
    System.out.println("String is \n" + encrypted);
    }
}
