package subhash.androidinterview;

import java.util.Locale;

import android.os.Bundle;
import android.speech.tts.TextToSpeech;

import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;

public class Tough_question extends AppCompatActivity implements
		OnClickListener {
	TextView tvquestion, tvanswer, tvtotallength_yy, tvpresentindex_xx;

	Button bleft, bshow, bright;

	String[] tough_question, tough_answers;

	int index;
	
	private static final String default_answer = "Press \"A\" Button for the Answer"; 

	// Variables & object of TTS
	TextToSpeech ttsobject;
	int result;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.questions);

		// Codes to add Action Bar

		LinearLayout questions_ll = (LinearLayout) findViewById(R.id.question_page_titlebar);
		getSupportActionBar().setDisplayOptions(ActionBar.DISPLAY_SHOW_CUSTOM);
		getSupportActionBar().setCustomView(R.layout.questions_title_bar);

		Button bspeak = (Button) findViewById(R.id.bspeak);
		Button bstop_mute = (Button) findViewById(R.id.bstop_mute);
		TextView tv_category = (TextView) findViewById(R.id.tv_questions_titlebar);
		tv_category.setText("Tough Questions");

		
		
		// Initialization of TextView
		tvquestion = (TextView) findViewById(R.id.tvquestion);
		tvanswer = (TextView) findViewById(R.id.tvanswer);
		tvpresentindex_xx = (TextView) findViewById(R.id.tvxx);
		tvtotallength_yy = (TextView) findViewById(R.id.tvyy);

		// Initialization of Buttons
		bleft = (Button) findViewById(R.id.bleft);
		bshow = (Button) findViewById(R.id.bshowanswer);
		bright = (Button) findViewById(R.id.bright);

		// Importing The string array from Values folder
		tough_question = getResources().getStringArray(R.array.difficult_ques);
		tough_answers = getResources().getStringArray(R.array.difficult_ans);

		// Onclick Listener for 3 Buttons
		bleft.setOnClickListener(this);
		bshow.setOnClickListener(this);
		bright.setOnClickListener(this);
		bspeak.setOnClickListener(this);
		bstop_mute.setOnClickListener(this);

		// Setting Values to our Variable and 4 TextViews
		index = 0;
		tvquestion.setText(tough_question[index]);
		tvanswer.setText(default_answer);
		tvpresentindex_xx.setText(String.valueOf(index + 1));
		tvtotallength_yy.setText("/" + String.valueOf(tough_question.length));

		
		// TTS object and Listener initialization
				ttsobject = new TextToSpeech(Tough_question.this,
						new TextToSpeech.OnInitListener() {

							@Override
							public void onInit(int status) {

								if (status == TextToSpeech.SUCCESS) {

									result = ttsobject.setLanguage(Locale.US);

								} else {

									Toast.makeText(getApplicationContext(),
											"Feature not Supported in Your Device",
											Toast.LENGTH_SHORT).show();

								}

							}
						});
		
	}

	@Override
	public void onClick(View v) {

		switch (v.getId()) {
		case R.id.bleft:
			tvanswer.setText(default_answer);
			index--;

			if (index == -1) {

				index = tough_question.length - 1;
				tvquestion.setText(tough_question[index]);

				tvpresentindex_xx.setText(String.valueOf(index + 1));
			} else {
				tvquestion.setText(tough_question[index]);

				tvpresentindex_xx.setText(String.valueOf(index + 1));
			}

			break;

		case R.id.bshowanswer:

			tvanswer.setText(tough_answers[index]);

			break;

		case R.id.bright:
			tvanswer.setText(default_answer);

			index++;

			if (index == tough_question.length) {

				index = 0;
				tvquestion.setText(tough_question[index]);

				tvpresentindex_xx.setText(String.valueOf(index + 1));

			} else {
				tvquestion.setText(tough_question[index]);

				tvpresentindex_xx.setText(String.valueOf(index + 1));
			}
			break;
		case R.id.bspeak:

			if (result == TextToSpeech.LANG_NOT_SUPPORTED
					|| result == TextToSpeech.LANG_MISSING_DATA) {

				Toast.makeText(getApplicationContext(),
						"Feature not Supported in Your Device",
						Toast.LENGTH_SHORT).show();

			} else {

				
				if(tvanswer.getText().toString().equals(default_answer)){
					
					
					
				}else{
					ttsobject.speak(tough_answers[index],
						TextToSpeech.QUEUE_FLUSH, null);
				}
			}

			break;

		case R.id.bstop_mute:

			if (ttsobject != null) {

				ttsobject.stop();

			}

			break;

		}

		}

	
	@Override
	protected void onDestroy() {
		// TODO Auto-generated method stub
		super.onDestroy();
		if(ttsobject != null){
			
			ttsobject.stop();
			ttsobject.shutdown();
			
		}
	
	}
}