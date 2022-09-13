import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse.BodyHandlers;
import java.util.List;
import java.util.Map;



public class App {
    public static void main(String[] args) throws Exception {
        //FAZER UMA CONEXÃO HTTP E BUSCAR OS TOP 250 FILMES
        String url = "https://imdb-api.com/en/API/Top250Movies/k_wlqr679w";
        var endereco = URI.create(url);
        var cliente = HttpClient.newHttpClient();
        var request = HttpRequest.newBuilder(endereco).GET().build();
        var response = cliente.send(request, BodyHandlers.ofString());
        String body = response.body();
      

        // PEGAR OS DADOS QUE INTERESSA(TITULO, POSTER, CLASSIFICAÇÃO)
        var parse = new JsonParser();
        List<Map<String, String>> ListaDeFilmes = parse.parse(body);
        System.out.println(ListaDeFilmes.size());

        // EXIBIR E MANIPULAR OS DADOS
        for (Map<String, String> filme : ListaDeFilmes) {
            System.out.println(filme.get("title"));
            System.out.println(filme.get("image"));
            System.out.println(filme.get("imDbRating"));
            System.out.println();
        }
    }
}
