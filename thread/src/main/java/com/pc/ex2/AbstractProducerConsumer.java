package com.pc.ex2;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingQueue;

/**
 * 对生产者消费者模型的抽象
 * 
 * @author winning007
 * 
 */
public abstract class AbstractProducerConsumer {
	private int sleepIfEmptyProduct = 5 * 1000; // 没有取得产品时，休眠的时间
	private int sleepIfExceptionRaise = 60 * 1000;// 如果出现异常工作线程做适当的休眠
	private boolean sleepWhenExceptionRaise; // 出现异常时是否进行适当的休眠
	private int producerSize = 1;// 生产者个数
	private int consumerSize = 1;// 消费者个数
	private int queenSize = 10; // 等待队列的大小
	// 系统运行的几种状态
	public final int STATE_INIT = 0;
	public final int STATE_PAUSED = 1;
	public final int STATE_RUNNING = 2;
	public final int STATE_SHUTDOWN = 3;
	// 系统的状态
	private int state = STATE_INIT;
	// 空的对象列表,做占位用
	public static List<Product> EMPTY_PRODUCT_LIST = new ArrayList<Product>();
	// 产品队列
	private BlockingQueue<Product> produceQueen;
	// 生产者队列
	private List<Producer> produerList;
	//消费者队列
	private List<Consumer> consumerList;
	// 生产者
	class Producer extends PeriodicalTask {
		public Producer() {
			this(0);
		}
		public Producer(int period) {
			super(period);
		}
		// 可以在这里处理异常
		public void handleException(Exception e) {
			handleProducerException(e);
			if (sleepWhenExceptionRaise) {
				try {
					Thread.sleep(sleepIfExceptionRaise);
				} catch (InterruptedException e1) {
					// ignore
				}
			}
		}
		@Override
		protected void doWork() throws Exception {
			List<Product> products = produce();
			for (Product p : products) {
				// 有可能发生阻塞
				produceQueen.put(p);
			}
			// 如果生产者取得的数据为空，则适当进行休眠,以免出现狂操作
			if (products.isEmpty()) {
				Thread.sleep(sleepIfEmptyProduct);
			}
		}
	
	}
	// 消费者
	class Consumer extends PeriodicalTask {
		private Product curProduct;
		public Consumer() {
			this(0);
		}
		// 可以在这里处理异常
		public void handleException(Exception e) {
			handleConsumerException(e, this.curProduct);
			if (sleepWhenExceptionRaise) {
				try {
					Thread.sleep(sleepIfExceptionRaise);
				} catch (InterruptedException e1) {
					// ignore
				}
			}
		}
		public Consumer(int period) {
			super(period);
		}
		@Override
		protected void doWork() throws Exception {
			// 有可能发生阻塞
			Product product = produceQueen.take();
			curProduct = product;
			consume(product);
		}
	
	}
	// 由子类来实现生成和消费的具体逻辑
	public abstract List<Product> produce();
	public abstract void consume(Product product);
	public abstract void handleProducerException(Exception e);
	public abstract void handleConsumerException(Exception e, Product product);
	// 启动之前做的事情
	public void preStartup() {
	}
	public void startup() {
		if (state == STATE_RUNNING) {
			throw new IllegalStateException("系统已经处于运行状态，不能够再启动！state="
					+ this.state);
		}
		this.preStartup();
		// 创建相关的队列和线程
		produceQueen = new LinkedBlockingQueue<Product>(this.queenSize);
		produerList = new LinkedList<Producer>();
		consumerList = new LinkedList<Consumer>();
		for (int i = 0; i < this.producerSize; i++) {
			Producer producer = new Producer();
			produerList.add(producer);
			producer.start();
		}
		for (int i = 0; i < this.consumerSize; i++) {
			Consumer consumer = new Consumer();
			consumerList.add(consumer);
			consumer.start();
		}
		state = STATE_RUNNING;
	}
	// /////////////////////////几个管理接口/////////////////////
	//暂停
	public void pause() {
		if (state != STATE_RUNNING) {
			throw new IllegalStateException("系统不处于运行状态，不能暂停！state="
					+ this.state);
		}
		for (Producer p : produerList) {
			p.pause();
		}
		for (Consumer c : consumerList) {
			c.pause();
		}
		state = STATE_PAUSED;
	}
	// 恢复
	public void resume() {
		if (state != STATE_PAUSED) {
			throw new IllegalStateException("系统不处于暂停状态，不能恢复！state="
					+ this.state);
		}
		for (Producer p : produerList) {
			p.resume();
		}
		for (Consumer c : consumerList) {
			c.resume();
		}
		state = STATE_RUNNING;
	}
	//停止
	public void shutdown() {
		if (state != STATE_RUNNING) {
			throw new IllegalStateException("系统不处于运行状态，不能关闭！state="
					+ this.state);
		}
		for (Producer p : produerList) {
			p.shutdown();
		}
		for (Consumer c : consumerList) {
			c.shutdown();
		}
		state = STATE_SHUTDOWN;
	}
	// //////////////////////////////////////
	public int getProducerSize() {
		return producerSize;
	}
	public void setProducerSize(int producerSize) {
		this.producerSize = producerSize;
	}
	public int getConsumerSize() {
		return consumerSize;
	}
	public void setConsumerSize(int consumerSize) {
		this.consumerSize = consumerSize;
	}
	public int getQueenSize() {
		return queenSize;
	}
	public void setQueenSize(int queenSize) {
		this.queenSize = queenSize;
	}
	public BlockingQueue<Product> getProduceQueen() {
		return produceQueen;
	}
	public void setProduceQueen(BlockingQueue<Product> produceQueen) {
		this.produceQueen = produceQueen;
	}
	public List<Producer> getProduerList() {
		return produerList;
	}
	public void setProduerList(List<Producer> produerList) {
		this.produerList = produerList;
	}
	public List<Consumer> getConsumerList() {
		return consumerList;
	}
	public void setConsumerList(List<Consumer> consumerList) {
		this.consumerList = consumerList;
	}
	public int getSleepIfEmptyProduct() {
		return sleepIfEmptyProduct;
	}
	public void setSleepIfEmptyProduct(int sleepIfEmptyProduct) {
		this.sleepIfEmptyProduct = sleepIfEmptyProduct;
	}
	public int getSleepIfExceptionRaise() {
		return sleepIfExceptionRaise;
	}
	public void setSleepIfExceptionRaise(int sleepIfExceptionRaise) {
		this.sleepIfExceptionRaise = sleepIfExceptionRaise;
	}
	public boolean isSleepWhenExceptionRaise() {
		return sleepWhenExceptionRaise;
	}
	public void setSleepWhenExceptionRaise(boolean sleepWhenExceptionRaise) {
		this.sleepWhenExceptionRaise = sleepWhenExceptionRaise;
	}
}