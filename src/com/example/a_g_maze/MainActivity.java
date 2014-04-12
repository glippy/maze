package com.example.a_g_maze;

import android.os.Bundle;
import android.app.Activity;
import android.view.Menu;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;

public class MainActivity extends Activity implements OnClickListener{

	  Button btnFMaze;
	  Button btnHMaze;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		
		btnFMaze = (Button) findViewById(R.id.btnFMaze);
	    btnHMaze = (Button) findViewById(R.id.btnHMaze);
	    
	    btnFMaze.setOnClickListener(this);
	    btnHMaze.setOnClickListener(this);
		
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.main, menu);
		return true;
	}

	@Override
	public void onClick(View v) {
		switch (v.getId()) {
	    case R.id.btnFMaze:
			MazeView maze = new MazeView(this);
			setContentView(maze);
	    	
	    	break;
	    case R.id.btnHMaze:
	    	MazeHiddenView mazeHidden = new MazeHiddenView(this);
			setContentView(mazeHidden);
	    	break;
	    }
	}

}
