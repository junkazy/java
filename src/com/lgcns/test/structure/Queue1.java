package com.lgcns.test.structure;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;

public class Queue1 {

	void queue() {
		Queue<String> numberQ = new LinkedList<>();
		// Deque<String> numberQ = new ArrayDeque<>();
		numberQ.add("one");
		numberQ.add("two");
		numberQ.add("three");
		System.out.println("Queue Count = " + numberQ.size());
		for (String number : numberQ) {
			System.out.println(number);
		}
		System.out.println("Deque '" + numberQ.poll() + "'");
		System.out.println("Peek : " + numberQ.peek());
		System.out.println("Contains(\"three\") = " + numberQ.contains("three"));
		numberQ.clear();
		System.out.println("Queue Count = " + numberQ.size());

	}

	void list() {
		ArrayList<String> numberQ = new ArrayList<>();
		numberQ.add("one");
		numberQ.add("two");
		numberQ.add("three");
		System.out.println("Queue Count = " + numberQ.size());
		for (String number : numberQ) {
			System.out.println(number);
		}
		System.out.println("Deque : " + numberQ.get(0));
		numberQ.remove(0);
		System.out.println("Peek : " + numberQ.get(0));
		System.out.println("Contains(\"three\") = " + numberQ.contains("three"));
		numberQ.clear();
		System.out.println("Queue Count = " + numberQ.size());
	}
}
