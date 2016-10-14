package dam.isi.frsf.utn.edu.ar.laboratorio3v2;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.Arrays;



import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;


import android.net.Uri;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.method.ScrollingMovementMethod;
import android.util.Log;
import android.view.View;

import android.widget.RadioButton;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Button;

import android.widget.RadioGroup;
import android.widget.Toast;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Random;



public class Categoria implements Serializable {

    //--------Declaración de Variables--------//
    private Integer id;
    private String descripcion;     //Nombre de la Categoría
    private ArrayList<Trabajo> trabajos; //Lista con todos los TRABAJOS de ésta categoría


    //--------Declaración de Constante--------//
    public static final Categoria[] CATEGORIAS_MOCK= new Categoria[]{

            new Categoria(1,"Arquitecto"),
            new Categoria(2,"Desarrollador"),
            new Categoria(3,"Tester"),
            new Categoria(4,"Analista"),
            new Categoria(5,"Mobile Developer"),
            new Categoria(6,"Otra..")
    };

    //---------------------------------------Constructor------------------------------------------//
    public Categoria(){
        this.trabajos=new ArrayList<Trabajo>();
    }

    //---------------------------------------Constructor------------------------------------------//
    public Categoria(Integer id, String desc){
        this();
        this.id = id;
        this.descripcion = desc;
    }

    //---------------------------------------Gets y Sets------------------------------------------//
    public Integer getId() {
        return id;
    }
    //----------------------------------------//
    // Agregado por mi
    public Integer getIdOf(String desc) {

        for(int i = 0; i < CATEGORIAS_MOCK.length; i++) {
            if (CATEGORIAS_MOCK[i].getDescripcion().equals(desc))
                return (CATEGORIAS_MOCK[i].getId());
        };
        return 6;
    }
    //----------------------------------------//
    public void setId(Integer id) {
        this.id = id;
    }
    //----------------------------------------//
    public String getDescripcion() {
        return descripcion;
    }
    //----------------------------------------//
    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }
    //----------------------------------------//
    public ArrayList<Trabajo> getTrabajo() {
        return trabajos;
    }
    //----------------------------------------//
    public void setTrabajo(ArrayList<Trabajo> trabajos) {
        this.trabajos = trabajos;
    }

    //---------------------------------------Add Trabajo------------------------------------------//
    public void addTrabajo(Trabajo t){
        this.trabajos.add(t);
    }

    // Agregado por mi
    /*--------------------------------------cantidadTrabajos--------------------------------------*/
  /*  protected int cantidadTrabajos(){

        int i = 0;
        int countId = 0;
        while(i < Categoria.CATEGORIAS_MOCK.length){
            countId =+ (Categoria.CATEGORIAS_MOCK[i]).getTrabajo().size();
            i++;
        }
        return countId;
    }*/

}