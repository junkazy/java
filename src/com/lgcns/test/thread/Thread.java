package com.lgcns.test.thread;

public class Thread {
	class ThreadClass extends Thread {
		public void run() {
			System.out.print("Thread is running...");
		}
	}

	public class ThreadSample {
		public static void main(String[] args) {
			ThreadClass tc1 = new ThreadClass();
			tc1.start();
			try {
				tc1.join();
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
	}

	class ThreadClass implements Runnable {
		public void run() {
			System.out.println("thread is running...");
		}
	}

	public class ThreadRunnable {
		public static void main(String[] args) {
			ThreadClass m1 = new ThreadClass();
			Thread t1 = new Thread(m1);
			t1.start();
			try {
				t1.join();
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
	}

	public class ThreadTest {
		public static void main(String[] args) {
			Thread t1 = new Thread(new Runnable() {
				@Override
				public void run() {
					System.out.println(Thread.currentThread().getName() + " is running...");
				}
			});
			t1.start();
			// Lambda Runnable 1
			Runnable taskR = () -> {
				System.out.println(Thread.currentThread().getName() + " is running");
			};
			new Thread(taskR).start();
			// Lambda Runnable 2
			new Thread(() -> {
				System.out.println(Thread.currentThread().getName() + " is running");
			}).start();
		}
	}

}
