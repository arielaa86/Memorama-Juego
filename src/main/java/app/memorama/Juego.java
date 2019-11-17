/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package app.memorama;

import java.awt.Color;
import java.util.Arrays;
import java.util.Collections;
import java.util.Date;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.border.LineBorder;

/**
 *
 * @author ariel
 */
public class Juego {

    private Tarjeta[][] tablero;
    private int cantClicks;
    private JButton ultimoBotonPresionado;

    private int valor1 = -1;
    private int valor2 = -2;

    private Date inicio;
    private Date fin;

    private boolean iniciado;
    private int piezasRestantes;
    private JFrame padre;

    public Juego(int x, JFrame padre) {
        this.tablero = new Tarjeta[x][x];
        inicio = null;
        fin = null;
        ultimoBotonPresionado = null;
        cantClicks = 0;
        iniciado = false;
        piezasRestantes = tablero.length * tablero.length / 2;
        this.padre = padre;
    }

    public void prepararTablero() {

        int longitudTablero = tablero.length;

        Integer[] valores = new Integer[longitudTablero * tablero.length / 2];
        Integer[] valores2 = new Integer[longitudTablero * tablero.length / 2];

        for (int i = 0; i < valores.length; i++) {
            valores[i] = i;
            valores2[i] = i;
        }

        List<Integer> desordenados1 = Arrays.asList(valores);
        Collections.shuffle(desordenados1);

        List<Integer> desordenados2 = Arrays.asList(valores2);
        Collections.shuffle(desordenados2);

        int pos = 0;

        for (int i = 0; i < tablero.length; i++) {

            for (int k = 0; k < tablero.length; k += 2) {
                tablero[i][k] = new Tarjeta(this, desordenados1.get(pos));
                tablero[i][k + 1] = new Tarjeta(this, desordenados2.get(pos));
                pos++;
            }
        }

    }

    public JButton getUltimoBotonPresionado() {
        return ultimoBotonPresionado;
    }

    public void setUltimoBotonPresionado(JButton ultimoBotonPresionado) {
        this.ultimoBotonPresionado = ultimoBotonPresionado;
    }

    public boolean coincidenParejas() {
        if (valor1 == valor2) {
            return true;
        }

        return false;

    }

    public void desahabilitarBotones() {

        for (int i = 0; i < tablero.length; i++) {
            for (int k = 0; k < tablero.length; k++) {

                if (tablero[i][k].getValor() == valor1) {

                    LineBorder lineBorder = new LineBorder(Color.LIGHT_GRAY, 1, true);
                    tablero[i][k].setBorder(lineBorder);
                    tablero[i][k].setEnabled(false);
              
                }
            }
        }

        piezasRestantes--;

        valor1 = -1;
        valor2 = -2;

    }

    public boolean estaTerminado() {
        return piezasRestantes == 0;
    }

    public Tarjeta[][] getTablero() {
        return tablero;
    }

    public void ocultarTodo() {

        try {
            Thread.sleep(400);

            for (int i = 0; i < tablero.length; i++) {
                for (int k = 0; k < tablero.length; k++) {

                    if (tablero[i][k].isEnabled()) {
                        tablero[i][k].ocultarValor();
                    }
                }
            }

        } catch (InterruptedException ex) {
            Logger.getLogger(Tarjeta.class.getName()).log(Level.SEVERE, null, ex);
        }

    }

    public int getCantClicks() {
        return cantClicks;
    }

    public void setCantClicks(int cantClicks) {
        this.cantClicks = cantClicks;
    }

    public int getValor1() {
        return valor1;
    }

    public int getValor2() {
        return valor2;
    }

    public void setValor1(int valor1) {
        this.valor1 = valor1;
    }

    public void setValor2(int valor2) {
        this.valor2 = valor2;
    }

    public Date getInicio() {
        return inicio;
    }

    public void setInicio(Date inicio) {
        this.inicio = inicio;
    }

    public Date getFin() {
        return fin;
    }

    public void setFin(Date fin) {
        this.fin = fin;
    }

    public boolean estaIniciado() {
        return iniciado;
    }

    public void setIniciado(boolean iniciado) {
        this.iniciado = iniciado;
    }

    public JFrame getPadre() {
        return padre;
    }
    
    

}
