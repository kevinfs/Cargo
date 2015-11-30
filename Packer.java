package cargo;

public class Packer extends Thread {
	private Wharf wharf;
	private boolean continueWork;
	private int packetCount;

	public Packer(Wharf wharf) {
		this.wharf = wharf;
		continueWork = true;
		packetCount = 1;
	}

	public void stopWorking() {
		continueWork = false;
		System.err.println("Stop working, Packer !");
	}

	public void run() {
		while (continueWork) {
			try {
				int randoWorkingTime = EntryUtility.getPackerWorkingTime();
				sleep(randoWorkingTime);
			} catch (Exception e) {
				continueWork = false;
			}
			Packet packet = new Packet(packetCount);
			if (continueWork) {
				wharf.put(packet);
				packetCount++;
				System.out.println("Packer : package " + packetCount + " finished");
			}
		}
		System.out.println("packer stopped");
	}
}