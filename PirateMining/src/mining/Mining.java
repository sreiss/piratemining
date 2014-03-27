package mining;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.Toolkit;
import java.io.StringReader;
import java.util.ArrayList;
import java.util.HashMap;

import javax.swing.JLabel;
import javax.swing.JOptionPane;

import weka.classifiers.Classifier;
import weka.classifiers.trees.J48;
import weka.core.Attribute;
import weka.core.FastVector;
import weka.core.Instance;
import weka.core.Instances;
import weka.gui.treevisualizer.Node;
import weka.gui.treevisualizer.PlaceNode2;
import weka.gui.treevisualizer.TreeBuild;
import weka.gui.treevisualizer.TreeVisualizer;

public class Mining {
	/** Les données Weka */
	private Instances _data = null;
	
	private String _explanation;

	public void setPirates(ArrayList<Pirate> pirates)
	{
		//Génération des attributs
		FastVector attributes = new FastVector();

		//On récupère le nom des attributs
		String[] attributeNames = Pirate.getAttributeNames();

		for(String an : attributeNames)
		{
			//On les ajoute à Weka
			FastVector att = new FastVector();
			att.addElement("true");
			att.addElement("false");
			attributes.addElement(new Attribute(an, att));
		}

		//Attribut final : pirate activé
		FastVector att = new FastVector();
		att.addElement("yes");
		att.addElement("no");
		attributes.addElement(new Attribute("ok", att, 0));


		// 2. create Instances object
		_data = new Instances("MyRelation", attributes, 0);
		_data.setClassIndex(_data.numAttributes() - 1); 
		for(Pirate pirate : pirates)
		{
			double[] vals = new double[_data.numAttributes()];

			//La table des données, côté logiciel (non Weka)
			HashMap<String, Boolean> table = pirate.getTable();
			
			int i=0;
			for(String an : attributeNames)
			{
				//Weka veut l'index de la valeur d'attribut à donner (ici 0 = "true", 1="false")
				vals[i] = table.get(an) ? 0 : 1;
				i++;
			}

			vals[vals.length -1] = pirate.isOk() ? 0 : 1;

			_data.add(new Instance(1.0, vals)); //On ajoute à Weka
		}

		System.out.println(_data); //Debug, affichage ARFF
	}

	public void explainGraph(Node n, StringBuffer str)
	{
		Node child1 = n.getChild(0).getTarget();
		Node child2 = n.getChild(1).getTarget();
		
		Node srcTrue = (n.getChild(0).getLabel().contains("true")) ? child1 : child2;
		Node srcFalse = (srcTrue == child1) ? child2 : child1;
	
		if(srcTrue.getLabel().contains("yes"))
		{
			str.append("il doit y avoir " + n.getLabel());
			
			if(!srcFalse.getLabel().contains("no"))
			{
				str.append(", sinon : ");
				explainGraph(srcFalse, str);
			}
		} else if(srcFalse.getLabel().contains("yes")) {
			
			str.append(" il ne doit pas y avoir " + n.getLabel());
			
			if(!srcTrue.getLabel().contains("no"))
			{
				str.append(" sinon : ");
				explainGraph(srcTrue, str);
			}
		} else if(!srcTrue.getLabel().contains("no")) {
			str.append(" il doit y avoir " + n.getLabel() + " ainsi que : ");
			explainGraph(srcTrue, str);
			if(!srcFalse.getLabel().contains("no"))
			{
				str.append(" mais s'il y a " + n.getLabel() + " alors : ");
				explainGraph(srcFalse, str);
			}
		} else {
			str.append(" il ne doit pas y avoir de " + n.getLabel() + " ainsi que :");
			explainGraph(srcFalse, str);
		}
	}


	public void evaluate()
	{
		//Création du classifier
		Classifier cls = new J48();
		try {
			cls.buildClassifier(_data); //On lui donne les données

			String graph = ((J48) cls).graph(); //Génération du graphique format text
			
			TreeBuild build = new TreeBuild();
			Node node = build.create(new StringReader(graph));
			
			try {
				StringBuffer buffer = new StringBuffer();
				explainGraph(node, buffer);
				_explanation = buffer.toString();
			} catch(Exception e)
			{
				_explanation = "";
			}
			
			
			if (node.getChild(0) != null) {//est représentatif
				//Affichage du graphique
				final javax.swing.JFrame jf = 
						new javax.swing.JFrame("Weka Classifier Tree Visualizer: J48");
				jf.setSize(500,400);
				jf.getContentPane().setLayout(new BorderLayout());
				TreeVisualizer tv = new TreeVisualizer(null,
						graph,
						new PlaceNode2());
				jf.getContentPane().add(tv, BorderLayout.CENTER);
				jf.getContentPane().add(new JLabel("<html>"+getExplanation()+"</html>"), BorderLayout.SOUTH);
				jf.addWindowListener(new java.awt.event.WindowAdapter() {
					public void windowClosing(java.awt.event.WindowEvent e) {
						jf.dispose();
					}
				});
			
				// Centrage de l'affichage
				Dimension d = Toolkit.getDefaultToolkit().getScreenSize();
				jf.setLocation(
						(int) ((d.getWidth() - jf.getWidth())/2),
						(int) ((d.getHeight() - jf.getHeight())/2)
				);
	
				jf.setVisible(true);
				tv.fitToScreen();
			} else {
				JOptionPane.showMessageDialog(null, "Le choix n'est pas assez repr�sentatif..", "Arbre", JOptionPane.ERROR_MESSAGE);

			}
		} catch (Exception e) {
			e.printStackTrace();
		}

	}
	
	
	public String getExplanation()
	{
		return "explication expérimentale : "+_explanation;
	}


}
