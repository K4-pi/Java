package words;

import run.Input;

import java.util.HashMap;
import java.util.Map;

public class HowManyWords {
    Input input = new Input();

    public void Words() {
        String text = input.StringInput();
        text = text.toLowerCase().replaceAll("[.,!?;:]", "");

        String[] words = text.split("\\s++");

        Map<String, Integer> wordMap = new HashMap<>();

        for (String word : words) {
            wordMap.put(word, wordMap.getOrDefault(word, 0) + 1);
        }

        System.out.println("\n Liczba wystąpień słów:\n");
        for (Map.Entry<String, Integer> entry : wordMap.entrySet()) {
            System.out.println(entry.getKey() + ": " + entry.getValue());
        }

    }


}
