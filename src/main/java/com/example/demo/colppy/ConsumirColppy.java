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


    public String claveSesion="";

    final private String uri="https://login.colppy.com/lib/frontera2/service.php";

    private HttpHeaders headers = new HttpHeaders(); 
    private RestTemplate restTemplate = new RestTemplate();

    /****************************************************
     * CONSTRUCTORES
     ****************************************************/

     public ConsumirColppy(){
         headers.add("Content-Type", "application/json");
     }


    /****************************************************
     * METODOS PUBLICOS
     ****************************************************/

     public String ObtenerEmpresas(){

        if(claveSesion.length()==0) {
            Login();
        }
         String body = "{"
                        +"\"auth\": {"
                        +"\"usuario\": \"damianm@dmat.com.ar\","
                        +"\"password\": \"046eab8ab4b17fa00338bcf65876ce3e\""
                        +"},"
                        +"\"service\": {"
                        +"\"provision\": \"Empresa\","
                        +"\"operacion\": \"listar_empresa\""
                        +"},"
                        +"\"parameters\":  {"
                        +"\"usuario\": null,"
                        +"\"password\": null,"
                        +"\"sesion\":"
                        +"{"
                        +"\"usuario\": \"damianm@dmat.com.ar\","
                        +"\"claveSesion\": \""+ claveSesion +"\""
                        +"},"
                        +"\"start\": 0,"
                        +"\"limit\": 10"
                        +"},"
                        +"\"filter\": []"
                        +"}";

         RespuestaColppyBean respuesta = EjecutarLlamada(body);

        return respuesta.toString();
     }


    /**
     * Metodo para realizar el login en colppy y obtener la claveSesion     * 
     * @return devuelve todo el resultado si es correcto el Login
     */
    public  String Login() {
        
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
       
        RespuestaColppyBean respuesta = EjecutarLlamada(body);
        
        ObtejerClaveSesionDeRespuestaColppy(respuesta);

        return claveSesion;
       
    }


 /****************************************************
  * METODOS PRIVADOS
  ****************************************************/

  /**
   * Busca la clave de session de la respuesta del login
   * @param respuesta
   */
    private void ObtejerClaveSesionDeRespuestaColppy(RespuestaColppyBean respuesta){
        
        JSONObject responseColppy = new JSONObject(respuesta.getResponse());
        JSONObject responseDataColppy = new JSONObject(responseColppy.getString("data"));
        claveSesion = responseDataColppy.getString("claveSesion");
        
    }


    private RespuestaColppyBean EjecutarLlamada(String body){
        HttpEntity<?> httpEntity = new HttpEntity<Object>(body, this.headers);

        ResponseEntity<?> result = this.restTemplate.exchange(this.uri, HttpMethod.POST, httpEntity, String.class);

        System.out.println(result.toString());

        JSONObject jsonBody = new JSONObject(result.getBody().toString());

        RespuestaColppyBean respuesta = new RespuestaColppyBean();
        respuesta.setRespuesta(jsonBody);

        return respuesta;
    }
}
