import edu.duke.*;
import java.util.*;

public class GladLibUsingHashMap {
    private HashMap<String,ArrayList<String>> myMap;
    private ArrayList<String> wordsUsed;
    private ArrayList<String> categoriesUsed;
    private Random myRandom;
    
    private static String dataSourceURL = "http://dukelearntoprogram.com/course3/data";
    private static String dataSourceDirectory = "data";
    
    public GladLibUsingHashMap(){
        myMap = new HashMap<String,ArrayList<String>>();
        initializeFromSource(dataSourceDirectory);
        myRandom = new Random();
    }
    
    public GladLibUsingHashMap(String source){
        myMap = new HashMap<String,ArrayList<String>>();        
        initializeFromSource(source);
        myRandom = new Random();
    }
    
    private void initializeFromSource(String source) {
        String[] categories = {"adjective","noun","color","country","name","animal","timeframe","verb","fruit"};
        for(String category : categories){
            ArrayList<String> list = readIt(source+"/"+ category+ ".txt");
            if(list!= null)
            myMap.put(category,list);
        }
        wordsUsed = new ArrayList<String>();
        categoriesUsed = new ArrayList<String>();        
    }
    
    private String randomFrom(ArrayList<String> source){
        int index = myRandom.nextInt(source.size());
        return source.get(index);
    }
    
    private String getSubstitute(String label) {
        categoriesUsed.add(label);
        if (label.equals("number")){
            return ""+myRandom.nextInt(50)+5;
        }
        return randomFrom(myMap.get(label));
    }
    
    private String processWord(String w){
        int first = w.indexOf("<");
        int last = w.indexOf(">",first);
        if (first == -1 || last == -1){
            return w;
        }
        String prefix = w.substring(0,first);
        String suffix = w.substring(last+1);
        String sub = getSubstitute(w.substring(first+1,last));
        int index = wordsUsed.indexOf(sub);
        if(index == -1){
            wordsUsed.add(sub);
        }
        else{
            while(index !=-1){
                sub = getSubstitute(w.substring(first+1,last));
                index = wordsUsed.indexOf(sub);
                if(index == -1)
                    wordsUsed.add(sub);
            }
        }
        return prefix+sub+suffix;
    }
    
    private void printOut(String s, int lineWidth){
        int charsWritten = 0;
        for(String w : s.split("\\s+")){
            if (charsWritten + w.length() > lineWidth){
                System.out.println();
                charsWritten = 0;
            }
            System.out.print(w+" ");
            charsWritten += w.length() + 1;
        }
    }
    
    private String fromTemplate(String source){
        String story = "";
        if (source.startsWith("http")) {
            URLResource resource = new URLResource(source);
            for(String word : resource.words()){
                story = story + processWord(word) + " ";
            }
        }
        else {
            FileResource resource = new FileResource(source);
            for(String word : resource.words()){
                story = story + processWord(word) + " ";
            }
        }
        return story;
    }
    
    private ArrayList<String> readIt(String source){
        ArrayList<String> list = new ArrayList<String>();
        if (source.startsWith("http")) {
            URLResource resource = new URLResource(source);
            for(String line : resource.lines()){
                list.add(line);
            }
        }
        else {
            FileResource resource = new FileResource(source);
            for(String line : resource.lines()){
                list.add(line);
            }
        }
        return list;
    }
    
    public int totalWordsInMap(){
        int totalWords = 0;
        for(String key : myMap.keySet()){
            totalWords += myMap.get(key).size();
        }
        return totalWords;
    }
    
    public int totalWordsConsidered(){
        return categoriesUsed.size();
    }
    
    public void makeStory(){
        wordsUsed.clear();
        System.out.println("\n");
        String story = fromTemplate("data/madtemplate2.txt");
        printOut(story, 60);
        System.out.println("Total number of words that were possible to pick from : "+ totalWordsInMap());
        System.out.println("Total number of words considered : "+ totalWordsConsidered());
        System.out.println("Total number of words replaced : "+ wordsUsed.size());
    }
    
}

