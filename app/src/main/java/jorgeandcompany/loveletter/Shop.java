package jorgeandcompany.loveletter;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;

public class Shop extends AppCompatActivity implements View.OnClickListener{

    protected Button back;
    protected Button buyToken;
    protected Button buySkin;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_shop);
        back = (Button) findViewById(R.id.b);
        buyToken = (Button) findViewById(R.id.token);
        buySkin = (Button) findViewById(R.id.skin);
        back.setOnClickListener(this);
        buyToken.setOnClickListener(this);
        buySkin.setOnClickListener(this);

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_shop, menu);
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
    public void onClick(View v)
    {
        switch (v.getId())
        {
            case R.id.b:
            {
                Intent theToken = new Intent(this, MainMenu.class);
                startActivity(theToken);
                finish();
                break;
            }
            case R.id.skin:
            {
                Intent theToken = new Intent(this, Skins.class);
                startActivity(theToken);
                finish();
                break;
            }
            case R.id.token:
            {
                Intent theToken = new Intent(this, Token.class);
                startActivity(theToken);
                finish();
                break;
            }
        }

    }
}
