import java.util.*;

public class SimuladorAlgoritmos {

    public static Map<String, Integer> executarTodos(int[] paginas, int qtdFrames) {
        Map<String, Integer> resultados = new LinkedHashMap<>();
        resultados.put("FIFO", simularFIFO(paginas, qtdFrames));
        resultados.put("LRU", simularLRU(paginas, qtdFrames));
        resultados.put("Ótimo", simularOtimo(paginas, qtdFrames));
        resultados.put("Relógio", simularRelogio(paginas, qtdFrames));
        return resultados;
    }

    private static int simularFIFO(int[] paginas, int qtdFrames) {
        Queue<Integer> memoria = new LinkedList<>();
        int faltas = 0;
        for (int pag : paginas) {
            if (!memoria.contains(pag)) {
                faltas++;
                if (memoria.size() == qtdFrames) {
                    memoria.poll(); 
                }
                memoria.add(pag); 
            }
        }
        return faltas;
    }

    private static int simularLRU(int[] paginas, int qtdFrames) {
        List<Integer> memoria = new ArrayList<>();
        int faltas = 0;
        for (int pag : paginas) {
            if (!memoria.contains(pag)) {
                faltas++;
                if (memoria.size() == qtdFrames) {
                    memoria.remove(0); 
                }
            } else {
                memoria.remove((Integer) pag); 
            }
            memoria.add(pag); 
        }
        return faltas;
    }

    private static int simularOtimo(int[] paginas, int qtdFrames) {
        List<Integer> memoria = new ArrayList<>();
        int faltas = 0;
        for (int i = 0; i < paginas.length; i++) {
            int pag = paginas[i];
            if (!memoria.contains(pag)) {
                faltas++;
                if (memoria.size() == qtdFrames) {
                    int indexParaSubstituir = -1;
                    int maisLonge = -1;
                    for (int j = 0; j < memoria.size(); j++) {
                        int indexFuturo = Integer.MAX_VALUE;
                        for (int k = i + 1; k < paginas.length; k++) {
                            if (memoria.get(j) == paginas[k]) {
                                indexFuturo = k;
                                break;
                            }
                        }
                        if (indexFuturo > maisLonge) {
                            maisLonge = indexFuturo;
                            indexParaSubstituir = j;
                        }
                    }
                    memoria.remove(indexParaSubstituir);
                }
                memoria.add(pag);
            }
        }
        return faltas;
    }

    private static int simularRelogio(int[] paginas, int qtdFrames) {
        int[] memoria = new int[qtdFrames];
        boolean[] bitUso = new boolean[qtdFrames];
        Arrays.fill(memoria, -1); 
        
        int faltas = 0;
        int ponteiro = 0;

        for (int pag : paginas) {
            boolean encontrou = false;
            for (int i = 0; i < qtdFrames; i++) {
                if (memoria[i] == pag) {
                    encontrou = true;
                    bitUso[i] = true; 
                    break;
                }
            }
            if (!encontrou) {
                faltas++;
                while (true) {
                    if (!bitUso[ponteiro]) {
                        memoria[ponteiro] = pag; 
                        bitUso[ponteiro] = true;
                        ponteiro = (ponteiro + 1) % qtdFrames;
                        break;
                    } else {
                        bitUso[ponteiro] = false; 
                        ponteiro = (ponteiro + 1) % qtdFrames;
                    }
                }
            }
        }
        return faltas;
    }
}