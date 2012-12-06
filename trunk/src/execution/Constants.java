/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package execution;

import java.io.File;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;

/**
 *
 * @author rafael
 */
public class Constants {

    public static String EVENT = " Eventos: ";
    public static String VARIABLES = " Variáveis: " + "\n\n";
    public static String NO_CODE = " Pseudocódigo ainda não selecionado.";
    public static String ELEMENT_CHANGE = " Escolha os elementos à serem trocados.";
    public static String NO_SIMULATION = " Nenhuma simulação selecionada. \n"
            + " Escolha dentre as opções dadas no menu à esquerda.";
    public static String NO_VARIABLES = " Nenhuma variável disponível. \n"
            + " Inicie uma simulação para visualizar as variáveis de execução.";
    public static String SIMULATION_FINISHED = "Simulação concluída com sucesso.";
    public static String BUBBLE_SORT = "       BUBBLE SORT     \n\n\n\n"
            + "troca = true;\n"
            + "j = 0;\n\n"
            + "enquanto(troca == true) {\n"
            + "  troca = false;\n"
            + "  j += 1;\n"
            + "    para(i = 0; i < tamanho_vetor; i++) {\n"
            + "      se(vetor[ i ] > vetor [ i + 1 ] {\n"
            + "        troca(vetor[ i ], vetor[ i + 1]);\n"
            + "      }\n"
            + "    }\n"
            + "}";
    public static String INSERTION_SORT = "       INSERTION SORT     \n\n\n"
            + "para(i = 1; i < tamanho_vetor; i++){\n\n"
            + "   j = i;\n\n"
            + "      enquanto(v[ j ] < v[ j - 1 ]) {\n\n"
            + "         troca(v [ j - 1], v [ j ]);\n"
            + "         j--.\n\n"
            + "         se(j == 0) {\n"
            + "           quebrar o laço;\n"
            + "         }\n"
            + "      }\n"
            + "}\n";
    public static String SELECTION_SORT = "            \tSELECTION SORT \n\n"
            + "para ( int i = 0; i < tamanho_vetor; i++ ) {\n\n"
            + "  index_min = i\n\n"
            + "    para ( int j = i + 1; j < tamanho_vetor; j++ ) {\n\n"
            + "      se ( v [ j ] < v [ index_min ] ) {\n"
            + "      index_min = j;\n"
            + "      }\n\n"
            + "    }\n\n"
            + "    se ( index_min != i ) {\n"
            + "      troca(v [ i ], v [ index_min ]);\n"
            + "    }\n"
            + "}";
    public static final String SHELL_SORT = "        SHELL SORT \t\t\n\n"
            + "n = tamanho_vetor;\n"
            + "h = n / 2;\n\n"
            + "enquanto( h > 0) {\n\n"
            + "   para (i = h; i < n; i++) {\n"
            + "     c = vetor[ i ];\n"
            + "     j = i;\n\n"
            + "       enquanto ( j >= h  E vetor [ j - h ] > c ) {\n"
            + "         troca( vetor[ j - h ], vetor [ j ] );\n"
            + "         j = j - h;\n"
            + "       }\n\n"
            + "       vetor[ j ] = c;\n"
            + "   }\n"
            + "   h = h / 2;\n"
            + "}";
    public static String IN_PLACE_QUICK_SORT = "        IN-PLACE QUICKSORT \t\t\n\n"
            + "se ( comeco >= fim ) {\n"
            + "  retorne;\n"
            + "}\n\n"
            + "pivo = v [ fim ];\n"
            + "l = comeco;\n"
            + "r = fim - 1;\n\n"
            + "enquanto( l <= r) {\n"
            + "  enquanto ( l <= r e v [ l ] <= pivo ) {\n"
            + "     l++;\n"
            + "  }\n"
            + "  enquanto ( r >= l e v [ r ] >= pivo ) {\n"
            + "     r--;\n"
            + "  }\n"
            + "  se ( l < r ) {\n"
            + "    troca( v [ l ], v [ r ] );\n"
            + "  }\n"
            + "}\n\n"
            + "  troca( v [ l ], v [ fim ] );\n"
            + "  inPlaceQuickSort(v, comeco, l - 1);\n"
            + "  inPlaceQuickSort(v, l + 1, fim);";
    public static final String COUNTING_SORT = "        COUNTING SORT \t\t\n\n"
            + "para (int i = 0; i < vetor.tamanho; i++) {\n\n"
            + " vetorContagem[ vetorContagem[ i ] - minimo]++;\n\n"
            + "}\n\n"
            + "z = 0;\n\n"
            + "para (int i = minimo; i <= maximo; i++) {\n\n"
            + "  enquanto (vetorContagem[i - minimo] > 0) {\n\n"
            + "    vetor[ z ] = i;\n"
            + "    z++;\n"
            + "    vetorContagem[i - min]--;\n\n"
            + "  }\n\n"
            + "}\n\n";
    
    public static String GUESS_RIGHT = "acerto.wav";
    public static String GUESS_WRONG = "falha.wav";
    public static boolean PLAY_SOUNDS = true;

    public static void playQuestionSound(int op) {
        
        if (PLAY_SOUNDS) {
        
        String source = "";

        switch (op) {
            case 0:
                source = new File(GUESS_RIGHT).toURI().toString();
                break;
            case 1:
                source = new File(GUESS_WRONG).toURI().toString();
                break;
        }

        Media media = null;
        media = new Media(source);
        MediaPlayer mediaPlayer = new MediaPlayer(media);
        mediaPlayer.play();
        
        }
        
    }
}
