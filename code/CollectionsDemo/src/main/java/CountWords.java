import java.util.Map;
import java.util.TreeMap;

public class CountWords {
    public static void main(String[] args) {
        String text = "Good morning. Have a good class. " +
                "Have a good visit. Have fun!";

        Map<String,Integer> map = new TreeMap<>();
        String[] words = text.split("[\\s+\\p{P}]");
        for(int i=0;i<words.length;i++){
            String word = words[i].toLowerCase();
            if(word.length()>0)
            {
                if(map.containsKey(word)) {
                    int value = map.get(word);
                    value++;
                    map.put(word,value);
                }
                else {
                    map.put(word,1);
                }
            }
        }
        System.out.println(map);
    }
}
