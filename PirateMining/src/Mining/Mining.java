package Mining;

import weka.classifiers.Classifier;
import weka.classifiers.trees.J48;
import weka.core.Instances;
import weka.filters.unsupervised.attribute.StringToWordVector;

public class Mining {
	  /** The training data gathered so far. */
	  private Instances _data = null;

	  /** The filter used to generate the word counts. */
	  private StringToWordVector _filter = new StringToWordVector();

	  /** The actual classifier. */
	  private Classifier _classifier = new J48();
	  
	  
}
