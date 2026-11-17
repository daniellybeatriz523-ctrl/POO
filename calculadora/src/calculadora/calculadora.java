package calculadora;

import java.awt.Color;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;

public class calculadora extends JFrame {

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private final JTextField txtresultado = new JTextField();
	double numero1;
	double numero2;
	String operacao = "";

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					calculadora frame = new calculadora();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Guarda o primeiro número e a operação escolhida.
	 */
	private void definirOperacao(String op) {
		String texto = txtresultado.getText();
		if (texto.isEmpty()) {
			return;
		}
		try {
			numero1 = Double.parseDouble(texto);
			operacao = op;
			txtresultado.setText("");
		} catch (NumberFormatException ex) {
			txtresultado.setText("Erro");
		}
	}

	/**
	 * Mostra o resultado sem ".0" quando for número inteiro.
	 */
	private void mostrarResultado(double resultado) {
		if (resultado == (long) resultado) {
			txtresultado.setText(String.valueOf((long) resultado));
		} else {
			txtresultado.setText(String.valueOf(resultado));
		}
	}

	/**
	 * Create the frame.
	 */
	public calculadora() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setTitle("Danielly Beatriz - 24806");
		setBounds(100, 100, 383, 408);
		contentPane = new JPanel();
		contentPane.setBackground(new Color(123, 102, 207));
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);

		txtresultado.setBounds(22, 29, 195, 56);
		txtresultado.setColumns(10);
		contentPane.add(txtresultado);

		JButton btn1 = new JButton("1");
		btn1.setFont(new Font("Arial", Font.BOLD, 12));
		btn1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				txtresultado.setText(txtresultado.getText() + "1");
			}
		});
		btn1.setBounds(35, 235, 53, 56);
		contentPane.add(btn1);

		JButton btn2 = new JButton("2");
		btn2.setFont(new Font("Arial", Font.BOLD, 12));
		btn2.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				txtresultado.setText(txtresultado.getText() + "2");
			}
		});
		btn2.setBounds(98, 235, 53, 56);
		contentPane.add(btn2);

		JButton btn3 = new JButton("3");
		btn3.setFont(new Font("Arial", Font.BOLD, 12));
		btn3.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				txtresultado.setText(txtresultado.getText() + "3");
			}
		});
		btn3.setBounds(161, 235, 53, 56);
		contentPane.add(btn3);

		JButton btn0 = new JButton("0");
		btn0.setFont(new Font("Arial", Font.BOLD, 12));
		btn0.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				txtresultado.setText(txtresultado.getText() + "0");
			}
		});
		btn0.setBounds(98, 301, 53, 56);
		contentPane.add(btn0);

		JButton btn4 = new JButton("4");
		btn4.setFont(new Font("Arial", Font.BOLD, 12));
		btn4.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				txtresultado.setText(txtresultado.getText() + "4");
			}
		});
		btn4.setBounds(35, 169, 53, 56);
		contentPane.add(btn4);

		JButton btn5 = new JButton("5");
		btn5.setFont(new Font("Arial", Font.BOLD, 12));
		btn5.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				txtresultado.setText(txtresultado.getText() + "5");
			}
		});
		btn5.setBounds(98, 169, 53, 56);
		contentPane.add(btn5);

		JButton btn6 = new JButton("6");
		btn6.setFont(new Font("Arial", Font.BOLD, 12));
		btn6.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				txtresultado.setText(txtresultado.getText() + "6");
			}
		});
		btn6.setBounds(161, 169, 53, 56);
		contentPane.add(btn6);

		JButton btn7 = new JButton("7");
		btn7.setFont(new Font("Arial", Font.BOLD, 12));
		btn7.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				txtresultado.setText(txtresultado.getText() + "7");
			}
		});
		btn7.setBounds(35, 103, 53, 56);
		contentPane.add(btn7);

		JButton btn8 = new JButton("8");
		btn8.setFont(new Font("Arial", Font.BOLD, 12));
		btn8.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				txtresultado.setText(txtresultado.getText() + "8");
			}
		});
		btn8.setBounds(98, 103, 53, 56);
		contentPane.add(btn8);

		JButton btn9 = new JButton("9");
		btn9.setFont(new Font("Arial", Font.BOLD, 12));
		btn9.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				txtresultado.setText(txtresultado.getText() + "9");
			}
		});
		btn9.setBounds(161, 103, 53, 56);
		contentPane.add(btn9);

		// Vírgula: mostra "," no botão, mas insere "." (formato aceito pelo Double.parseDouble)
		JButton btnvirgula = new JButton(",");
		btnvirgula.setFont(new Font("Arial", Font.BOLD, 12));
		btnvirgula.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String texto = txtresultado.getText();
				if (!texto.contains(".")) {
					if (texto.isEmpty()) {
						texto = "0";
					}
					txtresultado.setText(texto + ".");
				}
			}
		});
		btnvirgula.setBounds(161, 301, 53, 56);
		contentPane.add(btnvirgula);

		JButton btnigual = new JButton("=");
		btnigual.setFont(new Font("Arial", Font.BOLD | Font.ITALIC, 12));
		btnigual.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (operacao == null || operacao.isEmpty() || txtresultado.getText().isEmpty()) {
					return;
				}

				try {
					numero2 = Double.parseDouble(txtresultado.getText());
				} catch (NumberFormatException ex) {
					txtresultado.setText("Erro");
					return;
				}

				double resultado = 0;

				switch (operacao) {
				case "+":
					resultado = numero1 + numero2;
					break;
				case "-":
					resultado = numero1 - numero2;
					break;
				case "*":
					resultado = numero1 * numero2;
					break;
				case "/":
					if (numero2 != 0) {
						resultado = numero1 / numero2;
					} else {
						txtresultado.setText("Erro");
						operacao = "";
						return;
					}
					break;
				case "^":
					resultado = Math.pow(numero1, numero2);
					break;
				}

				mostrarResultado(resultado);
				operacao = "";
			}
		});
		btnigual.setBounds(237, 317, 53, 40);
		contentPane.add(btnigual);

		JButton btnmenos = new JButton("-");
		btnmenos.setFont(new Font("Arial", Font.BOLD, 12));
		btnmenos.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				definirOperacao("-");
			}
		});
		btnmenos.setBounds(237, 267, 53, 40);
		contentPane.add(btnmenos);

		JButton btnmais = new JButton("+");
		btnmais.setFont(new Font("Arial", Font.BOLD, 13));
		btnmais.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				definirOperacao("+");
			}
		});
		btnmais.setBounds(237, 217, 53, 40);
		contentPane.add(btnmais);

		JButton btnmultiplicaçao = new JButton("*");
		btnmultiplicaçao.setFont(new Font("Arial", Font.BOLD, 12));
		btnmultiplicaçao.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				definirOperacao("*");
			}
		});
		btnmultiplicaçao.setBounds(237, 167, 53, 40);
		contentPane.add(btnmultiplicaçao);

		JButton btndivisao = new JButton("/");
		btndivisao.setFont(new Font("Arial", Font.BOLD, 12));
		btndivisao.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				definirOperacao("/");
			}
		});
		btndivisao.setBounds(237, 111, 53, 40);
		contentPane.add(btndivisao);

		JButton btnapotencia = new JButton("^");
		btnapotencia.setFont(new Font("Arial", Font.BOLD, 12));
		btnapotencia.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				definirOperacao("^");
			}
		});
		btnapotencia.setBounds(237, 60, 53, 40);
		contentPane.add(btnapotencia);

		JButton btnapagar = new JButton("C");
		btnapagar.setFont(new Font("Arial", Font.BOLD, 13));
		btnapagar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				txtresultado.setText("");
				numero1 = 0;
				numero2 = 0;
				operacao = "";
			}
		});
		btnapagar.setBounds(237, 10, 53, 40);
		contentPane.add(btnapagar);
	}
}