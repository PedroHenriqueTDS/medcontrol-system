package arquivos;

import java.io.*;

public class Persistencia<T> {

    public void salvarEmArquivo(T objeto, String nomeArquivo) {
        try (ObjectOutputStream out = new ObjectOutputStream(new FileOutputStream(nomeArquivo))) {
            out.writeObject(objeto);
            System.out.println("Sistema salvo com sucesso em: " + nomeArquivo);
        } catch (IOException e) {
            System.err.println("Erro ao salvar: " + e.getMessage());
        }
    }

    public T carregarDeArquivo(String nomeArquivo) {
        try (ObjectInputStream in = new ObjectInputStream(new FileInputStream(nomeArquivo))) {
            return (T) in.readObject();
        } catch (FileNotFoundException e) {
            System.out.println("Nenhum dado anterior encontrado. Iniciando sistema novo.");
            return null;
        } catch (IOException | ClassNotFoundException e) {
            System.err.println("Erro ao carregar dados: " + e.getMessage());
            return null;
        }
    }
}
