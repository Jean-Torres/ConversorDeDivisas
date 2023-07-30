/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package ManejosDeDatoExternos;

import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.Scanner;
import org.json.*;

/**
 *
 * @author jean torres
 */
public class PeticionDatos {

    private static JSONObject jsonDatos = null;

    public double getValorPrincipal(String simboloMoneda) {
        StringBuilder informacionRecuperada = new StringBuilder();
        try {
            URL url = new URL("https://v6.exchangerate-api.com/v6/fe99ee814bff2bc9b29d394e/latest/" + simboloMoneda);
            HttpURLConnection conexion = (HttpURLConnection) url.openConnection();
            conexion.setRequestMethod("GET");
            conexion.connect();

            int responseCode = conexion.getResponseCode();

            if (responseCode != 200) {
                throw new RuntimeException("Ocurrio un error " + responseCode);
            } else {

                Scanner scanner = new Scanner(url.openStream());
                while (scanner.hasNext()) {
                    informacionRecuperada.append(scanner.nextLine());
                }
                scanner.close();

                jsonDatos = new JSONObject(informacionRecuperada.toString());

                return jsonDatos.getJSONObject("conversion_rates").getDouble(simboloMoneda);
            }

        } catch (IOException e) {
            System.out.println("Error en los datos" + e);
            return 0;
        }
    }

    public double getValor(String simboloMoneda) {
        try {
            // Obtener el valor asociado con el símbolo de la moneda
            return jsonDatos.getJSONObject("conversion_rates").getDouble(simboloMoneda);
        } catch (JSONException e) {
            System.err.println("La moneda especificada no existe en los datos JSON.");
            return 0;
        }
    }
}
