package jorgeandcompany.loveletter;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;

public class Skins extends AppCompatActivity implements View.OnClickListener{

    protected Button back;
    protected Button skin1;
    protected Button skin2;
    protected Button skin3;
    protected Button skin4;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_skins);
        back = (Button) findViewById(R.id.b);
        skin1 = (Button) findViewById(R.id.skin1);
        skin2 = (Button) findViewById(R.id.skin2);
        skin3 = (Button) findViewById(R.id.skin3);
        skin4 = (Button) findViewById(R.id.skin4);
        back.setOnClickListener(this);
        skin1.setOnClickListener(this);
        skin2.setOnClickListener(this);
        skin3.setOnClickListener(this);
        skin4.setOnClickListener(this);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_skins, menu);
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

    @Override
    public void onBackPressed() {
        Intent toShop = new Intent(this, Shop.class);
        startActivity(toShop);
        finish();
    }
}
