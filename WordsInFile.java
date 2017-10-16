import java.util.*;
import java.io.*;
import edu.duke.*;
public class WordsInFile {
    
    private HashMap<String,ArrayList<String>> map ;
    public WordsInFile(){
        map = new HashMap<String,ArrayList<String>>();
    }
    
    public String clean(String wordToClean){
        String cleanString = "";
        StringBuilder sb = new StringBuilder(cleanString);
        int flag = 0;
        wordToClean = wordToClean.replaceAll(" +"," ");
        int length = wordToClean.length();
        if(Character.isLetter(wordToClean.charAt(0)))
             sb.append(wordToClean.charAt(0));
        for(int i = 1; i< length - 1; i++)
             sb.append(wordToClean.charAt(i));
        if(Character.isLetter(wordToClean.charAt(length-1)))
             sb.append(wordToClean.charAt(length-1));
        return sb.toString();                
    }
 
    private void addWordsFromFile (File f){
        FileResource fr = new FileResource(f);
        for(String word : fr.words()){
            word = clean(word);
            if(!map.containsKey(word)){
                ArrayList<String> list = new ArrayList<String>();
                list.add(f.getName());
                map.put(word,list);
            }
            else{
                ArrayList<String> list = map.get(word);
                if(list.contains(f.getName())) continue;
                else{
                    list.add(f.getName());
                    map.put(word,list);
                }
            }
        }
    }
    
    public void buildWordFileMap(){
        map.clear();
        DirectoryResource dr = new DirectoryResource();
        for(File f : dr.selectedFiles()){
            addWordsFromFile(f);
        }
    }
    
    public int maxNumber(){
        int maxCount = 0;
        for(String key : map.keySet()){
            ArrayList<String> list = map.get(key);
            if(maxCount < list.size()){
                maxCount = list.size();
            }
        }
        return maxCount;
    }
    
    public ArrayList<String> wordsInNumFiles (int number){
        ArrayList<String> wordsOfCountNumber = new ArrayList<String>();
        for(String key : map.keySet()){
            if(number == map.get(key).size())
                wordsOfCountNumber.add(key);
        }
        return wordsOfCountNumber;
    }
    
    public void printFilesIn(String word){
        if(!map.containsKey(word))
            System.out.println("No such word in files\n");
        else{
            ArrayList<String> list = map.get(word);
            for(String fileName : list)
                System.out.println(fileName);
        }        
    }
    
    public void tester(){
        buildWordFileMap();
        //System.out.println("The max number of files a word appears is : "+ maxNumber());
        ArrayList<String> wordsCommon = wordsInNumFiles(4);
        System.out.println("Words in 4 files : "+ wordsCommon.size());
        printFilesIn("tree");
        /*for(String key: map.keySet()){
            System.out.println(key +" : ");
            ArrayList<String> list = map.get(key);
            for(String files : list)
                System.out.println("\t"+files);
        }*/
    }
}
