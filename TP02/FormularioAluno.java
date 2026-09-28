package LPR1.LPR2.TP02;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

public class FormularioAluno extends JFrame {
    // Utilizando a interface List e a classe ArrayList solicitadas
    private List<Aluno> listaAlunos;

    private JTextField txtNome;
    private JTextField txtIdade;
    private JTextField txtEndereco;

    public FormularioAluno() {
        listaAlunos = new ArrayList<>();

        // Configuração principal da Janela (Tamanho 400x180)
        setTitle("LPR2 - TP02");
        setSize(400, 180);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(new BorderLayout(10, 10)); 

        // Painel Superior com GridLayout 3x2, hgap 10 e vgap 10
        JPanel painelSuperior = new JPanel();
        painelSuperior.setLayout(new GridLayout(3, 2, 10, 10));
        painelSuperior.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));

        painelSuperior.add(new JLabel("Nome:"));
        txtNome = new JTextField();
        painelSuperior.add(txtNome);

        painelSuperior.add(new JLabel("Idade:"));
        txtIdade = new JTextField();
        painelSuperior.add(txtIdade);

        painelSuperior.add(new JLabel("Endereço:"));
        txtEndereco = new JTextField();
        painelSuperior.add(txtEndereco);

        // Painel Inferior com 4 botões e GridLayout
        JPanel painelInferior = new JPanel();
        painelInferior.setLayout(new GridLayout(1, 4, 5, 0));
        painelInferior.setBorder(BorderFactory.createEmptyBorder(0, 10, 10, 10));

        JButton btnOk = new JButton("Ok");
        JButton btnLimpar = new JButton("Limpar");
        JButton btnMostrar = new JButton("Mostrar");
        JButton btnSair = new JButton("Sair");

        painelInferior.add(btnOk);
        painelInferior.add(btnLimpar);
        painelInferior.add(btnMostrar);
        painelInferior.add(btnSair);

        // Adicionando os paineis com o Gerenciador de Layout (BorderLayout)
        add(painelSuperior, BorderLayout.CENTER);
        add(painelInferior, BorderLayout.SOUTH);

        // --- AÇÕES DOS BOTÕES ---

        // Ação do Botão Ok: Armazenar dados em memória
        btnOk.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    Aluno aluno = new Aluno();
                    aluno.setNome(txtNome.getText());
                    aluno.setIdade(Integer.parseInt(txtIdade.getText()));
                    aluno.setEndereco(txtEndereco.getText());
                    aluno.setUuid(UUID.randomUUID()); // Gera o identificador UUID
                    
                    listaAlunos.add(aluno);
                    JOptionPane.showMessageDialog(FormularioAluno.this, "Salvo com sucesso na memória.");
                } catch (NumberFormatException ex) {
                    JOptionPane.showMessageDialog(FormularioAluno.this, "Erro: A idade deve ser um número inteiro.", "Erro de Entrada", JOptionPane.ERROR_MESSAGE);
                }
            }
        });

        // Ação do Botão Limpar: Apagar o conteúdo dos campos textuais
        btnLimpar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                txtNome.setText("");
                txtIdade.setText("");
                txtEndereco.setText("");
            }
        });

        // Ação do Botão Mostrar: Exibir pop-up com ids e nomes dos alunos cadastrados
        btnMostrar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                StringBuilder mensagem = new StringBuilder("Resultado\n");
                
                for (Aluno a : listaAlunos) {
                    mensagem.append("Id: ").append(a.getUuid().toString())
                            .append(" Nome: ").append(a.getNome()).append("\n");
                }
                
                if(listaAlunos.isEmpty()) {
                    mensagem.append("Nenhum aluno cadastrado.");
                }
                
                JOptionPane.showMessageDialog(FormularioAluno.this, mensagem.toString(), "Mensagem", JOptionPane.INFORMATION_MESSAGE);
            }
        });

        // Ação do Botão Sair: Encerrar a aplicação
        btnSair.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                System.exit(0);
            }
        });
    }

    public static void main(String[] args) {
        // Garantindo que a interface seja inicializada na Event Dispatch Thread
        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                new FormularioAluno().setVisible(true);
            }
        });
    }
}