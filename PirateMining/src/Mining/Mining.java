package Mining;

import java.awt.BorderLayout;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map.Entry;

import weka.classifiers.Classifier;
import weka.classifiers.trees.J48;
import weka.classifiers.trees.LMT;
import weka.core.Attribute;
import weka.core.FastVector;
import weka.core.Instance;
import weka.core.Instances;
import weka.gui.treevisualizer.PlaceNode2;
import weka.gui.treevisualizer.TreeVisualizer;

public class Mining {
	  /** The training data gathered so far. */
	  private Instances _data = null;

	  /** The filter used to generate the word counts. */
	  //private StringToWordVector _filter = new StringToWordVector();

	  /** The actual classifier. */
	  private Classifier _classifier = new J48();
	  
	  
	  public void setPirates(ArrayList<Pirate> pirates)
	  {
		  
		  FastVector attributes = new FastVector();
		  
		  String[] attributeNames = Pirate.getAttributeNames();
		  
		  for(String an : attributeNames)
		  {
			  FastVector att = new FastVector();
			  att.addElement("true");
			  att.addElement("false");
			  attributes.addElement(new Attribute(an, att));
		  }
		  
		  
		  
		 
		     // 2. create Instances object
		     _data = new Instances("MyRelation", attributes, 0);
		     
		     for(Pirate pirate : pirates)
		     {System.out.println(pirate);
		    	 double[] vals = new double[_data.numAttributes()];
		    	 
		    	 HashMap<String, Boolean> table = pirate.getTable();
		    	 int i=0;
		    	 
		    	// Instance ins = new Instance();
		    	 
		    	 //ins.
		    	 
		    	 for(Entry<String, Boolean> entry : table.entrySet())
		    	 {
		    		 vals[i] = entry.getValue() ? 1 : 0;
		    		 i++;
		    	 }
		    	 _data.add(new Instance(1.0, vals));
		    	 _data.add(new Instance(1.0, vals));
		     }
		     
		     _data.setClassIndex(_data.numAttributes() - 1); 
	  }
	  
	  
	  public void evaluate()
	  {
		  // train classifier
		  Classifier cls = new J48();
		  try {
			cls.buildClassifier(_data);
			

		     // display classifier
		     final javax.swing.JFrame jf = 
		       new javax.swing.JFrame("Weka Classifier Tree Visualizer: J48");
		     jf.setSize(500,400);
		     jf.getContentPane().setLayout(new BorderLayout());
		     TreeVisualizer tv = new TreeVisualizer(null,
		         ((J48) cls).graph(),
		         new PlaceNode2());
		     jf.getContentPane().add(tv, BorderLayout.CENTER);
		     jf.addWindowListener(new java.awt.event.WindowAdapter() {
		       public void windowClosing(java.awt.event.WindowEvent e) {
		         jf.dispose();
		       }
		     });
		 
		     jf.setVisible(true);
		     tv.fitToScreen();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		  // evaluate classifier and print some statistics
		  //Evaluation eval = new Evaluation(_data);
		  //eval.evaluateModel(cls, test);
		  //System.out.println(eval.toSummaryString("\nResults\n======\n", false));
	  }
	  
	  
	  public static void main(String[] args)
	  {
		  ArrayList<Pirate> l = new ArrayList<Pirate>();
		  Pirate p = new Pirate();
		  p.setAttribute("bidule", true);
		  l.add(p);
		  p = new Pirate();
		  l.add(p);
		  p.setAttribute("bidule", false);
		  
		  Mining m = new Mining();
		  m.setPirates(l);
		  m.evaluate();
	  }
	  
}
