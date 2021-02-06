package utils;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Properties;

public class ConfiguracaoPropeties {

	Properties config = new Properties();

	public Properties lerArquivoConfiguracao() {
		String cwd = System.getProperty("user.dir");
		FileInputStream stream;

		if (Files.exists(Paths.get(cwd + "/config/config.properties"))) {

			System.out.println("config.properties encontrado localmente em " + cwd + "/config/");

			try {
				stream = new FileInputStream(cwd + "/config/config.properties");
				config.load(stream);
			} catch (FileNotFoundException e) {
				e.printStackTrace();				
				throw new RuntimeException("Arquivo config-db.properties nao encontrado.");
			} catch (IOException e) {
				e.printStackTrace();
			}
		} else {
			System.out.println("Erro ao procurar o arquivo de configuracao");
		}

		return config;
	}
}
