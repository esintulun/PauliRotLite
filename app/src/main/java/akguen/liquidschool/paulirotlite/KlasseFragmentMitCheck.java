package akguen.liquidschool.paulirotlite;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ListView;

import java.util.ArrayList;

import model.CustomAdapter_l;
import model.SchuelerMitCBox;

public class KlasseFragmentMitCheck extends Fragment {

        Button btnnext, btnselect, btndeselect;
        ArrayList<SchuelerMitCBox> modelArrayList;
        private CustomAdapter_l customAdapter;


        @Nullable
        @Override
        public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

            View rootView=inflater.inflate(R.layout.fragment_schueler,container,false);
            ListView lv= (ListView) rootView.findViewById(R.id.schuelerListView);
            //btnselect = (Button) findViewById(R.id.select);
            // btndeselect = (Button) findViewById(R.id.deselect);
            btnnext = (Button) rootView.findViewById(R.id.btn_zumvergehen);
            modelArrayList = getModel(false);
            //customAdapter = new CustomAdapterBack(getContext(),schuelerMitCbListe);
            customAdapter = new CustomAdapter_l(getContext(),  modelArrayList);


            lv.setAdapter(customAdapter);
            //lv.setAdapter(customAdapter);

            //String [] parts = pasteData.split("<br />");


            btnnext.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {


                    final StringBuilder sb = new StringBuilder();
                    Log.i("adapter: count: ", "--"+customAdapter.getCount());
                    //Intent intent = new Intent(getActivity(), VorfaelleListView.class);
                    //Intent intent = new Intent(getActivity(), SchuelerVorfallInfo.class);


                    for(int i= 0; i < customAdapter.getCount(); i++){

                        if(((SchuelerMitCBox)(customAdapter.getItem(i))).isChecked()){
                            String name = ((SchuelerMitCBox)(customAdapter.getItem(i))).getSchuelerName();
                            sb.append(" " + name);

                            customAdapter.getName();
                            //intent.putExtra("name", sb.toString());
                        }
                    }
                   // startActivity(intent);

                }
            });


            return rootView;


        }

        private ArrayList<String> getSchuelerName() {
            //COLECTION OF CRIME MOVIES
            ArrayList<String> schuelerName=new ArrayList<>();
            schuelerName.add("alev");
            schuelerName.add("kaan");
            schuelerName.add("stefan");
            schuelerName.add("hans");
            schuelerName.add("ebru");
            schuelerName.add("fatma");
            schuelerName.add("esin");
            schuelerName.add("fazilet");
            schuelerName.add("petek");
            schuelerName.add("aysun");
            schuelerName.add("nadia");
            schuelerName.add("farouk");
            schuelerName.add("can");
            schuelerName.add("petek5");
            schuelerName.add("siegfried");
            schuelerName.add("ayse");
            schuelerName.add("gerd");


            return schuelerName;
        }

        @Override
        public String toString() {
            String title="Kl-5a";
            return title;
        }


        private ArrayList<SchuelerMitCBox> getModel(boolean isSelect){
            ArrayList<SchuelerMitCBox> list = new ArrayList<>();
            for(int i = 0; i < 17; i++){

                SchuelerMitCBox model = new SchuelerMitCBox();
                model.setChecked(isSelect);
                model.setSchuelerName(getSchuelerName().get(i));
                list.add(model);
            }
            return list;
        }


}
