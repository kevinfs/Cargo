package cargo;

public class Charger extends Thread {
	private Wharf wharf;
	private Cargo cargo = new Cargo(EntryUtility.CARGO_CAPACITY);

	public Charger(Wharf wharf) {
		this.wharf = wharf;
	}

	public void run() {
		while (!cargo.isFull()) {
			Packet packet = wharf.get();
			try {
				int randoWorkingTime = EntryUtility.getChargerWorkingTime();
				sleep(randoWorkingTime);
			} catch (Exception e) {
				System.err.println(e.getMessage());
			}
			cargo.charge(packet);
			System.out.println("Charger : loaded package : " + packet.getId());
		}
		System.out.println("Charger : finished");
	}
}	
