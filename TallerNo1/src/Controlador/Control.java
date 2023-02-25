    /*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Controlador;
import Modelo.Vino;
import Vista.Interfaz;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.Iterator;
/**
 *
 * @author Alejandro Penagos
 */
public class Control implements ActionListener  {
    private Vino vino;
    private Interfaz vista;
    private ArrayList <Vino> listaVinos;
        
    public Control(){        
        vista = new Interfaz();
        listaVinos = new ArrayList<Vino>();
        
        this.vista.btnInsertar.addActionListener(this);
        this.vista.btnLimpiar.addActionListener(this);
        this.vista.btnConsultar.addActionListener( this);
        this.vista.btnSalir.addActionListener(this);
        this.iniciar();
    }
    
    public void agregarVino(String identificador, String marca, String Color, String Edad){
        boolean estado = false;
        Iterator <Vino> it = listaVinos.iterator();
        Vino aux; 
        
        while(it.hasNext()){
            aux = (Vino) it.next();//Está linea es para crear una variable auxiliar 
            //Casting: Convertir forzadamente algo que no conozoco en algo que si
                    
            if(aux.getIdentifiacion().equals(identificador)){
                estado = true;
            }
        }
        
        if(estado){
            vista.MostrarMensaje("Vino ya existe");
        }else{ 
            vino = new Vino();
            vino.setIdentifiacion(identificador);
            vino.setMarca(marca);
            vino.setColor(Color);
            vino.setEdad(Edad);
            listaVinos.add(vino);
            vista.MostrarMensaje("Vino Insertado");
            vista.blanquearCampos();
        }
    }
    
    
    public void consultarVino(String identificador){
        int k = -1;
        for(int i = 0; i <listaVinos.size();i++){
            if(listaVinos.get(i).getIdentifiacion().equals(identificador)){
                k = i;
                this.vista.txtIdentificadorConsultar.setText((listaVinos.get(k).getIdentifiacion()));
                this.vista.txtMarcaConsultar.setText(String.valueOf(listaVinos.get(k).getMarca()));
                this.vista.txtColorConsultar.setText(String.valueOf(listaVinos.get(k).getColor()));
                this.vista.txtEdadConsultar.setText(String.valueOf(listaVinos.get(k).getEdad()));
                break;
            }
        }           

        if(k==-1){
            vista.MostrarMensaje("El vino no está registrado");
            }
        }
    
    
    /*
    Opción 2 =)
    public void consultarVino(String identificador){
        boolean estado = false;
        Iterator <Vino> it = listaVinos.iterator();
        Vino aux; 
        
        while(it.hasNext()){
            aux = (Vino) it.next();//Está linea es para crear una variable auxiliar 
            //Casting: Convertir forzadamente algo que no conozoco en algo que si
                    
            if(aux.getIdentifiacion().equals(identificador)){
                estado = true;
                this.vista.txtIdentificadorConsultar.setText(aux.getIdentifiacion());
                this.vista.txtMarcaConsultar.setText(String.valueOf(aux.getMarca()));
                this.vista.txtColorConsultar.setText(String.valueOf(aux.getColor()));
                this.vista.txtEdadConsultar.setText(String.valueOf(aux.getEdad()));
            }
        }
        
        if(!estado){
            vista.MostrarMensaje("El vino no está registrado");
            }
        }
      */
    
    public void iniciar() {
        this.vista.setTitle("Vinos");
        //Se le indica la posicion --> null para que la ventana inicie en la posicion 0 es decir en el centro de la pantalla
        vista.setVisible(true);
        this.vista.setLocationRelativeTo(null);
    }
    
    public boolean ValidarId(String m){
        boolean estado = true;
        for(int i = 0; i < m.length();i++){
            if(!Character.isDigit(m.charAt(i))){
                estado = false;
            }
        }
        return estado;    
    }
    
    @Override
    public void actionPerformed(ActionEvent e){
        
       if(e.getSource() == this.vista.btnInsertar){
          if((this.vista.txtIdentificador.getText().isEmpty())||
             (this.vista.txtMarca.getText().isEmpty())||
             (this.vista.txtColor.getSelectedItem()== null)||
             (this.vista.txtEdad.getSelectedItem())== null){
              vista.MostrarMensaje("Debes rellenar todos los espacios");
          }else if(!ValidarId(this.vista.txtIdentificador.getText())){//si es falso
              vista.MostrarMensaje("Solo puedes ingresar ID númericos");
          }else{
              this.agregarVino((this.vista.txtIdentificador.getText()),this.vista.txtMarca.getText(),(String) this.vista.txtColor.getSelectedItem(),(String)this.vista.txtEdad.getSelectedItem());
         }
       }
       
       if(e.getSource()==this.vista.btnConsultar){
            if(listaVinos.isEmpty()){
                vista.MostrarMensaje("No se ha ingresado ningún vino");
            }else if(this.vista.txtIdentificadorConsultar.getText().isEmpty()){
                vista.MostrarMensaje("Debes rellenar todos los espacios");   
            }else{
               this.consultarVino(this.vista.txtIdentificadorConsultar.getText());   
           }
       }
       
       if(e.getSource()==this.vista.btnLimpiar){
           this.vista.blanquearCampos();
       }
       
       if(e.getSource()==this.vista.btnSalir){
           this.vista.setVisible(false);
           this.vista.dispose();
       }
    }
    
}
