package dam.isi.frsf.utn.edu.ar.laboratorio3v2;

import android.app.Activity;
import android.content.Context;
import android.view.ContextMenu;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.CheckBox;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;
import android.view.ContextMenu;

import dam.isi.frsf.utn.edu.ar.laboratorio3v2.Trabajo;



//Esta clase extiende de ArrayAdapter para poder personalizarla a nuestro gusto
public class AdaptadorOfLaboral extends ArrayAdapter<Trabajo> {

    Context contexto;
    List<Trabajo> trabajos;
    LayoutInflater inflater;

    /*----------------------------------- Constructor --------------------------------------------*/
    //Constructor del AdaptadorDias donde se le pasaran por parametro el contexto de la aplicacion y el ArrayList de los dias
    public AdaptadorOfLaboral(Context context, List<Trabajo> trabajos) {
        //Llamada al constructor de la clase superior donde requiere el contexto, el layout y el arraylist
        super(context, R.layout.row, trabajos);/*R.layout.row*//*o 0*/
        inflater= LayoutInflater.from(context);/*context*/
        this.contexto = context;
        this.trabajos = trabajos;

    }

    /*-------------------------------------- Get View --------------------------------------------*/
    //Este metodo es el que se encarga de dibujar cada Item de la lista y se invoca cada vez que se
    //necesita mostrar un nuevo item. En el se pasan parámetros como la posicion del elemento mostrado,
    //la vista (View) del elemento mostrado y el conjunto de vistas.
    public View getView(int position, View convertView, ViewGroup parent) {

        DecimalFormat df = new DecimalFormat("#.##");
        String aux;
        Integer moneda;
        View item = convertView;

        //Creamos esta variable para almacenar posteriormente en el, la vista que ha dibujado
        ViewHolder viewholder;

        //Si se decide que no existe una vista reutilizable para el proximo item entra en la condicion.
        //De este modo tambien ahorramos tener que volver a generar vistas
        if (item == null) {

            //Obtenemos una referencia de Inflater para poder inflar el diseño
            LayoutInflater inf =(LayoutInflater) contexto.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            item = inf.inflate(R.layout.row, null);

            //Creamos un nuevo viewholder que se almacenara en el tag de la vista
            viewholder = new ViewHolder(item);

            //Ahora si, guardamos en el tag de la vista el objeto vistaitem
            item.setTag(viewholder);

        } else {
            //En caso de que la vista sea ya reutilizable se recupera el objeto VistaItem almacenada en su tag
            viewholder = (ViewHolder) item.getTag();
        }


        //Se cargan los datos desde el ArrayList
        viewholder.categoria.setText(trabajos.get(position).getCategoria().getDescripcion());
        viewholder.descripcion.setText(trabajos.get(position).getDescripcion());
        viewholder.horas.setText(String.format("%1$s %2$s %3$s","Horas:",trabajos.get(position).getHorasPresupuestadas(),"Max "));

        viewholder.precio.setText(String.format("%1$s %2$s", "$/Hora:", df.format(trabajos.get(position).getPrecioMaximoHora())));

        moneda = trabajos.get(position).getMonedaPago();
        switch(moneda) {//1 U$S 2Euro 3 AR$- 4 Libra 5 R$
            case 1 ://U$S
                viewholder.moneda.setImageResource(R.drawable.us);
                break;
            case 2 ://Euro
                viewholder.moneda.setImageResource(R.drawable.eu);
                break;
            case 3 ://ARS
                viewholder.moneda.setImageResource(R.drawable.ar);
                break;
            case 4 ://Libra
                viewholder.moneda.setImageResource(R.drawable.uk);
                break;
            case 5 ://Real
                viewholder.moneda.setImageResource(R.drawable.br);
                break;
            default:
                viewholder.moneda.setImageResource(R.drawable.desconocido);
                break;
        }
        SimpleDateFormat sdf = new SimpleDateFormat("dd-MM-yyyy");

        viewholder.fin.setText(String.format("%1$s %2$s %3$s","Fecha Fin:",sdf.format(trabajos.get(position).getFechaEntrega()),"  "));
        viewholder.ingles.setChecked(trabajos.get(position).getRequiereIngles());

        //Implementamos el método OnLongClickListener que capturará la opción laboral seleccionada
        item.setOnLongClickListener(new View.OnLongClickListener(){
            @Override
            public boolean onLongClick(View v) {

                Toast.makeText(contexto, "Long Clicked", Toast.LENGTH_SHORT).show();
                return true;
            }
                //Se notifica al adaptador de que el ArrayList que tiene asociado ha sufrido cambios (forzando asi a ir al metodo getView())
     /*           adaptador.notifyDataSetChanged();*/
        });

        //Se devuelve ya la vista nueva o reutilizada que ha sido dibujada
        return (item);
    }

}
