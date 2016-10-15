package dam.isi.frsf.utn.edu.ar.laboratorio3v2;

import android.content.Intent;
import android.inputmethodservice.Keyboard;
import android.support.v7.app.AppCompatActivity;

import android.os.Bundle;
import android.support.v7.widget.ListViewCompat;
import android.text.Layout;
import android.view.ContextMenu;
import android.view.Menu;//Lo agregué yo porque no me veía los Menu
import android.view.MenuInflater;
import android.view.MenuItem;//Lo agregué yo porque no me veía los MenuItem
//import android.view.ContextMenu;
//import android.widget.AdapterView;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

import java.text.DecimalFormat;
import java.util.ArrayList;
import java.lang.String;
import java.util.Arrays;
import java.util.Random;
import dam.isi.frsf.utn.edu.ar.laboratorio3v2.AdaptadorOfLaboral;

public class MainActivity extends AppCompatActivity {

    /**********************************Declaración de Variables************************************/
    //Se crea una objeto tipo ListView
    private ListView listVw;

    //Se crea un ArrayList de tipo Trabajo con Todos los trabajos
    private ArrayList<Trabajo> listaTrabajos;

    //Se crea un objeto de tipo AdaptadorDias
    private AdaptadorOfLaboral adaptador;



    /****************************************** Métodos *******************************************/

    /*-----------------------------------------On Create------------------------------------------*/
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // llamamos a un Inicializar listas trabajos que cargue cada trabajo dentro de cada categoria
        inicializarListasTrabajo();

        //llamamos a un método que hace una lista sola con todas las listas trabajo de las distintas categorías
        listaTrabajos = new ArrayList<Trabajo>();
        listaTotalTrabajos();

        //Se define un nuevo adaptador de tipo AdaptadorOfLaboral donde se le pasa como argumentos el contexto de la actividad y el arraylist de los trabajos
        adaptador= new AdaptadorOfLaboral(this,listaTrabajos );//getApplicationContext(),Arrays.asList(jobs)

        listVw = (ListView) findViewById(R.id.listview);

        //Se establece el adaptador en la Listview
        listVw.setAdapter(adaptador);

        //Esto es mas que nada es a nivel de diseño con el objetivo de crear unas lineas mas anchas entre item y item
        listVw.setDividerHeight(3);

        //Registramos que el listView de Ofertas Laborales, tendrá asociado un Menú Contextual
        registerForContextMenu(listVw);

     }

    /*----------------------- On Create Option Menu (creación Menú Ppal)--------------------------*/
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_principal, menu);
        return true;

    }

    /*------------------- On Options Item Selected (Listener del Menú Ppal)-----------------------*/
    public boolean onOptionsItemSelected(MenuItem item) {

        switch (item.getItemId()) {
            case R.id.idMenu1:
                Intent tarea= new Intent(this,NuevaOfLaboral.class);
                tarea.putExtra("cantidadTrabajos",Integer.toString(listaTrabajos.size()));
                startActivityForResult(tarea,0);
                 return true;
            case R.id.idMenu2:
                 // do whatever
                 return true;
            case R.id.idMenu3:
                Toast.makeText(this, "Opción Configurar: en Mantenimieto!", Toast.LENGTH_LONG).show();
                return true;
            case R.id.idMenu4:
                // Cerramos app
                finish();
                return true;
            default:
                 return super.onOptionsItemSelected(item);
        }
    }

    /*----------------- On Create Context Option Menu (creación Menú Contextual)------------------*/
    public void onCreateContextMenu(ContextMenu menu, View v, ContextMenu.ContextMenuInfo menuInfo){

        super.onCreateContextMenu(menu,v,menuInfo);
        menu.setHeaderTitle("Acciones:");

        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.menu_contextual, menu);

    }

    /*---------------------------- On Context Item Selected --------------------------------------*/
    public boolean onContextItemSelected(MenuItem item) {

        switch (item.getItemId()){
            case R.id.Postular:
                Toast.makeText(this, "Se registro la postulación con Exito.", Toast.LENGTH_LONG).show();
                return true;
            case R.id.Compartir:
                Toast.makeText(this, "Se Compartio el Item.", Toast.LENGTH_LONG).show();
                return true;
            default:
                return super.onContextItemSelected(item);
        }
    }

    /*-------------------------------- On Activity Result ----------------------------------------*/
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        Trabajo newTrabajo = (Trabajo) data.getSerializableExtra("resultado");

        if(newTrabajo.getId() != 0) { // Si NO es un Trabajo Nuevo Vacío, osea que no hayan Cancelado la operación
            Toast.makeText(getBaseContext(), "Nuevo Trabajo: " + newTrabajo.getDescripcion(), Toast.LENGTH_LONG).show();

            // Insertamos en la Categoría correspondiente el Nuevo Trabajo
            Categoria.CATEGORIAS_MOCK[newTrabajo.getCategoria().getId()].addTrabajo(newTrabajo);

            // Incorporamos a la "listaTrabajos" el Nuevo Trabajo
            this.listaTotalTrabajos();

            //Se notifica al adaptador de que el ArrayList que tiene asociado ha sufrido cambios (forzando asi a ir al metodo getView())
            adaptador.notifyDataSetChanged();
        }
    }

    /*------------------------------ InicializarListasTrabajo----------------------------------*/
    //Inicializa las listas de cada Categoría con los trabajos que le pertenecen
    private void inicializarListasTrabajo() {

        int idCat;
        int idTrab = Trabajo.TRABAJOS_MOCK.length;

        for (int i = 0; i < idTrab; i++) {
            idCat = (Trabajo.TRABAJOS_MOCK[i]).getCategoria().getId();
            Categoria.CATEGORIAS_MOCK[idCat-1].addTrabajo(Trabajo.TRABAJOS_MOCK[i]); // inserta en la lista de Trabajos de la Categoría, el trabajo TRABAJOS_MOCK[i]
       }
    }

    /*------------------------------------listaTotalTrabajos--------------------------------------*/
    protected void listaTotalTrabajos() {

        for(int i = 0; i < Categoria.CATEGORIAS_MOCK.length; i++){
            listaTrabajos.addAll(Categoria.CATEGORIAS_MOCK[i].getTrabajo());
        }
    }

    /*------------------------------------getListaTrabajos--------------------------------------*/
    public ArrayList<Trabajo> getListaTrabajos(){
        return listaTrabajos;
    }

}
