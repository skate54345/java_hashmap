
public class HashSet<E> implements ISet<E> {

	private E[] set; //creates generic array
	private int size;

	public HashSet(int numItems){ //constructor
		size = 0;
		set = (E[]) new Object[numItems];
	}


	public int gethashcode(E x){
		int hashCode = x.hashCode(); //gets hashcode of input
		int result = Math.floorMod(hashCode, set.length); //makes nonnegative
		if(set[result] != null){
			return gethashcode(String.valueOf(hashCode)); //recursively calls itself
		}
		return result;
	}

	public int gethashcode(String x){ //same as generic but takes in string
		int hashCode = x.hashCode();
		int result = Math.floorMod(hashCode, set.length);
		if(set[result] != null){
			return gethashcode(String.valueOf(hashCode));
		}
		return result;
	}

	@Override
	public void add(E element) {
		size++; //increments size
		if(has(element) == false){ //if element doesn't exist in array
			int key = gethashcode(element); //gets location to store
			set[key] = element; //stores element
		}
	}

	@Override
	public boolean has(E element) {
		for(int i = 0; i<set.length; i++){
			if (element.equals(set[i])) //iterates through and returns true
			{
				return true;
			}
		}
		return false; //false if gets to end
	}

	@Override
	public int size() { //getter for size
		return size;
	}

	@Override
	public Object[] getInternalArray() { //getter for set
		return set;
	}

	public String toString(){
		boolean first = true;
		String result = "["; //starts with bracket

		for (int i = 0; i<set.length; i++){ //iterates through length
			if (set[i] != null)
			{
				if(first){ //changes first on first iteration
					first = false;
				}
				else{
					result += ", "; //adds comma at beginning
				}
				result += (set[i].toString());
			}

		}
		result += "]"; //closes bracket

		return result;
	}
}
