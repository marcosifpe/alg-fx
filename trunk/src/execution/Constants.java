/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package execution;

/**
 *
 * @author rafael
 */
public class Constants {
    
    public static String EVENT = " Eventos: ";
    public static String ELEMENT_CHANGE = " Escolha os elementos à serem trocados.";
    public static String NO_SIMULATION = " Nenhuma simulação selecionada. \n"
            + " Escolha dentre as opções dadas no menu à esquerda.";
    public static String NO_VARIABLES = " Nenhuma variável disponível. \n"
            + " Inicie uma simulação para visualizar as variáveis de execução.";
    public static String SIMULATION_FINISHED = "Simulação concluída com sucesso.";
    public static String BUBBLE_SORT = "       BUBBLE SORT     \n\n\n\n"
            + "para ( i = tamanho_vetor; i >= 1; i-- ) { \n\n"
            + "   para ( j = 1; j < i; j++ ) {\n\n"
            + "      se ( v[ j - 1 ].valor( ) > v [ j ].valor( ) ) {\n"
            + "      auxiliar = v[ j ];\n"
            + "      v[ j ] = v[ j - 1 ];\n"
            + "      v[ j - 1 ] = auxiliar;\n\n"
            + "      }\n\n"
            + "   }\n\n"
            + "}";
    public static String INSERTION_SORT = "       INSERTION SORT     \n\n\n"
            + "para(i = 1; i < tamanho_vetor; i++){\n\n"
            + "   j = i;\n\n"
            + "      enquanto(v[ j ] < v[ j - 1 ]) {\n\n"
            + "         auxiliar = v[ j ];\n"
            + "         v[ j ] = v[ j - 1 ];\n"
            + "         v[ j - 1 ] = auxiliar;\n"
            + "         j--.\n\n"
            + "         se(j == 0) quebrar o laço;\n\n"
            + "      }\n"
            + "}\n";
    public static String SELECTION_SORT = "            \tSELECTION SORT \n\n\n"
            + "para ( int i = 0; i < tamanho_vetor; i++ ) {\n\n"
            + "  index_min = i\n\n"
            + "    para ( int j = i + 1; j < tamanho_vetor; j++ ) {\n\n"
            + "      se ( v [ j ] < v [ index_min ] ) {\n"
            + "      index_min = j;\n"
            + "      }\n\n"
            + "    }\n\n"
            + "    se ( index_min != i ) {\n\n"
            + "      aux = v [ index_min ];\n"
            + "      v [ index_min ] = v [ i ];\n"
            + "      v [ i ] = aux;\n\n"
            + "    }\n\n"
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
            + "    auxiliar = v [ l ];\n"
            + "    v [ l ] = v [ r ];\n"
            + "    v [ r ] = auxiliar;\n"
            + "  }\n"
            + "}\n\n"
            + "  auxiliar = v [ l ];\n"
            + "  v [ l ] = v [ fim ];\n"
            + "  v [ fim ] = auxiliar;\n"
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
    public static final String SHELL_SORT = "        SHELL SORT \t\t\n\n"
            + "h = 1;\n\n"
            + "faça { \n\n"
            + "  h = h * 3 + 1;\n\n"
            + "} enquanto( h < vetor_tamanho) ;\n\n"
            + "faça {\n\n"
            + "  h = h/3;\n\n"
            + "    para i de h até vetor_tamanho {\n\n"
            + "      x = v[ i ];\n"
            + "      j = i;\n\n"
            + "        enquanto ( v[ j - h] > x) {\n\n"
            + "          v[ j ] = v [ j - h ];\n"
            + "          j = j - h;\n"
            + "          se ( j < h ) pare;\n\n"
            + "        };\n\n"
            + "      v [ j ] = x;\n\n"
            + "} enquanto ( h != 1);";
    
}
