
import javax.swing.SwingUtilities;
 
public class Main {
    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            Interface janela = new Interface();
            janela.setVisible(true);
        });
    }
}