package com.raul.rental_shop.Ultra_Vision.model.customer;

/**
 * @author Unknown
 * 
 * <P>Class provided on Moodle by CCT. It is available on Michael Weiss page.<br>
 * Although this class is available in the Michale Weiss page in the same<br>
 * section of this assignment it is not mentioned in the PDF.</p>
 * 
 * <p>I personally understand it is optional since is not in the PDF
 * requirements.</p>
 *
 */
public class MembershipCard {

	   private int points;
	   private boolean freeRentAllowed;
	   
	   /**
	    * @param points which type of int
	    */
	   public void addPoints(int points){
			this.points += points;
			setRentAllowed();
	   }
	   
	   /**
	    * @return true if it has 100 points or more. Otherwise false.
	    */
	   public boolean availFreeRent(){
			if(this.isfreeRentAllowed()){
				this.points -= 100;
				setRentAllowed();
				return true;
			} else {
				return false;
			}
	   }
	   
	   /**
	    * Change state of attribute freeRentAllowed to true if points 
	    * are larger or equal 100. Otherwise false.
	    */
	   private void setRentAllowed(){
			if (this.points >= 100){
				this.freeRentAllowed = true;
			} else {
				this.freeRentAllowed = false;
			}
	   }
	   
	   /**
	    * @return points which is type of int.
	    */
	   public int getPoints() {
			return  points;
       }
	   
	   /**
	    * @return freeRentAllowed which is type of int.
	    */
	   public boolean isfreeRentAllowed(){
			return freeRentAllowed;
	   
	   }
	   
}