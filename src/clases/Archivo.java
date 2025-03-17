
package clases;


import java.io.*;
import java.util.ArrayList;
import java.util.List;
import javax.swing.JOptionPane;
import gui.frmInfo;
import java.text.DecimalFormat;

public class Archivo {
    private String codigo;
    private String nombre;
    private String tipoB;
    private String Cantidad;
    private String PrecioU;
    private String ValorCom;
   
    
    //variable boll
    private boolean encontro=false;
    //variable para manipular el archivo
    private FileWriter escritor=null;
    private PrintWriter pw=null;
    private File archivo=null;
    private FileReader lector=null;
    private BufferedReader br=null;
    
    //vector para separar los campos del archivo ´plano
    String [] cadena;

    
    public static ArrayList<Archivo> com=new ArrayList<>();
    
    public static ArrayList<Archivo> getVenta() {
        return com;
    }

    
    
    public Archivo() {
    }

    public String getCodigo() {
        return codigo;
    }

    public void setCodigo(String codigo) {
        this.codigo = codigo;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getTipoB() {
        return tipoB;
    }

    public void setTipoB(String tipoB) {
        this.tipoB = tipoB;
    }

    public String getCantidad() {
        return Cantidad;
    }

    public void setCantidad(String Cantidad) {
        this.Cantidad = Cantidad;
    }

    public String getPrecioU() {
        return PrecioU;
    }

    public void setPrecioU(String PrecioU) {
        this.PrecioU = PrecioU;
    }

    public String getValorCom() {
        return ValorCom;
    }

    public void setValorCom(String ValorCom) {
        this.ValorCom = ValorCom;
    }

    public boolean getEncontro(){
       return  this.encontro;
    }

    
    
    
    
    public void escribir(String cod,String nom,String tip,String cant,String precio,String valor){
        try{
            escritor=new FileWriter("./src/recursos/ventas.txt",true);
            pw=new PrintWriter(escritor);
            pw.println(cod + "," + nom +"," + tip + "," + cant + "," + precio + "," + valor);
            JOptionPane.showMessageDialog(null, "Se ha registrado la venta");
            
            escritor.close();
            
        }catch(IOException ex){
            JOptionPane.showMessageDialog(null,"ERROR: "+ ex.getMessage());
        
        }
    
    }//cierre del metodo
    
     public void buscarEve(String cod){
    
         try{
            archivo=new File("./src/recursos/eventos.txt");
            lector=new FileReader(archivo);
            br=new BufferedReader(lector);
            String linea;
            encontro=false;
            while((linea=br.readLine())!=null){
                //separacion de los datos para llevarlos al vector
                cadena=linea.split(",");
                if(cadena[0].equalsIgnoreCase(cod)){
                    encontro=true;
                    break;
                }else{
                    encontro=false;
                }
            
            
            }//cierre while
            if(encontro){
                this.codigo=cadena[0];
                this.nombre=cadena[1];
                this.tipoB=cadena[2];
                
            
            }else{
                this.codigo="";
                this.nombre="";
                this.tipoB="";
                JOptionPane.showMessageDialog(null, "El evento no existe");
            }
            
            lector.close();
            
        }catch(IOException ex){
            JOptionPane.showMessageDialog(null,"ERROR: "+ ex.getMessage());
        
        }
    
    
    }//fin del metodo
     
     
      public void buscarVenta(String cod){
    
         try{
            archivo=new File("./src/recursos/ventas.txt");
            lector=new FileReader(archivo);
            br=new BufferedReader(lector);
            String linea;
            encontro=false;
            while((linea=br.readLine())!=null){
                //separacion de los datos para llevarlos al vector
                cadena=linea.split(",");
                if(cadena[0].equalsIgnoreCase(cod)){
                    encontro=true;
                    break;
                }else{
                    encontro=false;
                }
            
            
            }//cierre while
            if(encontro){
                this.codigo=cadena[0];
                this.nombre=cadena[1];
                this.tipoB=cadena[2];
                this.Cantidad=cadena[3];
                this.PrecioU=cadena[4];
                this.ValorCom=cadena[5];
                
            
            }else{
                this.codigo="";
                this.nombre="";
                this.tipoB="";
                this.Cantidad="";
                this.PrecioU="";
                this.ValorCom="";
                JOptionPane.showMessageDialog(null, "La venta no existe");
            }
            
            lector.close();
            
        }catch(IOException ex){
            JOptionPane.showMessageDialog(null,"ERROR: "+ ex.getMessage());
        
        }
    
    
    }//fin del metodo
    
    //metodo listar
    public List<String[]> listadoEve(){
        List<String[]> datos=new ArrayList<>(); 
         try{
            archivo=new File("./src/recursos/eventos.txt");
            lector=new FileReader(archivo);
            br=new BufferedReader(lector);
            String linea;
            encontro=false;
            while((linea=br.readLine())!=null){
                //separacion de los datos para llevarlos al vector
                cadena=linea.split(",");
                datos.add(cadena);
            
            }//cierre while
           
            lector.close();
            
        }catch(IOException ex){
            JOptionPane.showMessageDialog(null,"ERROR: "+ ex.getMessage());
        
        }
         return datos;
    }
    
    public List<String[]> listadoVenta(){
        List<String[]> datos=new ArrayList<>(); 
         try{
            archivo=new File("./src/recursos/ventas.txt");
            lector=new FileReader(archivo);
            br=new BufferedReader(lector);
            String linea;
            encontro=false;
            while((linea=br.readLine())!=null){
                //separacion de los datos para llevarlos al vector
                cadena=linea.split(",");
                datos.add(cadena);
            
            }//cierre while
           
            lector.close();
            
        }catch(IOException ex){
            JOptionPane.showMessageDialog(null,"ERROR: "+ ex.getMessage());
        
        }
         return datos;
    }
     public void calcularMetricas(frmInfo info) {
    int cantidadFamiliar = 0;
    int cantidadMusical = 0;
    double totalVentas = 0.0;
    int totalPersonas = 0;
     DecimalFormat formatoMoneda = new DecimalFormat("$#,###.0");
    try {
        archivo = new File("./src/recursos/ventas.txt");

        // Verificar si el archivo está vacío
        if (archivo.length() == 0) {
            JOptionPane.showMessageDialog(null, "El archivo de ventas está vacío. No hay datos para procesar.");
            return; // Salir del método si el archivo está vacío
        }

        lector = new FileReader(archivo);
        br = new BufferedReader(lector);
        String linea;

        while ((linea = br.readLine()) != null) {
            cadena = linea.split(",");
            String tipoEvento = cadena[2]; 
            int cantidad = Integer.parseInt(cadena[3]); 
            double precio = Double.parseDouble(cadena[4]); 
            double valortotal = Double.parseDouble(cadena[5]);

            totalVentas += valortotal;
            totalPersonas++;

            if (tipoEvento.equalsIgnoreCase("familiar") || tipoEvento.equalsIgnoreCase("familiar nocturno")) {
                cantidadFamiliar++;
            } else if (tipoEvento.equalsIgnoreCase("musical") || tipoEvento.equalsIgnoreCase("musical nocturno")) {
                cantidadMusical++;
            }
        }

        lector.close();

        // Verificar si se procesaron datos
        if (totalPersonas == 0) {
            JOptionPane.showMessageDialog(null, "No se encontraron datos válidos en el archivo.");
            return; 
        }

        double promedioVentas = totalVentas / totalPersonas;

        double porcentajeFamiliar = (double) cantidadFamiliar / totalPersonas * 100;
        double porcentajeMusical = (double) cantidadMusical / totalPersonas * 100;
        info.lblcantfa.setText(String.valueOf(cantidadFamiliar));
        info.lblcantmu.setText(String.valueOf(cantidadMusical));
        info.lblprom.setText(formatoMoneda.format( promedioVentas)); 
        info.lbltotal.setText(formatoMoneda.format(totalVentas)); 
        info.lblporfa.setText(String.format("%.2f%%", porcentajeFamiliar)); 
        info.lblpormu.setText(String.format("%.2f%%", porcentajeMusical)); 

    } catch (IOException ex) {
        JOptionPane.showMessageDialog(null, "ERROR: " + ex.getMessage());
    } catch (NumberFormatException ex) {
        JOptionPane.showMessageDialog(null, "ERROR: Formato de número incorrecto en el archivo.");
    }
}
    
}
