package Shared;

import com.google.gson.Gson;

public class util {
	public static Gson getGSON(){
		return new Gson();
	}
	public static String getType(String jsonText, Gson gson){
	
		TypeIdentifier thisObject = gson.fromJson(jsonText, TypeIdentifier.class);
		return thisObject.type;
	}

	
	/* testy thingy
	public static void main(String args[]){
		String object = "{\"type\" : \"init\", \"client_id\" : 5}";
		System.out.println(getType(object, getGSON()));
	}
	*/
	
}

