import java.util.*;

/**
 * Subclass that inherits methods from the base class.
 * This version scans the training text once regardless
 * of the number of randomly-generated characters.
 * This makes generating N characters an O(N+T)
 * compared to O(N*T) in the base class, where
 * T is the size of the training text
 *
 * All methods are the same as the methods in the
 * base class apart from two that are overriden:
 * setTraining and getFollows
 *
 */

public class EfficientMarkov extends BaseMarkov {
	private Map<String,ArrayList<String>> myMap;

	/**
	 * Construct an EfficientMarkov object with
	 * the specified order
	 * @param order size of this markov generator
	 */

	public EfficientMarkov(int order) {
		super(order);
		myMap = new HashMap<>();
	}

	/**
	 * Default constructor has order 3
	 */

	public EfficientMarkov() {
		this(3);
	}

	/**Constructs a map and stores every possible
	 * k-Gram as it's key and characters associated
	 * with each k-Gram as it's value, where k is
	 * the order(size of the markov generator)
	 *
	 *
	 * @param text
	 */

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

/**
 * Returns the associated values of the k-gram if
 * present in the map
 *
 *
 * @param key
 * */

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
