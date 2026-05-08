import javax.swing.*;
import java.awt.*;
import java.util.Arrays;
 

public class Interface extends JFrame {
 
    private static final String[] NOMES_ALGORITMOS = {"FIFO", "LRU", "Clock", "Ótimo"};
 
    private JTextField campoCadeia;
    private JTextField campoFrames;
    private JTextArea areaResultado;
    private GraficoPanel grafico;
    private int[] resultados = new int[4];
 
    public Interface() {
        configurarJanela();
        add(criarPainelEntrada(), BorderLayout.NORTH);
        add(criarPainelResultado(), BorderLayout.CENTER);
        add(criarGrafico(), BorderLayout.SOUTH);
    }
 
    // Configuração da janela
 
    private static final Color COR_FUNDO        = new Color(18, 18, 18);
    private static final Color COR_PAINEL       = new Color(30, 30, 30);
    private static final Color COR_BORDA        = new Color(60, 60, 60);
    private static final Color COR_TEXTO        = new Color(220, 220, 220);
    private static final Color COR_TEXTO_DIM    = new Color(140, 140, 140);
    private static final Color COR_CAMPO        = new Color(40, 40, 40);
    private static final Color COR_BOTAO        = new Color(0, 120, 215);
 
    private void configurarJanela() {
        setTitle("Simulador de Algoritmos de Substituição de Páginas");
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setLayout(new BorderLayout(10, 10));
        setSize(900, 620);
        setLocationRelativeTo(null);
        getContentPane().setBackground(COR_FUNDO);
        getRootPane().setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
    }
 
    // Painel de entrada
 
    private JPanel criarPainelEntrada() {
        JPanel painel = new JPanel(new GridBagLayout());
        painel.setBackground(COR_PAINEL);
        painel.setBorder(BorderFactory.createTitledBorder(
            BorderFactory.createLineBorder(COR_BORDA), "Configuração",
            javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION,
            javax.swing.border.TitledBorder.DEFAULT_POSITION,
            new Font("Arial", Font.BOLD, 12), COR_TEXTO_DIM));
 
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(6, 6, 6, 6);
        gbc.fill = GridBagConstraints.HORIZONTAL;
 
        // Linha 0 — cadeia de páginas
        gbc.gridx = 0; gbc.gridy = 0; gbc.weightx = 0;
        painel.add(labelDark("Cadeia de páginas (separadas por espaço):"), gbc);
 
        gbc.gridx = 1; gbc.weightx = 1;
        campoCadeia = campoDark("7 0 1 2 0 3 0 4 2 3 0 3 2 1 2 0 1 7 0 1");
        painel.add(campoCadeia, gbc);
 
        // Linha 1 — número de frames
        gbc.gridx = 0; gbc.gridy = 1; gbc.weightx = 0;
        painel.add(labelDark("Número de quadros (frames):"), gbc);
 
        gbc.gridx = 1; gbc.weightx = 1;
        campoFrames = campoDark("3");
        painel.add(campoFrames, gbc);
 
        // Linha 2 — botão
        gbc.gridx = 0; gbc.gridy = 2; gbc.gridwidth = 2; gbc.weightx = 0;
        gbc.fill = GridBagConstraints.HORIZONTAL;
        gbc.anchor = GridBagConstraints.CENTER;
        JButton btnSimular = criarBotaoSimular();
        painel.add(btnSimular, gbc);
 
        return painel;
    }
 
    private JLabel labelDark(String texto) {
        JLabel label = new JLabel(texto);
        label.setForeground(COR_TEXTO);
        label.setFont(new Font("Arial", Font.PLAIN, 12));
        return label;
    }
 
    private JTextField campoDark(String valorInicial) {
        JTextField campo = new JTextField(valorInicial);
        campo.setBackground(COR_CAMPO);
        campo.setForeground(COR_TEXTO);
        campo.setCaretColor(COR_TEXTO);
        campo.setBorder(BorderFactory.createCompoundBorder(
            BorderFactory.createLineBorder(COR_BORDA),
            BorderFactory.createEmptyBorder(4, 6, 4, 6)));
        campo.setFont(new Font("Monospaced", Font.PLAIN, 13));
        return campo;
    }
 
    private JButton criarBotaoSimular() {
        JButton btn = new JButton("Simular");
        btn.setBackground(COR_BOTAO);
        btn.setForeground(Color.WHITE);
        btn.setFont(new Font("Arial", Font.BOLD, 13));
        btn.setFocusPainted(false);
        btn.setBorderPainted(false);
        btn.setOpaque(true);
        btn.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
        btn.addActionListener(e -> executarSimulacao());
        return btn;
    }
 
    // Painel de resultado textual
 
    private JScrollPane criarPainelResultado() {
        areaResultado = new JTextArea(8, 40);
        areaResultado.setFont(new Font("Monospaced", Font.PLAIN, 13));
        areaResultado.setEditable(false);
        areaResultado.setBackground(COR_PAINEL);
        areaResultado.setForeground(COR_TEXTO);
        areaResultado.setCaretColor(COR_TEXTO);
        areaResultado.setBorder(BorderFactory.createEmptyBorder(8, 12, 8, 8));
 
        JScrollPane scroll = new JScrollPane(areaResultado);
        scroll.setBackground(COR_PAINEL);
        scroll.getViewport().setBackground(COR_PAINEL);
        scroll.setBorder(BorderFactory.createTitledBorder(
            BorderFactory.createLineBorder(COR_BORDA), "Resultado",
            javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION,
            javax.swing.border.TitledBorder.DEFAULT_POSITION,
            new Font("Arial", Font.BOLD, 12), COR_TEXTO_DIM));
        return scroll;
    }
 
