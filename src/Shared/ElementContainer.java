package Shared;

import java.util.Collection;
import java.util.HashMap;
import java.util.Iterator;

import Operations.insertOperation;

public class ElementContainer {
	HashMap<String, Element> elements;

	public ElementContainer(){
		elements = new HashMap<String, Element>(100, 0.5f);
	}

	/* Standard put operation that can be called in three different ways:
	 * 1) With a string indicating the source of the element, which is used
	 * as the hash name and consists of the user's name (or id number)
	 * concatenated with the element's tracker.
	 * 2) With a source object and the element to be inserted
	 * 3) With only an element, in which case, the mehthod determines the
	 * elements source using the src object embedded in the element.
	 * 4) With a insertOperation object and string indicating the user
	 * creating the element, from which the method initializes
	 * an Element object. 
	 * 
	 * #3 and #4 are the recommended means of inserting into this data structure,
	 * since it ensures consistency between the object's hash key and the element's
	 * actual source.
	 * 
	 * If the element is already in the collection of containers, the function
	 * returns false and does not modify the data structures. Otherwise, the
	 * element is inserted verbatim.
	 */
	public synchronized boolean put(String src, Element el){
		if(elements.containsKey(src)) return false;
		elements.put(src, el);
		return true;
	}

	public synchronized boolean put(SourceObject src, Element el){
		return put(src.user + src.tracker, el);
	}

	public synchronized boolean put(Element el){
		return put(el.src.user + el.src.tracker, el);
	}

	public synchronized boolean put(insertOperation iO, String user){
		Element el = new Element(iO);
		el.src.user = user;
		return put(el);
	}
	
	// For use ONLY on the client, where incoming insert operations will
	// have the source object already set.
	public synchronized boolean put(insertOperation iO){
		Element el = new Element(iO);
		return put(el);
	}
	
	/* Removes the element with the specified key (user+tracker) from the
	 * data structure. Returns true if the structure actually contained
	 * this key or false if it did not.
	 */
	public synchronized boolean remove(String key){
		return elements.remove(key) != null;
	}
	
	public synchronized boolean remove(SourceObject src){
		return elements.remove(src.user + src.tracker) != null;
	}

	/* Returns an Iterator that can be used to iterate over all of the
	 * elements (shapes) in the white board.
	 */
	public Iterator<Element> getValues(){
		return elements.values().iterator();
	}
}
