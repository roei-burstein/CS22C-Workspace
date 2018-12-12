public class Music {
	private String title;
	private String composer;
	private int opus;

	public static <T> void displayArray(T[] myArray) {
		for (int i = 0; i < myArray.length; ++i) {
			System.out.println(myArray[i]);
		}

	}

	public static void main(String[] args) {
		Music[] musicArray = { new Music("Moonlight Sonata", "Beethoven", 27),
				new Music("Brandenburg Concerto #3", "Bach", 1048), new Music("Prelude in e minor", "Chopin", 28) };
		LList<Music> musicList = new LList<Music>(); // creates LinkedList!
		Music tempMusic;

		displayArray(musicArray);// template is Music here
		for (int i = 0; i < 3; ++i)
			musicList.add(musicArray[i]);

		System.out.println("\nThe list as displayed in LList.java: ");
		musicList.display();

		tempMusic = musicList.getEntry(2);
		if (tempMusic != null)
			System.out.println("\nFound " + tempMusic.getTitle());
		else
			System.out.println("\nUnable to find " + musicArray[1].getTitle());
		musicList.add(new Music("Nocturne 2", "Chopin", 9));
		Music music1 = new Music(musicList.getEntry(3).title, musicList.getEntry(3).composer, musicList.getEntry(3).opus);
		if(musicList.contains(music1)) music1.toString();
		else System.out.println("not found");
		musicList.display();
		musicList.remove(music1);
		musicList.display();
	}

	public Music() {
		title = "";
		composer = "";
		opus = 0;
	}

	public Music(String ti, String comp, int op) {
		title = ti;
		composer = comp;
		opus = op;
	}

	public String getTitle() {
		return title;
	}

	public String getComposer() {

		return composer;
	}

	public int getOpus() {
		return opus;
	}

	public void setTitle(String newTitle) {
		title = newTitle;
	}

	public void setComposer(String newComp) {
		composer = newComp;
	}

	public void setOpus(int newOpus) {
		opus = newOpus;
	}

	public boolean equals(Object obj) // overrides Object's equals method
	{
		Music other = (Music) obj;
		return title.equals(other.title) && composer.equals(other.composer);
	}

	// GENERIC METHOD EXAMPLE: (inside another class)

	public String toString() {
		return ("Title: " + title + "\nComposer: " + composer + "\nOpus: " + opus);
	}
}
