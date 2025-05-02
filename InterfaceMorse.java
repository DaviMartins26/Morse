import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class InterfaceMorse {

    public static void main(String[] args) {
        Morse morse = new Morse();
        morse.iniciar();
        morse.inserirElementos();

        JFrame frame = new JFrame("Conversor Morse");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(400, 250);
        frame.setLayout(new BorderLayout());

        JTextField inputField = new JTextField("Digite aqui o texto ou código Morse");
        inputField.setForeground(Color.GRAY);
        frame.add(inputField, BorderLayout.NORTH);

        inputField.addFocusListener(new FocusAdapter() {
            public void focusGained(FocusEvent e) {
                if (inputField.getText().equals("Digite aqui o texto ou código Morse")) {
                    inputField.setText("");
                    inputField.setForeground(Color.BLACK);
                }
            }

            public void focusLost(FocusEvent e) {
                if (inputField.getText().isEmpty()) {
                    inputField.setText("Digite aqui o texto ou código Morse");
                    inputField.setForeground(Color.GRAY);
                }
            }
        });

        //Saída
        JTextArea outputArea = new JTextArea();
        outputArea.setEditable(false);
        frame.add(new JScrollPane(outputArea), BorderLayout.CENTER);

        //botões
        JPanel buttonPanel = new JPanel();
        JButton btnParaMorse = new JButton("Texto para Morse");
        JButton btnParaTexto = new JButton("Morse para Texto");
        buttonPanel.add(btnParaMorse);
        buttonPanel.add(btnParaTexto);
        frame.add(buttonPanel, BorderLayout.SOUTH);

        //Texto para Morse
        btnParaMorse.addActionListener(e -> {
            String texto = inputField.getText().trim();
            String resultado = morse.fraseParaMorse(texto);
            outputArea.setText(resultado);
        });
        


        // Morse para Texto
        btnParaTexto.addActionListener(e -> {
            String morseTexto = inputField.getText().trim();
            String resultado = morse.buscaFrase(morseTexto);
            outputArea.setText(resultado);
        });

        frame.setVisible(true);
    }
}
