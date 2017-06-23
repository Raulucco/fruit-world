package udemy.android.rauluco.fruitworld.activities;

import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.BaseAdapter;
import android.widget.GridView;
import android.widget.ListView;

import java.util.ArrayList;
import java.util.List;

import udemy.android.rauluco.fruitworld.R;
import udemy.android.rauluco.fruitworld.adapters.FruitAdapter;
import udemy.android.rauluco.fruitworld.models.Fruit;

public class MainActivity extends AppCompatActivity implements AdapterView.OnItemClickListener {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        forceIconBar();
        List fruits = builFruitList();
        BaseAdapter gridAdapter = new FruitAdapter(this, R.layout.fruit_grid_layout, fruits);
        BaseAdapter listAdapter = new FruitAdapter(this, R.layout.fruit_list_layout, fruits);

        ListView list = (ListView) findViewById(R.id.fruit_list_view);
        GridView grid = (GridView) findViewById(R.id.fruit_grid_view);

        list.setOnItemClickListener(this);
        grid.setOnItemClickListener(this);

        list.setAdapter(listAdapter);
        grid.setAdapter(gridAdapter);
    }

    private List builFruitList() {
        return new ArrayList<Fruit> (){{
            add(new Fruit("banana", "Canarias", R.mipmap.ic_launcher));
            add(new Fruit("chirimolla", "Granada", R.mipmap.ic_launcher));
            add(new Fruit("albaricoque", "Almeria", R.mipmap.ic_launcher));
            add(new Fruit("naranja", "Valencia", R.mipmap.ic_launcher));
            add(new Fruit("coca", "Malaga", R.mipmap.ic_launcher));
            add(new Fruit("sandia", "Sevilla", R.mipmap.ic_launcher));
            add(new Fruit("cerazas", "Olztyn", R.mipmap.ic_launcher));
        }};
    }

    private void forceIconBar() {
        ActionBar bar = getSupportActionBar();

        bar.setIcon(R.mipmap.ic_launcher);
        bar.setDisplayUseLogoEnabled(true);
        bar.setDisplayShowHomeEnabled(true);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater menuInfl = getMenuInflater();
        menuInfl.inflate(R.menu.options_menu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.add_fruit:
                break;
            case R.id.grid_view:
                break;
            case R.id.list_view:
                break;
            default:
                return super.onOptionsItemSelected(item);
        }
        return true;
    }

    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

    }
}
