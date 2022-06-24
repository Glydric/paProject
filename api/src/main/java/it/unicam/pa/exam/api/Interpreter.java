package it.unicam.pa.exam.api;

public interface Interpreter {
    /**
     * execute interpreta un comando e lo esegue sul proprio environment
     * @param command il comando logo da interpretare
     */
    void execute(String command);
}
