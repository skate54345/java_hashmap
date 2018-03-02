import java.lang.reflect.Array; //import packages
import java.util.NoSuchElementException;

public class HashMap<K, E> implements IMap{

	int numItems; //initialize items
	MapPair<K, E>[] map;
	int size;


	public HashMap(int numItems) //constructor
	{
		this.numItems = numItems;
		map = (MapPair<K, E>[]) Array.newInstance(MapPair.class, numItems); //creates a map
		size = 0;
	}

	public int gethashcode(Object key){
		return key.hashCode();
	}

	@Override
	public void put(Object key, Object element) {
		int code = Math.floorMod(key.hashCode(), map.length); //gets code, makes nonnegative
		if (map[code] == null){ //if space
			map[code] = new MapPair<K, E>((K)key, (E)element); //stores element
		}
		else{
			MapPair<K, E> currentPair = map[code];
			while(currentPair.next != null){
				currentPair = currentPair.next; //scan through until space
			}
			currentPair.next = new MapPair<K, E>((K)key, (E)element); //set next
		}
		size++; //increment size
	}

	@Override
	public Object get(Object key){
		int code = Math.floorMod(key.hashCode(), map.length); //gets code
		if(map[code] == null){
			throw new NoSuchElementException(); //throws exception if null
		}
		MapPair<K, E> currentPair = map[code];
		while(currentPair != null){ //iterates through
			if(currentPair.getKey().equals(key)){  //if key is key we're looking for
				return currentPair.getElement(); //return its element
			}
			currentPair = currentPair.next;
		}
		throw new NoSuchElementException(); //throws exception if reaches the end
	}

	@Override
	public int size() { //size getter
		return size;
	}

	@Override
	public Object[] getInternalArray() { //map getter
		return map;
	}

	public String toString(){
		boolean first = true; //checks if first one so no comma
		String result = "["; //starts with bracket

		for (int i = 0; i<map.length; i++){ //iterates through map
			if (map[i] != null)
			{
				MapPair<K, E> currentPair = map[i];
				while(currentPair != null){
					if(first){
						first = false; //sets to false first iteration
					}
					else{
						result += ", "; //adds comma at beginning
					}
					result += currentPair.toString(); //converts to string
					currentPair = currentPair.next; //sets current to nexts
				}
			}
		}
		result += "]"; //closes brackets
		return result;
	}

	public class MapPair<K, E> implements IMapPair<K, E>{

		private K key; //initializes generic items
		private E element;
		MapPair<K, E> next;

		public MapPair(K key, E element){
			this.key = key;
			this.element = element; //sets global variables to parameters
		}

		@Override
		public K getKey() { //key getter
			return key;
		}

		@Override
		public E getElement() {
			return element;
		}

		@Override
		public MapPair<K,E> next() {
			return next;
		}

		public String toString(){
			return key + ": " + element; //adds colon between
		}

	}
}



