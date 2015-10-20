/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Estadistica;

import Secuencial.BuscadorSecuencial;
import Secuencial.EstadisticaPalabra;
import Secuencial.Resultado;
import java.awt.BorderLayout;
import java.awt.Color;
import java.io.IOException;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.scene.layout.Border;
import javax.swing.JTabbedPane;
import javax.swing.table.DefaultTableColumnModel;
import javax.swing.table.DefaultTableModel;
import modelo.Archivo;
import modelo.PaginasWeb;
import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartPanel;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.plot.CategoryPlot;
import org.jfree.chart.plot.PlotOrientation;
import org.jfree.data.category.DefaultCategoryDataset;

/**
 *
 * @author manfred
 */
public class VEstadistica extends javax.swing.JFrame {

    Archivo archivo;
    ArrayList<String> urlWebPages;

    ///variables para estadisticas de secuencial
    public ArrayList<Resultado> resultadosSecuencial;
    public ArrayList<EstadisticaPalabra> tiemposPalabrasSecuencial = new ArrayList<>();
    ArrayList<PaginasWeb> sitiosWebSecuencial;
    public BuscadorSecuencial buscador;

    /// variables para estadisticas de concurrente
    /**
     * Creates new form VEstadistica
     */
    public VEstadistica() throws IOException {
        this.archivo = new Archivo();
        this.archivo.direccionArchivo = "//home//manfred//NetBeansProjects//TaskforceProjects//Buscador//trunk//src//Secuencial//urls.txt";
        this.buscador = new BuscadorSecuencial();//BORAR
        this.buscador.searchManager("vida | trabajo |casa"); //BORRAR
        this.resultadosSecuencial = buscador.resultados;//BORAR
        this.urlWebPages = archivo.leer();// inicializar URLS BORRAR
        initArrayPaginasWeb();//inicializar el arreglo de paginas web
        initComponents();
        tiempoSTF.setText(Long.toString(buscador.tiempo));
    }

    /**
     * Agrega un objeto de tipo resultado al objeto Paginas Web correcto
     *
     * @param resultado
     */
    public void addResultado(ArrayList<Resultado> resultado) {
        for (Resultado resultadoAux : resultado) {
            addResultadoAux(resultadoAux);
            //System.out.println("addResultado");
        }
    }

    public void addResultadoAux(Resultado resultado) {
        for (PaginasWeb paginaWeb : this.sitiosWebSecuencial) {
            if (paginaWeb.getUrl().equals(resultado.getUrl())) {
                paginaWeb.addItemListaResultados(resultado);
                paginaWeb.setIncidencias(paginaWeb.getIncidencias() + resultado.getCoincidencias());//sumar las coincidencias a la pagina
            }
        }
    }

    public final void initArrayPaginasWeb() {
        this.sitiosWebSecuencial = new ArrayList<>();
        for (String url : urlWebPages) {
            PaginasWeb nPaginasWeb = new PaginasWeb(url, new ArrayList<Resultado>());
            this.sitiosWebSecuencial.add(nPaginasWeb);
            //System.out.println("initArrayPaginasWeb");
        }
    }

