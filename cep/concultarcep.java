package Cep;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

public class concultarcep {

    public static void main(String[] args) {

        String cep = "72503612";
        String urlCep = "https://viacep.com.br/ws/" + cep + "/json/";

        try {
            URL url = new URL(urlCep);
            HttpURLConnection conn = (HttpURLConnection) url.openConnection();
            conn.setRequestMethod("GET");

            BufferedReader reader = new BufferedReader(
                    new InputStreamReader(conn.getInputStream(), "UTF-8"));

            StringBuilder resposta = new StringBuilder();
            String linha;
            while ((linha = reader.readLine()) != null) {
                resposta.append(linha);
            }
            reader.close();

            System.out.println(resposta.toString());

        } catch (Exception e) {
            System.out.println("Erro ao consultar o CEP: " + e.getMessage());
        }
    }
}


