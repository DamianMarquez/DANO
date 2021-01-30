package com.example.demo.colppy;

import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import top.jfunc.json.impl.JSONObject;

@Service
public class ConsumirColppy {


    private String claveSesion="";


    /*
    METODOS PUBLICOS
     */


    /**
     * Metodo para realizar el login en colppy y obtener la claveSesion     * 
     * @return devuelve todo el resultado si es correcto el Login
     */
    public  String Login() {
        final String uri = "https://login.colppy.com/lib/frontera2/service.php";

        String body = "{"
            +"\"auth\":{"
            +"\"usuario\":\"damianm@dmat.com.ar\", "
            +"\"password\": \"046eab8ab4b17fa00338bcf65876ce3e\" "
            +"},"
            +"\"service\":{"
            +"\"provision\":\"Usuario\","
            +"\"operacion\":\"iniciar_sesion\""
            +"},"
            +"\"parameters\":{"
            +"\"usuario\":\"damianm@dmat.com.ar\","
            +"\"password\":\"046eab8ab4b17fa00338bcf65876ce3e\" "
            +"}"
            +"}";

        HttpHeaders headers = new HttpHeaders(); headers.add("Content-Type","application/json");

        HttpEntity<?> httpEntity = new HttpEntity<Object>(body, headers);

        RestTemplate restTemplate = new RestTemplate();
        
        ResponseEntity<?> result = restTemplate.exchange(uri, HttpMethod.POST, httpEntity, String.class);

        System.out.println(result.toString());

        JSONObject jsonBody = new JSONObject(result.getBody().toString());
        
        RespuestaColppy respuesta = new RespuestaColppy();
        respuesta.setRespuesta(jsonBody);
        
        obtejerClaveSesionDeRespuestaColppy(respuesta);

        return claveSesion;
       
    }
/*
 * @Override public CotizacionBean cotizar(Integer asegurados, TipoSeguroEnum
 * tipoSeguro) throws Exception {
 * logger.info("METODO: cotizar(".concat(asegurados.toString()).concat(",").
 * concat(tipoSeguro.getCodigo())
 * .concat(",").concat(tipoSeguro.getCnlComExtCod().toString()).concat(",")
 * .concat(tipoSeguro.getCnlComLinEmi().toString()).concat(",")
 * .concat(tipoSeguro.getCnlSubComExtCod().toString()).concat(",")
 * .concat(tipoSeguro.getPrdCod().toString()).concat(")"));
 * 
 * String URL = REST_URL.concat(
 * "/seg_rest/api/ext/cotizador/netCash/obtenerCotizacionVida.do");
 * 
 * UriComponentsBuilder urlBuilder =
 * UriComponentsBuilder.fromHttpUrl(URL).queryParam("access_token",
 * generadorToken.getToken());
 * 
 * HttpHeaders headers = new HttpHeaders(); headers.add("Content-Type",
 * "application/json");
 * 
 * String body = "{ \"cnlComExtCod\": \"" +tipoSeguro.getCnlComExtCod()+"\", " +
 * "\"cnlSubComExtCod\": \"" +tipoSeguro.getCnlSubComExtCod()+"\", " +
 * "\"cnlComLinEmi\": \"" +tipoSeguro.getCnlComLinEmi()+"\"," +
 * "\"prdCod\": \""+ tipoSeguro.getPrdCod() +"\"," 
 * y codigoPostal + "\"provinciaCodigo\": \""+ "21" +"\"," +
 * "\"codigoPostal\": "+ "3363" +"," +
 * "\"cantIntegrantesNomina\":\""+asegurados+"\"," +
 * "\"accCod\":\""+tipoSeguro.getAccCod() +"\"}";
 * logger.info("BODY COTIZACION: " + body);
 * 
 * HttpEntity<?> httpEntity = new HttpEntity<Object>(body, headers);
 * 
 * ResponseEntity<String> response = ejecutarServicio(urlBuilder,
 * HttpMethod.POST, httpEntity, String.class);
 * 
 * String jsonStr = response.getBody();
 * 
 * logger.info("RESPUESTA COTIZACION: " + jsonStr);
 * 
 * JSONObject jsonObj = JSONObject.fromObject(jsonStr); JSONObject estado =
 * JSONObject.fromObject(jsonObj.getString("estado")); String errorCod =
 * estado.getString("codigo");
 * 
 * // SI NO HAY ERROR CARGA LA COTIZACION - SINO GENERA ERROR CON MENSAJE if
 * (errorCod.equals("0")) { CotizacionBean cotizacion = new CotizacionBean();
 * 
 * JSONArray arrayCotizaciones = jsonObj.getJSONArray("cotizaciones"); // A
 * diferencia de integral comercio, viene una cotizacion en el array JSONObject
 * cotizacionJson = arrayCotizaciones.getJSONObject(0);
 * 
 * cotizacion.cargarDatosDesdeUnJSON(cotizacionJson.getJSONObject(
 * "cotizacionSDT"));
 * 
 * cotizacion.setCodError(cotizacionJson.getInt("codError"));
 * cotizacion.setDesError(cotizacionJson.getString("desError")); //
 * cotizacion.setCantidadDeVidas(asegurados);
 * cotizacion.setImporteCuotaPorVida(cotizacion.getImporteCuota() / asegurados);
 * cotizacion.setValorTotalCuota(cotizacion.getImporteCuota());
 * cotizacion.setSumaAseguradaPorTrabajador(cotizacion.getSumaAsegurada());
 * return cotizacion;
 * 
 * } else { return null; } }
 * 
 */



 /****************************************************
  * METODOS PRIVADOS
  ****************************************************/

  /**
   * Busca la clave de session de la respuesta del login
   * @param respuesta
   */
    private void obtejerClaveSesionDeRespuestaColppy(RespuestaColppy respuesta){
        
        JSONObject responseColppy = new JSONObject(respuesta.getResponse());
        JSONObject responseDataColppy = new JSONObject(responseColppy.getString("data"));
        claveSesion = responseDataColppy.getString("claveSesion");
        
    }

}
