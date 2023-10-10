package view;

import java.awt.Color;
import java.awt.EventQueue;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.List;

import javax.swing.AbstractAction;
import javax.swing.Action;
import javax.swing.ButtonGroup;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JRadioButton;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.table.DefaultTableModel;

import model.bean.Cliente;
import model.dao.ClienteDAO;
import javax.swing.JComboBox;
import javax.swing.DefaultComboBoxModel;

public class gui {

	private JFrame frame;
	private JTextField tfNome;
	private JTextField tfCodigo;
	private JTextField tfCelular;
	private JTextField tfNomeDoTutor;
	private JTable table;
	private final ButtonGroup buttonGroup = new ButtonGroup();
	private final Action action = new SwingAction();
	private JTextField textField;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					gui window = new gui();
					window.frame.setVisible(true);
					window.readJTable();
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 */
	public gui() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frame = new JFrame();
		frame.setBounds(100, 100, 814, 594);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);

		JLabel labelCodigo = new JLabel("ID");
		labelCodigo.setBounds(10, 11, 33, 14);
		frame.getContentPane().add(labelCodigo);

		JLabel lblNomeDoPet = new JLabel("Nome do Pet");
		lblNomeDoPet.setBounds(53, 11, 67, 14);
		frame.getContentPane().add(lblNomeDoPet);

		tfNome = new JTextField();
		tfNome.setToolTipText("");
		tfNome.setBounds(53, 32, 185, 20);
		frame.getContentPane().add(tfNome);
		tfNome.setColumns(10);

		JLabel lblPorte = new JLabel("Porte");
		lblPorte.setBounds(63, 63, 46, 14);
		frame.getContentPane().add(lblPorte);

		tfCodigo = new JTextField();
		tfCodigo.setBackground(Color.LIGHT_GRAY);
		tfCodigo.setEnabled(false);
		tfCodigo.setBounds(10, 32, 33, 20);
		frame.getContentPane().add(tfCodigo);
		tfCodigo.setColumns(10);

		JLabel lblCor = new JLabel("Cor");
		lblCor.setBounds(180, 63, 46, 14);
		frame.getContentPane().add(lblCor);

		tfCelular = new JTextField();
		tfCelular.setBounds(152, 88, 86, 20);
		frame.getContentPane().add(tfCelular);
		tfCelular.setColumns(10);

		JLabel lblNomeDoTutor = new JLabel("Nome do Tutor");
		lblNomeDoTutor.setBounds(247, 11, 82, 14);
		frame.getContentPane().add(lblNomeDoTutor);

		tfNomeDoTutor = new JTextField();
		tfNomeDoTutor.setBounds(247, 32, 185, 20);
		frame.getContentPane().add(tfNomeDoTutor);
		tfNomeDoTutor.setColumns(10);

		JLabel labelSexo = new JLabel("Sexo");
		labelSexo.setBounds(38, 153, 46, 14);
		frame.getContentPane().add(labelSexo);

		JRadioButton rbMacho = new JRadioButton("Macho");
		buttonGroup.add(rbMacho);
		rbMacho.setBounds(192, 149, 67, 23);
		frame.getContentPane().add(rbMacho);

		JRadioButton rbFemea = new JRadioButton("Fêmea");
		buttonGroup.add(rbFemea);
		rbFemea.setBounds(123, 149, 67, 23);
		frame.getContentPane().add(rbFemea);
		
		DefaultTableModel modelo = new DefaultTableModel(new String[] { "ID", "Nome do Pet", "Nome do Tutor", "Porte", "Cor", "Sexo"}, 0);
		table = new JTable(modelo);
		table.setBackground(Color.LIGHT_GRAY);
		table.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				if (table.getSelectedRow() != -1) { // -1 representa que não selecionou a linha da tabela= indice -1
					tfCodigo.setText(table.getValueAt(table.getSelectedRow(), 0).toString());
					tfNome.setText(table.getValueAt(table.getSelectedRow(), 1).toString());
					tfNomeDoTutor.setText(table.getValueAt(table.getSelectedRow(), 2).toString());
					tfFone.setText(table.getValueAt(table.getSelectedRow(), 3).toString());
					tfCelular.setText(table.getValueAt(table.getSelectedRow(), 4).toString());
					if ("M".equals(table.getValueAt(table.getSelectedRow(), 5).toString())) {
					    rbMacho.setSelected(true);
					    rbFemea.setSelected(false);
					} else {
					    rbMacho.setSelected(false);
					    rbFemea.setSelected(true);
					}
				} else {
					JOptionPane.showMessageDialog(null, "Selecione uma linha para ser alterada.");
				}
			}
		});
		JScrollPane scrollPane = new JScrollPane(table); // Adicione a tabela a um JScrollPane
		scrollPane.setBounds(10, 364, 732, 180);
		frame.getContentPane().add(scrollPane);

		JButton btnCadastrar = new JButton("Cadastrar");
		btnCadastrar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Cliente c = new Cliente();
				ClienteDAO dao = new ClienteDAO();
				c.setNome(tfNome.getText());
				c.setEndereco(tfNomeDoTutor.getText());
				c.setFone(tfFone.getText());
				c.setCelular(tfCelular.getText());
				if (rbMacho.isSelected()) {
					c.setSexo("M");
				} else {
					c.setSexo("F");
				}
				c.setObs(textArea.getText());
				dao.create(c);
				readJTable();
			}
		});
		btnCadastrar.setBounds(10, 309, 100, 23);
		frame.getContentPane().add(btnCadastrar);

		JButton btnExcluir = new JButton("Excluir");
		btnExcluir.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (table.getSelectedRow() != -1) {
					Cliente c = new Cliente();
					ClienteDAO dao = new ClienteDAO();
					c.setCodigo((int) table.getValueAt(table.getSelectedRow(), 0));
					dao.delete(c);
					readJTable();
					tfNome.setText("");
					tfNomeDoTutor.setText("");
					tfFone.setText("");
					tfCelular.setText("");
					tfCodigo.setText("");
					textArea.setText("");

				}
			}
		});
		btnExcluir.setBounds(219, 309, 89, 23);
		frame.getContentPane().add(btnExcluir);

		JButton btnAlterar = new JButton("Alterar");
		btnAlterar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (table.getSelectedRow() != -1) {
					Cliente c = new Cliente();
					ClienteDAO dao = new ClienteDAO();
					c.setCodigo((int) table.getValueAt(table.getSelectedRow(), 0));
					c.setNome(tfNome.getText());
					c.setEndereco(tfNomeDoTutor.getText());
					c.setFone(tfFone.getText());
					c.setCelular(tfCelular.getText());
					if (rbMacho.isSelected()) {
						c.setSexo("M");
					} else {
						c.setSexo("F");
					}
					c.setObs(textArea.getText());
					dao.update(c);
					readJTable();
				}
			}
		});
		btnAlterar.setBounds(120, 309, 89, 23);
		frame.getContentPane().add(btnAlterar);
		
		JComboBox comboBox = new JComboBox();
		comboBox.setModel(new DefaultComboBoxModel(new String[] {"Pequeno", "Medio", "Grande"}));
		comboBox.setBounds(34, 88, 86, 20);
		frame.getContentPane().add(comboBox);
		
		textField = new JTextField();
		textField.setBounds(442, 32, 300, 300);
		frame.getContentPane().add(textField);
		textField.setColumns(10);
		
		JButton brtImagem = new JButton("Selecionar Imagem");
		brtImagem.setBounds(38, 221, 220, 23);
		frame.getContentPane().add(brtImagem);
	}

	private class SwingAction extends AbstractAction {
		public SwingAction() {
			putValue(NAME, "SwingAction");
			putValue(SHORT_DESCRIPTION, "Some short description");
		}

		public void actionPerformed(ActionEvent e) {
		}
	}

	public void readJTable() {
	    // Obtém o modelo de tabela da JTable
	    DefaultTableModel modelo = (DefaultTableModel) table.getModel();

	    // Limpa todas as linhas da tabela para evitar duplicações
	    modelo.setNumRows(0);

	    // Instancia o ClienteDAO para ler os dados do banco de dados
	    ClienteDAO cdao = new ClienteDAO();

	    // Obtém a lista de clientes do banco de dados
	    List<Cliente> clientes = cdao.read();

	    // Adiciona os dados dos clientes ao modelo de tabela
	    for (Cliente c : clientes) {
	        modelo.addRow(new Object[] {
	            c.getCodigo(),
	            c.getNome(),
	            c.getEndereco(),
	            c.getFone(),
	            c.getCelular(),
	            c.getSexo(),
	            c.getObs()
	        });
	    }
	}
}
