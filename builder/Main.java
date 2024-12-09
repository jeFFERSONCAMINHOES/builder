public class Main {
    public static void main(String[] args) {
        Construtora construtora = new Construtora();
        Carro.CarroBuilder builder = new Carro.CarroBuilder();

        Carro carroEsportivo = construtora.construirSport(builder);
        Carro carroSUV = construtora.construirSUV(builder);

        System.out.println(carroEsportivo);
        System.out.println(carroSUV);
    }
}
