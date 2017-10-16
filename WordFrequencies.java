
import java.util.*;
import edu.duke.*;
public class WordFrequencies {
    private ArrayList<String> myWords;
    private ArrayList<Integer> myFreqs;
    public WordFrequencies(){
        myWords = new ArrayList<String>();
        myFreqs = new ArrayList<Integer>();
    }
    
    public void findUnique(){
        myWords.clear();
        myFreqs.clear();
        FileResource fr = new FileResource("errors.txt");
        for(String word : fr.words()){
            word = word.toLowerCase();
            int index = myWords.indexOf(word);
            if(index == -1){
                myWords.add(word);
                myFreqs.add(1);
            }
            else{
                int num = myFreqs.get(index);
                myFreqs.set(index,num+1);
            }
        }
        System.out.println(myWords.size());
    }
    
    public int findIndexOfMax(){
        int maxIndex = 0;
        int maxValue = myFreqs.get(0);
        for(int i = 1; i < myFreqs.size(); i++){
            if( myFreqs.get(i) > maxValue){
                maxValue = myFreqs.get(i);
                maxIndex = i;
            }
        }
        return maxIndex;
    }
    
    public void test(){
        //WordFrequencies wf = new WordFrequencies();
        findUnique();
        /*int size = myWords.size();
        System.out.println("The number of unique words :"+ size);
        for(int i = 0; i < size; i++){
            System.out.println(myWords.get(i) + " : " + myFreqs.get(i));
        }
        */int maxIndex = findIndexOfMax();
        System.out.println("The word occured maximum is "+ myWords.get(maxIndex) + " and occured "+ myFreqs.get(maxIndex)+"times");
        
    }
}

