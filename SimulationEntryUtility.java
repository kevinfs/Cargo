package cargo;

public class SimulationEntryUtility {
	public static final int CHARGER_MIN = 1;
	public static final int CHARGER_MAX = 2;
	public static final int PACKER_MIN = 1;
	public static final int PACKER_MAX = 3;
	public static final int WHARF_CAPACITY = 3;
	public static final int CARGO_CAPACITY = 15;

	public static int getChargerWorkingTime() {
		return getRandomTime(CHARGER_MIN, CHARGER_MAX);
	}

	public static int getPackerWorkingTime() {
		return getRandomTime(PACKER_MIN, PACKER_MAX);
	}

	/**
	 * Gets a random integer between min * 1000 and max * 1000. Time unit is
	 * second.
	 * 
	 * @param min
	 *            minimal value
	 * @param max
	 *            maximal value
	 * @return random value
	 */
	private static int getRandomTime(int min, int max) {
		return (int) ((Math.random() * (max + 1 - min)) + min) * 1000;
	}

}
