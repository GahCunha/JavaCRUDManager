import java.io.*;
import java.util.ArrayList;
import java.util.List;
import java.util.function.Predicate;
import java.util.stream.Collectors;

/**
 * Classe genérica para gerenciar operações CRUD (Create, Read, Update, Delete)
 * em uma lista de objetos serializáveis.
 *
 * @param <T> o tipo de objeto que será gerenciado
 */
public class CRUDManager<T extends Serializable> {
    private List<T> items;
    private final String filePath;

    /**
     * Cria uma instância de CRUDManager com o caminho do arquivo especificado.
     *
     * @param filePath o caminho do arquivo onde os itens serão salvos
     */
    public CRUDManager(String filePath) {
        this.filePath = filePath;
        this.items = new ArrayList<>();
        load();
    }

    /**
     * Adiciona um novo item à lista e salva os itens no arquivo.
     *
     * @param item o item a ser criado
     */
    public void create(T item) {
        items.add(item);
        save();
    }

    /**
     * Retorna a lista de itens atualmente armazenados.
     *
     * @return a lista de itens
     */
    public List<T> read() {
        return items;
    }

    /**
     * Atualiza o item na posição especificada da lista e salva os itens no arquivo.
     *
     * @param index a posição do item a ser atualizado
     * @param item  o novo valor do item
     */
    public void update(int index, T item) {
        if (index >= 0 && index < items.size()) {
            items.set(index, item);
            save();
        }
    }

    /**
     * Remove o item na posição especificada da lista e salva os itens no arquivo.
     *
     * @param index a posição do item a ser removido
     */
    public void delete(int index) {
        if (index >= 0 && index < items.size()) {
            items.remove(index);
            save();
        }
    }

    /**
     * Procura por itens na lista com base nos critérios fornecidos.
     *
     * @param criterio O critério de busca.
     * @return Uma lista de itens que correspondem ao critério de busca.
     */
    public List<T> buscarPorCriterio(Predicate<T> criterio) {
        return items.stream().filter(criterio).collect(Collectors.toList());
    }

    /**
     * Procura por um item na lista com base no critério fornecido e retorna o
     * índice do primeiro item encontrado.
     *
     * @param criterio O critério de busca.
     * @return O índice do primeiro item que corresponde ao critério de busca, ou -1
     * se nenhum item for encontrado.
     */
    public int buscarIndexPorCriterio(Predicate<T> criterio) {
        for (int i = 0; i < items.size(); i++) {
            if (criterio.test(items.get(i))) {
                return i;
            }
        }
        return -1;
    }

    /**
     * Salva os itens no arquivo especificado.
     */
    private void save() {
        try (ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(filePath))) {
            oos.writeObject(items);
        } catch (IOException e) {
            System.err.println("Erro ao salvar os itens no arquivo: " + e.getMessage());
        }
    }

    /**
     * Carrega os itens do arquivo especificado.
     */
    @SuppressWarnings("unchecked")
    private void load() {
        try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream(filePath))) {
            Object readObject = ois.readObject();
            if (readObject instanceof List<?>) {
                items = (List<T>) readObject;
            }
        } catch (FileNotFoundException e) {
            System.out.println("Nenhum arquivo existente encontrado. Iniciando uma nova lista.");
        } catch (IOException | ClassNotFoundException e) {
            System.err.println("Erro ao carregar os itens do arquivo: " + e.getMessage());
        }
    }
}