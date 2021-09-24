import java.io.*;
import java.util.ArrayList;

public class Main {
    public static void main(String[] args) {

        // Armazena as linhas do arquivo lido Ex: -20.3119345 -40.285505
        ArrayList<String> lineList = new ArrayList<>();

        BufferedReader reader;

        File path = new File("S:\\TI\\Areas");
        File[] files = path.listFiles(File::isFile);

        // Faz a leitura da pasta
        for (int i = 0; i < files.length; i++) {

            // Armazena o nome do arquivo de saida
            String nameOut = files[i].getName();

            // Armazena o caminho do arquivo de entrada
            String fileInp = files[i].getPath();

            // Armazena a linha atual
            String sCurrentLine;

            try {

                reader = new BufferedReader(new FileReader(fileInp));
                while ((sCurrentLine = reader.readLine()) != null) {

                    // Remove os caracteres indesejável
                    String newLine = prepareString(sCurrentLine);

                    // Armazena apenas as linhas não vazias
                    if (!newLine.isEmpty()) lineList.add(newLine);
                }

                // Escreve no arquivo de saida
                writeFile(lineList, nameOut);
                lineList = new ArrayList<>();

            } catch (IOException e) {
                e.printStackTrace();
            }
        }

    }

    // Escreve no arquivo de saida
    public static void writeFile(ArrayList<String> lineList, String fileInp) {
        try (BufferedWriter bw = new BufferedWriter(new FileWriter("S:\\TI\\Areas\\Atualizados\\" + fileInp))) {
            for (int j = 0; j < lineList.size(); j++) {

                String[] lines = lineList.get(j).split(" ");

                // Remove a linha do último
                if (j != lineList.size() - 1) {
                    bw.write("-" + lines[1] + " " + "-" + lines[0] + ",");
                } else {
                    bw.write("-" + lines[1] + " " + "-" + lines[0]);
                }
                bw.newLine();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    // Remove os caracteres indesejável
    public static String prepareString(String sCurrentLine) {
        return sCurrentLine
                .replaceAll("}", "")
                .replaceAll("[{]", "")
                .replaceAll("lat:", "")
                .replaceAll("lng:", "")
                .replaceAll(",", "")
                .replaceAll("  ", "")
                .replaceAll("-", " ")
                .trim();
    }

}
