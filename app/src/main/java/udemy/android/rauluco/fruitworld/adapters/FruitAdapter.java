package udemy.android.rauluco.fruitworld.adapters;


import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.List;

import udemy.android.rauluco.fruitworld.R;
import udemy.android.rauluco.fruitworld.models.Fruit;

public class FruitAdapter extends BaseAdapter {
    private Context context;
    private int layout;
    private List<Fruit> list;

    public FruitAdapter(Context context, int layout, List<Fruit> list) {
        this.context = context;
        this.layout = layout;
        this.list = list;
    }

    @Override
    public int getCount() {
        return this.list.size();
    }

    @Override
    public Object getItem(int position) {
        return this.list.get(position);
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ViewMemoizer memoView;

        if (convertView == null) {
            convertView = LayoutInflater.from(context).inflate(layout, null);
            memoView = new ViewMemoizer();
            memoView.name = (TextView) convertView.findViewById(R.id.name_text_view);
            memoView.origin = (TextView) convertView.findViewById(R.id.origin_text_view);
            memoView.icon = (ImageView) convertView.findViewById(R.id.icon_image_view);
            convertView.setTag(memoView);
        } else {
            memoView = (ViewMemoizer) convertView.getTag();
        }
        final Fruit fruit = (Fruit) getItem(position);
        memoView.name.setText(fruit.getName());
        memoView.origin.setText(fruit.getOrigin());
        memoView.icon.setImageResource(fruit.getIcon());
        return  convertView;
    }

    static class ViewMemoizer {
        private TextView name;
        private TextView origin;
        private ImageView icon;
    }
}
