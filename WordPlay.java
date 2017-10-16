
import edu.duke.*;
public class WordPlay {
    public boolean isVowel(char ch){
        char temp = Character.toLowerCase(ch);
        if(temp == 'a' || temp=='e' || temp=='i' || temp=='o' || temp=='u')
            return true;
        return false;
    }
    
    public String replaceVowels(String message, char ch){
        StringBuilder sb = new StringBuilder(message);
        for(int i=0;i<message.length();i++){
            if(isVowel(sb.charAt(i))) sb.setCharAt(i,ch);
        }
        return sb.toString();
    }
    
    public String emphasize(String message, char ch){
        StringBuilder sb = new StringBuilder(message);
        for(int i=0;i<message.length();i++){
            if(isVowel(sb.charAt(i))){
                if(i%2 == 1) sb.setCharAt(i,'+');
                else  sb.setCharAt(i,'*');
            }
        }
        return sb.toString();
    }
}
