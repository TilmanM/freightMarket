package demand;

import java.util.Comparator;

public class ProbabilityTupleComparator implements Comparator<ProbabilityTuple> {


	public int compare(ProbabilityTuple arg0, ProbabilityTuple arg1) {
		
		if((arg0.getChoiceProbability() - arg1.getChoiceProbability() > 0)){
			return 1;
		}
		
		if((arg0.getChoiceProbability() - arg1.getChoiceProbability() < 0)){
			return -1;
		}
		
		else{
			return 0;
		}
		
	}

		
	
}
