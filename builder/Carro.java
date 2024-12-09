public class Carro {
    private String motor;
    private int rodas;
    private String cor;

    // Getters e toString
    public String getMotor() { return motor; }
    public int getRodas() { return rodas; }
    public String getCor() { return cor; }

    @Override
    public String toString() {
        return "Carro [motor=" + motor + ", rodas=" + rodas + ", cor=" + cor + "]";
    }

    // Classe Builder
    public static class CarroBuilder {
        private String motor;
        private int rodas;
        private String cor;

        public CarroBuilder setMotor(String motor) {
            this.motor = motor;
            return this;
        }

        public CarroBuilder setRodas(int rodas) {
            this.rodas = rodas;
            return this;
        }

        public CarroBuilder setCor(String cor) {
            this.cor = cor;
            return this;
        }

        public Carro build() {
            Carro carro = new Carro();
            carro.motor = this.motor;
            carro.rodas = this.rodas;
            carro.cor = this.cor;
            return carro;
        }
    }
}
