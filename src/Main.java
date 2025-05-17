import br.com.exemplo.models.ApiConnect;
import br.com.exemplo.models.Menu;


import java.io.IOException;

public class Main {
    public static void main(String[] args) throws IOException, InterruptedException {
        ApiConnect busca = new ApiConnect();
        Menu menu = new Menu();

        menu.exibir();


    }
}
