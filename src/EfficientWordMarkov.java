import java.util.*;

public class EfficientWordMarkov extends BaseWordMarkov {
    private Map<WordGram,ArrayList<String>> myMap;

    public EfficientWordMarkov(int order) {
        super(order);
        myMap = new HashMap<>();
    }

    public EfficientWordMarkov() {
        this(3);
    }

    @Override
    public void setTraining(String text) {
        myWords = text.split("\\s+");
        super.setTraining(text);
        myMap.clear();
        for (int i = 0; i <= myWords.length - myOrder; i++) {
            WordGram key = new WordGram(myWords, i, myOrder);
            myMap.putIfAbsent(key, new ArrayList<>());
            if (i + myOrder < myWords.length) {
                myMap.get(key).add(myWords[i + myOrder]);
            } else {
                myMap.get(key).add(PSEUDO_EOS);
            }
        }
    }
    @Override
    public ArrayList<String> getFollows(WordGram kGram) {
        ArrayList<String> follows = new ArrayList<String>();
        if (myMap.containsKey(kGram)) {
            follows = myMap.get(kGram);
        }
        else {
            throw new NoSuchElementException(kGram + " not in map");
        }
        return follows;
    }

}
