import java.io.Console;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.*;
import java.util.stream.Stream;

public class Main {

    public static void main(String[] args) throws IOException {

        FileInputStream reader = new FileInputStream("TextAnalyzer.txt");
        Scanner file = new Scanner(reader);

        ArrayList<String>  words = new ArrayList<>();
        ArrayList<Integer> frequency = new ArrayList<>();

        while(file.hasNext()){
            String next = file.next().toLowerCase().replaceAll("[^a-zA-Z0-9]","");
            if(words.contains(next)){
                next.toLowerCase();
                int indexer = words.indexOf(next);
                frequency.set(indexer,frequency.get(indexer) + 1);
            }else{
                next.toLowerCase();
                words.add(next);
                frequency.add(1);
            }
        }

        file.close();
        reader.close();

        LinkedHashMap<String, Integer> myMap = new LinkedHashMap<>();

        for(int i = 0; i < words.size(); i++) {
            myMap.put(words.get(i), frequency.get(i));
        }

        LinkedHashMap<String, Integer> mySortedMap = new LinkedHashMap<>();

        myMap.entrySet()
                .stream()
                .sorted(Map.Entry.comparingByValue())
                .forEachOrdered(x -> mySortedMap.put(x.getKey(),x.getValue()));

        //Use Comparator.reverseOrder() for reverse ordering
        LinkedHashMap <String,Integer> myReverseSortedMap = new LinkedHashMap<>();
        mySortedMap.entrySet()
                .stream()
                .sorted(Map.Entry.comparingByValue(Comparator.reverseOrder()))
                .forEachOrdered(x -> myReverseSortedMap.put(x.getKey(),x.getValue()));

        //Print out highest 20 words and their count
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
