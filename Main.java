import javax.swing.SwingUtilities;

public class Main {
    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            InterfaceGrafica simulador = new InterfaceGrafica();
            simulador.setVisible(true);
        });
    }
}