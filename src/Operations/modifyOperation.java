package Operations;

import Shared.AttributeObject;
import Shared.SourceObject;

public class modifyOperation extends Operation{
	public static final String type = MODIFY;
	public String node_id;
	public int tracker;
	public SourceObject src;
	public AttributeObject[] diff;
}