    public void graficar(ArrayList<PaginasWeb> sitiosWeb) {
        addResultado(this.resultadosSecuencial);//meter las coincidencias a cada pagina web
        DefaultCategoryDataset barChartDatos = new DefaultCategoryDataset();// grafico de secuencial

        for (PaginasWeb paginas : sitiosWeb) {
            barChartDatos.setValue(paginas.getIncidencias(), "Sitios", paginas.getListaResultados().get(0).getTitulo());
        }

        //fin datos
        
        //generar los datos de las tablas
        this.tiemposPalabrasSecuencial = buscador.calcularTiempoPalabra("vida | trabajo|casa", resultadosSecuencial);

        DefaultTableModel modeloTablaIncidencias = (DefaultTableModel) tablaIncidenciaSec.getModel();
        Object[] fila = new Object[modeloTablaIncidencias.getColumnCount()];
        int cont = 0;
        for (EstadisticaPalabra palabra : tiemposPalabrasSecuencial) {
            fila[0] = palabra.getPalabra();
            fila[1] = palabra.getTiempo();
            modeloTablaIncidencias.addRow(fila);
            cont++;
            System.out.println(palabra.getPalabra());
        }
        System.out.println(cont);
//         Grafico
//        titulo-titulo arriba
        JFreeChart grafico = ChartFactory.createBarChart3D("Incidencias por sitio", "Sitios", "Numero de Incidencias", barChartDatos, PlotOrientation.HORIZONTAL, false, true, false);
        //

        CategoryPlot barChartCP = grafico.getCategoryPlot();
        barChartCP.setRangeGridlinePaint(Color.cyan);

        ChartPanel barPanel = new ChartPanel(grafico);
        lienzo.removeAll();
        lienzo.add(barPanel, BorderLayout.CENTER);
        lienzo.validate();
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        IncidenciasB = new javax.swing.JButton();
        CPUB = new javax.swing.JButton();
        lienzo = new javax.swing.JPanel();
        pTablaSec = new javax.swing.JScrollPane();
        tablaIncidenciaSec = new javax.swing.JTable();
        pTablaSec1 = new javax.swing.JScrollPane();
        tablaIncidenciaSec1 = new javax.swing.JTable();
        lienzo1 = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        jSeparator1 = new javax.swing.JSeparator();
        jSeparator2 = new javax.swing.JSeparator();
        tiempoCTF = new javax.swing.JTextField();
        tiempoSTF = new javax.swing.JTextField();
        jLabel6 = new javax.swing.JLabel();
        jLabel7 = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        IncidenciasB.setText("Incidencias");
        IncidenciasB.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                IncidenciasBActionPerformed(evt);
            }
        });

        CPUB.setText("CPU");

        lienzo.setBackground(new java.awt.Color(204, 204, 204));
        lienzo.setLayout(new java.awt.BorderLayout());

        tablaIncidenciaSec.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Incidencia", "Tiempo(ms)"
            }
        ));
        pTablaSec.setViewportView(tablaIncidenciaSec);

        tablaIncidenciaSec1.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Incidencia", "Tiempo(ms)"
            }
        ));
        pTablaSec1.setViewportView(tablaIncidenciaSec1);

        lienzo1.setBackground(new java.awt.Color(204, 204, 204));
        lienzo1.setLayout(new java.awt.BorderLayout());

        jLabel1.setText("Secuencial");

        jLabel2.setText("Concurrente");

        jLabel3.setText("Tiempos Total de Ejecución ");
        jLabel3.setVerticalAlignment(javax.swing.SwingConstants.BOTTOM);

        jLabel4.setText("Concurrente:");

        jLabel5.setText("Secuencial:");

        tiempoCTF.setEditable(false);
        tiempoCTF.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                tiempoCTFActionPerformed(evt);
            }
        });

        tiempoSTF.setEditable(false);

        jLabel6.setText("ms");

        jLabel7.setText("ms");

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(15, 15, 15)
                        .addComponent(lienzo, javax.swing.GroupLayout.PREFERRED_SIZE, 645, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(lienzo1, javax.swing.GroupLayout.PREFERRED_SIZE, 618, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(layout.createSequentialGroup()
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jLabel3)
                                    .addGroup(layout.createSequentialGroup()
                                        .addGap(35, 35, 35)
                                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                            .addComponent(IncidenciasB)
                                            .addComponent(CPUB, javax.swing.GroupLayout.PREFERRED_SIZE, 113, javax.swing.GroupLayout.PREFERRED_SIZE)))
                                    .addComponent(jSeparator1, javax.swing.GroupLayout.PREFERRED_SIZE, 195, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(jSeparator2, javax.swing.GroupLayout.PREFERRED_SIZE, 195, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addGroup(layout.createSequentialGroup()
                                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                            .addComponent(jLabel5)
                                            .addComponent(jLabel4))
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                            .addComponent(tiempoSTF)
                                            .addComponent(tiempoCTF, javax.swing.GroupLayout.DEFAULT_SIZE, 66, Short.MAX_VALUE))
                                        .addGap(3, 3, 3)
                                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                            .addComponent(jLabel6, javax.swing.GroupLayout.PREFERRED_SIZE, 52, javax.swing.GroupLayout.PREFERRED_SIZE)
                                            .addComponent(jLabel7))))
                                .addGap(216, 216, 216))
                            .addComponent(jLabel1, javax.swing.GroupLayout.Alignment.TRAILING))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(jLabel2)
                        .addGap(247, 247, 247))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(298, 298, 298)
                        .addComponent(pTablaSec, javax.swing.GroupLayout.PREFERRED_SIZE, 192, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(pTablaSec1, javax.swing.GroupLayout.PREFERRED_SIZE, 192, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(192, 192, 192))))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(0, 0, Short.MAX_VALUE)
                        .addComponent(jLabel1)
                        .addGap(10, 10, 10)
                        .addComponent(IncidenciasB)
                        .addGap(18, 18, 18)
                        .addComponent(CPUB)
                        .addGap(4, 4, 4)
                        .addComponent(jSeparator1, javax.swing.GroupLayout.PREFERRED_SIZE, 10, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(4, 4, 4)
                        .addComponent(jLabel3)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel4)
                            .addComponent(tiempoCTF, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel7))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel5)
                            .addComponent(tiempoSTF, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel6))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(jSeparator2, javax.swing.GroupLayout.PREFERRED_SIZE, 10, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(15, 15, 15))
                    .addGroup(layout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(jLabel2)
                        .addGap(2, 2, 2)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(pTablaSec, javax.swing.GroupLayout.PREFERRED_SIZE, 196, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(pTablaSec1, javax.swing.GroupLayout.PREFERRED_SIZE, 196, javax.swing.GroupLayout.PREFERRED_SIZE))))
                .addGap(20, 20, 20)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(lienzo, javax.swing.GroupLayout.DEFAULT_SIZE, 430, Short.MAX_VALUE)
                    .addComponent(lienzo1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap())
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void IncidenciasBActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_IncidenciasBActionPerformed
        addResultado(this.resultadosSecuencial);//meter las coincidencias a cada pagina web
        DefaultCategoryDataset barChartDatos = new DefaultCategoryDataset();// grafico de secuencial

        for (PaginasWeb paginas : sitiosWebSecuencial) {
            barChartDatos.setValue(paginas.getIncidencias(), "Sitios", paginas.getListaResultados().get(0).getTitulo());
        }

        //fin datos
        this.tiemposPalabrasSecuencial = buscador.calcularTiempoPalabra("vida | trabajo|casa", resultadosSecuencial);

        DefaultTableModel modeloTablaIncidencias = (DefaultTableModel) tablaIncidenciaSec.getModel();
        Object[] fila = new Object[modeloTablaIncidencias.getColumnCount()];
        int cont = 0;
        for (EstadisticaPalabra palabra : tiemposPalabrasSecuencial) {
            fila[0] = palabra.getPalabra();
            fila[1] = palabra.getTiempo();
            modeloTablaIncidencias.addRow(fila);
            cont++;
            System.out.println(palabra.getPalabra());
        }
        System.out.println(cont);
