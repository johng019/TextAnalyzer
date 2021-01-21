import java.io.Console;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.*;
import java.util.stream.Stream;

public class Main {

    public static void main(String[] args) throws IOException {
        //Open and read a file.
        FileInputStream reader = new FileInputStream("TextAnalyzer.txt");
        Scanner file = new Scanner(reader);

        //Create 2 ArrayLists for the words and count of frequency of each word.
        ArrayList<String>  words = new ArrayList<>();
        ArrayList<Integer> frequency = new ArrayList<>();

        //Sets up the reading of the file and then performs word identification and frequency of the word tasks.
        //Then adds the word to the words ArrayList & count to the frequency ArrayList.
        while(file.hasNext()){
            String next = file.next().toLowerCase().replaceAll("[^a-zA-Z0-9]","");
            if(words.contains(next)){
                int indexer = words.indexOf(next);
                frequency.set(indexer,frequency.get(indexer) + 1);
            }else{
                words.add(next);
                frequency.add(1);
            }
        }

        //Close file open/read tools.
        file.close();
        reader.close();

        //Linked Hash Map of words(key) and count(value).
        LinkedHashMap<String, Integer> myMap = new LinkedHashMap<>();
        for(int i = 0; i < words.size(); i++) {
            myMap.put(words.get(i), frequency.get(i));
        }

        //Use Entry.comparingByValue to sort entries.
        LinkedHashMap<String, Integer> mySortedMap = new LinkedHashMap<>();
        myMap.entrySet()
                .stream()
                .sorted(Map.Entry.comparingByValue())
                .forEachOrdered(x -> mySortedMap.put(x.getKey(),x.getValue()));

        //Use Comparator.reverseOrder() for reverse ordering.
        LinkedHashMap <String,Integer> myReverseSortedMap = new LinkedHashMap<>();
        mySortedMap.entrySet()
                .stream()
                .sorted(Map.Entry.comparingByValue(Comparator.reverseOrder()))
                .forEachOrdered(x -> myReverseSortedMap.put(x.getKey(),x.getValue()));

        //Print out highest 20 words and their count.
        int i = 0;
        System.out.println(" ");
        System.out.println("The 20 most frequently appearing words in Edgar Allen Poe's poem 'The Raven' are : ");
        for(String  item: myReverseSortedMap.keySet()){
            if(i < 20) {
                System.out.println(item + " = " + myReverseSortedMap.get(item));
                i++;
            }
        }

    }
}
