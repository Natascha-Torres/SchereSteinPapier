package nati.de;

import android.app.Activity;

import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

public class SchereSteinPapierAppActivity extends Activity {
	private Symbol schere = new Symbol("schere");
	private Symbol stein = new Symbol("stein");
	private Symbol papier = new Symbol("papier");
	
	private Symbol userSymbol = null;
	private Symbol computerSymbol = null;
	
	private int userSpielStand = 0;
	private int computerSpielStand = 0;
	
	private Symbol[] symbolAuswahl = new Symbol[3];
	
	/** Called when the activity is first created. */
	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.main);
		
		symbolAuswahl[0] = new Symbol("Schere");
		symbolAuswahl[1] = new Symbol("Stein");
		symbolAuswahl[2] = new Symbol("Papier");
		
		Button schereButton = (Button) findViewById(R.id.schereButton);
		schereButton.setOnClickListener(new SchereButtonListener());
		
		Button steinButton = (Button) findViewById(R.id.steinButton);
		steinButton.setOnClickListener(new SteinButtonListener());
		
		Button papierButton = (Button) findViewById(R.id.papierButton);
		papierButton.setOnClickListener(new PapierButtonListener());
		
		
	} // End onCreate()
	private void spielzug() {
		computerSymbol = computerSymbolAuswaehlen();
		ImageView bild = (ImageView) findViewById(R.id.computerAuswahl);
		if(computerSymbol.equals(schere)) {
			bild.setImageResource(R.drawable.schere);
		}
		else if(computerSymbol.equals(stein)) {
			bild.setImageResource(R.drawable.stein);
		}
		else if(computerSymbol.equals(papier)) {
			bild.setImageResource(R.drawable.papier);
		}
		int gewinner = auswerten(userSymbol,computerSymbol);
		
		TextView user = (TextView) findViewById(R.id.userSpielStandText);
		user.setText("" + userSpielStand);
		
		TextView computer = (TextView) findViewById(R.id.computerSpielStandText);
		computer.setText("" + computerSpielStand);
		
		showToastGewinner(gewinner);
	}
	
	private class SchereButtonListener implements OnClickListener {

		public void onClick(View v) {
			userSymbol = schere;
			spielzug();
		}
	}
	private class SteinButtonListener implements OnClickListener {

		public void onClick(View v) {
			userSymbol = stein;
			spielzug();
		}		
	}
	private class PapierButtonListener implements OnClickListener {

		public void onClick(View v) {
			userSymbol = papier;
			spielzug();
		}		
	}
	
	private Symbol computerSymbolAuswaehlen() {
		int auswaehlen = (int)(Math.random() * 3);
		return symbolAuswahl[auswaehlen];
	}
	
	public int auswerten(Symbol userS, Symbol computerS) {
		if(userS.getName().equalsIgnoreCase(computerS.getName())) {
			return 0;
		}
		else if(userS.getName().equalsIgnoreCase("Schere") && computerS.getName().equalsIgnoreCase("Stein") ||
				userS.getName().equalsIgnoreCase("Stein") && computerS.getName().equalsIgnoreCase("Papier") ||
				userS.getName().equalsIgnoreCase("Papier") && computerS.getName().equalsIgnoreCase("Schere")) {
			computerSpielStand++;
			return 1;
		}
		else if(userS.getName().equalsIgnoreCase("Schere") && computerS.getName().equalsIgnoreCase("Papier") ||
				userS.getName().equalsIgnoreCase("Papier") && computerS.getName().equalsIgnoreCase("Stein") ||
				userS.getName().equalsIgnoreCase("Stein") && computerS.getName().equalsIgnoreCase("Schere")) {
			userSpielStand++;
			return 2;
		}
		return 0;
	} // End auswerten()
	
	private void showToastGewinner(int gewinner) {
		if(gewinner == 0) {
			Toast.makeText(this, R.string.gewinnerUnentschieden, Toast.LENGTH_SHORT).show();
		}
		else if(gewinner == 1) {
			Toast.makeText(this, R.string.gewinnerComputer, Toast.LENGTH_SHORT).show();
		}
		else if(gewinner == 2) {
			Toast.makeText(this, R.string.gewinnerUser, Toast.LENGTH_SHORT).show();
		}
	}	

} // End class SchereSteinPapierAppActivity