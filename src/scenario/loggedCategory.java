package scenario;

public enum loggedCategory{
	FLOWS, TSPs, OPEN, PRICE;
	
	public String toString(){
		 if(this.name().equals("FLOWS")){
			 return("Number of Flows ");
		 }
		 else if(this.name().equals("TSPs")){
			 return("Number of TSPs ");
		 }
		 else if(this.name().equals("OPEN")){
			 return("Is lane open?");
		 }
		 else if(this.name().equals("PRICE")){
			 return("Average Market Price ");
		 }
		 return null;
		
	}
}