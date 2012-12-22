/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package execution;

import java.io.File;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import model.Question;

/**
 *
 * @author rafael
 */
public class Constants {

    public static String EVENT = " Eventos: ";
    public static String LINE_BREAK = "\n\n ";
    public static String INSERT_POSITION = "Escolha o índice (posição) o qual "
            + "será inserido o elemento.";
    public static String REMOVE_POSITION = "Escolha o índice (posição) do elemento "
            + "à ser removido.";
    public static String UNAVAILABLE_EVENT = "Por enquanto, não há eventos nesta simulação.";
    public static String INVALID_NUMBER = " Número inválido! Tente novamente.";
    public static String VARIABLES = " Variáveis: " + "\n\n";
    public static String NO_CODE = " Pseudocódigo ainda não selecionado.";
    public static String ELEMENT_CHANGE = " Escolha os elementos à serem trocados.";
    public static String TREE_INSERTION = " Escolha o elemento à ser inserido.";
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
    public static final String BINARY_INSERTION = "        ÁRVORE BINÁRIA - INSERÇÂO(elemento, no)\t\n\n"
            + "se (no == null) entao {\n"
            + "  no = novo No(elemento);\n"
            + "} senao se (elemento = no.elemento) entao {\n"
            + "    ERRO: Elemento já existe!\n"
            + "} senao se (elemento < no.elemento) entao {\n"
            + "    no.esquerda = insercao(elemento, no.esquerda);\n"
            + "} senao {\n"
            + "    no.direita = insercao(elemento, no.direita);\n"
            + "}\n"
            + "retorne no";
    public static String STACK_PUSH = "        PILHA \t\t\n\n"
            + "push(int numero) {\n"
            + "  se (tamanho == capacidade) {\n"
            + "    Erro: Não há capacidade para mais elementos!\n"
            + "  } senao {\n"
            + "    pilha[tamanho] = numero;\n"
            + "    tamanho++;\n"
            + "  }\n"
            + "}\n\n";
    public static String STACK_POP = "        PILHA \t\t\n\n"
            + "pop() {\n"
            + "  se(tamanho == 0) {\n"
            + "    Erro: Não há elementos à serem retirados!\n"
            + "  } senao  {\n"
            + "    pilha[tamanho - 1] = null;\n"
            + "    tamanho--;\n"
            + "  }\n"
            + "}\n\n";
    public static String STACK_TOP = "        PILHA \t\t\n\n"
            + "top() {\n"
            + "  se (tamanho == 0) {\n"
            + "    Erro: Não há elementos na pilha!\n"
            + "  } senao   {\n"
            + "    int top = pilha[tamanho - 1];\n"
            + "  }\n"
            + "  retorna top;\n"
            + "}";
    public static String QUEUE_ENQUEUE = "        FILA \t\t\n\n"
            + "Inserir Elemento(int numero) {\n"
            + "  se (tamanho == capacidade) {\n"
            + "    Erro: Não há capacidade para mais elementos!\n"
            + "  } senao {\n"
            + "    fila[tamanho] = numero;\n"
            + "    tamanho++;\n"
            + "  }\n"
            + "}\n\n";
    public static String QUEUE_DEQUEUE = "        FILA \t\t\n\n"
            + "Remover Elemento() {\n"
            + "  se(tamanho == 0) {\n"
            + "    Erro: Não há elementos à serem retirados!\n"
            + "  } senao  {\n"
            + "    fila[0] = null;\n"
            + "    tamanho--;\n"
            + "  }\n"
            + "}\n\n";
    public static String QUEUE_FRONT = "        FILA \t\t\n\n"
            + "Front() {\n"
            + "  se (tamanho == 0) {\n"
            + "    Erro: Não há elementos na fila!\n"
            + "  } senao   {\n"
            + "  retorna fila[0];\n"
            + "  }\n"
            + "}";
    public static String LIST_INSERT = "        LISTA \t\t\n\n"
            + "Inserir Elemento(int indice, int valor) {\n"
            + "  se (indice < 0 || indice > tamanho_lista) {\n"
            + "    Erro: Indice fora da lista!\n"
            + "  }\n"
            + "  se (head == tail) {\n"
            + "    head.setProximo(novo No(valor, head, tail));\n"
            + "    tail.setAnterior(head.proximo);\n"
            + "  } senao {\n"
            + "    No elemento = head;\n"
            + "    para (int i = 0; i < indice; i++) {\n"
            + "      elemento = elemento.getProximo();\n"
            + "    }\n"
            + "    No elemento2 = elemento.getProximo();\n"
            + "    elemento.setProximo(novo No(valor, elemento.getProximo(), elemento);\n"
            + "    elemento2.setAnterior(elemento.getProximo());\n"
            + "  }\n"
            + "  tamanho_lista++;\n"
            + "}";
    public static String LIST_REMOVE = "        LISTA \t\t\n\n"
            + "Remover Elemento(int indice) {\n"
            + "  No elemento = head;\n"
            + "  se (indice > 0 || indice > tamanho_vetor - 1) {\n"
            + "    Erro: Indice fora da lista!\n"
            + "  }\n"
            + "  se (indice == 0) {\n"
            + "    elemento = head.getProximo();\n"
            + "    head.getProximo().getProximo().setAnterior(head);\n"
            + "    head.setProximo(head.getProximo().getProximo());\n"
            + "  } senao {\nk"
            + "    para (int i = 0; i < indice; i++) {\n"
            + "      elemento = elemento.getProximo();\n"
            + "    }\n"
            + "   elemento.getProximo().getProximo().setAnterior(elemento);\n"
            + "   elemento.setProximo(elemento.getProximo().getProximo());\n"
            + "  }\n"
            + "  tamanho_lista--;\n"
            + "}";
    public static String VECTOR_ADD = "        VETOR \t\t\n\n"
            + "Inserir Elemento(int indice, int numero) {\n"
            + "  se (indice < 0 || indice > tamanho_vetor) {\n"
            + "    Erro: O índice não está nos limites do vetor!\n"
            + "  } senao {\n"
            + "    se (tamanho_vetor == capacidade_vetor) {\n"
            + "      vetorTemp = vetor[tamanho_vetor];\n"
            + "        para (int i = 0; i < tamanho_vetor; i++) {\n"
            + "          vetorTemp[i] = vetor[i];\n"
            + "        }\n"
            + "      vetor = novo vetor[vetor_capacidade * 2];\n"
            + "        para (int i = 0; i < vetor_capacidade / 2; i++) {\n"
            + "          vetor[i] = vetorTemp[i];\n"
            + "        }\n"
            + "    }\n"
            + "    para (int i = tamanho_vetor; i > indice; i--) {\n"
            + "      vetor[i] = vetor[i - 1];\n"
            + "    }\n"
            + "    vetor[indice] = numero;\n"
            + "    tamanho_vetor++;\n"
            + "  }";
    public static String VECTOR_REMOVE = "        VETOR \t\t\n\n"
            + "Remover Elemento(int indice) {\n"
            + "  se (indice < 0 || indice >= tamanho_vetor) {\n"
            + "    Erro: O índice não está nos limites do vetor!\n"
            + "  } senao {\n"
            + "    vetor[indice] = null;\n"
            + "    para(int i = indice; i < tamanho_vetor; i++) {\n"
            + "      se (i != tamanho_vetor) {\n"
            + "        vetor[i] = vetor[i + 1];\n"
            + "      }\n"
            + "    }\n"
            + "    tamanho_vetor--;"
            + "  }\n"
            + "}";
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
        
        try {
            Thread.sleep(1000);
        } catch (InterruptedException ex) {
            Logger.getLogger(Question.class.getName()).log(Level.SEVERE, null, ex);
        }
        Main.events.setText(Constants.EVENT + Constants.LINE_BREAK
                        + Constants.UNAVAILABLE_EVENT);
        
    }
}
