package cargo;

import java.util.LinkedList;

public class Wharf {
	private volatile LinkedList<Packet> packets = new LinkedList<Packet>();
	private volatile boolean endOfWork = false;

	public synchronized void put(Packet packet) {
		if (packetOnWharfCount() == EntryUtility.WHARF_CAPACITY) {
			System.err.println("Wharf : full ... Packer waiting");
			try {
				wait();
			} catch (InterruptedException e) {
				System.err.println(e.getMessage());
			}
		}
		if (!endOfWork) {
			packets.addLast(packet);
			if (packetOnWharfCount() >= 1) {
				notifyAll();
			}
		}
	}

	public synchronized Packet get() {
		Packet packet = null;
		if (packetOnWharfCount() == 0) {
			System.err.println("Wharf : nothing here... Charger waiting");
			try {
				wait();
			} catch (InterruptedException e) {
				System.err.println(e.getMessage());
			}
		}
		if (!endOfWork) {
			packet = packets.removeFirst();
			if (packetOnWharfCount() < EntryUtility.WHARF_CAPACITY) {
				notifyAll();
			}
		}
		return packet;
	}

	private synchronized int packetOnWharfCount() {
		return packets.size();
	}
	
	private synchronized void endOfWork() {
		endOfWork = true;
		notifyAll();
	}

	public void simulate() {
		Packer packer = new Packer(this);
		Charger charger = new Charger(this);
		packer.start();
		charger.start();

		try {
			charger.join();
			endOfWork();
			packer.stopWorking();
			packer.join();
		} catch (Exception e) {
			System.err.println(e.getMessage());
		}
		System.out.println("Wharf work finished, " + packetOnWharfCount() + " packages left on the wharf.");
	}
}
