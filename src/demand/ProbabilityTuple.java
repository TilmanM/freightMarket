package demand;

import supply.LinearOffer;

public class ProbabilityTuple {
	
		private LinearOffer offer;
		private double choiceProbability;
		private double utility;
		
		public ProbabilityTuple(LinearOffer offer){
			this.offer = offer;
		}

		public double getChoiceProbability() {
			return choiceProbability;
		}

		public void setChoiceProbability(double choiceProbability) {
			this.choiceProbability = choiceProbability;
		}

		public LinearOffer getOffer() {
			return offer;
		}

		public double getUtility() {
			return utility;
		}

		public void setUtility(double utility) {
			this.utility = utility;
		}
	
		
		
		
		
}
