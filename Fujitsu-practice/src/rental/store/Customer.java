package rental.store;

import java.util.ArrayList;

public class Customer {
	private ArrayList<Film> rentedFilms;
	
	private int bonusPoints;
	private int totalPrice;
	private int totalCharge;

	public int getBonusPoints() {
		return bonusPoints;
	}
	
	public void setBonusPoints(int bonusPoints) {
		this.bonusPoints = bonusPoints;
	}

	public int getTotalPrice() {
		return totalPrice;
	}

	public void setTotalPrice(int totalPrice) {
		this.totalPrice = totalPrice;
	}

	public int getTotalCharge() {
		return totalCharge;
	}

	public void setTotalCharge(int totalCharge) {
		this.totalCharge = totalCharge;
	}

	public Customer() {
		rentedFilms = new ArrayList<Film>();
	}
	
	public void addRentedFilm(Film f) {
		rentedFilms.add(f);
	}
	
	public void removeRentedFilm (Film f) {
		rentedFilms.remove(f);
	}
	
	public void checkIfTheFilmWasRented(Film f) {
		if(rentedFilms.contains(f)) {
			//System.out.println(f.getName() + " was rented");
		}else throw new IllegalArgumentException("This film was not rented and can't be returned!");
	}
}
