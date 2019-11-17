/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package app.memorama;

import app.memorama.gui.FinPartida;
import java.awt.Color;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.Date;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.border.LineBorder;

/**
 *
 * @author ariel
 */
public class Tarjeta extends JButton {

    private Juego juego;
    private int valor;

    public Tarjeta(Juego juego, int valor) {
        super();
        this.juego = juego;
        this.valor = valor;
        LineBorder lineBorder = new LineBorder(new Color(117, 175, 255, 95), 1, true);
        this.setBorder(lineBorder);
      

    }

    public int getValor() {
        return valor;
    }

    public void setValor(int valor) {
        this.valor = valor;
    }

    public void mostrarValor() {

        this.setIcon(new ImageIcon("img/" + valor + ".png"));
    }

    public void ocultarValor() {
        this.setIcon(null);
    }

    @Override
    public synchronized void addMouseListener(MouseListener l) {
        super.addMouseListener(new MouseListener() {
            @Override
            public void mouseClicked(MouseEvent e) {

                if (!juego.estaIniciado()) {
                    juego.setIniciado(true);
                    juego.setInicio(new Date());
                }

                if (juego.getUltimoBotonPresionado() == null || !juego.getUltimoBotonPresionado().equals((JButton) e.getSource())) {

                    juego.setUltimoBotonPresionado((JButton) e.getSource());
                    juego.setCantClicks(juego.getCantClicks() + 1);
                    mostrarValor();

                    if (juego.getCantClicks() == 1) {
                        juego.setValor1(valor);
                    }

                    if (juego.getCantClicks() == 2) {

                        juego.setValor2(valor);

                        if (juego.coincidenParejas()) {

                            juego.desahabilitarBotones();
                            juego.setValor1(-1);
                            juego.setValor2(-2);

                        } else {

                            juego.setValor1(-1);
                            juego.setValor2(-2);

                            new Thread(new Runnable() {
                                @Override
                                public void run() {
                                    juego.ocultarTodo();

                                }
                            }).start();

                        }

                        juego.setCantClicks(0);

                    }

                    if (juego.estaTerminado()) {

                        juego.setIniciado(true);
                        juego.setFin(new Date());

                        long milliseconds = juego.getFin().getTime() - juego.getInicio().getTime();
                        int segundos = (int) (milliseconds / 1000) % 60;
                        int minutos = (int) ((milliseconds / (1000 * 60)) % 60);

                        String mensaje = "Completado en: " + (minutos > 0 ? minutos + " min" : "") + " " + (segundos > 0 ? segundos + " seg" : "");

                        juego.getPadre().setVisible(false);

                        FinPartida ventana = new FinPartida(mensaje);
                        ventana.setLocationRelativeTo(null);
                        ventana.setVisible(true);

                    }

                }

            }

            @Override
            public void mousePressed(MouseEvent e) {

            }

            @Override
            public void mouseReleased(MouseEvent e) {

            }

            @Override
            public void mouseEntered(MouseEvent e) {

            }

            @Override
            public void mouseExited(MouseEvent e) {

            }
        });

    }

}
