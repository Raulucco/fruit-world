package udemy.android.rauluco.fruitworld.activities;

import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.ContextMenu;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.BaseAdapter;
import android.widget.GridView;
import android.widget.ListView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

import udemy.android.rauluco.fruitworld.R;
import udemy.android.rauluco.fruitworld.adapters.FruitAdapter;
import udemy.android.rauluco.fruitworld.models.Fruit;

public class MainActivity extends AppCompatActivity implements AdapterView.OnItemClickListener {
    private BaseAdapter gridAdapter;
    private BaseAdapter listAdapter;
    private ListView list;
    private GridView grid;
    private MenuItem showAsListBtn;
    private MenuItem showAsGridBtn;

    private List<Fruit> fruits;
    private  final String UNKNOWN_FRUIT_ORIGIN = "Unknown";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        forceIconBar();
        fruits = builFruitList();
        gridAdapter = new FruitAdapter(this, R.layout.fruit_grid_layout, fruits);
        listAdapter = new FruitAdapter(this, R.layout.fruit_list_layout, fruits);

        list = (ListView) findViewById(R.id.fruit_list_view);
        grid = (GridView) findViewById(R.id.fruit_grid_view);

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
        showAsGridBtn = menu.findItem(R.id.fruit_grid_view);
        showAsListBtn = menu.findItem(R.id.fruit_list_view);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.add_fruit:
                addFruit(new Fruit(UNKNOWN_FRUIT_ORIGIN, "Fruit #" + fruits.size(), R.mipmap.ic_launcher_round));
                break;
            case R.id.grid_view:
                switchToListView();
                break;
            case R.id.list_view:
                switchToGridView();
                break;
            default:
                return super.onOptionsItemSelected(item);
        }
        return true;
    }

    @Override
    public void onCreateContextMenu(ContextMenu menu, View v, ContextMenu.ContextMenuInfo menuInfo) {
        super.onCreateContextMenu(menu, v, menuInfo);
        MenuInflater inflater = getMenuInflater();
        AdapterView.AdapterContextMenuInfo info = (AdapterView.AdapterContextMenuInfo) menuInfo;
        menu.setHeaderTitle(fruits.get(info.position).getName());
        inflater.inflate(R.menu.context_menu_fruit, menu);
    }

    @Override
    public boolean onContextItemSelected(MenuItem item) {
        return super.onContextItemSelected(item);
    }

    private void switchToGridView() {
        if (grid.getVisibility() != View.VISIBLE) {
            swichtViews(grid, list);
            switchButtons(showAsGridBtn, showAsListBtn);
        }
    }

    private void switchToListView() {
        if (list.getVisibility() != View.VISIBLE) {
            swichtViews(list, grid);
            switchButtons(showAsListBtn, showAsGridBtn);
        }
    }

    private void switchButtons(MenuItem reveal, MenuItem hide) {
        reveal.setVisible(true);
        hide.setVisible(false);
    }

    private void swichtViews(View revealView, View hideView) {
        hideView.setVisibility(View.INVISIBLE);
        revealView.setVisibility(View.VISIBLE);
    }

    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
        showFruitInfo(fruits.get(position));
    }

    private void showFruitInfo(Fruit fruit) {
        if (fruit.getOrigin().equals(UNKNOWN_FRUIT_ORIGIN)) {
            Toast.makeText(this, "We don't have any information from this fruit", Toast.LENGTH_SHORT).show();
        } else {
            Toast.makeText(this, "" + fruit.getName() + " is from " + fruit.getOrigin(), Toast.LENGTH_SHORT).show();
        }
    }

    private void addFruit (Fruit fruit) {
        fruits.add(fruit);
        notifyDataSetHasChanged();
    }

    private void deleteFruit(int position) {
        fruits.remove(position);
        notifyDataSetHasChanged();
    }

    private void notifyDataSetHasChanged() {
        listAdapter.notifyDataSetChanged();
        gridAdapter.notifyDataSetChanged();
    }
}
