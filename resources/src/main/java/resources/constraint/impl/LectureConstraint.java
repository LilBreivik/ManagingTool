package resources.constraint.impl;
  
import java.util.Arrays;
import org.springframework.stereotype.Component; 
import resources.components.elements.POJO.Lecture.LecturePOJOBuilderRecipe;
import resources.components.elements.POJO.Persistence.LectureSchedulePOJO; 
import resources.constraint.strategies.VDTypConstraintStrategy;
import resources.constraint.strategies.VNameConstraintStrategy;
import resources.constraint.strategies.VZeitConstraintStrategy; 
 
 


/**
 * This class contains every constraint
 * that is needed to parse the contents of 
 * the xls file, to be more precisely, 
 * to parse the lectures correctly with their 
 * timings and paractice names...  
 * 
 * 
 * */

@Component
public class LectureConstraint 
						extends ConstraintImpl<LectureSchedulePOJO, LecturePOJOBuilderRecipe>{

	public LectureConstraint(VNameConstraintStrategy VNameConstraint, 
								VDTypConstraintStrategy VDTypConstraint,
									VZeitConstraintStrategy VZeitConstraint) {
		
		super(Arrays.asList( VNameConstraint, VDTypConstraint, VZeitConstraint), new LecturePOJOBuilderRecipe());
	}
		
}
