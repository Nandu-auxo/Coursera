
public class OOCaeserCypher {
    private String alphabets;
    private String shiftedAlphabets;
    private int mainKey;
    
    public OOCaeserCypher(int key){
        alphabets = "abcdefghijklmnopqrstuvwxyz";
        shiftedAlphabets = alphabets.substring(key) + alphabets.substring(0,key);
        mainKey = key;
    }
    
    public String encrypt(String message){
        StringBuilder sb = new StringBuilder(message);
        for(int i=0;i<sb.length();i++){
            char ch = sb.charAt(i);
            if(Character.isLowerCase(ch))  {
                alphabets= alphabets.toLowerCase();
                shiftedAlphabets= shiftedAlphabets.toLowerCase();
            }
            else if(Character.isUpperCase(ch))  {
                alphabets= alphabets.toUpperCase();
                shiftedAlphabets= shiftedAlphabets.toUpperCase(); 
            }
            int index = alphabets.indexOf(ch);
            if(index!=-1){
                char newch = shiftedAlphabets.charAt(index);
                sb.setCharAt(i,newch);
            }
        }
        return sb.toString();
    }    
    
    public String decrypt(String input){
        OOCaeserCypher cc = new OOCaeserCypher(26-mainKey);
        return cc.encrypt(input);
    }
}
