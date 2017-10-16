
public class OOCaeserCypherTwo {
    private String alphabet;
    private String shiftedAlphabet1;
    private String shiftedAlphabet2;
    int key1;
    int key2;
    
    OOCaeserCypherTwo(int key1,int key2){
        alphabet = "abcdefghijklmnopqrstuvwxyz";
        shiftedAlphabet1 = alphabet.substring(key1) + alphabet.substring(0,key1);
        shiftedAlphabet2 = alphabet.substring(key2) + alphabet.substring(0,key2);
        this.key1 = key1;
        this.key2 = key2;
    }
    
    public String encrypt(String message){
        StringBuilder sb = new StringBuilder(message);
        for(int i=0;i<sb.length();i++){
            char ch = sb.charAt(i);
            if(Character.isLowerCase(ch))  {
                alphabet = alphabet.toLowerCase();
                shiftedAlphabet1= shiftedAlphabet1.toLowerCase();
                shiftedAlphabet2= shiftedAlphabet2.toLowerCase();
            }
            else if(Character.isUpperCase(ch))  {
                alphabet= alphabet.toUpperCase();
                shiftedAlphabet1= shiftedAlphabet1.toLowerCase();
                shiftedAlphabet2= shiftedAlphabet2.toLowerCase(); 
            }
            int index = alphabet.indexOf(ch);
            if(index!=-1 && i%2 == 0){
                char newch1 = shiftedAlphabet1.charAt(index);
                sb.setCharAt(i,newch1);
            }
            else if(index!=-1 && i%2 == 1){
                char newch2 = shiftedAlphabet2.charAt(index);
                sb.setCharAt(i,newch2);
            }
        }
        return sb.toString();        
    }
    
    public String decrypt(String encrypted){
        OOCaeserCypherTwo cc = new OOCaeserCypherTwo(26-key1,26-key2);
        String message = cc.encrypt(encrypted);
        return message;
    }
    
    
}
