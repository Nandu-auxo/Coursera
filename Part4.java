import edu.duke.*;
public class Part4 {
    public void getURLs(){
        URLResource url = new URLResource("http://www.dukelearntoprogram.com/course2/data/manylinks.html");
        for(String word: url.words()){
            String lowerWord = word.toLowerCase();
            int index = lowerWord.indexOf("youtube.com");
            
            if(index == -1) continue;
            int startIndex = lowerWord.lastIndexOf("\"",index);
            int endIndex = lowerWord.indexOf("\"",index);
            System.out.println(word.substring(startIndex,endIndex+1));
        }
    }
}
