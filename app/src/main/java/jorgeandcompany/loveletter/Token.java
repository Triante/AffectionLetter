package jorgeandcompany.loveletter;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;

public class Token extends AppCompatActivity implements View.OnClickListener{

    protected Button back;
    protected Button one;
    protected Button four;
    protected Button ten;
    protected Button thirty;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_token);
        back = (Button) findViewById(R.id.b);
        one = (Button) findViewById(R.id.oneToken);
        four = (Button) findViewById(R.id.fourTokens);
        ten = (Button) findViewById(R.id.tenTokens);
        thirty = (Button) findViewById(R.id.thirtyTokens);
        back.setOnClickListener(this);
        one.setOnClickListener(this);
        four.setOnClickListener(this);
        ten.setOnClickListener(this);
        thirty.setOnClickListener(this);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_token, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId())
        {
            case R.id.b:
            {
                Intent theToken = new Intent(this, Shop.class);
                startActivity(theToken);
                finish();
                break;
            }
            case R.id.oneToken:
            {
                finish();
                break;
            }
            case R.id.fourTokens:
            {
                finish();
                break;
            }
            case R.id.tenTokens:
            {
                finish();
                break;
            }
            case R.id.thirtyTokens:
            {
                finish();
                break;
            }
        }
    }
}
