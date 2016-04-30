package ztbd.riak;

import java.util.Collections;

public class App {
	public static void main(String[] args) {
		System.out.println("Hello World!");
		Collections.sort(Collections.<String>emptyList(), (o1, o2) -> {return o1.compareTo(o2);
		});
	}
}
