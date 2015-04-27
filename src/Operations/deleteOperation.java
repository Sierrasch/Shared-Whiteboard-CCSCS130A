package Operations;

import java.util.HashMap;

import Shared.AttributeObject;
import Shared.SourceObject;

public class deleteOperation extends Operation{
	public static final String type = DELETE;
	public String node_id;
	public int tracker;
	public SourceObject src;
}
