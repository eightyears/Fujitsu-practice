package rental.store;

public class Price {
	
	private final int PREMIUM_PRICE = 4;
	private final int BASIC_PRICE = 3;
	private int filmPrice;
	private int lateCharge;
	private int bonusPointsRequired;
	private int remainingBonusPoints;
	
	public Price(){		
	}	
	
	
	public void filmRental(Customer c, Film f, Inventory i, int numberOfDays) {
		i.checkIfTheFilmIsInTheStoreInventory(f);
		if(f.getType().equals("new")) {
			filmPrice = PREMIUM_PRICE * numberOfDays;
			c.setBonusPoints(c.getBonusPoints() + 2);
		}else if(f.getType().equals("regular")) {
			if(numberOfDays <= 3 && numberOfDays > 0) {
				filmPrice = BASIC_PRICE;
			}else {
				filmPrice = BASIC_PRICE + BASIC_PRICE * (numberOfDays - 3);
			}
			c.setBonusPoints(c.getBonusPoints() + 1);
		}else if(f.getType().equals("old")) {
			if(numberOfDays <= 5 && numberOfDays > 0) {
				filmPrice = BASIC_PRICE;
			}else {
				filmPrice = BASIC_PRICE + BASIC_PRICE * (numberOfDays - 5);
			}
			c.setBonusPoints(c.getBonusPoints() + 1);
		}
		c.addRentedFilm(f);
		f.setStatus("rented");
		c.setTotalPrice(c.getTotalPrice() + filmPrice);
		System.out.println(f.getName() + " (" + f.getType() + ") " + numberOfDays + " days " + filmPrice + " EUR");
	}
	
	public void filmReturn(Customer c, Film f, int numberOfExtraDays) {
		c.checkIfTheFilmWasRented(f);
		if(f.getType().equals("new") && numberOfExtraDays >= 0) {
			lateCharge = PREMIUM_PRICE * numberOfExtraDays;
		}else if((f.getType().equals("regular") || f.getType().equals("old")) && numberOfExtraDays >= 0) {
			lateCharge = BASIC_PRICE * numberOfExtraDays;
		}else throw new IllegalArgumentException("Number of delayed days can't be negative!");
		c.removeRentedFilm(f);
		f.setStatus("available");
		c.setTotalCharge(c.getTotalCharge() + lateCharge);
		System.out.println(f.getName() + " (" + f.getType() + ") " + numberOfExtraDays + " extra days " + lateCharge + " EUR");
	}
	
	public void bonusPointsRental(Customer c, Film f, Inventory i, int numberOfDays) {
		i.checkIfTheFilmIsInTheStoreInventory(f);
		bonusPointsRequired = 25 * numberOfDays;
		remainingBonusPoints = c.getBonusPoints() - bonusPointsRequired;
		if(remainingBonusPoints < 0) throw new IllegalArgumentException("You don't have enough bonus points!");
		else {
			if(f.getType().equals("new")) {	
				c.setBonusPoints(c.getBonusPoints() - bonusPointsRequired);
				c.addRentedFilm(f);
				f.setStatus("rented");
				System.out.println(f.getName() + " (" + f.getType() + ") " + numberOfDays + " days " + 
				" (Paid with " + bonusPointsRequired + " bonus points)");
			}else throw new IllegalArgumentException("Only 'new' films can be paid with bonus points!");			
		}
	}

}