//         Grafico
//        titulo-titulo arriba
        JFreeChart grafico = ChartFactory.createBarChart3D("Incidencias por sitio", "Sitios", "Numero de Incidencias", barChartDatos, PlotOrientation.HORIZONTAL, false, true, false);
        //

        CategoryPlot barChartCP = grafico.getCategoryPlot();
        barChartCP.setRangeGridlinePaint(Color.cyan);

        ChartPanel barPanel = new ChartPanel(grafico);
        lienzo.removeAll();
        lienzo.add(barPanel, BorderLayout.CENTER);
        lienzo.validate();

    }//GEN-LAST:event_IncidenciasBActionPerformed

    private void tiempoCTFActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_tiempoCTFActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_tiempoCTFActionPerformed

    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
         */
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(VEstadistica.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(VEstadistica.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(VEstadistica.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(VEstadistica.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                try {
                    new VEstadistica().setVisible(true);
                } catch (IOException ex) {
                    Logger.getLogger(VEstadistica.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton CPUB;
    private javax.swing.JButton IncidenciasB;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JSeparator jSeparator1;
    private javax.swing.JSeparator jSeparator2;
    private javax.swing.JPanel lienzo;
    private javax.swing.JPanel lienzo1;
    private javax.swing.JScrollPane pTablaSec;
    private javax.swing.JScrollPane pTablaSec1;
    private javax.swing.JTable tablaIncidenciaSec;
    private javax.swing.JTable tablaIncidenciaSec1;
    private javax.swing.JTextField tiempoCTF;
    private javax.swing.JTextField tiempoSTF;
    // End of variables declaration//GEN-END:variables
}
