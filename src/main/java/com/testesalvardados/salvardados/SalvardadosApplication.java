package com.testesalvardados.salvardados;

import com.google.gson.Gson;
import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.bind.annotation.PathVariable;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;

@SpringBootApplication
public class SalvardadosApplication {

	public static void main(String[] args) {

		SpringApplication.run(SalvardadosApplication.class, args);

		//Essa é a parte que pega os dados da API e salva no documento data.txt
//		String apiURL = "https://olinda.bcb.gov.br/olinda/servico/Expectativas/versao/v1/odata/ExpectativaMercadoMensais?$top=100&$format=json";
//		HttpClient httpClient = HttpClient.newHttpClient();
//		HttpRequest request = HttpRequest.newBuilder().uri(URI.create(apiURL)).build();
//
//		try {
//			HttpResponse<String> response = httpClient.send(request, HttpResponse.BodyHandlers.ofString());
//			String responseBody = response.body();
//
//			// Salvar os dados em um arquivo
//			try (FileWriter fileWriter = new FileWriter("data.txt")) {
//				fileWriter.write(responseBody);
//			} catch (IOException e) {
//				e.printStackTrace();
//			}
//		} catch (IOException | InterruptedException e) {
//			e.printStackTrace();
//		}
//	}

		// Nessa parte os dados são inseridos no banco de dados
		String filename = "data.txt";

		// Configuração da conexão com o banco de dados
		String jdbcUrl = "jdbc:mysql://localhost:3306/dadosmensais";
		String username = "root";
		String password = "Root1234.";

		try {
			// Leitura do arquivo JSON
			BufferedReader br = new BufferedReader(new FileReader(filename));
			JsonParser jsonParser = new JsonParser();
			JsonObject jsonObject = jsonParser.parse(br).getAsJsonObject();
			JsonArray jsonArray = jsonObject.getAsJsonArray("value");

			// Configuração da conexão com o banco de dados
			Connection connection = DriverManager.getConnection(jdbcUrl, username, password);

			// Inserção dos dados no banco de dados
			Gson gson = new Gson();
			for (int i = 0; i < jsonArray.size(); i++) {
				JsonObject data = jsonArray.get(i).getAsJsonObject();

				String indicador = data.get("Indicador").getAsString();
				String dataValor = data.get("Data").getAsString();
				String dataReferencia = data.get("DataReferencia").getAsString();
				double media = data.get("Media").getAsDouble();
				double mediana = data.get("Mediana").getAsDouble();
				double desvioPadrao = data.get("DesvioPadrao").getAsDouble();
				double minimo = data.get("Minimo").getAsDouble();
				double maximo = data.get("Maximo").getAsDouble();
				int numeroRespondentes = data.get("numeroRespondentes").getAsInt();
				int baseCalculo = data.get("baseCalculo").getAsInt();

				// Inserção dos valores na tabela do banco de dados
				String sql = "INSERT INTO dadomensal (indicador, data, dataReferencia, media, mediana, desvioPadrao, minimo, maximo, numeroRespondentes, baseCalculo) " +
						"VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";

				PreparedStatement preparedStatement = connection.prepareStatement(sql);
				preparedStatement.setString(1, indicador);
				preparedStatement.setString(2, dataValor);
				preparedStatement.setString(3, dataReferencia);
				preparedStatement.setDouble(4, media);
				preparedStatement.setDouble(5, mediana);
				preparedStatement.setDouble(6, desvioPadrao);
				preparedStatement.setDouble(7, minimo);
				preparedStatement.setDouble(8, maximo);
				preparedStatement.setInt(9, numeroRespondentes);
				preparedStatement.setInt(10, baseCalculo);

				preparedStatement.executeUpdate();
			}

			// Fechar a conexão com o banco de dados
			connection.close();
		} catch (IOException | SQLException e) {
			e.printStackTrace();
		}
	}
}
