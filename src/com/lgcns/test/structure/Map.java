package com.lgcns.test.structure;

import java.util.HashMap;

public class Map {

	void map() {

		HashMap<String, String> m = new HashMap<String, String>();
		m.put("kit", "Michael Knight");
		m.put("knife", "Mac Guyver");
		m.put("superman", "Clark Kent");
		m.put("batman", "Bruce Wayne");
		m.put("ironman", "Tony Stark");
		for (String key : m.keySet()) {
			System.out.println(key + " : " + m.get(key));
		}
		System.out.println();
		m.remove("superman");
		for (String key : m.keySet()) {
			System.out.println(key + " : " + m.get(key));
		}
		System.out.println();
		m.replace("batman", "Robin");
		for (String key : m.keySet()) {
			System.out.println(key + " : " + m.get(key));
		}
	}
}
