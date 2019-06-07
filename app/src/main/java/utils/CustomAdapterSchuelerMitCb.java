package utils;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.CheckBox;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;

import akguen.liquidschool.paulirotlite.R;
import model.SchuelerMitCBox;

public class CustomAdapterSchuelerMitCb extends BaseAdapter {

    String name;
    private Context context;
    public static ArrayList<SchuelerMitCBox> schuelerMitCbListe;


    public CustomAdapterSchuelerMitCb(Context context, ArrayList<SchuelerMitCBox> schuelerMitCbListe) {

        this.context = context;
        this.schuelerMitCbListe = schuelerMitCbListe;

    }


    public String getName() {
        return name;
    }
    public void setName(String name) {

        this.name = name;
    }
    @Override
    public int getViewTypeCount() {
        return getCount();
    }

    @Override
    public int getItemViewType(int position) {

        return position;
    }

    //How many items are in the dataset represented in this data
    @Override
    public int getCount() {
        return schuelerMitCbListe.size();
    }

    @Override
    public Object getItem(int position) {
        return schuelerMitCbListe.get(position);
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        final ViewHolder holder;

        if (convertView == null) {

            holder = new ViewHolder();
            LayoutInflater inflater = (LayoutInflater) context
                    .getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            convertView = inflater.inflate(R.layout.schuelermitcheckbox, null, true);

            holder.tvName = (TextView) convertView.findViewById(R.id.nameTxt);
            holder.checkBox = (CheckBox) convertView.findViewById(R.id.checkBox);

            convertView.setTag(holder);


        } else {
            // the getTag returns the viewHolder object set as a tag to the view
            holder = (ViewHolder) convertView.getTag();
        }


        // holder.checkBox.setText("Checkbox "+position);
        holder.tvName.setText(schuelerMitCbListe.get(position).getSchuelerName());
        holder.checkBox.setChecked(schuelerMitCbListe.get(position).isChecked());
        holder.checkBox.setTag(R.integer.btnplusview, convertView); // ?
        holder.checkBox.setTag(position);//?

        holder.checkBox.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                View tempview = (View) holder.checkBox.getTag(R.integer.btnplusview);
                TextView tvName = (TextView) tempview.findViewById(R.id.nameTxt);
                Integer pos = (Integer) holder.checkBox.getTag();
                //Toast.makeText(context, "Checkbox " + pos + " clicked!", Toast.LENGTH_SHORT).show();
                Toast.makeText(context, "Name:  " + tvName.getText() + " clicked!", Toast.LENGTH_SHORT).show();
                name = tvName.getText().toString();

                if (schuelerMitCbListe.get(pos).isChecked()) {
                    schuelerMitCbListe.get(pos).setChecked(false);
                } else {
                    schuelerMitCbListe.get(pos).setChecked(true);
                }
            }
        });

        return convertView;
    }

    private class ViewHolder {

        protected CheckBox checkBox;
        private TextView tvName;

    }
}
