import java.util.*;

public class EfficientMarkov extends BaseMarkov {
	private Map<String,ArrayList<String>> myMap;

	public EfficientMarkov(int order) {
		super(order);
		myMap = new HashMap<>();
	}

	public EfficientMarkov() {
		this(3);
	}

	@Override
	public void setTraining(String text) {
		super.setTraining(text);
		myMap.clear();
		for (int i = 0; i <= myText.length() - myOrder; i++) {
			myMap.putIfAbsent(myText.substring(i, i + myOrder), new ArrayList<>());
			if (i + myOrder < myText.length()) {
				myMap.get(myText.substring(i, i + myOrder)).add(myText.substring(i + myOrder, i + myOrder + 1));
			}
			else {
				myMap.get(myText.substring(i, i + myOrder)).add(PSEUDO_EOS);
			}
		}
	}

	@Override
	public ArrayList<String> getFollows(String key) {
		ArrayList<String> follows = new ArrayList<String>();
		if (myMap.containsKey(key)) {
			follows = myMap.get(key);
		}
		else {
			throw new NoSuchElementException(key + " not in map");
		}
		return follows;
	}

}	
