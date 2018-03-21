package rental.store;


public class Store {

	public static void main(String[] args) {
		Inventory i = new Inventory();
		Price p = new Price();
		Film f1 = new Film("Matrix:Revolution", "old", "available");
		Film f2 = new Film("Shrek: The Return", "new", "available");
		Film f3 = new Film("Spooky Scary Skeletons", "regular", "available");
		Film f4 = new Film("Power Rangers", "cringe", "available");
		//System.out.println(f1);
		try{		
			i.addFilm(f1);
			i.addFilm(f2);
			i.addFilm(f3);
			i.addFilm(f4);
		}catch(IllegalArgumentException e1) {
			System.out.println(e1.getMessage());
		}
		
		//i.removeFilm(f1);
		//i.changeFilmType(f2, "regular");
		
		i.listAllFilms();
		
		Customer c1 = new Customer();
		
		try{
			p.filmRental(c1, f2, i, 5); //we rent film for 5 days
			p.filmRental(c1, f4, i, 6); //this film is not in the inventory
		}catch(IllegalArgumentException e2) {
			System.out.println(e2.getMessage());
		}
	
		System.out.println("Total price: " + c1.getTotalPrice() + " EUR");
		System.out.println("Total bonus points: " + c1.getBonusPoints());
		
		c1.setBonusPoints(78); //use this only to test rental with bonus points
		try {
			p.bonusPointsRental(c1, f2, i, 4); //should be an exception, if num of days > 3
		}catch(IllegalArgumentException e3) {
			System.out.println(e3.getMessage());
		}
		
		try {
			p.bonusPointsRental(c1, f3, i, 2); //should be an exception, if the film type is not 'new'
		}catch(IllegalArgumentException e4) {
			System.out.println(e4.getMessage());
		}
		
		
		System.out.println("Remaining bonus points: " + c1.getBonusPoints());
		
		try {
			//p.filmReturn(c1, f2, 2); //we return film with 2-day delay
			p.filmReturn(c1, f3, -1); //can't return this one because of the exception (negative amount of days)
		}catch(IllegalArgumentException e5) {
			System.out.println(e5.getMessage());
		}
		
		try {
			p.filmReturn(c1, f3, 1); //can't return the film that we didn't rented
		}catch(IllegalArgumentException e6) {
			System.out.println(e6.getMessage());
		}
		
		System.out.println("Total late charge: " + c1.getTotalCharge() + " EUR");
		
		//i.listAllAvailableFilms();
	}

}
