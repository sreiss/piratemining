package mining;

import java.awt.BorderLayout;
import java.io.StringReader;
import java.util.ArrayList;
import java.util.HashMap;

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

	
	public void explainGraph(Node n)
	{
		Node child1 = n.getChild(0).getTarget();
		Node child2 = n.getChild(1).getTarget();
		
		Node srcTrue = (n.getChild(0).getLabel().contains("true")) ? child1 : child2;
		Node srcFalse = (srcTrue == child1) ? child2 : child1;
	
		if(srcTrue.getLabel().contains("yes"))
		{
			System.out.println("il doit y avoir un " + n.getLabel());
			
			if(!srcFalse.getLabel().contains("no"))
			{
				System.out.println("sinon : ");
				explainGraph(srcFalse);
			}
		} else if(srcFalse.getLabel().contains("yes")) {
			
			System.out.println("il ne doit pas y avoir un " + n.getLabel());
			
			if(!srcTrue.getLabel().contains("no"))
			{
				System.out.println("sinon : ");
				explainGraph(srcTrue);
			}
		} else if(!srcTrue.getLabel().contains("no")) {
			System.out.println("il doit y avoir un " + n.getLabel() + " ainsi que : ");
			explainGraph(srcTrue);
			if(!srcFalse.getLabel().contains("no"))
			{
				System.out.println("mais s'il y a un " + n.getLabel() + " alors : ");
				explainGraph(srcFalse);
			}
		} else {
			System.out.println("il ne doit pas y avoir de " + n.getLabel() + " ainsi que :");
			explainGraph(srcFalse);
		}
	}

	public void evaluate()
	{
		//Création du classifier
		Classifier cls = new J48();
		try {
			cls.buildClassifier(_data); //On lui donne les données
			String graph = ((J48) cls).graph(); //Génération du graphique format text

			System.out.println(graph);
			

			
			TreeBuild build = new TreeBuild();
			Node node = build.create(new StringReader(graph));
			System.out.println(node.getChild(0).getLabel());
			
			explainGraph(node);
			
			//Affichage du graphique
			final javax.swing.JFrame jf = 
					new javax.swing.JFrame("Weka Classifier Tree Visualizer: J48");
			jf.setSize(500,400);
			jf.getContentPane().setLayout(new BorderLayout());
			TreeVisualizer tv = new TreeVisualizer(null,
					graph,
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
			
			e.printStackTrace();
		}

	}

}
