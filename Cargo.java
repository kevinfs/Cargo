package cargo;

import java.util.ArrayList;
import java.util.List;

public class Cargo {
	private int capacity;
	private List<Packet> packages = new ArrayList<Packet>();

	public Cargo(int capacity) {
		this.capacity = capacity;
	}

	public int getCapacity() {
		return capacity;
	}

	public void charge(Packet p) {
		packages.add(p);
	}

	public boolean isFull() {
		return packetCount() == capacity;
	}

	public int packetCount() {
		return packages.size();
	}

}
