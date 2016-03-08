/**
 * 
 */
package chapter10.p145;

/**
 * 队列实现，先进先出实现机制
 * 
 * @author snoopy
 *
 */
public class QueueImpl {

	private int head = 0;
	private int tail = 0;
	private String[] Q;

	private final int max_size;

	public QueueImpl(int size) {
		Q = new String[size];
		this.max_size = size;
	}

	public QueueImpl() {
		this(100);
	}

	// 入队
	public synchronized boolean enqueue(String s) {
		if (tail >= max_size) {
			return false;
		}
		Q[tail] = s;
		tail++;
		return true;
	}

	// 出队
	public synchronized String dequeue() {
		String str = null;
		if (head < tail) {
			str = Q[head];
		}
		head++;
		if (head == max_size) {
			head = 0;
		}
		return str;
	}

}
