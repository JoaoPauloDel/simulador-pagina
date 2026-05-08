import javax.swing.*;
import java.awt.*;
import java.util.*;

public class InterfaceGrafica extends JFrame {

    private JTextField inputSequencia;
    private JTextField inputFrames;
    private JPanel painelGrafico;
    private Map<String, Integer> resultadosTela;

    private final Color BG_COLOR = new Color(43, 43, 43);
    private final Color TEXT_COLOR = new Color(169, 183, 198);
    private final Color ACCENT_COLOR = new Color(73, 156, 84); 

    public InterfaceGrafica() {
        setTitle("Simulador de Substituição de Páginas (OS)");
        setSize(700, 500);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        getContentPane().setBackground(BG_COLOR);
        setLayout(new BorderLayout(10, 10));

        resultadosTela = new LinkedHashMap<>();

        JPanel painelTopo = new JPanel(new GridLayout(3, 2, 5, 5));
        painelTopo.setBackground(BG_COLOR);
        painelTopo.setBorder(BorderFactory.createEmptyBorder(20, 20, 10, 20));

        JLabel lblSeq = new JLabel("Sequência de Páginas (ex: 7 0 1 2 0 3 0 4):");
        lblSeq.setForeground(TEXT_COLOR);
        inputSequencia = new JTextField("7 0 1 2 0 3 0 4 2 3 0 3 2 1 2 0 1 7 0 1");
        inputSequencia.setBackground(new Color(60, 63, 65));
        inputSequencia.setForeground(Color.WHITE);
        inputSequencia.setCaretColor(Color.WHITE);

        JLabel lblFrames = new JLabel("Quantidade de Frames na Memória:");
        lblFrames.setForeground(TEXT_COLOR);
        inputFrames = new JTextField("3");
        inputFrames.setBackground(new Color(60, 63, 65));
        inputFrames.setForeground(Color.WHITE);
        inputFrames.setCaretColor(Color.WHITE);

        JButton btnSimular = new JButton("Simular Algoritmos");
        btnSimular.setBackground(new Color(71, 110, 154));
        btnSimular.setForeground(Color.WHITE);
        btnSimular.setFocusPainted(false);
        
        btnSimular.addActionListener(e -> processarDados());

        painelTopo.add(lblSeq);
        painelTopo.add(inputSequencia);
        painelTopo.add(lblFrames);
        painelTopo.add(inputFrames);
        painelTopo.add(new JLabel("")); 
        painelTopo.add(btnSimular);

        add(painelTopo, BorderLayout.NORTH);

        painelGrafico = new JPanel() {
            @Override
            protected void paintComponent(Graphics g) {
                super.paintComponent(g);
                desenharGraficoNativo(g);
            }
        };
        painelGrafico.setBackground(new Color(30, 30, 30));
        add(painelGrafico, BorderLayout.CENTER);
    }

    private void processarDados() {
        try {
            String[] tokens = inputSequencia.getText().trim().split("\\s+");
            int[] paginas = new int[tokens.length];
            for (int i = 0; i < tokens.length; i++) paginas[i] = Integer.parseInt(tokens[i]);
            
            int frames = Integer.parseInt(inputFrames.getText().trim());

            // AQUI A INTERFACE CHAMA A LÓGICA SEPARADA
            resultadosTela = SimuladorAlgoritmos.executarTodos(paginas, frames);

            painelGrafico.repaint(); 

        } catch (Exception ex) {
            JOptionPane.showMessageDialog(this, "Erro ao ler entradas. Use números separados por espaço.", "Erro", JOptionPane.ERROR_MESSAGE);
        }
    }

    private void desenharGraficoNativo(Graphics g) {
        if (resultadosTela == null || resultadosTela.isEmpty()) return;

        Graphics2D g2d = (Graphics2D) g;
        g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);

        int larguraPainel = painelGrafico.getWidth();
        int alturaPainel = painelGrafico.getHeight();
        int maxFaltas = Collections.max(resultadosTela.values()) + 2; 

        int qtdBarras = resultadosTela.size();
        int larguraBarra = 60;
        int espacamento = (larguraPainel - (qtdBarras * larguraBarra)) / (qtdBarras + 1);

        int x = espacamento;
        for (Map.Entry<String, Integer> entry : resultadosTela.entrySet()) {
            String nome = entry.getKey();
            int faltas = entry.getValue();

            int alturaBarra = (int) (((double) faltas / maxFaltas) * (alturaPainel - 80));
            int y = alturaPainel - alturaBarra - 40;

            g2d.setColor(ACCENT_COLOR);
            g2d.fillRect(x, y, larguraBarra, alturaBarra);

            g2d.setColor(Color.WHITE);
            g2d.drawRect(x, y, larguraBarra, alturaBarra);

            g2d.setColor(TEXT_COLOR);
            g2d.setFont(new Font("Arial", Font.BOLD, 16));
            g2d.drawString(faltas + " faltas", x, y - 10);

            g2d.drawString(nome, x + 5, alturaPainel - 15);

            x += larguraBarra + espacamento;
        }
    }

}