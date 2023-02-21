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
    
    public void agregarVino(int identificador, String marca, String Color, String Edad){
        boolean estado = false;
        Iterator <Vino> it = listaVinos.iterator();
        Vino aux; 
        
        while(it.hasNext()){
            aux = (Vino) it.next();//Está linea es para crear una variable auxiliar 
            //Casting: Convertir forzadamente algo que no conozoco en algo que si
                    
            if(aux.getIdentifiacion() == identificador){
                estado = true;
            }
        }
        
        if(!estado){
            vino = new Vino();
            vino.setIdentifiacion(identificador);
            vino.setMarca(marca);
            vino.setColor(Color);
            vino.setEdad(Edad);
            listaVinos.add(vino);
            vista.MostrarMensaje1();
            vista.blanquearCampos();
        }else{
            vista.MostrarMensaje2();
        }
    }
    
    public void consultarVino(int identificador){
        int k = -1;
        for(int i = 0; i <listaVinos.size();i++){
            if(listaVinos.get(i).getIdentifiacion() == identificador){
                k = i;
                this.vista.txtIdentificadorConsultar.setText(String.valueOf(listaVinos.get(k).getIdentifiacion()));
                this.vista.txtMarcaConsultar.setText(String.valueOf(listaVinos.get(k).getMarca()));
                this.vista.txtColorConsultar.setText(String.valueOf(listaVinos.get(k).getColor()));
                this.vista.txtEdadConsultar.setText(String.valueOf(listaVinos.get(k).getEdad()));
                break;
            }
        }           

        if(k==-1){
            vista.MostrarMensaje4();
            }
        }
    
    public void iniciar() {
        this.vista.setTitle("Multiplicar numeros");
        //Se le indica la posicion --> null para que la ventana inicie en la posicion 0 es decir en el centro de la pantalla
        vista.setVisible(true);
        this.vista.setLocationRelativeTo(null);
    }
    
    
    @Override
    public void actionPerformed(ActionEvent e){
        
       if(e.getSource() == this.vista.btnInsertar){
          if((this.vista.txtIdentificador.getText().isEmpty())||
             (this.vista.txtMarca.getText().isEmpty())){
              vista.MostrarMensaje5();
          }else{
              this.agregarVino(Integer.parseInt(this.vista.txtIdentificador.getText()),this.vista.txtMarca.getText(),(String) this.vista.txtColor.getSelectedItem(),(String)this.vista.txtEdad.getSelectedItem());
         }
       }
       
       if(e.getSource()==this.vista.btnConsultar){
           try{
               if(listaVinos.isEmpty()){
                   if(listaVinos.isEmpty())
                    vista.MostrarMensaje3();
               }else{
                    this.consultarVino(Integer.parseInt(this.vista.txtIdentificadorConsultar.getText()));
                }
           }catch(Exception a){
               vista.MostrarMensaje5();
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