    // Gráfico de barras
 
    private GraficoPanel criarGrafico() {
        grafico = new GraficoPanel();
        grafico.setPreferredSize(new Dimension(900, 210));
        grafico.setBackground(COR_PAINEL);
        grafico.setBorder(BorderFactory.createTitledBorder(
            BorderFactory.createLineBorder(COR_BORDA), "Gráfico Comparativo — Faltas de Página",
            javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION,
            javax.swing.border.TitledBorder.DEFAULT_POSITION,
            new Font("Arial", Font.BOLD, 12), COR_TEXTO_DIM));
        return grafico;
    }
 
    // Ação de simulação — conecta UI com a lógica
 
    private void executarSimulacao() {
        try {
            int[] paginas = lerCadeia();
            int frames = lerFrames();
 
            // Delega o cálculo para a classe de lógica
            resultados[0] = Algoritmos.fifo(paginas, frames);
            resultados[1] = Algoritmos.lru(paginas, frames);
            resultados[2] = Algoritmos.clock(paginas, frames);
            resultados[3] = Algoritmos.otimo(paginas, frames);
 
            exibirResultadoTextual();
            grafico.atualizar(resultados, NOMES_ALGORITMOS);
 
        } catch (NumberFormatException ex) {
            JOptionPane.showMessageDialog(
                this,
                "Entrada inválida.\nUse apenas números inteiros separados por espaço.",
                "Erro de entrada",
                JOptionPane.ERROR_MESSAGE
            );
        }
    }
 
    private int[] lerCadeia() {
        String[] partes = campoCadeia.getText().trim().split("\\s+");
        return Arrays.stream(partes).mapToInt(Integer::parseInt).toArray();
    }
 
    private int lerFrames() {
        int frames = Integer.parseInt(campoFrames.getText().trim());
        if (frames <= 0) throw new NumberFormatException("Frames deve ser > 0");
        return frames;
    }
 
    private void exibirResultadoTextual() {
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < resultados.length; i++) {
            sb.append(String.format("Método %d (%s)%s: %d faltas de página%n",
                i + 1,
                NOMES_ALGORITMOS[i],
                " ".repeat(6 - NOMES_ALGORITMOS[i].length()),
                resultados[i]));
        }
        areaResultado.setText(sb.toString());
    }
 
    // Componente de gráfico de barras
 
    static class GraficoPanel extends JPanel {
 
        private static final Color[] CORES = {
            new Color(70, 130, 180),   // FIFO  — azul
            new Color(60, 179, 113),   // LRU   — verde
            new Color(255, 165, 0),    // Clock — laranja
            new Color(220, 80, 80)     // Ótimo — vermelho
        };
 
        private int[] dados;
        private String[] labels;
 
        /** Atualiza os dados e repinta o componente. */
        void atualizar(int[] dados, String[] labels) {
            this.dados = dados.clone();
            this.labels = labels;
            repaint();
        }
 
        @Override
        protected void paintComponent(Graphics g) {
            super.paintComponent(g);
            if (dados == null) return;
 
            Graphics2D g2 = (Graphics2D) g;
            g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
 
            int largura  = getWidth();
            int altura   = getHeight();
            int margemEsq = 55;
            int margemDir = 20;
            int margemTop = 25;
            int margemBot = 45;
 
            int areaLarg = largura - margemEsq - margemDir;
            int areaAltu = altura - margemTop - margemBot;
            int baseY    = margemTop + areaAltu;
 
            int maxVal = Arrays.stream(dados).max().getAsInt();
 
            // Eixos
            g2.setColor(new Color(80, 80, 80));
            g2.drawLine(margemEsq, margemTop, margemEsq, baseY);
            g2.drawLine(margemEsq, baseY, largura - margemDir, baseY);
 
            // Linhas de grade horizontais
            g2.setFont(new Font("Arial", Font.PLAIN, 10));
            int linhas = Math.min(maxVal, 5);
            if (linhas > 0) {
                for (int i = 1; i <= linhas; i++) {
                    int val = (int) Math.round((double) maxVal * i / linhas);
                    int y = baseY - (int) ((double) val / maxVal * areaAltu);
                    g2.setColor(new Color(55, 55, 55));
                    g2.drawLine(margemEsq + 1, y, largura - margemDir, y);
                    g2.setColor(new Color(140, 140, 140));
                    g2.drawString(String.valueOf(val), margemEsq - 30, y + 4);
                }
            }
 
            // Barras
            int n = dados.length;
            int espaco = areaLarg / (n * 2);
            int largBarra = espaco;
 
            for (int i = 0; i < n; i++) {
                int barraAltura = maxVal == 0 ? 0 : (int) ((double) dados[i] / maxVal * areaAltu);
                int x = margemEsq + i * (espaco * 2) + espaco / 2;
                int y = baseY - barraAltura;
 
                // Barra colorida
                g2.setColor(CORES[i]);
                g2.fillRoundRect(x, y, largBarra, barraAltura, 8, 8);
 
                // Valor acima da barra
                g2.setColor(new Color(220, 220, 220));
                g2.setFont(new Font("Arial", Font.BOLD, 12));
                String valor = String.valueOf(dados[i]);
                int valX = x + largBarra / 2 - g2.getFontMetrics().stringWidth(valor) / 2;
                g2.drawString(valor, valX, y - 5);
 
                // Label abaixo do eixo
                g2.setFont(new Font("Arial", Font.PLAIN, 12));
                g2.setColor(new Color(180, 180, 180));
                String label = labels[i];
                int labX = x + largBarra / 2 - g2.getFontMetrics().stringWidth(label) / 2;
                g2.drawString(label, labX, baseY + 18);
            }
        }
    }
}
