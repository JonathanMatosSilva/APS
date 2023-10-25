package model.ordenacao;

import java.util.Arrays;

public class Ordenacao {


	public static void ordenacaoBolha(int[] array) {
        int n = array.length;
        boolean trocado;
        do {
            trocado = false;
            for (int i = 1; i < n; i++) {
                if (array[i - 1] > array[i]) {
                    // Troca os elementos
                    int temp = array[i - 1];
                    array[i - 1] = array[i];
                    array[i] = temp;
                    trocado = true;
                }
            }
        } while (trocado);
    }

	public static void ordenacaoRapida(int[] array, int baixo, int alto) {
        if (baixo < alto) {
            int indicePivo = particao(array, baixo, alto);
            ordenacaoRapida(array, baixo, indicePivo - 1);
            ordenacaoRapida(array, indicePivo + 1, alto);
        }
    }

    private static int particao(int[] array, int baixo, int alto) {
        int pivo = array[alto];
        int i = baixo - 1;
        for (int j = baixo; j < alto; j++) {
            if (array[j] < pivo) {
                i++;
                int temp = array[i];
                array[i] = array[j];
                array[j] = temp;
            }
        }
        int temp = array[i + 1];
        array[i + 1] = array[alto];
        array[alto] = temp;
        return i + 1;
    }

	
    public static void ordenacaoPorFusao(int[] array) {
        if (array.length > 1) {
            int meio = array.length / 2;
            int[] esquerda = Arrays.copyOfRange(array, 0, meio);
            int[] direita = Arrays.copyOfRange(array, meio, array.length);

            ordenacaoPorFusao(esquerda);
            ordenacaoPorFusao(direita);

            fusao(array, esquerda, direita);
        }
    }

    private static void fusao(int[] array, int[] esquerda, int[] direita) {
        int i = 0, j = 0, k = 0;
        while (i < esquerda.length && j < direita.length) {
            if (esquerda[i] < direita[j]) {
                array[k++] = esquerda[i++];
            } else {
                array[k++] = direita[j++];
            }
        }
        while (i < esquerda.length) {
            array[k++] = esquerda[i++];
        }
        while (j < direita.length) {
            array[k++] = direita[j++];
        }
    }

}
