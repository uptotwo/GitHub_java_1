package listener;

public class SomeBedListener implements BedListener{

	public void onBedSound(String sound) {
		System.out.println(sound);
	}

}
