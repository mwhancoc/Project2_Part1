package edu.ncsu.soc.project2.part1;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;





import com.hp.hpl.jena.rdf.model.Model;
import com.hp.hpl.jena.rdf.model.ModelFactory;
import com.hp.hpl.jena.rdf.model.Property;
import com.hp.hpl.jena.rdf.model.RDFNode;
import com.hp.hpl.jena.rdf.model.Resource;
import com.hp.hpl.jena.rdf.model.SimpleSelector;
import com.hp.hpl.jena.rdf.model.Statement;
import com.hp.hpl.jena.rdf.model.StmtIterator;

import android.app.Activity;
import android.content.res.AssetManager;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.TextView;

public class MainActivity extends Activity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		
		TextView textView = (TextView) findViewById(R.id.activity_main_text_view);
		
		//works! next open a file and print to the textView 
		//textView.setText("Hey Mike!");
		
		// open demoData.rdf
		// create an empty model
		Model model = ModelFactory.createDefaultModel();
		AssetManager assetManager = getAssets();
		String output = "";
		
		InputStream inputStream;
		
			try {
				inputStream = assetManager.open("demoData.rdf");
				model.read(inputStream, null);
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
			// read the RDF/XML file
			

			StmtIterator iter = model.listStatements();
			
			// print out the predicate, subject and object of each statement
			while (iter.hasNext())
			{
			    Statement stmt      = iter.nextStatement();  // get next statement
			    Resource  subject   = stmt.getSubject();     // get the subject
			    Property  predicate = stmt.getPredicate();   // get the predicate
			    RDFNode   object    = stmt.getObject();      // get the object

			    output += subject.toString();
			    output += " " + predicate.toString() + " ";
			    if(object instanceof Resource)
			    {
			       output += object.toString() + ".\n\n";
			    } 
			    else{
			        // object is a literal
			        output += " \"" + object.toString() + "\"" + ".\n\n";
			    }
			}
			textView.setText(output);
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.main, menu);
		return true;
	}

	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		// Handle action bar item clicks here. The action bar will
		// automatically handle clicks on the Home/Up button, so long
		// as you specify a parent activity in AndroidManifest.xml.
		int id = item.getItemId();
		if (id == R.id.action_settings) {
			return true;
		}
		return super.onOptionsItemSelected(item);
	}
}
