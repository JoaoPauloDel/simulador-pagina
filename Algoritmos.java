
import java.util.*;

public class Algoritmos {


     // FIFO (First In, First Out)

    public static int fifo(int[] paginas, int frames) {
        Queue<Integer> fila = new LinkedList<>();
        Set<Integer> memoria = new HashSet<>();
        int faltas = 0;

        for (int pagina : paginas) {
            if (!memoria.contains(pagina)) {
                faltas++;
                if (memoria.size() == frames) {
                    int removido = fila.poll();
                    memoria.remove(removido);
                }
                memoria.add(pagina);
                fila.add(pagina);
            }
        }
        return faltas;
    }


     // LRU (Least Recently Used)

    public static int lru(int[] paginas, int frames) {
        LinkedHashSet<Integer> memoria = new LinkedHashSet<>();
        int faltas = 0;

        for (int pagina : paginas) {
            if (memoria.contains(pagina)) {
                memoria.remove(pagina);
                memoria.add(pagina);
            } else {
                faltas++;
                if (memoria.size() == frames) {
                    memoria.remove(memoria.iterator().next());
                }
                memoria.add(pagina);
            }
        }
        return faltas;
    }

    
     // Clock (Segunda Chance)
    
    public static int clock(int[] paginas, int frames) {
        int[] quadros = new int[frames];
        int[] bitUso = new int[frames];
        Arrays.fill(quadros, -1);
        int ponteiro = 0;
        int faltas = 0;

        for (int pagina : paginas) {
            boolean encontrada = false;
            for (int i = 0; i < frames; i++) {
                if (quadros[i] == pagina) {
                    bitUso[i] = 1;
                    encontrada = true;
                    break;
                }
            }

            if (!encontrada) {
                faltas++;
                while (bitUso[ponteiro] == 1) {
                    bitUso[ponteiro] = 0;
                    ponteiro = (ponteiro + 1) % frames;
                }
                quadros[ponteiro] = pagina;
                bitUso[ponteiro] = 1;
                ponteiro = (ponteiro + 1) % frames;
            }
        }
        return faltas;
    }

     // Ótimo (Belady's Algorithm)

    public static int otimo(int[] paginas, int frames) {
        List<Integer> memoria = new ArrayList<>();
        int faltas = 0;

        for (int i = 0; i < paginas.length; i++) {
            int pagina = paginas[i];

            if (!memoria.contains(pagina)) {
                faltas++;
                if (memoria.size() == frames) {
                    int indiceParaSubstituir = 0;
                    int maiorDistancia = -1;

                    for (int j = 0; j < memoria.size(); j++) {
                        int pg = memoria.get(j);
                        int proximoUso = Integer.MAX_VALUE;

                        for (int k = i + 1; k < paginas.length; k++) {
                            if (paginas[k] == pg) {
                                proximoUso = k;
                                break;
                            }
                        }

                        if (proximoUso > maiorDistancia) {
                            maiorDistancia = proximoUso;
                            indiceParaSubstituir = j;
                        }
                    }
                    memoria.remove(indiceParaSubstituir);
                }
                memoria.add(pagina);
            }
        }
        return faltas;
    }
}