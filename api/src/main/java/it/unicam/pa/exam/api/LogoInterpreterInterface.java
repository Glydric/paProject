package it.unicam.pa.exam.api;

import it.unicam.pa.exam.api.Model.Logo.Angle;

import java.util.List;

public interface LogoInterpreterInterface <E extends Angle<Integer>>  extends Interpreter{

    /**
     * sposta il cursore in avanti verso la sua direzione (se si raggiungono i limiti dell’area il cursore si ferma al bordo)
     *
     * @param dist la distanza da percorrere
     */
    void forward(int dist);

    /**
     * sposta il cursore indietro rispetto la sua direzione (se si raggiungono i limiti dell’area il cursore si ferma al bordo)
     *
     * @param dist la distanza da percorrere
     */
    void backward(int dist);


    /**
     * ruota il cursore in senso antiorario dei gradi descritti dal parametro (gli angoli sono indicati come interi nel range [0, 360]
     *
     * @param angle la gradazione da applicare
     */
    void left(E angle);

    /**
     * ruota il cursore in senso orario dei gradi descritti dal parametro (gli angoli sono indicati come interi nel range [0, 360])
     *
     * @param angle la gradazione da applicare
     */
    void right(E angle);

    /**
     * cancella quanto disegnato
     */
    void clear();

    /**
     * muove il cursore nella posizione di home (height/2,width/2)
     */
    void home();

    /**
     * stacca la penna dal foglio
     */
    void penUp();

    /**
     * attacca la penna al foglio
     */
    void penDown();

    /**
     * imposta il colore della penna al colore
     * rappresentato dal colore RGB rappresentato dai tre byte dati
     *
     * @param r quantità di rosso [0,255]
     * @param g quantità di verde [0,255]
     * @param b quantità di blu [0,255]
     */
    void setPenColor(int r, int g, int b);

    /**
     * imposta il colore del riempimento di
     * un’area chiusa
     *
     * @param r quantità di rosso [0,255]
     * @param g quantità di verde [0,255]
     * @param b quantità di blu [0,255]
     */
    void setFillColor(int r, int g, int b);

    /**
     * imposta il colore di background
     * dell’area di disegno
     *
     * @param r quantità di rosso [0,255]
     * @param g quantità di verde [0,255]
     * @param b quantità di blu [0,255]
     */
    void setScreenColor(int r, int g, int b);

    /**
     * indica la grandezza del tratto della penna,
     *
     * @param size è un intero
     *             di grandezza maggiore o uguale a 1
     */
    void setPenSize(int size);

    /**
     * ripete una sequenza di comandi
     *
     * @param num  indica il numero di esecuzioni
     * @param list una lista di comandi da eseguire in ordine
     */
    void repeat(int num, List<String> list);
}
