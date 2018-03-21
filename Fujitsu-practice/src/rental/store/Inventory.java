package rental.store;

import java.util.ArrayList;

public class Inventory {
	
	private ArrayList<Film> films;
	
	public Inventory() {
		films = new ArrayList<Film>();
	}

	public void addFilm(Film f) {
		checkIsTheFilmTypeCorrect(f);
		films.add(f);
	}
	
	public void checkIsTheFilmTypeCorrect(Film f) {
		if(f.getType().equals("new") || f.getType().equals("regular") || f.getType().equals("old")) {
			//System.out.println(f.getName() + " type is OK");
		}else throw new IllegalArgumentException("Film type can only be 'new', 'regular' or 'old' !");
	}

	public void removeFilm (Film f) {
		films.remove(f);
	}
	
	public void changeFilmType (Film f, String newType) {
		if(newType.equals("new") || newType.equals("regular") || newType.equals("old")) {
			f.setType(newType);
		}else throw new IllegalArgumentException("Film type can only be 'new', 'regular' or 'old' !");		
		System.out.println(f.getType());
	}
	
	public void listAllFilms() {
		for(Film f : films) {
			System.out.println(f.getName());
		}
	}
	
	public void listAllAvailableFilms() {
		for(Film f : films) {
			if(f.getStatus().equals("available")) {
				System.out.println(f.getName());
			}			
		}
	}
	
	public void checkIfTheFilmIsInTheStoreInventory(Film f) {
		if(films.contains(f)) {
			//System.out.println(f.getName() + " is in the store inventory");
		}else throw new IllegalArgumentException("This film is not in the store inventory!");
	}

	public ArrayList<Film> getFilms() {
		return films;
	}
}
