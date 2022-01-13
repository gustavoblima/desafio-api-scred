package Tests;

import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import org.json.simple.JSONObject;
import org.junit.Assert;
import org.junit.Test;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static io.restassured.RestAssured.given;

public class APITest {
    int StatusCode;

    @Test
    public void verificaRestricaoCPF(){

        List cpf = new ArrayList<>();
        cpf.add("97093236014");
        cpf.add("60094146012");
        cpf.add("84809766080");
        cpf.add("62648716050");
        cpf.add("26276298085");
        cpf.add("01317496094");
        cpf.add("55856777050");
        cpf.add("19626829001");
        cpf.add("24094592008");
        cpf.add("58063164083");

        for(int i = 0; i < cpf.size(); i++){
            Response response = RestAssured.get("http://localhost:8080/api/v1/restricoes/"+cpf.get(i));

            StatusCode = response.getStatusCode();
            System.out.println("Fiz o Check do cpf: " + cpf.get(i));

            Assert.assertEquals(StatusCode, 200);
        }


    }

    @Test
    public void criarSimulacao201(){

        Map<String, Object> map = new HashMap<String, Object>();

        map.put("nome", "Gustavo Blima");
        map.put("cpf", "97093238044");
        map.put("email", "gustavoblima@bsd.com.br");
        map.put("valor", "1200");
        map.put("parcelas", "3");
        map.put("seguro", "true");

        JSONObject request = new JSONObject(map);

        System.out.println(request.toJSONString());

        given().
                header("Content-Type","application/json").
                contentType(ContentType.JSON).
                accept(ContentType.JSON).
                body(request.toJSONString()).
        when().
                post("http://localhost:8080/api/v1/simulacoes/").
        then().
                statusCode(201);
    }

    @Test
    public void criarSimulacao400(){

        Map<String, Object> map = new HashMap<String, Object>();

        map.put("nome", "Gustavo Blima");
        map.put("cpf", "97093238044");
        map.put("email", "gustavoblima@bsd.com.br");
        map.put("valor", "1200");
        map.put("parcelas", "3");
        map.put("seguro", "true");

        JSONObject request = new JSONObject(map);

        System.out.println(request.toJSONString());

        given().
                header("Content-Type","application/json").
                contentType(ContentType.JSON).
                accept(ContentType.JSON).
                body(request.toJSONString()).
        when().
                post("http://localhost:8080/api/v1/simulacoes/").
        then().
                statusCode(400);
    }

    @Test
    public void criarSimulacao409(){

        Map<String, Object> map = new HashMap<String, Object>();

        map.put("nome", "Gustavo Blima");
        map.put("cpf", "97093238044");
        map.put("email", "gustavoblima@bsd.com.br");
        map.put("valor", "1200");
        map.put("parcelas", "3");
        map.put("seguro", "true");

        JSONObject request = new JSONObject(map);

        System.out.println(request.toJSONString());

        given().
                header("Content-Type","application/json").
                contentType(ContentType.JSON).
                accept(ContentType.JSON).
                body(request.toJSONString()).
        when().
                post("http://localhost:8080/api/v1/simulacoes/").
        then().
                statusCode(409);
    }

    @Test
    public void alterarSimulacaoCPF200(){
        String cpf = "97093238044";

        Map<String, Object> map = new HashMap<String, Object>();

        map.put("nome", "Gustavo Blima_90");
        map.put("cpf", "97093238044");
        map.put("email", "gustavoblima@bsd.com.br");
        map.put("valor", "1200");
        map.put("parcelas", "3");
        map.put("seguro", "true");

        JSONObject request = new JSONObject(map);

        System.out.println(request.toJSONString());

        given().
                header("Content-Type","application/json").
                contentType(ContentType.JSON).
                accept(ContentType.JSON).
                body(request.toJSONString()).
        when().
                put("http://localhost:8080/api/v1/simulacoes/"+cpf).
        then().
                statusCode(200);
    }

    @Test
    public void alterarSimulacaoCPF404(){
        String cpf = "97093238048";

        Map<String, Object> map = new HashMap<String, Object>();

        map.put("nome", "Gustavo Blima");
        map.put("cpf", "97093238044");
        map.put("email", "gustavoblima@bsd.com.br");
        map.put("valor", "1200");
        map.put("parcelas", "3");
        map.put("seguro", "true");

        JSONObject request = new JSONObject(map);

        System.out.println(request.toJSONString());

        given().
                header("Content-Type","application/json").
                contentType(ContentType.JSON).
                accept(ContentType.JSON).
                body(request.toJSONString()).
        when().
                put("http://localhost:8080/api/v1/simulacoes/"+cpf).
        then().
                statusCode(404);
    }

    @Test
    public void alterarSimulacaoCPF409(){
        String cpf = "97093238044";

        Map<String, Object> map = new HashMap<String, Object>();

        map.put("nome", "Gustavo Blima");
        map.put("cpf", "97093238044");
        map.put("email", "gustavoblima@bsd.com.br");
        map.put("valor", "1200");
        map.put("parcelas", "3");
        map.put("seguro", "true");

        JSONObject request = new JSONObject(map);

        System.out.println(request.toJSONString());

        given().
                header("Content-Type","application/json").
                contentType(ContentType.JSON).
                accept(ContentType.JSON).
                body(request.toJSONString()).
        when().
                put("http://localhost:8080/api/v1/simulacoes/"+cpf).
        then().
                statusCode(409);
    }

    @Test
    public void consultarSimulacaoCadastrada(){

        Response response = RestAssured.get("http://localhost:8080/api/v1/simulacoes/");
        StatusCode = response.getStatusCode();
        Assert.assertEquals(StatusCode, 204);

    }

    @Test
    public void consultarSimulacaoCadastradaPorCPF(){

        String cpf = "97093236014";

        Response response = RestAssured.get("http://localhost:8080/api/v1/simulacoes/"+cpf);
        StatusCode = response.getStatusCode();
        Assert.assertEquals(StatusCode, 404);

    }

    @Test
    public void deletarSimulacaoCadastradaPorID204(){

        String id = "1";

        Response response = RestAssured.get("http://localhost:8080/api/v1/simulacoes/"+id);
        StatusCode = response.getStatusCode();
        Assert.assertEquals(StatusCode, 204);

    }

    @Test
    public void deletarSimulacaoCadastradaPorID404(){

        String id = "1";

        Response response = RestAssured.get("http://localhost:8080/api/v1/simulacoes/"+id);
        StatusCode = response.getStatusCode();
        Assert.assertEquals(StatusCode, 404);

    }


}
