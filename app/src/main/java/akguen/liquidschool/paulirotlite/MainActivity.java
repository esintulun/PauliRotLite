package akguen.liquidschool.paulirotlite;

import android.content.Intent;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.support.design.widget.NavigationView;
import android.widget.Toast;

import java.util.List;

import db.SchuelerDataSource;
import db.SchuelerStammgruppeDataSource;
import db.StammgruppeDataSource;
import model.Schueler;
import model.SchuelerStammgruppe;
import model.Stammgruppe;


public class MainActivity extends AppCompatActivity {

    private DrawerLayout mDrawerLayout;

    SchuelerDataSource dataSource;
    StammgruppeDataSource stammgruppeDataSource;
    SchuelerStammgruppeDataSource schuelerStammgruppeDataSource;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);



        Intent datenerzeugenintent = new Intent(MainActivity.this, DatenInDieDBActivity.class);
        datenerzeugenintent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
        startActivity(datenerzeugenintent);
/*
        dataSource = new SchuelerDataSource(this);
        stammgruppeDataSource = new StammgruppeDataSource(this);
        schuelerStammgruppeDataSource = new SchuelerStammgruppeDataSource(this);
        //TODO ---  in den onResume()
        dataSource.open();
        stammgruppeDataSource.open();
        schuelerStammgruppeDataSource.open();*/


       // showAllListEntries();



        ActionBar actionbar = getSupportActionBar();
        actionbar.setDisplayHomeAsUpEnabled(true);
        actionbar.setHomeAsUpIndicator(R.drawable.hamburger);


        //TODO starActivity Vorfaelle
        //Intent intent = new Intent(MainActivity.this, VorfaelleActivity.class);
       // startActivity(intent);


       /* Intent intent = new Intent(MainActivity.this, SchulerprofilActivity.class);
        startActivity(intent);*/

       /* Intent intent = new Intent(MainActivity.this, VergehenActivity.class);
        startActivity(intent);*/
        goDrawer();
    }

    private void goDrawer() {

        mDrawerLayout = findViewById(R.id.drawer_layout);

        mDrawerLayout.addDrawerListener(
                new DrawerLayout.DrawerListener() {

                    @Override
                    public void onDrawerSlide(View drawerView, float slideOffset) {
                        // Respond when the drawer's position changes
                    }

                    @Override
                    public void onDrawerOpened(View drawerView) {
                        // Respond when the drawer is opened
                    }

                    @Override
                    public void onDrawerClosed(View drawerView) {
                        // Respond when the drawer is closed
                    }

                    @Override
                    public void onDrawerStateChanged(int newState) {
                        // Respond when the drawer motion state changes
                    }
                }
        );

        NavigationView navigationView = findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(
                new NavigationView.OnNavigationItemSelectedListener() {

                    @Override
                    public boolean onNavigationItemSelected(MenuItem menuItem) {
                        Log.i("item", "on: "+menuItem.getItemId());

                        switch (menuItem.getItemId()) {
                            case android.R.id.home:
                                mDrawerLayout.openDrawer(GravityCompat.START);
                                return true;

                            case R.id.nav_klassen:

                                Intent klassenIntent = new Intent(MainActivity.this, KlassenActivity.class);
                                klassenIntent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                                startActivity(klassenIntent);
                                return true;


                            case R.id.nav_chronik:
                                Intent schuelerVorfaelle = new Intent(MainActivity.this, VorfaelleActivity.class);
                                schuelerVorfaelle.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                                startActivity(schuelerVorfaelle);
                                return true;

                            case R.id.nav_übersicht:
                                Intent übersichtActivity = new Intent(MainActivity.this, SchulerprofilActivity.class);
                                übersichtActivity.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                                startActivity(übersichtActivity);
                                return true;

                            case R.id.nav_vergehen:
                                Intent vergehenActivity = new Intent(MainActivity.this, VergehenActivity.class);
                                vergehenActivity.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                                startActivity(vergehenActivity);
                                return true;

                            case R.id.nav_fullscreen:
                                Intent fullscreen = new Intent(MainActivity.this, FullScreenActivity.class);
                                fullscreen.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                                startActivity(fullscreen);
                                return true;
                            case R.id.nav_unterrichtplan:
                                Intent unterrichtplan = new Intent(MainActivity.this, UnterrichtplanActivity.class);
                                unterrichtplan.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                                startActivity(unterrichtplan);
                                return true;

                            case R.id.nav_calendar:
                                Intent calendar = new Intent(MainActivity.this, CalendarActivity.class);
                                calendar.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                                startActivity(calendar);
                                return true;
                                /*
                            case R.id.nav_schulleiter:
                                return true;
                            case R.id.nav_camera:
                                //Intent intentCamera = new Intent(MainActivity.this, CameraTest.class);
                                Intent intentCamera = new Intent(MainActivity.this, Cameratest2.class);

                                //Intent intentCamera = new Intent(MainActivity.this, CameraActivity.class);
                                intentCamera.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                                startActivity(intentCamera);
                                return true;
                            case R.id.nav_statistics:
                                //Intent intentCamera = new Intent(MainActivity.this, CameraTest.class);
                                Intent statistics = new Intent(MainActivity.this, Statistics.class);
                                startActivity(statistics);
                                return true;

                            case R.id.nav_mailsend:
                                //Intent intentCamera = new Intent(MainActivity.this, CameraTest.class);
                                Intent mailsend = new Intent(MainActivity.this, SendMailActivity.class);
                                startActivity(mailsend);
                                return true;*/
                        }

                        // set item as selected to persist highlight
                        menuItem.setChecked(true);
                        // close drawer when item is tapped
                        mDrawerLayout.closeDrawers();

                        // Add code here to update the UI based on the item selected
                        // For example, swap UI fragments here

                        return true;
                    }
                });

    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        Log.i("item", ""+item.getItemId());

        switch (item.getItemId()) {
            case android.R.id.home:
                mDrawerLayout.openDrawer(GravityCompat.START);
                return true;

                // TODO kann es weg, weil es die Logout Funktion in option menu von der PauseActivity gibt.
                case R.id.logout:
                    Toast.makeText(this, "Settings wurde gedrückt", Toast.LENGTH_SHORT).show();
                    startActivity(new Intent(this, PauseActivity.class));

                    return true;
                default:
                    return super.onOptionsItemSelected(item);
            }
          /*
            case R.id.nav_schulleiter:
                return true;
            case R.id.nav_lehrer:
                return true;
            case R.id.nav_schueler:
                Log.i("item", ""+item.getItemId());
                 Intent intent = new Intent(MainActivity.this, SchuelerActivity.class);
                 intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                 startActivity(intent);
                return true;*/
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_logout, menu);
        return true;
    }

    private void showAllListEntries() {


        dataSource.createSchueler("Sila");
        dataSource.createSchueler("Ali");
        dataSource.createSchueler("Ayse");


        List<Schueler> schuelers = dataSource.getAllSchueler();

        for(Schueler schueler : schuelers) {
            Log.i("datenSchueler: ", " -- "+ schueler.getName());
        }

        stammgruppeDataSource.createStammgruppe("GruppeA");
        stammgruppeDataSource.createStammgruppe("GruppeB");
        stammgruppeDataSource.createStammgruppe("GruppeC");
        stammgruppeDataSource.createStammgruppe("GruppeD");


        List<Stammgruppe> stammgruppes = stammgruppeDataSource.getAllStammgruppen();

        for(Stammgruppe stammgruppe : stammgruppes) {
            Log.i("datenStammgruppe: ", " -- "+ stammgruppe.getName());
        }


        schuelerStammgruppeDataSource.createSchuelerStammgruppe(1, 2);
        schuelerStammgruppeDataSource.createSchuelerStammgruppe(1, 3);
        schuelerStammgruppeDataSource.createSchuelerStammgruppe(2, 4);
        schuelerStammgruppeDataSource.createSchuelerStammgruppe(2, 1);

        List<SchuelerStammgruppe>  schuelerStammgruppen=   schuelerStammgruppeDataSource.getAllSchuelerStammgruppen();


        for(SchuelerStammgruppe schuelerStammgruppe : schuelerStammgruppen) {
            Log.i("datenschuelerStam: ", " -- "+ schuelerStammgruppe.getSchueler());
            Log.i("datenschuelerStam: ", " -- "+ schuelerStammgruppe.getStammgruppe());

        }

    }


 /*   @Override
    protected void onResume() {
        super.onResume();
        Log.d("MainA onResume", "Datenquelle wird geöffnet");
        dataSource.open();
        stammgruppeDataSource.open();
        showAllListEntries();
    }


    @Override
    protected void onPause() {
        super.onPause();
        Log.d("MainA onPause", "Datenquelle wird geschlossen.");
        dataSource.close();
        stammgruppeDataSource.close();
    }*/

}
