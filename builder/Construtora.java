public class Construtora {
    public Carro construirSport(Carro.CarroBuilder builder) {
        return builder.setMotor("V8")
                      .setRodas(4)
                      .setCor("Vermelho")
                      .build();
    }

    public Carro construirSUV(Carro.CarroBuilder builder) {
        return builder.setMotor("V6")
                      .setRodas(4)
                      .setCor("Preto")
                      .build();
    }
}
