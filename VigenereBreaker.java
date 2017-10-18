import java.util.*;
import edu.duke.*;
import java.io.File;
public class VigenereBreaker {
    public String sliceString(String message, int whichSlice, int totalSlices) {
        String sliced = "";
        StringBuilder sb = new StringBuilder(sliced);
        for(int i = whichSlice; i < message.length(); i += totalSlices){
            sb.append(message.charAt(i));
        }
        return sb.toString();
    }

    public int[] tryKeyLength(String encrypted, int klength, char mostCommon) {
        int[] key = new int[klength];
        CaesarCracker cc = new CaesarCracker();
        for(int i=0;i<klength;i++){
            key[i] = cc.getKey(sliceString(encrypted,i,klength));
        }
        return key;
    }
    
    public HashSet<String> readDictionary(FileResource fr){
        HashSet<String> words = new HashSet<String>();
        for(String line : fr.lines()){
            words.add(line.toLowerCase());
        }
        return words;
    }
    
    public int countWords(String message, HashSet<String> dictionary){
        int countValid = 0;
        String[] words = message.split("\\W+");
        for(String word : words){
            if(dictionary.contains(word.toLowerCase())){
                countValid++;
            }
        }
        return countValid;
    }
    
    public String breakForLanguage(String encrypted, HashSet<String> dictionary){
        int[] key = tryKeyLength(encrypted,1,'e');;
        int max = 0;
        int valid = 0;
        int[] count = key;
        String message = "";
        char mostCommon = mostCommonCharIn(dictionary);
        for(int i=1;i<=100;i++){
            key = tryKeyLength(encrypted,i,mostCommon);
            VigenereCipher vc = new VigenereCipher(key);
            String decrypted = vc.decrypt(encrypted);
            valid = countWords(decrypted,dictionary);
            if(max < valid){
                max = valid;
                message = decrypted;
                count = key;
            }
        }
        System.out.println("Max num of valid words : " + max);
        System.out.println("keylength: "+ count.length);
        System.out.println("The key is ");
        for(int num: count)
            System.out.print(num+" ");
        System.out.print("\n");
        return message;
    }
    
    public char mostCommonCharIn(HashSet<String> dictionary){
        HashMap<Character,Integer> charCount = new HashMap<Character,Integer>();
        char mostCommon = Character.MIN_VALUE;
        int max = 0;        
        for(String word : dictionary){
            for(int i=0;i<word.length();i++){
                if(!charCount.containsKey(word.charAt(i))){
                    charCount.put(word.charAt(i),1);
                }
                else{
                    charCount.put(word.charAt(i),charCount.get(word.charAt(i))+1);
                }
            }
        }
        for(char a : charCount.keySet()){
            int count = charCount.get(a);
            if(count > max){
                max = count;
                mostCommon = a;
            }
        }
        return mostCommon;
    }
    
    public String breakForAllLangs(String encrypted,HashMap<String,HashSet<String>> languages){
        int i = 0;
        int countValid = 0;
        int max=0;
        String probableLanguage = "";
        HashMap<String,Integer> languageToValidCount = new HashMap<String,Integer>();
        for(String language : languages.keySet()){
            System.out.println("Language : "+ language);
            String message = breakForLanguage(encrypted,languages.get(language));
            countValid = countWords(message,languages.get(language));
            languageToValidCount.put(language,countValid);
        }
        for(String language : languageToValidCount.keySet()){
            if(max < languageToValidCount.get(language)){
                max = languageToValidCount.get(language);
                probableLanguage = language;
            }
        }
        return breakForLanguage(encrypted,languages.get(probableLanguage));
    }
    
    public void breakVigenere() {
        FileResource fr = new FileResource();
        String encrypted = fr.asString();
        HashMap<String,HashSet<String>> languages= new HashMap<String,HashSet<String>>();
        DirectoryResource dr = new DirectoryResource();
        for(File file : dr.selectedFiles()){
            FileResource dict = new FileResource(file);
            HashSet<String> dictionary = readDictionary(dict);
            languages.put(file.getName(),dictionary);
        }
        String decrypted = breakForAllLangs(encrypted,languages);
        System.out.println(decrypted);
    }
}