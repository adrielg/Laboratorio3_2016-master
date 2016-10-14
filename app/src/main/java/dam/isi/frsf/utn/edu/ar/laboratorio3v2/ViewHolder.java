package dam.isi.frsf.utn.edu.ar.laboratorio3v2;

import android.view.View;
import android.widget.CheckBox;
import android.widget.ImageView;
import android.widget.TextView;

//Esta clase se usa para almacenar los TextView, la ImageView y el CheckBox de una vista y es donde esta el "truco" para que las vistas se guarden
public class ViewHolder {

   TextView categoria=null;
   TextView descripcion=null;
   TextView horas=null;
   TextView precio=null;
   ImageView moneda=null;
   TextView fin=null;
   CheckBox ingles=null;

    ViewHolder(View base) {

        this.categoria= (TextView) base.findViewById(R.id.tv_categoria);
        this.descripcion= (TextView) base.findViewById(R.id.tv_descripcion);
        this.horas= (TextView) base.findViewById(R.id.tv_horas);
        this.precio= (TextView) base.findViewById(R.id.tv_precio);
        this.moneda= (ImageView) base.findViewById(R.id.iv_moneda);
        this.fin= (TextView) base.findViewById(R.id.tv_fin);
        this.ingles = (CheckBox) base.findViewById(R.id.cb_ingles);
    }

    //---------------------------------------Gets y Sets------------------------------------------//
    public TextView getCategoria() {
        return categoria;
    }
    //----------------------------------------//
    public TextView getDescripcion() {
        return descripcion;
    }
    //----------------------------------------//
    public TextView getHoras() { return horas;    }
    //----------------------------------------//
    public TextView getPrecio() {  return precio;   }
    //----------------------------------------//
    public ImageView getMoneda() {
        return moneda;
    }
    //----------------------------------------//
    public TextView getFin() {
        return fin;
    }
    //----------------------------------------//
    public CheckBox getIngles() {
        return ingles;
    }
    //----------------------------------------//*/
}
